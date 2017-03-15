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

import com.yash.tdms.dao.MemberDao;
import com.yash.tdms.model.Member;

/**
 * MemberDaoImpl is the JDBC implementation of all the CRUD operations related
 * to Member
 * 
 * @author goyal.ayush
 *
 */
@Repository
public class MemberDaoImpl implements MemberDao {

	private DataSource dataSource;

	private JdbcTemplate jdbcTemplate;

	public DataSource getDataSource() {
		return dataSource;
	}

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public void addMember(Member member) {
		jdbcTemplate
				.update("INSERT INTO members(firstname,lastname,contact,email,PASSWORD,createddate,modifiedDate,batch_id) VALUES (?,?,?,?,?,?,?,?)",
						new Object[] { member.getFirstname(),
								member.getLastname(), member.getContact(),
								member.getEmail(), member.getPassword(),
								new Timestamp(new Date().getTime()),
								new Timestamp(new Date().getTime()),
								member.getBatchId() });
	}

	public List<Member> getAllMembers() {
		return jdbcTemplate.query("select * from members ",
				new MemberRowMapper());
	}

	public void deleteMember(int id) {

	}

	public void editMember(int id) {
		// TODO Auto-generated method stub

	}

	public boolean checkForAuthentication(String email, String password) {

		try {
			jdbcTemplate
					.queryForObject(
							"select email from members where email=? and password=? and isActive=1",
							new Object[] { email, password }, String.class);
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return false;
	}

	public Member getMemberByEmail(String email) {
		return jdbcTemplate.queryForObject(
				"select * from members where email=?", new Object[] { email },
				new MemberRowMapper());
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

	public boolean checkIfEmailExists(String email) {
		try {
			jdbcTemplate.queryForObject(
					"select email from members where email=?",
					new Object[] { email }, String.class);
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return false;
	}

	public List getNonActiveMembers() {
		List list = jdbcTemplate
				.query("SELECT *,b.name FROM members m,batches b WHERE m.`batch_id`=b.`id` AND  isActive=2",
						new MemberRowMapperWithBatchName());
		return list;
	}

	private static final class MemberRowMapperWithBatchName implements
			RowMapper<List> {

		public List mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			List list = new ArrayList();
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
			list.add(member);
			list.add(resultSet.getString("name"));
			System.out.println(list);
			return list;
		}
	}

	public void activateMember(int memberId) {
		jdbcTemplate.update("update members set isActive = 1 where id = ?",
				memberId);
	}

	public void changeRole(int memberId, int role) {
		jdbcTemplate.update("update members set role = ? where id = ?",
				new Object[] { role, memberId });
	}

	public List<Member> getAllTrainers() {
		return jdbcTemplate.query("select * from members where role=2 ",
				new MemberRowMapper());
	}

	@Override
	public List<Member> getAllMembersByBatchId(int batchId) {
		return jdbcTemplate.query(
				"select * from members where batch_id=? and isActive=1",
				new Object[] { batchId }, new MemberRowMapper());
	}

	@Override
	public List getBatchMemberGraphData() {
		return jdbcTemplate
				.query("SELECT bat.name AS batchName,(SELECT COUNT(*) FROM members mem WHERE mem.`batch_id`=bat.id GROUP BY mem.`batch_id`) AS totalMembers, (SELECT COUNT(*) FROM documents doc WHERE doc.`batch_id`=bat.id GROUP BY doc.`batch_id` ) AS totalDocuments FROM batches bat;",
						new BatchAndTotalMemberRowMapper());
	}

	private static final class BatchAndTotalMemberRowMapper implements
			RowMapper<List> {

		public List mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			List list = new ArrayList();
			list.add(resultSet.getInt("totalMembers"));
			list.add(resultSet.getString("batchName"));
			list.add(resultSet.getString("totalDocuments"));

			return list;
		}
	}

	@Override
	public void changePassword(String email, String newPassword) {
		jdbcTemplate.update("update members set password = ? where email = ?",
				newPassword, email);
	}

}
