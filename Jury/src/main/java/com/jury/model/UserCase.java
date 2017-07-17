package com.jury.model;


import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="USERCASE")
public class UserCase {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String accusedname;
	
	private String accusednumber;

	private String casetype;

	private String subcase;
	
	private String subcasetype;
	
	private String userLoginId;

	private Timestamp createTime;
	
	public Timestamp getCreateTime() {
		return createTime;
	}


	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getAccusedname() {
		return accusedname;
	}


	public void setAccusedname(String accusedname) {
		this.accusedname = accusedname;
	}


	public String getAccusednumber() {
		return accusednumber;
	}


	public void setAccusednumber(String accusednumber) {
		this.accusednumber = accusednumber;
	}


	public String getCasetype() {
		return casetype;
	}


	public void setCasetype(String casetype) {
		this.casetype = casetype;
	}


	public String getSubcase() {
		return subcase;
	}


	public void setSubcase(String subcase) {
		this.subcase = subcase;
	}


	public String getSubcasetype() {
		return subcasetype;
	}


	public void setSubcasetype(String subcasetype) {
		this.subcasetype = subcasetype;
	}


	public String getUsername() {
		return userLoginId;
	}


	public void setUsername(String username) {
		this.userLoginId = username;
	}


	public UserCase(int id, String accusedname, String accusednumber, String casetype, String subcase,
			String subcasetype, String username,Timestamp createTime) {
		super();
		this.id = id;
		this.accusedname = accusedname;
		this.accusednumber = accusednumber;
		this.casetype = casetype;
		this.subcase = subcase;
		this.subcasetype = subcasetype;
		this.userLoginId = username;
		this.createTime = createTime;
	}


	public UserCase(){}
	

}