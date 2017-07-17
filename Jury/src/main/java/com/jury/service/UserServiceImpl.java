package com.jury.service;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jury.dao.UserDAO;
import com.jury.model.User;

@Service
public class UserServiceImpl implements UserService {
	
	private UserDAO userDAO;

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	@Transactional
	public void addUser(User p) {
		this.userDAO.addUser(p);
	}

	@Transactional
	public void updateUser(User p) {
		this.userDAO.updateUser(p);
	}
	@Transactional
	public void updateUserPassword(String newpassword,String username) {
		this.userDAO.updateUserPassword(newpassword,username);
	}
	
	@Transactional
	public List<User> listUsers() {
		return this.userDAO.listUsers();
	}

	@Transactional
	public User getUserById(int id) {
		return this.userDAO.getUserById(id);
	}

	@Transactional
	public void removeUser(int id) {
		this.userDAO.removeUser(id);
	}
	@Transactional
	public User findByUserName(String username,String password) {
		return this.userDAO.findByUserName(username,password);
	}
	@Transactional
	public User findByUser(String username) {
		return this.userDAO.findByUser(username);
	}

	@Transactional
	public User findByUserAnswers(String q1,String username) {
		return this.userDAO.findByUserAnswers(q1,username);
	}
	@Transactional
	public User findByUserAnswers2(String q2,String username) {
		return this.userDAO.findByUserAnswers2(q2,username);
	}
	@Transactional
	public void enableOrDisable(String enable,String username){
		this.userDAO.enableOrDisable(enable,username);
	}
}
