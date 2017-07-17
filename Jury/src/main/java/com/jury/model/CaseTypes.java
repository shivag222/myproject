package com.jury.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="CASETYPES")
public class CaseTypes {

	@Id
	@Column(name="casename")
	private String casename;
	
	private String casedescription;

	public String getCasename() {
		return casename;
	}

	public void setCasename(String casename) {
		this.casename = casename;
	}

	public String getCasedescription() {
		return casedescription;
	}

	public void setCasedescription(String casedescription) {
		this.casedescription = casedescription;
	}


}