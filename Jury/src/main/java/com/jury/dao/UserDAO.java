package com.jury.dao;

import java.util.List;

import com.jury.model.User;

public interface UserDAO {

	public void addUser(User p);
	public void updateUser(User p);
	public List<User> listUsers();
	public User getUserById(int id);
	public void removeUser(int id);
	public User findByUserName(String name,String password);
	public User findByUser(String name);
	public User findByUserAnswers(String q1,String username);
	public User findByUserAnswers2(String q2,String username);
	public void updateUserPassword(String newpassword,String username);
	public void enableOrDisable(String enable,String username);
}