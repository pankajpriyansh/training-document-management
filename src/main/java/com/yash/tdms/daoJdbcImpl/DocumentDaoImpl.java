package com.yash.tdms.daoJdbcImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.yash.tdms.dao.DocumentDao;
import com.yash.tdms.model.Document;

/**
 * DocumentDaoImpl is the JDBC implementation of all the CRUD operations related
 * to Document
 * 
 * @author goyal.ayush
 *
 */
@Repository
public class DocumentDaoImpl implements DocumentDao {

	private DataSource dataSource;

	private JdbcTemplate jdbcTemplate;

	public DataSource getDataSource() {
		return dataSource;
	}

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public void addDocument(Document document) {
		jdbcTemplate
				.update("INSERT INTO documents(user_id,category_id,NAME,description,createdby,modifiedby,createddate,modifieddate,filepath) VALUES (?,?,?,?,?,?,?,?,?);",
						new Object[] { document.getUser_id(),
								document.getCategory_id(), document.getName(),
								document.getDescription(),
								document.getUser_id(), document.getUser_id(),
								new Timestamp(new Date().getTime()),
								new Timestamp(new Date().getTime()),
								document.getFilePath() });
	}

	/**
	 * Trainer can see his own documents only, thats why we are getting
	 * getAllDocumentsByUserId
	 */

	public List<Document> getAllDocumentsByUserId(int id) {
		return jdbcTemplate.query("select * from documents where user_id=?",
				new Object[] { id }, new DocumentRowMapper());
	}

	private static final class DocumentRowMapper implements RowMapper<Document> {

		public Document mapRow(ResultSet resultSet, int rowNum)
				throws SQLException {
			Document document = new Document();

			// Map columns to document object

			document.setId(resultSet.getInt("id"));
			document.setName(resultSet.getString("name"));
			document.setCategory_id(resultSet.getInt("category_id"));
			document.setUser_id(resultSet.getInt("user_id"));
			document.setDescription(resultSet.getString("description"));
			document.setFilePath(resultSet.getString("filepath"));
			document.setIsShow(resultSet.getInt("isShow"));
			document.setIsActive(resultSet.getInt("isActive"));
			document.setCreatedBy(resultSet.getInt("createdby"));
			if (document.getName().contains(" ")) {
				document.setName(document.getName().replaceAll(" ", "_"));
			}
			System.out.println(document);
			return document;
		}
	}

	public List<Document> getAllActiveDocuments() {
		return jdbcTemplate.query("select * from documents where isShow = 1",
				new DocumentRowMapper());
	}

	public int getTotalDocuments(int memberId) {
		return jdbcTemplate.queryForObject(
				"select count(*) from documents where user_id=?",
				new Object[] { memberId }, Integer.class);
	}

	public void changeStatusOfDocumentByDocumentId(int documentId, int statusId) {
		jdbcTemplate.update("update documents set isShow=? where id=?",
				new Object[] { statusId, documentId });
	}

	public void deleteDocumentById(int documentId) {
		jdbcTemplate.update("DELETE FROM documents WHERE id=?",
				new Object[] { documentId });
	}

	public Document getDocumentById(int documentId) {
		return jdbcTemplate.queryForObject(
				"select * from documents where id=?",
				new Object[] { documentId }, new DocumentRowMapper());
	}

	public void updateDocument(int documentId, String name, String description) {
		jdbcTemplate.update(
				"update documents set name=?,description=? where id=?",
				new Object[] { name, description, documentId });
	}

	public boolean checkIfStatusAlreadyRead(int documentId, int user_id) {
		try {
			jdbcTemplate
					.queryForObject(
							"select id from members_documents_junction where document_id=? and user_id=?",
							new Object[] { documentId, user_id }, Integer.class);
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

	public void doEntryAsReadForThisDocument(int documentId, int user_id) {
		jdbcTemplate
				.update("INSERT INTO members_documents_junction(document_id,user_id,STATUS,createddate,modifieddate) VALUES(?,?,?,?,?)",
						new Object[] { documentId, user_id, 1,
								new Timestamp(new Date().getTime()),
								new Timestamp(new Date().getTime()) });
	}

	public void updateReadEntryOfDocument(int documentId, int user_id) {
		jdbcTemplate
				.update("update members_documents_junction set modifieddate=? where document_id=? and user_id=?",
						new Object[] { new Timestamp(new Date().getTime()),
								documentId, user_id });
	}

	public Map<String, Object> getDocumentReadStatus(int documentId, int user_id) {

		try {
			Map<String, Object> map = (Map<String, Object>) jdbcTemplate
					.queryForMap(
							"SELECT STATUS,createddate,modifieddate FROM members_documents_junction WHERE document_id =? AND user_id=? ",
							new Object[] { documentId, user_id });
			return map;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
}
