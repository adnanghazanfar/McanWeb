package com.eamtar.mccn.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
/**
 * @author ADNAN GHAZANFAR
 * @email  adnan.ghazanfar@yahoo.com
 * @since 01 JULY, 2014
 */
@SuppressWarnings("all")
public class HibernateTransactionImpl implements GenericTransaction{

	private SessionFactory  sessionFactory;
	private Transaction transaction; 
	 

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public boolean begin() {
		
		System.out.println("\n\n\n***** SPRING AOP *****");
		System.out.println("***** Transaction Begin *****");
		try{
			Session session = this.sessionFactory.getCurrentSession();
			if (session == null || session.isOpen() == false)
	    		session = this.sessionFactory.openSession();
			
			transaction =session.beginTransaction();
		return true;
		}
		catch (HibernateException e) {
		
			return false;
		}
	}


	public boolean commit() {
		
		try{
		transaction.commit();
		System.out.println("***** Transaction Closed *****");
		return true;
		}catch (HibernateException e) {
		
		return false;
		}
	}


	public boolean rollback() {
		try{
			transaction.rollback();
			return true;
			}catch (HibernateException e) {
			
			return false;
			}
	}
}
