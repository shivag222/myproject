package com.jury.dao;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
/*import org.slf4j.Logger;
import org.slf4j.LoggerFactory;*/
import org.springframework.stereotype.Repository;

import com.jury.model.SubCaseDetail;
import com.jury.model.SubCases;
import com.jury.model.User;
@Repository
public class SubCaseDetailDAOImpl implements SubCaseDetailDAO{
	//private static final Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}

	public List<SubCaseDetail> getSubCaseDetail(String subcasename){
		
		Session session = this.sessionFactory.getCurrentSession();
		List<SubCaseDetail> subcasesList = session.createQuery("from SubCaseDetail where subcase=?").setParameter(0, subcasename).list();
		for(SubCaseDetail p : subcasesList){
			System.out.println(p);
		}
		return subcasesList;
	}
	public SubCaseDetail getSubCaseById(int id){
			Session session = this.sessionFactory.getCurrentSession();
			List<SubCaseDetail> subcases =  session.createQuery("from SubCaseDetail where id=?").setParameter(0, id).list();
				System.out.println(subcases);
			if(subcases.size() == 0){
				return null;
			}	
			return subcases.get(0);
		}
	public void updateCaseQA(String q,String a,int id) {
		Session session = this.sessionFactory.getCurrentSession();
		SubCaseDetail objectToUpdate = (SubCaseDetail) session.get(SubCaseDetail.class, id);
		objectToUpdate.setCasequestions(q);
		objectToUpdate.setVerdict(a);
		//logger.info("User updated successfully, User Details="+p);
	}
	public void removeQA(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		SubCaseDetail p = (SubCaseDetail) session.load(SubCaseDetail.class, id);
		if(null != p){
			session.delete(p);
		}
		//logger.info("User deleted successfully, User details="+p);
	}
	@SuppressWarnings("unchecked")
	public int getNextId() {

		List<SubCaseDetail> scds = new ArrayList<SubCaseDetail>();

		scds = sessionFactory.getCurrentSession()
			.createQuery("from SubCaseDetail ORDER BY id desc")
			.setMaxResults(1)
			.list();

		if (scds.size() > 0) {
			return scds.get(0).getId();
		} else {
			return 0;
		}

	}
	public void addsubcase(SubCaseDetail p) {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(p);
		//logger.info("User saved successfully, User Details="+p);
	}
	
	public List<SubCaseDetail> searchSubCaseDetail(String search){
		
		Session session = this.sessionFactory.getCurrentSession();
		System.out.println("hiii");
		List<SubCaseDetail> subcasesList = session.createQuery("from SubCaseDetail where verdict like ?").setParameter(0, '%' + search + '%').list();
		for(SubCaseDetail p : subcasesList){
			System.out.println(p.getVerdict());
		}
		return subcasesList;
	}
}
