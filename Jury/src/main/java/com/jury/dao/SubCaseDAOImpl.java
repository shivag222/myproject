package com.jury.dao;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
/*import org.slf4j.Logger;
import org.slf4j.LoggerFactory;*/
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jury.model.SubCases;
import com.jury.model.User;
@Repository
public class SubCaseDAOImpl implements SubCaseDAO{
	//private static final Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}

	public List<SubCases> getSubCaseTypes(String casename){
		
		Session session = this.sessionFactory.getCurrentSession();
		List<SubCases> subcasesList = session.createQuery("from SubCases where casename=?").setParameter(0, casename).list();
		for(SubCases p : subcasesList){
			System.out.println(p);
		}
		return subcasesList;
	}	
	public SubCases getCaseTypes(String subcasename){
		
		Session session = this.sessionFactory.getCurrentSession();
		List<SubCases> subcases = session.createQuery("from SubCases where subcase=?").setParameter(0, subcasename).list();
/*		for(SubCases p : subcasesList){
			System.out.println(p);
		}*/
		if(subcases.size() == 0){
			return null;
		}
		return subcases.get(0);
	}
	public void addSubCase(SubCases sc) {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(sc);
		//logger.info("User saved successfully, User Details="+p);
	}

	
}
