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
		jdbcTemplate.update(
				"INSERT INTO members(firstname,lastname,contact,email,PASSWORD,createddate,modifiedDate) VALUES (?,?,?,?,?,?,?)",
				new Object[] { member.getFirstname(), member.getLastname(), member.getContact(), member.getEmail(),
						member.getPassword(), new Timestamp(new Date().getTime()),
						new Timestamp(new Date().getTime()) });
	}

	public List<Member> getAllMembers() {
		return jdbcTemplate.query("select * from members ", new MemberRowMapper());
	}

	public void deleteMember(int id) {
		// TODO Auto-generated method stub

	}

	public void editMember(int id) {
		// TODO Auto-generated method stub

	}

	public boolean checkForAuthentication(String email, String password) {

		try {
			jdbcTemplate.queryForObject("select email from members where email=? and password=?",
					new Object[] { email, password }, String.class);
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return false;
	}

	public Member getMemberByEmail(String email) {
		return jdbcTemplate.queryForObject("select * from members where email=?", new Object[] { email },
				new MemberRowMapper());
	}

	private static final class MemberRowMapper implements RowMapper<Member> {

		public Member mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			Member member = new Member();
			member.setId(resultSet.getInt("id"));
			member.setFirstname(resultSet.getString("firstname"));
			member.setLastname(resultSet.getString("lastname"));
			member.setContact(resultSet.getLong("contact"));
			member.setEmail(resultSet.getString("email"));
			member.setIsActive(resultSet.getInt("isActive"));
			member.setRole(resultSet.getInt("role"));
			System.out.println(member);
			return member;
		}
	}

	public boolean checkIfEmailExists(String email) {
		try {
			jdbcTemplate.queryForObject("select email from members where email=?", new Object[] { email },
					String.class);
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return false;
	}

}
