package com.jury.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="USERLOGIN")
public class User {

	@Id
	@Column(name="userloginid")
	private String userLoginId;
	
	private String password;

	private Timestamp regTime;

	private String email;
	
	private String phone;
	
	private String address;
	
	private String zipcode;
	
	private String gender;
	
	private String usertype;
	
	private String isenabled;
	
	public String getIsenabled() {
		return isenabled;
	}

	public void setIsenabled(String isenabled) {
		this.isenabled = isenabled;
	}

	public String getSecquestion1() {
		return secquestion1;
	}

	public void setSecquestion1(String secquestion1) {
		this.secquestion1 = secquestion1;
	}

	public String getSecanswer1() {
		return secanswer1;
	}

	public void setSecanswer1(String secanswer1) {
		this.secanswer1 = secanswer1;
	}

	public String getSecquestion2() {
		return secquestion2;
	}

	public void setSecquestion2(String secquestion2) {
		this.secquestion2 = secquestion2;
	}

	public String getSecanswer2() {
		return secanswer2;
	}

	public void setSecanswer2(String secanswer2) {
		this.secanswer2 = secanswer2;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUserLoginId() {
		return userLoginId;
	}

	public void setUserLoginId(String userLoginId) {
		this.userLoginId = userLoginId;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getUsertype() {
		return usertype;
	}

	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}

	private String city;
	
	private String fname;

	private String lname;
	
	private String mname;
	
	private String secquestion1;
	
	private String secanswer1;
	
	private String secquestion2;
	
	private String secanswer2;
	
	private String isAdmin;
	
	
	
	public String getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(String isAdmin) {
		this.isAdmin = isAdmin;
	}
	public User(){}
	
	public User(String username, String password, Timestamp regTime, String email, String phone, String address,
			String city, String fname, String lname, String mname,String secquestion1,String secanswer1,String secquestion2,String secanswer2,String isAdmin,String usertype,String gender,String zipcode,String isenabled) {
		super();
		this.userLoginId = username;
		this.password = password;
		this.regTime = regTime;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.city = city;
		this.fname = fname;
		this.lname = lname;
		this.mname = mname;
		this.secquestion1 = secquestion1;
		this.secanswer1 = secanswer1;
		this.secquestion2 = secquestion2;
		this.secanswer2 = secanswer2;
		this.isAdmin = isAdmin;
		this.usertype = usertype;
		this.gender = gender;
		this.zipcode = zipcode;
		this.isenabled = isenabled;
	}

	
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public String getUsername() {
		return userLoginId;
	}

	public void setUsername(String userLoginId) {
		this.userLoginId = userLoginId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Timestamp getRegTime() {
		return regTime;
	}

	public void setRegTime(Timestamp regTime) {
		this.regTime = regTime;
	}
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}

	public void setPhoneNumber(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}