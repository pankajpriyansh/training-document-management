package com.yash.tdms.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yash.tdms.dao.MemberDao;
import com.yash.tdms.model.Member;
import com.yash.tdms.service.MemberService;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberDao memberDao;

	public boolean checkForAuthentication(String email, String password) {
		return memberDao.checkForAuthentication(email, password);
	}

	public Member getMemberByEmail(String email) {
		return memberDao.getMemberByEmail(email);
	}

	public void addMember(Member member) {
		memberDao.addMember(member);
	}

	public boolean checkIfEmailExists(String email) {
		return memberDao.checkIfEmailExists(email);
	}

	public List<Member> getAllMembers() {
		return memberDao.getAllMembers();
	}

	public List getNonActiveMembers() {
		return memberDao.getNonActiveMembers();
	}

	public void activateMember(int memberId) {
		memberDao.activateMember(memberId);
	}

	public void changeRole(int memberId, int role) {
		memberDao.changeRole(memberId, role);
	}

	public List<Member> getAllTrainers() {
		return memberDao.getAllTrainers();
	}

	@Override
	public List<Member> getAllMembersByBatchId(int batchId) {
		return memberDao.getAllMembersByBatchId(batchId);
	}

	@Override
	public List getBatchMemberGraphData() {
		return memberDao.getBatchMemberGraphData();
	}

}
