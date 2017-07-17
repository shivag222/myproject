package com.jury.service;
import java.util.List;

import com.jury.model.SubCases;
import com.jury.model.User;
public interface SubCaseService {
	public List<SubCases> getSubCaseTypes(String casename);
	public SubCases getCaseTypes(String subcasename);
	public void addSubCase(SubCases sc);
}
