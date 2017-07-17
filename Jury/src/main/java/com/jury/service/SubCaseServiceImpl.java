package com.jury.service;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jury.dao.CaseDAO;
import com.jury.dao.SubCaseDAO;
import com.jury.dao.UserDAO;
import com.jury.model.CaseTypes;
import com.jury.model.SubCases;

@Service
public class SubCaseServiceImpl implements SubCaseService {
	
	private SubCaseDAO subCaseDAO;
	public void setSubCaseDAO(SubCaseDAO subCaseDAO) {
		this.subCaseDAO = subCaseDAO;
	}
	@Transactional
	public List<SubCases> getSubCaseTypes(String casename){
		return subCaseDAO.getSubCaseTypes(casename);
	}
	@Transactional
	public SubCases getCaseTypes(String subcasename){
		return subCaseDAO.getCaseTypes(subcasename);
	}
	@Transactional
	public void addSubCase(SubCases sc){
		subCaseDAO.addSubCase(sc);
	}
	
}
