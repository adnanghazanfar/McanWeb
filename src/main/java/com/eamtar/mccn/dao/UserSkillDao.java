package com.eamtar.mccn.dao;

import java.util.List;

import com.eamtar.mccn.model.UserClinicalQualification;
import com.eamtar.mccn.model.UserSkill;

/**
 * @author ADNAN GHAZANFAR
 * @email  adnan.ghazanfar@yahoo.com
 * @since 27 AUG, 2014
 */
@SuppressWarnings("all")
public interface UserSkillDao extends GenericDAO<UserSkill, Integer>{
	public List<UserSkill> getUserSkillByUserId(Integer userId);
}