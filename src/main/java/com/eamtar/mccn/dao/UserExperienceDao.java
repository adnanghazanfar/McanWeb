package com.eamtar.mccn.dao;

import java.util.List;

import com.eamtar.mccn.model.UserExperience;

/**
 * @author ADNAN GHAZANFAR
 * @email  adnan.ghazanfar@yahoo.com
 * @since 27 AUG, 2014
 */
@SuppressWarnings("all")
public interface UserExperienceDao extends GenericDAO<UserExperience, Integer>{
	public List<UserExperience> getUserExperienceByUserId(Integer userId);
}