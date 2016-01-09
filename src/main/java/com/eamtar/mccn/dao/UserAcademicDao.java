package com.eamtar.mccn.dao;

import java.util.List;

import com.eamtar.mccn.model.UserAcademic;

/**
 * @author ADNAN GHAZANFAR
 * @email  adnan.ghazanfar@yahoo.com
 * @since 01 JULY, 2014
 */
@SuppressWarnings("all")
public interface UserAcademicDao extends GenericDAO<UserAcademic, Integer>{
	
	public List<UserAcademic> getUserAcademicByUserId(Integer userId);
 
}