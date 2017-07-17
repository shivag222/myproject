package com.jury.dao;

import java.util.List;
import com.jury.model.UserCase;
public interface UserCaseDAO {
	public void addUserCase(UserCase uc);
	public List<UserCase> listUserCases(String username);
	public UserCase getUserCaseById(int id,String username);
	public int getNextId();
	public void removeUserCase(int id);
}