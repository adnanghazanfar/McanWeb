package com.eamtar.mccn.service.impl;
 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eamtar.mccn.dao.ManagerDAO;
import com.eamtar.mccn.model.User;
import com.eamtar.mccn.model.UserProfile;
import com.eamtar.mccn.service.UserService;

@Service 
public class UserServiceImpl implements UserService{
	
	@Autowired
	private ManagerDAO managerDao;
 
	public ManagerDAO getManagerDao() {
		return managerDao;
	}

	public void setManagerDao(ManagerDAO managerDao) {
		this.managerDao = managerDao;
	}

	public User save(User user) {
		// TODO Auto-generated method stub
		return managerDao.getUserDao().save(user);
	}
	
	public boolean saveUserComplete(User user, UserProfile userProfile) {
		// TODO Auto-generated method stub
		try{
			user = managerDao.getUserDao().save(user);
			userProfile.setUser(user);
			userProfile = managerDao.getUserProfileDao().save(userProfile);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean updateUserComplete(User user, UserProfile userProfile) {
		// TODO Auto-generated method stub
		try{
			user = managerDao.getUserDao().update(user);
			userProfile = managerDao.getUserProfileDao().update(userProfile);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	
	public User getById(Integer userId) {
		// TODO Auto-generated method stub
		return managerDao.getUserDao().getById(userId);
	}
	
	public User update(User user) {
		// TODO Auto-generated method stub
		return managerDao.getUserDao().update(user);
	}
	
	public String updateList(List<User> users) {
		// TODO Auto-generated method stub
		return managerDao.getUserDao().saveOrUpdate(users);
	}
	
	public List<User> findViaCriteria(User searchUser){
 		return managerDao.getUserDao().findViaCriteria(searchUser);
	}

	public User findUser(String email, String password){
		// TODO Auto-generated method stub
		return managerDao.getUserDao().findUser(email, password);
	}

	public boolean isUserExists(String email) {
		// TODO Auto-generated method stub
		return managerDao.getUserDao().isUserExists(email);
	}

	public String saveOrUpdate(List<User> users) {
		// TODO Auto-generated method stub
		return managerDao.getUserDao().saveOrUpdate(users);
	}	
	
	public User getUserByEmail(String emailAddress){
		// TODO Auto-generated method stub
		return managerDao.getUserDao().getUserByEmail(emailAddress);
	}

}