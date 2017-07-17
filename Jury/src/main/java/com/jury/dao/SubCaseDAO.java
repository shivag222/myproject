package com.jury.dao;

import java.util.List;

import com.jury.model.SubCases;

public interface SubCaseDAO {
	public List<SubCases> getSubCaseTypes(String casename);
	public SubCases getCaseTypes(String subcasename);
	public void addSubCase(SubCases sc);
}