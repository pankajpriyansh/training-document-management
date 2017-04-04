package com.yash.tdms.model;

import java.util.Date;

import org.springframework.stereotype.Component;

/**
 * This model will move through the controller to service and dao. using @ModelAttribute
 * on controller this model will be mapped with Member registration and update
 * forms.
 * 
 * @author sharma.pankaj
 *
 */
@Component
public class Member {
	/**
	 * Unique id of the member, primary key, auto generated in database itself.
	 */
	private int id;
	/**
	 * first name of the member
	 */
	private String firstname;
	/**
	 * last name of the member
	 */
	private String lastname;
	/**
	 * contact number of the member
	 */
	private long contact;
	/**
	 * email of the member
	 */
	private String email;

	private int isRegistered;
	/**
	 * password of the member
	 */
	// private String password;
	/**
	 * isActive will be used to check whether the the user is Active or
	 * Registered user 1. Active, 2.Registered Admin will have to activate the
	 * user and assign role, accordingly when user will login to system, he will
	 * be redirected on the welcome screen as per the Active and role wise
	 */
	private int isActive;
	/**
	 * role of the member. 1. Trainer, 2. Manager, 3. Trainee
	 */
	private int role;
	/**
	 * member id of the created registered member, in case if Admin or some
	 * other user will be given rights to register member, then admin or other
	 * role user's id will be set as the createdby
	 * 
	 */
	private int createdBy;
	/**
	 * member id of the modified registered member, in case if Admin or some
	 * other user will be given rights to register member, then admin or other
	 * role user's id will be set as the modified by
	 * 
	 */
	private int modifiedBy;
	/**
	 * date on which member is registered
	 */
	private Date createdDate;
	/**
	 * date on which member or somebody else has modified the member details.
	 */
	private Date modifiedDate;

	/**
	 * 
	 */
	private int batchId;

	public int getIsRegistered() {
		return isRegistered;
	}

	public void setIsRegistered(int isRegistered) {
		this.isRegistered = isRegistered;
	}

	public int getBatchId() {
		return batchId;
	}

	public void setBatchId(int batchId) {
		this.batchId = batchId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public long getContact() {
		return contact;
	}

	public void setContact(long contact) {
		this.contact = contact;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	/*
	 * public String getPassword() { return password; }
	 * 
	 * public void setPassword(String password) { this.password = password; }
	 */

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public int getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(int modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	@Override
	public String toString() {
		return "Member [id=" + id + ", firstname=" + firstname + ", lastname="
				+ lastname + ", contact=" + contact + ", email=" + email
				+ ", isActive=" + isActive + ", role=" + role + ", createdBy="
				+ createdBy + ", modifiedBy=" + modifiedBy + ", createdDate="
				+ createdDate + ", modifiedDate=" + modifiedDate + ", batchId="
				+ batchId + "]";
	}

}
