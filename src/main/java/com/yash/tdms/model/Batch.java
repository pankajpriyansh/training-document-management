package com.yash.tdms.model;

import java.util.Date;

import org.springframework.stereotype.Component;

/**
 * 
 * @author goyal.ayush
 *
 */
@Component
public class Batch {
	private int id;
	private String name;
	private int createdBy;
	private int modifiedBy;
	private Date createdDate;
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

	@Override
	public String toString() {
		return "Batch [id=" + id + ", name=" + name + ", createdBy="
				+ createdBy + ", modifiedBy=" + modifiedBy + ", createdDate="
				+ createdDate + ", modifiedDate=" + modifiedDate + "]";
	}

}
