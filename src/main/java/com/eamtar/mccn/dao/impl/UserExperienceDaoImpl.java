package com.eamtar.mccn.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.eamtar.mccn.dao.AbstractHibernateDAO;
import com.eamtar.mccn.dao.UserExperienceDao;
import com.eamtar.mccn.model.UserExperience;

@Repository
public class UserExperienceDaoImpl extends  AbstractHibernateDAO<UserExperience, Integer> 
implements UserExperienceDao{

	@Override
	public List<UserExperience> getUserExperienceByUserId(Integer userId) {
		if (userId == null)
			return null;
		String hqlStr = "from UserExperience userExperience " 
				+ "where userExperience.userProfile.userId=:userId "
				+ "order by STR_TO_DATE(CONCAT('01/',userExperience.fromMonth,'/',userExperience.fromYear),'%d/%b/%YY') desc";
		Query query = getSession().createQuery(hqlStr);
		query.setInteger("userId", userId);
		List<UserExperience> userExperiences = query.list();
		return userExperiences;
	}
}