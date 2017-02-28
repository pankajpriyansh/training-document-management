package com.yash.tdms.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yash.tdms.model.Member;

/**
 * MemberDao will hold all the common CRUD tasks related to member. This is the
 * design, implementation will be provided by the specific type of
 * implementation
 * 
 * @author sharma.pankaj
 *
 */
@Repository
public interface MemberDao {

	/**
	 * addMember method will add the provided member in the data base
	 * 
	 * @param member
	 */
	public void addMember(Member member);

	/**
	 * getAllMembers method will return the list of all the available members
	 * 
	 * @return
	 */
	public List<Member> getAllMembers();

	/**
	 * deleteMember method will delete the member from the database based on
	 * member id
	 * 
	 * @param id
	 */
	public void deleteMember(int id);

	/**
	 * editMember method will modify the member based on provided id
	 * 
	 * @param id
	 */
	public void editMember(int id);

	public boolean checkForAuthentication(String email, String password);

	public Member getMemberByEmail(String email);

	public boolean checkIfEmailExists(String email);

	public List getNonActiveMembers();

	public void activateMember(int memberId);

	public void changeRole(int memberId, int role);

	public List<Member> getAllTrainers();

	public List<Member> getAllMembersByBatchId(int batchId);

	public List getBatchMemberGraphData();

}
