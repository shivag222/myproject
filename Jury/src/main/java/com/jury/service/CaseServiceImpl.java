package com.jury.service;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jury.dao.CaseDAO;
import com.jury.dao.UserDAO;
import com.jury.model.CaseTypes;

@Service
public class CaseServiceImpl implements CaseService {
	
	private CaseDAO caseDAO;
	public void setCaseDAO(CaseDAO caseDAO) {
		this.caseDAO = caseDAO;
	}
	@Transactional
	public List<CaseTypes> getCaseTypes(){
		return caseDAO.getCaseTypes();
	}
	
}
