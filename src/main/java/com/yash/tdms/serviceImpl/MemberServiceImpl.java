package com.yash.tdms.serviceImpl;

import java.util.Hashtable;
import java.util.List;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yash.tdms.dao.MemberDao;
import com.yash.tdms.model.Member;
import com.yash.tdms.service.MemberService;
import com.yash.util.BasicInitializationsForLDAP;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberDao memberDao;

	// private SearchResult searchResult;

	public boolean checkForAuthentication(String email, String password) {
		// return memberDao.checkForAuthentication(email, password);
		// LDAP CODE
		String accountToLookUp;
		Hashtable<String, Object> environment = initializeHashTableEnvironmentVariables(
				email, password);
		accountToLookUp = "sAMAccountName="
				+ email.substring(0, email.indexOf("@"));
		InitialDirContext ctx = null;
		try {
			ctx = new InitialDirContext(environment);
			SearchControls searchControls = new SearchControls();
			searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);
			NamingEnumeration<SearchResult> results = ctx.search(
					BasicInitializationsForLDAP.LDADP_SEARCH_BASE,
					accountToLookUp, searchControls);
			if (results.hasMoreElements()) {
				// searchResult = results.nextElement();
				return true;
			}
			ctx.close();
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return false;

	}

	public Hashtable<String, Object> initializeHashTableEnvironmentVariables(
			String email, String password) {
		Hashtable<String, Object> environment = new Hashtable<>();
		environment.put(Context.SECURITY_AUTHENTICATION,
				BasicInitializationsForLDAP.LDAP_SECURITY_AUTHENTICATION);
		environment.put(Context.INITIAL_CONTEXT_FACTORY,
				BasicInitializationsForLDAP.LDAP_INITIAL_CONTEXT_FACTORY);
		environment.put(Context.PROVIDER_URL,
				BasicInitializationsForLDAP.LDAP_PROVIDER_URL);
		environment.put(Context.SECURITY_PRINCIPAL, email);
		environment.put(Context.SECURITY_CREDENTIALS, password);

		/**
		 * objectSID --> When a new object is created in Directory, Domain
		 * Controller assigns a unique value used to identify the object as a
		 * security principal. This value is unique inside the domain.
		 * 
		 * 'java.naming.ldap.attributes.binary' attribute informs the Context
		 * that the 'objectSid' attribute should be fetched as byte[] (and not
		 * as string) - this is crucial. In order to get the objectSID properly
		 * , we need to add it to the list of binary attributes.
		 */
		environment.put("java.naming.ldap.attributes.binary", "objectSID");
		return environment;
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

	public List getNonRegisteredMembers() {
		return memberDao.getNonRegisteredMembers();
	}

	public void registerMember(int memberId) {
		memberDao.registerMember(memberId);
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

	/*
	 * @Override public void changePassword(String email, String newPassword) {
	 * memberDao.changePassword(email, newPassword); }
	 */
	@Override
	public void shiftMemberByBatch(int fromBatchId, int toBatchId, int memberId) {
		memberDao.shiftMemberByBatch(fromBatchId, toBatchId, memberId);
	}

	@Override
	public List<Member> getAllTraineesByBatchId(int batchId) {
		return memberDao.getAllTraineesByBatchId(batchId);
	}

	@Override
	public void setMemberIsActive(int memberId, boolean status) {
		memberDao.setMemberIsActive(memberId, status);
	}

}
