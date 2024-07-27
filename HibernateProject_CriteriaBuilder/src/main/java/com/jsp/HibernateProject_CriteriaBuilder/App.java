package com.jsp.HibernateProject_CriteriaBuilder;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class App 
{
    public static void main( String[] args )
    {
    	Configuration cfg = new Configuration().configure().addAnnotatedClass(Student.class);
    	SessionFactory sf = cfg.buildSessionFactory();
    	Session see = sf.openSession();
    	Transaction tr = see.beginTransaction();
    	
    	
    	CriteriaBuilder cb = see.getCriteriaBuilder();
    	CriteriaQuery<Student> cq = cb.createQuery(Student.class);
    	Root<Student> root= cq.from(Student.class);
    	cq.select(root);
    	cq.where(cb.equal(root.get("studentId"),101));
    	
    	Query<Student> query = see.createQuery(cq);
    	List<Student> Students = query.list();
    	for(Student s : Students) {
    		System.out.println(s);
    	}
    	
    	tr.commit();
    	see.close();
    	sf.close();
    	
    }
}
