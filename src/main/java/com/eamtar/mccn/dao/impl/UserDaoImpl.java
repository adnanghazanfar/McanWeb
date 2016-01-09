package com.eamtar.mccn.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.eamtar.mccn.dao.AbstractHibernateDAO;
import com.eamtar.mccn.dao.UserDao;
import com.eamtar.mccn.model.User;

@Repository
public class UserDaoImpl extends  AbstractHibernateDAO<User, Integer> implements UserDao{


	@SuppressWarnings("unchecked")
	public List<User> findViaCriteria(User searchUser){
		Criteria criteria = getSession().createCriteria(User.class);

		if(searchUser.getFirstName()!=null && !searchUser.getFirstName().equals(""))
			criteria.add(Restrictions.ilike("firstName",searchUser.getFirstName()+"%"));
		if(searchUser.getLastName()!=null && !searchUser.getLastName().equals(""))
			criteria.add(Expression.ilike("lastName",searchUser.getLastName()+"%"));
		if(searchUser.getEmailAddress()!=null && !searchUser.getEmailAddress().equals(""))
			criteria.add(Expression.ilike("emailAddress",searchUser.getEmailAddress()+"%"));
		if(searchUser.getUserType()!=null && searchUser.getUserType().intValue() != -1)
			criteria.add(Expression.eq("userType",searchUser.getUserType()));
		if(searchUser.getGender()!=null && !searchUser.getGender().equals(""))
			criteria.add(Expression.ilike("gender",searchUser.getGender().substring(0,1)+"%"));
		if(searchUser.getStatus()!=null && searchUser.getStatus().intValue() != -1)
			criteria.add(Expression.eq("status",searchUser.getStatus()));		

		criteria.addOrder(Order.asc("emailAddress"));
		List<User> users = criteria.list();
		return users;
	}

	@SuppressWarnings("unchecked")
	public User findUser(String emailAddress, String password) {
		String hqlStr = "from User user "
				+"where user.emailAddress=:emailAddress and user.password=:password";
		Query query = getSession().createQuery(hqlStr);
		query.setString("emailAddress", emailAddress);
		query.setString("password", password);
		User user = (User)query.uniqueResult();
		return user;
	}
	
	/*
	 *  I have made this method to test your methods
	 *  and  to check Hibernate Hql methods execution 
	 *  backend techniques... 
	 *  
	 */
	
	public void findHibernateAnswers() {
		
		//Criterion c = new Criterion();
		
	}
	

	@SuppressWarnings("unchecked")
	public boolean isUserExists(String emailAddress) {
		// TODO Auto-generated method stub
		Query query = getSession().createQuery("from User user where user.emailAddress=:emailAddress");
		query.setString("emailAddress", emailAddress);
		User user = (User)query.uniqueResult();
		if(user != null)
			return true;
		return false;
	}
	
	
	@SuppressWarnings("unchecked")
	public User getUserByEmail(String emailAddress) {
		// TODO Auto-generated method stub
		Query query = getSession().createQuery("from User user where user.emailAddress=:emailAddress");
		query.setString("emailAddress", emailAddress);
		User user = (User)query.uniqueResult();
		return user;
	}


}