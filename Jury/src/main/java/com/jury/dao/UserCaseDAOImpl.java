package com.jury.dao;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
/*import org.slf4j.Logger;
import org.slf4j.LoggerFactory;*/
import org.springframework.stereotype.Repository;

import com.jury.model.SubCaseDetail;
import com.jury.model.User;
import com.jury.model.UserCase;
@Repository
public class UserCaseDAOImpl implements UserCaseDAO{
	//private static final Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}

	public void addUserCase(UserCase uc) {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(uc);
		//logger.info("User saved successfully, User Details="+p);
	}


	
	public List<UserCase> listUserCases(String username) {
		Session session = this.sessionFactory.getCurrentSession();
		List<UserCase> UserCaseList = session.createQuery("from UserCase where userLoginId=?").setParameter(0, username).list();
		for(UserCase p : UserCaseList){
			System.out.println("===dasdas==="+p.getAccusednumber());
		}
		return UserCaseList;
	}

	
	@SuppressWarnings("unchecked")
	public UserCase getUserCaseById(int id,String username) {

		List<UserCase> usercase = new ArrayList<UserCase>();

		usercase = sessionFactory.getCurrentSession()
			.createQuery("from UserCase where id=? AND userLoginId=?")
			.setParameter(0, id)
			.setParameter(1, username)
			.list();

		if (usercase.size() > 0) {
			return usercase.get(0);
		} else {
			return null;
		}

	}
	@SuppressWarnings("unchecked")
	public int getNextId() {

		List<UserCase> usercase = new ArrayList<UserCase>();

		usercase = sessionFactory.getCurrentSession()
			.createQuery("from UserCase ORDER BY id desc")
			.setMaxResults(1)
			.list();

		if (usercase.size() > 0) {
			return usercase.get(0).getId();
		} else {
			return 0;
		}

	}
	public void removeUserCase(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		UserCase p = (UserCase) session.load(UserCase.class, id);
		if(null != p){
			session.delete(p);
		}
		//logger.info("User deleted successfully, User details="+p);
	}
	
}
