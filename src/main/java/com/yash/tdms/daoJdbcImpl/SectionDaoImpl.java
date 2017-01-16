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

import com.yash.tdms.dao.SectionDao;
import com.yash.tdms.model.Section;

/**
 * SectionDaoImpl is the JDBC implementation of all the CRUD operations related
 * to Section
 * 
 * @author goyal.ayush
 *
 */
@Repository
public class SectionDaoImpl implements SectionDao {

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
	public List<Section> getAllSections() {
		return jdbcTemplate.query("select * from sections",
				new SectionRowMapper());
	}

	private static final class SectionRowMapper implements RowMapper<Section> {

		public Section mapRow(ResultSet resultSet, int rowNum)
				throws SQLException {
			Section section = new Section();
			section.setId(resultSet.getInt("id"));
			section.setName(resultSet.getString("name"));
			return section;
		}
	}

	@Override
	public int addSection(Section section) {
		jdbcTemplate
				.update("INSERT INTO sections(NAME,createdby,modifiedby,createddate,modifiedDate) VALUES(?,?,?,?,?)",
						new Object[] { section.getName(),
								section.getCreatedBy(),
								section.getModifiedBy(),
								new Timestamp(new Date().getTime()),
								new Timestamp(new Date().getTime()) });

		return jdbcTemplate.queryForObject(
				"select id from sections where name = ? and createdby=? ",
				new Object[] { section.getName(), section.getCreatedBy() },
				Integer.class);
	}

	@Override
	public String getSectionNameBySectionId(Integer sectionId) {
		return jdbcTemplate.queryForObject(
				"select name from sections where id = ? ",
				new Object[] { sectionId }, String.class);
	}

}
