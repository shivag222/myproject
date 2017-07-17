package com.jury.dao;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
/*import org.slf4j.Logger;
import org.slf4j.LoggerFactory;*/
import org.springframework.stereotype.Repository;
import com.jury.model.CaseTypes;
@Repository
public class CaseDAOImpl implements CaseDAO{
	//private static final Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}

	public List<CaseTypes> getCaseTypes(){
		
		Session session = this.sessionFactory.getCurrentSession();
		List<CaseTypes> casesList = session.createQuery("from CaseTypes").list();
		for(CaseTypes p : casesList){
			System.out.println(p);
		}
		return casesList;
	}
}
