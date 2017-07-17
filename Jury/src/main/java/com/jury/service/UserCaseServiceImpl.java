package com.jury.service;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jury.dao.UserCaseDAO;
import com.jury.dao.UserDAO;
import com.jury.model.User;
import com.jury.model.UserCase;

@Service
public class UserCaseServiceImpl implements UserCaseService {
	
	private UserCaseDAO userCaseDAO;

	public void setUserCaseDAO(UserCaseDAO userCaseDAO) {
		this.userCaseDAO = userCaseDAO;
	}

	@Transactional
	public void addUserCase(UserCase p) {
		this.userCaseDAO.addUserCase(p);
	}

	
	@Transactional
	public List<UserCase> listUserCases(String username) {
		return this.userCaseDAO.listUserCases(username);
	}

	@Transactional
	public UserCase getUserCaseById(int id,String username) {
		return this.userCaseDAO.getUserCaseById(id,username);
	}
	@Transactional
	public int getNextId() {
		return this.userCaseDAO.getNextId();
	}
	@Transactional
	public void removeUserCase(int id) {
		this.userCaseDAO.removeUserCase(id);
	}
	
}
