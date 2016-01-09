package com.eamtar.mccn.dao;
import java.util.List;

import com.eamtar.mccn.model.UserAcademic;
import com.eamtar.mccn.model.UserAddress;

/**
 * @author ADNAN GHAZANFAR
 * @email  adnan.ghazanfar@yahoo.com
 * @since 01 JULY, 2014
 */
@SuppressWarnings("all")
public interface UserAddressDao extends GenericDAO<UserAddress, Integer>{
	public List<UserAddress> getUserAddressByUserId(Integer userId);
}