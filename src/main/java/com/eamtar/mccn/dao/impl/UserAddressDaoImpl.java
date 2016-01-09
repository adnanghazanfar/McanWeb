package com.eamtar.mccn.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.eamtar.mccn.dao.AbstractHibernateDAO;
import com.eamtar.mccn.dao.UserAddressDao;
import com.eamtar.mccn.model.UserAddress;

@Repository
public class UserAddressDaoImpl extends AbstractHibernateDAO<UserAddress, Integer> implements UserAddressDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<UserAddress> getUserAddressByUserId(Integer userId) {
		if (userId == null)
			return null;
		String hqlStr = "from UserAddress userAddress where userAddress.userProfile.userId=:userId "
				+ "order by userAddress.addressType asc";
		Query query = getSession().createQuery(hqlStr);
		query.setInteger("userId", userId);
		List<UserAddress> userAddresses = query.list();
		return userAddresses;
	}

}