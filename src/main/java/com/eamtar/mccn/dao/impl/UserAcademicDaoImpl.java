package com.eamtar.mccn.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.eamtar.mccn.dao.AbstractHibernateDAO;
import com.eamtar.mccn.dao.UserAcademicDao;
import com.eamtar.mccn.model.UserAcademic;

@Repository
public class UserAcademicDaoImpl extends AbstractHibernateDAO<UserAcademic, Integer> implements UserAcademicDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<UserAcademic> getUserAcademicByUserId(Integer userId) {
		if (userId == null)
			return null;
		String hqlStr = "from UserAcademic userAcademic " 
				+ "where userAcademic.userProfile.userId=:userId "
				+ "order by userAcademic.yearCompleted desc";
		Query query = getSession().createQuery(hqlStr);
		query.setInteger("userId", userId);
		List<UserAcademic> userAcademics = query.list();
		return userAcademics;
	}

}