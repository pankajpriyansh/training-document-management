package com.yash.tdms.model;

import java.util.Date;

import org.springframework.stereotype.Component;

/**
 * This model will move through the controller to service and DAO. using @ModelAttribute
 * on controller this model will be mapped with Document Creation and update
 * forms.
 * 
 * @author sharma.pankaj
 *
 */
@Component
public class Document {

	/**
	 * Unique id of the document, primary key, auto generated in database
	 * itself.
	 */

	private int id;

	private int user_id;

	private int category_id;

	private String name;

	private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	private int createdBy;

	private int modifiedBy;

	private Date createdDate;

	private Date modifiedDate;

	private int isActive;

	private String filePath;

	private int isShow;

	private int batchId;

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

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public int getIsShow() {
		return isShow;
	}

	public void setIsShow(int isShow) {
		this.isShow = isShow;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Document other = (Document) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Document [id=" + id + ", user_id=" + user_id + ", category_id="
				+ category_id + ", name=" + name + ", description="
				+ description + ", createdBy=" + createdBy + ", modifiedBy="
				+ modifiedBy + ", createdDate=" + createdDate
				+ ", modifiedDate=" + modifiedDate + ", isActive=" + isActive
				+ ", filePath=" + filePath + ", isShow=" + isShow
				+ ", batchId=" + batchId + "]";
	}

}
