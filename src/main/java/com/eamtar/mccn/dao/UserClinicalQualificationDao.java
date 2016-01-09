package com.eamtar.mccn.dao;

import java.util.List;

import com.eamtar.mccn.model.UserClinicalQualification;

/**
 * @author ADNAN GHAZANFAR
 * @email adnan.ghazanfar@yahoo.com
 * @since 09 SEP, 2014
 */
@SuppressWarnings("all")
public interface UserClinicalQualificationDao extends GenericDAO<UserClinicalQualification, Integer> {
	
	public List<UserClinicalQualification> getUserClinicalQualificationByUserId(Integer userId);
	
}