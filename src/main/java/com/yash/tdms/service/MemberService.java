package com.yash.tdms.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.yash.tdms.model.Document;
import com.yash.tdms.model.Member;

/**
 * MemberService provides all the services and functionalities related to
 * Member. This is the design, implemented by any specific implementation
 * provider . It contains all the services related to business logics .
 * 
 * @author goyal.ayush
 *
 */
@Service
public interface MemberService {

	boolean checkForAuthentication(String email, String password);

	Member getMemberByEmail(String email);

	void addMember(Member member);

	boolean checkIfEmailExists(String email);

	List<Member> getAllMembers();

	List getNonActiveMembers();

	void activateMember(int memberId);

	void changeRole(int memberId, int role);

	List<Member> getAllTrainers();

	List<Member> getAllMembersByBatchId(int batchId);

	List getBatchMemberGraphData();


}
