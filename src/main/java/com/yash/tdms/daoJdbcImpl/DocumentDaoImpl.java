package com.yash.tdms.daoJdbcImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.yash.tdms.dao.DocumentDao;
import com.yash.tdms.model.Document;
import com.yash.tdms.model.Member;

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
				.update("INSERT INTO documents(user_id,category_id,NAME,description,createdby,modifiedby,createddate,modifieddate,filepath,batch_id) VALUES (?,?,?,?,?,?,?,?,?,?);",
						new Object[] { document.getUser_id(),
								document.getCategory_id(), document.getName(),
								document.getDescription(),
								document.getUser_id(), document.getUser_id(),
								new Timestamp(new Date().getTime()),
								new Timestamp(new Date().getTime()),
								document.getFilePath(), document.getBatchId() });
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
			document.setModifiedBy(resultSet.getInt("modifiedby"));
			document.setCreatedDate(resultSet.getDate("createddate"));
			document.setModifiedDate(resultSet.getDate("modifieddate"));
			document.setBatchId(resultSet.getInt("batch_id"));
			if (document.getName().contains(" ")) {
				document.setName(document.getName().replaceAll(" ", "_"));
			}
			System.out.println(document);
			return document;
		}
	}

	public List<Document> getAllActiveDocuments(int batchId, int memberId) {
		List<Document> documents = jdbcTemplate.query(
				"select * from documents where isShow = 1 AND batch_id=?",
				new Object[] { batchId }, new DocumentRowMapper());
		try {
			List<Document> documentsForSpecificMember = jdbcTemplate
					.query("SELECT * FROM documents WHERE id IN (SELECT documentId FROM `members_documents_junction_show_status` WHERE `user_id`=?)",
							new Object[] { memberId }, new DocumentRowMapper());
			documents.addAll(documentsForSpecificMember);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return documents;
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
		System.out.println("------------------------------  " + documentId);
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
				.update("update members_documents_junction set modifieddate=?,count=count+1 where document_id=? and user_id=?",
						new Object[] { new Timestamp(new Date().getTime()),
								documentId, user_id });
	}

	public Map<String, Object> getDocumentReadStatus(int documentId, int user_id) {

		try {
			Map<String, Object> map = (Map<String, Object>) jdbcTemplate
					.queryForMap(
							"SELECT STATUS,createddate,modifieddate,count FROM members_documents_junction WHERE document_id =? AND user_id=? ",
							new Object[] { documentId, user_id });
			return map;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	public List<Document> getAllDocumentsByBatchId(int batchId) {
		return jdbcTemplate.query("select * from documents where batch_id=?",
				new Object[] { batchId }, new DocumentRowMapper());
	}

	public List getDocumentReadStautsList(int batchId, int documentId) {
		List<Member> members = jdbcTemplate.query(
				"select * from members where batch_id=?",
				new Object[] { batchId }, new MemberRowMapper());
		List list = new ArrayList();
		members.forEach((i) -> {
			try {
				Map<String, Object> map = (Map<String, Object>) jdbcTemplate
						.queryForMap(
								"SELECT status,createddate,modifieddate,count FROM members_documents_junction WHERE document_id =? AND user_id=? ",
								new Object[] { documentId, i.getId() });
				map.put("name", i.getFirstname() + " " + i.getLastname());
				map.put("email", i.getEmail());
				System.out.println(map);
				list.add(map);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("name", i.getFirstname() + " " + i.getLastname());
				map.put("email", i.getEmail());
				map.put("status", 0);
				System.out.println(map);
				list.add(map);
			}
		});
		return list;
	}

	private static final class MemberRowMapper implements RowMapper<Member> {

		public Member mapRow(ResultSet resultSet, int rowNum)
				throws SQLException {
			Member member = new Member();
			member.setId(resultSet.getInt("id"));
			member.setFirstname(resultSet.getString("firstname"));
			member.setLastname(resultSet.getString("lastname"));
			member.setContact(resultSet.getLong("contact"));
			member.setEmail(resultSet.getString("email"));
			member.setIsActive(resultSet.getInt("isActive"));
			member.setRole(resultSet.getInt("role"));
			member.setBatchId(resultSet.getInt("batch_id"));
			member.setCreatedDate(resultSet.getDate("createddate"));
			System.out.println(member);
			return member;
		}

	}

	@Override
	public int getBatchIdByDocumentId(int documentId) {
		return jdbcTemplate.queryForObject(
				"select batch_id from documents where id=?",
				new Object[] { documentId }, Integer.class);
	}

	@Override
	public void changeStatusOfDocumentByDocumentIdForSpecificMember(
			int documentId, int status, int memberId) {
		jdbcTemplate
				.update("INSERT INTO members_documents_junction_show_status(documentId,user_id,createddate,modifieddate) VALUES(?,?,?,?)",
						new Object[] { documentId, memberId,
								new Timestamp(new Date().getTime()),
								new Timestamp(new Date().getTime()) });
	}

	@Override
	public void hideDocumentForSpecificMember() {
		jdbcTemplate
				.update("DELETE FROM members_documents_junction_show_status WHERE createddate< NOW() - INTERVAL 2 DAY;");
	}

	@Override
	public void shiftDocumentsByBatch(int fromBatchId, int toBatchId) {
		List<Document> documents = jdbcTemplate.query(
				"select * from documents where batch_id=?",
				new Object[] { fromBatchId }, new DocumentRowMapper());
		String insertSqlString = "INSERT INTO documents(user_id,category_id,NAME,description,createdby,modifiedby,createddate,modifieddate,filepath,batch_id) VALUES (?,?,?,?,?,?,?,?,?,?);";

		jdbcTemplate.batchUpdate(insertSqlString,
				new BatchPreparedStatementSetter() {

					@Override
					public void setValues(PreparedStatement ps, int i)
							throws SQLException {
						Document document = documents.get(i);
						ps.setInt(1, document.getUser_id());
						ps.setInt(2, document.getCategory_id());
						ps.setString(3, document.getName());
						ps.setString(4, document.getDescription());
						ps.setInt(5, document.getCreatedBy());
						ps.setInt(6, document.getModifiedBy());
						ps.setDate(7, new java.sql.Date(document
								.getCreatedDate().getTime()));
						ps.setDate(8, new java.sql.Date(document
								.getModifiedDate().getTime()));
						ps.setString(9, document.getFilePath());
						ps.setInt(10, toBatchId);
					}

					@Override
					public int getBatchSize() {
						return documents.size();
					}

				});
	}

	@Override
	public void updateDocumentObject(Document document) {
		jdbcTemplate
				.update("update documents set user_id=?,category_id=?,NAME=?,description=?,createdby=?,modifiedby=?,createddate=?,modifieddate=?,filepath=?,batch_id=? where id=?",
						new Object[] { document.getUser_id(),
								document.getCategory_id(), document.getName(),
								document.getDescription(),
								document.getUser_id(), document.getUser_id(),
								new Timestamp(new Date().getTime()),
								new Timestamp(new Date().getTime()),
								document.getFilePath(), document.getBatchId(),
								document.getId() });
	}

	@Override
	public boolean documentNameExistsUnderThisBatch(int batchId,
			String documentName) {
		try {
			jdbcTemplate.queryForObject(
					"select id from documents where batch_id=? and name=?",
					new Object[] { batchId, documentName }, Integer.class);
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
}
