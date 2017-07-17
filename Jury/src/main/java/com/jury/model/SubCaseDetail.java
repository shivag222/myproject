package com.jury.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="SUBCASEDETAIL")
public class SubCaseDetail {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String casename;
	
	private String subcase;

	private String casequestions;

	private String verdict;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCasename() {
		return casename;
	}

	public void setCasename(String casename) {
		this.casename = casename;
	}

	public String getSubcase() {
		return subcase;
	}

	public void setSubcase(String subcase) {
		this.subcase = subcase;
	}

	public String getCasequestions() {
		return casequestions;
	}

	public void setCasequestions(String casequestions) {
		this.casequestions = casequestions;
	}

	public String getVerdict() {
		return verdict;
	}

	public void setVerdict(String verdict) {
		this.verdict = verdict;
	}
	public SubCaseDetail(){}
	public SubCaseDetail(int id, String casename, String subcase, String casequestions, String verdict) {
		super();
		this.id = id;
		this.casename = casename;
		this.subcase = subcase;
		this.casequestions = casequestions;
		this.verdict = verdict;
	}
	
	
	
}