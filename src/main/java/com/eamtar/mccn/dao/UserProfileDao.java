package com.eamtar.mccn.dao;
import java.util.List;

import com.eamtar.mccn.faces.model.SearchDTO;
import com.eamtar.mccn.model.User;
import com.eamtar.mccn.model.UserProfile;

/**
 * @author ADNAN GHAZANFAR
 * @email  adnan.ghazanfar@yahoo.com
 * @since 25 AUG, 2014
 */
@SuppressWarnings("all")
public interface UserProfileDao extends GenericDAO<UserProfile, Integer> {
 	
	public UserProfile getQuickViewUserProfileById(Integer userId);
	public UserProfile getCompleteUserProfileById(Integer userId);
	public List<UserProfile> findUserViaCriteria(User userDTO, SearchDTO searchDTO);
	
}