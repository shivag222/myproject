package com.jury.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="SUBCASES")
public class SubCases {

	@Id
	@Column(name="subcase")
	private String subcase;
	private String casename;

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


	public SubCases(String casename, String subcase) {
		super();
		this.casename = casename;
		this.subcase = subcase;
	}
	public SubCases(){}
}