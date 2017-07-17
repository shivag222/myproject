package com.jury.service;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jury.dao.SubCaseDetailDAO;
import com.jury.model.SubCaseDetail;
import com.jury.model.SubCases;


@Service
public class SubCaseDetailServiceImpl implements SubCaseDetailService {
	
	private SubCaseDetailDAO subCaseDetailDAO;
	public void setSubCaseDetailDAO(SubCaseDetailDAO subCaseDetailDAO) {
		this.subCaseDetailDAO = subCaseDetailDAO;
	}
	@Transactional
	public List<SubCaseDetail> getSubCaseDetail(String subcasename){
		return subCaseDetailDAO.getSubCaseDetail(subcasename);
	}
	@Transactional
	public SubCaseDetail getSubCaseById(int id){
		return subCaseDetailDAO.getSubCaseById(id);
	}
	@Transactional
	public void updateCaseQA(String q,String a,int id){
		subCaseDetailDAO.updateCaseQA(q,a,id);
	}
	@Transactional
	public void removeQA(int id){
		subCaseDetailDAO.removeQA(id);
	}
	@Transactional
	public int getNextId() {
		return this.subCaseDetailDAO.getNextId();
	}
	@Transactional
	public void addsubcase(SubCaseDetail p) {
		subCaseDetailDAO.addsubcase(p);
	}
	@Transactional
	public List<SubCaseDetail> searchSubCaseDetail(String search){
		return subCaseDetailDAO.searchSubCaseDetail(search);
	}
}
