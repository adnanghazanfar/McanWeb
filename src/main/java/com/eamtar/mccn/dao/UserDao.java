package com.eamtar.mccn.dao;
import java.util.List;

import com.eamtar.mccn.model.User;

/**
 * @author ADNAN GHAZANFAR
 * @email  adnan.ghazanfar@yahoo.com
 * @since 01 JULY, 2014
 */
@SuppressWarnings("all")
public interface UserDao extends GenericDAO<User, Integer> {
 	
	public List<User> findViaCriteria(User searchUser);
	public boolean isUserExists(String email);
	public User findUser(String email, String password);
	public User getUserByEmail(String emailAddress);
}