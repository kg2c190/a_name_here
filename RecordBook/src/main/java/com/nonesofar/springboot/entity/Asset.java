package com.nonesofar.springboot.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.ToString;



@Table(name="tbl_assets")
@Entity
@ToString
public class Asset {	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private long assetID;
	
	private String name;
	
	//purchase_date
	@CreationTimestamp
	@Column(name= "purchase_date")
	private Date purchasedate;
	
	
	@Column(name= "notes")
	private String conditionNotes;
	
	private String category;
	
	//Assignment_Status
	@Column(name= "Assignment_Status")
	private String assignmentstatus;

	public long getAssetID() {
		return assetID;
	}

	public void setAssetID(long assetID) {
		this.assetID = assetID;
	}

	public Date getPurchasedate() {
		return purchasedate;
	}

	public void setPurchasedate(Date purchasedate) {
		this.purchasedate = purchasedate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getConditionNotes() {
		return conditionNotes;
	}

	public void setConditionNotes(String conditionNotes) {
		this.conditionNotes = conditionNotes;
	}

	public String getAssignmentstatus() {
		return assignmentstatus;
	}

	public void setAssignmentstatus(String assignmentstatus) {
		this.assignmentstatus = assignmentstatus;
	}
	
	
	
}
