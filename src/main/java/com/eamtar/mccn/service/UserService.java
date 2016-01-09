package com.eamtar.mccn.service;
 
import java.util.List;

import com.eamtar.mccn.model.User;
import com.eamtar.mccn.model.UserProfile;
 
public interface UserService{
 
	
	public boolean saveUserComplete(User user, UserProfile userProfile);
	public boolean updateUserComplete(User user, UserProfile userProfile);
	
	public User getById(Integer userId);
	public User save(User user);
	public User update(User customer);
	public String saveOrUpdate(List<User> users);
	
	public List<User> findViaCriteria(User searchUser);
	public boolean isUserExists(String email);
	public User findUser(String email, String password);
	public User getUserByEmail(String emailAddress);
	
}