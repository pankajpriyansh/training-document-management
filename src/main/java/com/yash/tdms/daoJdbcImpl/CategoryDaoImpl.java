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

import com.yash.tdms.dao.CategoryDao;
import com.yash.tdms.model.Category;

/**
 * CategoryDaoImpl is the JDBC implementation of all the CRUD operations related
 * to category
 * 
 * @author goyal.ayush
 *
 */
@Repository
public class CategoryDaoImpl implements CategoryDao {

	private DataSource dataSource;

	private JdbcTemplate jdbcTemplate;

	public DataSource getDataSource() {
		return dataSource;
	}

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public List<Category> getCategoriesBySectionId(int sectionId) {

		return jdbcTemplate.query(
				"select * from categories where section_id=?",
				new Object[] { sectionId }, new CategoryRowMapper());
	}

	private static final class CategoryRowMapper implements RowMapper<Category> {

		public Category mapRow(ResultSet resultSet, int rowNum)
				throws SQLException {
			Category category = new Category();
			category.setId(resultSet.getInt("id"));
			category.setName(resultSet.getString("name"));
			category.setSection_id(resultSet.getInt("section_id"));
			/*if (category.getName().contains(" ")) {
				category.setName(category.getName().replaceAll(" ", "_"));
			}*/
			System.out.println(category);
			return category;
		}
	}

	public int addCategory(Category category) {

		jdbcTemplate
				.update("INSERT INTO categories(section_id,NAME,createdby,modifiedby,createddate,modifiedDate) VALUES(?,?,?,?,?,?)",
						new Object[] { category.getSection_id(),
								category.getName(), category.getCreatedBy(),
								category.getModifiedBy(),
								new Timestamp(new Date().getTime()),
								new Timestamp(new Date().getTime()) });
		return jdbcTemplate.queryForObject(
				"select id from categories where name = ? and createdby=? ",
				new Object[] { category.getName(), category.getCreatedBy() },
				Integer.class);
	}

	public int getTotalCategories() {
		return jdbcTemplate.queryForObject("select count(*) from categories",
				Integer.class);
	}

	public boolean checkIfCategoryExists(String categoryName, Integer sectionId) {

		try {
			jdbcTemplate
					.queryForObject(
							"select name from categories where name=? and section_id=?",
							new Object[] { categoryName, sectionId },
							String.class);
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return false;
	}

}
