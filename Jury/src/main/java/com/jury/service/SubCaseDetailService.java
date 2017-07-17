package com.jury.service;
import java.util.List;
import com.jury.model.SubCaseDetail;
import com.jury.model.User;
public interface SubCaseDetailService {
	public List<SubCaseDetail> getSubCaseDetail(String subcasename);
	public SubCaseDetail getSubCaseById(int id);
	public void updateCaseQA(String q,String a,int id);
	public void removeQA(int id);
	public int getNextId();
	public void addsubcase(SubCaseDetail p);
	public List<SubCaseDetail> searchSubCaseDetail(String search);
}
