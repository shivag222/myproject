package com.jury.dao;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
/*import org.slf4j.Logger;
import org.slf4j.LoggerFactory;*/
import org.springframework.stereotype.Repository;
import com.jury.model.User;
@Repository
public class UserDAOImpl implements UserDAO{
	//private static final Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}

	public void addUser(User p) {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(p);
		//logger.info("User saved successfully, User Details="+p);
	}

	public void updateUser(User p) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(p);
		//logger.info("User updated successfully, User Details="+p);
	}

	public void updateUserPassword(String newpassword,String username) {
		Session session = this.sessionFactory.getCurrentSession();
		User objectToUpdate = (User) session.get(User.class, username);
		objectToUpdate.setPassword(newpassword);
		//logger.info("User updated successfully, User Details="+p);
	}
	public void enableOrDisable(String enable,String username) {
		Session session = this.sessionFactory.getCurrentSession();
		User objectToUpdate = (User) session.get(User.class, username);
		objectToUpdate.setIsenabled(enable);
		//logger.info("User updated successfully, User Details="+p);
	}
	public List<User> listUsers() {
		Session session = this.sessionFactory.getCurrentSession();
		List<User> UsersList = session.createQuery("from User").list();
		for(User p : UsersList){
			//logger.info("User List::"+p);
		}
		return UsersList;
	}

	public User getUserById(int id) {
		Session session = this.sessionFactory.getCurrentSession();		
		User p = (User) session.load(User.class, new Integer(id));
		//logger.info("User loaded successfully, User details="+p);
		return p;
	}

	public void removeUser(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		User p = (User) session.load(User.class, new Integer(id));
		if(null != p){
			session.delete(p);
		}
		//logger.info("User deleted successfully, User details="+p);
	}
	@SuppressWarnings("unchecked")
	public User findByUserName(String username,String password) {

		List<User> users = new ArrayList<User>();

		users = sessionFactory.getCurrentSession()
			.createQuery("from User where userLoginId=? AND password=?")
			.setParameter(0, username)
			.setParameter(1,password)
			.list();

		if (users.size() > 0) {
			return users.get(0);
		} else {
			return null;
		}

	}
	@SuppressWarnings("unchecked")
	public User findByUser(String username) {

		List<User> users = new ArrayList<User>();

		users = sessionFactory.getCurrentSession()
			.createQuery("from User where userLoginId=?")
			.setParameter(0, username)
			.list();

		if (users.size() > 0) {
			return users.get(0);
		} else {
			return null;
		}

	}
	
	@SuppressWarnings("unchecked")
	public User findByUserAnswers(String q1,String username) {

		List<User> users = new ArrayList<User>();

		users = sessionFactory.getCurrentSession()
			.createQuery("from User where secanswer1=? AND userLoginId=?")
			.setParameter(0, q1)
			.setParameter(1, username)
			.list();

		if (users.size() > 0) {
			return users.get(0);
		} else {
			return null;
		}

	}
	
	@SuppressWarnings("unchecked")
	public User findByUserAnswers2(String q2,String username) {

		List<User> users = new ArrayList<User>();

		users = sessionFactory.getCurrentSession()
			.createQuery("from User where secanswer2=? AND userLoginId=?")
			.setParameter(0, q2)
			.setParameter(1, username)
			.list();

		if (users.size() > 0) {
			return users.get(0);
		} else {
			return null;
		}

	}
}
