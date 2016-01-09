package com.eamtar.mccn.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.eamtar.mccn.dao.AbstractHibernateDAO;
import com.eamtar.mccn.dao.UserSkillDao;
import com.eamtar.mccn.model.UserSkill;

@Repository
public class UserSkillDaoImpl extends  AbstractHibernateDAO<UserSkill, Integer> 
implements UserSkillDao{

	@Override
	public List<UserSkill> getUserSkillByUserId(Integer userId) {
		if (userId == null)
			return null;
		String hqlStr = "from UserSkill userSkill " 
				+ "where userSkill.userProfile.userId=:userId "
				+ "order by userSkill.rating desc";
		Query query = getSession().createQuery(hqlStr);
		query.setInteger("userId", userId);
		List<UserSkill> userSkills = query.list();
		return userSkills;
	}

	
}