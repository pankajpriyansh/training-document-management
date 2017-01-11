package com.yash.tdms.model;

import java.util.Date;

/**
 * This model will move through the controller to service and DAO. using @ModelAttribute on controller
 * this model will be mapped with Section Creation and update forms. 
 * @author sharma.pankaj
 *
 */
public class Section {
	/**
	 * Unique id of the section, primary key, auto generated in database itself. 
	 */
	private int id;
	/**
	 * name of the section
	 */
	private String name;
	/**
	 * section created by which member, it will be the foreign key, will be assigned automatically 
	 * at the time of the creation of the section logged in member. 
	 */
	private int createdBy;
	/**
	 * section modified by which member, it will be the foreign key, will be assigned automatically at
	 * the time of the modification of the section as per the logged in member. will be changing constantly. 
	 */
	private int modifiedBy;
	/**
	 * created date of the section
	 */
	private Date createdDate;
	/**
	 * modified date of the section
	 */
	private Date modifiedDate;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	
	

}
