package com.nonesofar.springboot.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Table(name="tb;_assets")
@Entity
@Setter
@Getter
@ToString
public class Asset {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private long assetID;
	
	@Column(name= "purchase_Data")
	private Date purchaseDate;
	
	@Column(name= "notes")
	private String conditionNotes;
	
	private String Category;
	
	private String assignmentStatus;
	
	
	
}
