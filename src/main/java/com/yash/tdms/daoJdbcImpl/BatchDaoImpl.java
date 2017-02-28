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

import com.yash.tdms.dao.BatchDao;
import com.yash.tdms.model.Batch;

@Repository
public class BatchDaoImpl implements BatchDao {

	private DataSource dataSource;

	private JdbcTemplate jdbcTemplate;

	public DataSource getDataSource() {
		return dataSource;
	}

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public List<Batch> getAllBatches() {
		System.out.println("Inside DAO");

		return jdbcTemplate
				.query("select * from batches", new BatchRowMapper());
	}

	private static final class BatchRowMapper implements RowMapper<Batch> {

		public Batch mapRow(ResultSet resultSet, int rowNum)
				throws SQLException {
			System.out.println("Inside row mapper");

			Batch batch = new Batch();
			batch.setId(resultSet.getInt("id"));
			batch.setName(resultSet.getString("name"));
			System.out.println(batch);

			if (batch.getName().contains(" ")) {
				batch.setName(batch.getName().replaceAll(" ", "_"));
			}
			return batch;
		}
	}

	public boolean checkIfBatchExists(String batchName) {
		try {
			jdbcTemplate.queryForObject(
					"select name from batches where name=?",
					new Object[] { batchName }, String.class);
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return false;
	}

	public int addBatch(Batch batch) {
		jdbcTemplate
				.update("INSERT INTO batches(NAME,createdby,modifiedby,createddate,modifiedDate) VALUES(?,?,?,?,?)",
						new Object[] { batch.getName(), batch.getCreatedBy(),
								batch.getModifiedBy(),
								new Timestamp(new Date().getTime()),
								new Timestamp(new Date().getTime()) });

		return jdbcTemplate.queryForObject(
				"select id from batches where name = ? and createdby=? ",
				new Object[] { batch.getName(), batch.getCreatedBy() },
				Integer.class);
	}

	public int getTotalBatches() {
		return jdbcTemplate.queryForObject("select count(*) from batches",
				Integer.class);
	}

}
