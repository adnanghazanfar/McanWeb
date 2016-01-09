package com.eamtar.mccn.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.eamtar.mccn.dao.AbstractHibernateDAO;
import com.eamtar.mccn.dao.UserClinicalQualificationDao;
import com.eamtar.mccn.model.UserClinicalQualification;

@Repository
public class UserClinicalQualificationDaoImpl extends  AbstractHibernateDAO<UserClinicalQualification, Integer> 
implements UserClinicalQualificationDao{
	
	@SuppressWarnings("unchecked")
	@Override
	public List<UserClinicalQualification> getUserClinicalQualificationByUserId(Integer userId) {
		if (userId == null)
			return null;
		String hqlStr = "from UserClinicalQualification userClinicalQualification " 
				+ "where userClinicalQualification.userProfile.userId=:userId "
				+ "order by userClinicalQualification.createdDate desc";
		Query query = getSession().createQuery(hqlStr);
		query.setInteger("userId", userId);
		List<UserClinicalQualification> userClinicalQualifications = query.list();
		return userClinicalQualifications;
	}
	
}