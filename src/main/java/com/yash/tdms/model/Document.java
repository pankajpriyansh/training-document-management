package com.yash.tdms.model;

import java.util.Date;

/**
 * This model will move through the controller to service and DAO. using @ModelAttribute on controller
 * this model will be mapped with Document Creation and update forms. 
 * @author sharma.pankaj
 *
 */
public class Document {
	
	/**
	 * Unique id of the document, primary key, auto generated in database itself. 
	 */
	
	private int id;
	
	private int user_id;
	
	private int category_id;
	
	private String name;
	
	
	private int createdBy;
	
	private int modifiedBy;
	
	private Date createdDate;
	
	private Date modifiedDate;
	
	private int isActive;
	
	private int filePath;
	
	private int isShow;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getCategory_id() {
		return category_id;
	}

	public void setCategory_id(int category_id) {
		this.category_id = category_id;
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

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	public int getFilePath() {
		return filePath;
	}

	public void setFilePath(int filePath) {
		this.filePath = filePath;
	}

	public int getIsShow() {
		return isShow;
	}

	public void setIsShow(int isShow) {
		this.isShow = isShow;
	}
	
	

}
