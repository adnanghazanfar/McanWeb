package com.eamtar.mccn.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.eamtar.mccn.dao.AbstractHibernateDAO;
import com.eamtar.mccn.dao.LocationDao;
import com.eamtar.mccn.model.Location;

@Repository
public class LocationDaoImpl extends AbstractHibernateDAO<Location, Integer>
		implements LocationDao {

	@Override
	public Location isLocationExist(Location location) {
		// TODO Auto-generated method stub

		if (location == null || location.getCityName() == null
				|| location.getStateName() == null
				|| location.getCountryName() == null)
			return null;

		Criteria criteria = getSession().createCriteria(Location.class);
		criteria.add(Restrictions.eq("cityName", location.getCityName()).ignoreCase());
		criteria.add(Restrictions.eq("stateName", location.getStateName()).ignoreCase());
		criteria.add(Restrictions.eq("countryName", location.getCountryName()).ignoreCase());
		List<Location> locations = criteria.list();

		if (locations != null && locations.size() > 0)
			return locations.get(0);

		return null;
	}

}