package com.yash.tdms.daoJdbcImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
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
			/*
			 * if (section.getName().contains(" ")) {
			 * section.setName(section.getName().replaceAll(" ", "_")); }
			 */
			return section;
		}
	}

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

	public String getSectionNameBySectionId(Integer sectionId) {
		return jdbcTemplate.queryForObject(
				"select name from sections where id = ? ",
				new Object[] { sectionId }, String.class);
	}

	public int getTotalSections() {
		return jdbcTemplate.queryForObject("select count(*) from sections",
				Integer.class);
	}

	public boolean checkIfSectionExists(String sectionName) {

		try {
			jdbcTemplate.queryForObject(
					"select name from sections where name=?",
					new Object[] { sectionName }, String.class);
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return false;
	}

	@Override
	public List getSectionCategoryDocumentGraphData(int batchId) {

		List<Section> sections = jdbcTemplate.query("select * from sections",
				new SectionRowMapper());
		List listOfData = new ArrayList();
		List listOfSectionAndTotalDocuments = jdbcTemplate
				.query("SELECT sec.name AS sectionName,COUNT(*) AS totalDocuments FROM sections sec , categories cat, documents doc WHERE doc.category_id=cat.`id` AND cat.`section_id`=sec.id AND doc.batch_id=?  GROUP BY sec.id;",
						new Object[] { batchId },
						new SectionAndTotalDocumentsRowMapper());

		List listOfSectionAndTotalCategories = jdbcTemplate
				.query("SELECT sec.name AS sectionName,COUNT(*) AS totalCategories FROM sections sec,categories cat WHERE sec.id=cat.section_id GROUP BY sec.name;",
						new SectionAndTotalCategoriesRowMapper());
		sections.forEach((i) -> {
			List list = new ArrayList();
			list.add(i.getName());
			list.add(getTotalCategories(listOfSectionAndTotalCategories,
					i.getName()));
			list.add(getTotalDocuments(listOfSectionAndTotalDocuments,
					i.getName()));
			System.out.println(list);
			listOfData.add(list);
		});
		System.out.println(listOfData);
		return listOfData;
	}

	private int getTotalDocuments(List listOfSectionAndTotalDocuments,
			String sectionName) {
		for (Object object : listOfSectionAndTotalDocuments) {
			List list = (List) object;
			if (((String) list.get(1)).equals(sectionName)) {
				return (int) list.get(0);
			}

		}
		return 0;
	}

	private int getTotalCategories(List listOfSectionAndTotalCategories,
			String sectionName) {
		for (Object object : listOfSectionAndTotalCategories) {
			List list = (List) object;
			if (((String) list.get(1)).equals(sectionName)) {
				return (int) list.get(0);
			}

		}
		return 0;
	}

	private static final class SectionAndTotalDocumentsRowMapper implements
			RowMapper<List> {

		public List mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			List list = new ArrayList();
			list.add(resultSet.getInt("totalDocuments"));
			list.add(resultSet.getString("sectionName"));
			return list;
		}
	}

	private static final class SectionAndTotalCategoriesRowMapper implements
			RowMapper<List> {

		public List mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			List list = new ArrayList();
			list.add(resultSet.getInt("totalCategories"));
			list.add(resultSet.getString("sectionName"));
			return list;
		}
	}

}
