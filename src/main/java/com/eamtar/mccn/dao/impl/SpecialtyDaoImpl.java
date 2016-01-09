package com.eamtar.mccn.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.eamtar.mccn.dao.AbstractHibernateDAO;
import com.eamtar.mccn.dao.SpecialtyDao;
import com.eamtar.mccn.model.Specialty;

@Repository
public class SpecialtyDaoImpl extends AbstractHibernateDAO<Specialty, Integer>
		implements SpecialtyDao {

	@Override
	public List<Specialty> findByParentId(Integer parentId) {
		// TODO Auto-generated method stub
		String hqlQuery = "from Specialty specialty ";

		if (parentId == null)
			hqlQuery += "where specialty.parentId IS NULL ";
		else
			hqlQuery += "where specialty.parentId=:parentId ";
		
		hqlQuery += "order by specialty.specialtyId asc ";

		Query query = getSession().createQuery(hqlQuery);
		
		if (parentId != null)
			query.setInteger("parentId", parentId);
		
		return query.list();
	}

}