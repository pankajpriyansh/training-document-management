package com.yash.tdms.daoJdbcImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

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

	@Override
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
	@Override
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

			System.out.println(document);

			return document;
		}
	}

	@Override
	public List<Document> getAllActiveDocuments() {
		return jdbcTemplate.query("select * from documents where isShow = 1",
				new DocumentRowMapper());
	}

}
