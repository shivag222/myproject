package com.jury.dao;

import java.util.List;
import com.jury.model.SubCaseDetail;

public interface SubCaseDetailDAO {
	public List<SubCaseDetail> getSubCaseDetail(String casename);
	public SubCaseDetail getSubCaseById(int id);
	public void updateCaseQA(String q,String a,int id);
	public void removeQA(int id);
	public int getNextId();
	public void addsubcase(SubCaseDetail p);
	public List<SubCaseDetail> searchSubCaseDetail(String search);
}