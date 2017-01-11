package com.yash.tdms.model;

import java.util.Date;

/**
 * This model will move through the controller to service and DAO. using @ModelAttribute on controller
 * this model will be mapped with Category Creation and update forms. 
 * @author sharma.pankaj
 *
 */
public class Category {
	
	/**
	 * Unique id of the category, primary key, auto generated in database itself. 
	 */
	
	private int id;
	/**
	 * section_id : which will show that which category falles in which section, will be foreign key
	 */
	private int section_id;
	/**
	 * name of the category
	 */
	private String name;
	/**
	 * category created by which member, it will be the foreign key, will be assigned automatically 
	 * at the time of the creation of the category, logged in member. 
	 */
	private int createdBy;
	/**
	 * category modified by which member, it will be the foreign key, will be assigned automatically at
	 * the time of the modification of the category as per the logged in member. will be changing constantly. 
	 */
	private int modifiedBy;
	/**
	 * created date of the category
	 */
	private Date createdDate;
	/**
	 * modified date of the category
	 */
	private Date modifiedDate;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSection_id() {
		return section_id;
	}
	public void setSection_id(int section_id) {
		this.section_id = section_id;
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
