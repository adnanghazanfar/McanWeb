package com.eamtar.mccn.faces.managedbean;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import com.eamtar.mccn.faces.components.UserDataModel;
import com.eamtar.mccn.faces.custom.scope.SpringViewScoped;
import com.eamtar.mccn.model.User;

@Component(value = "manageUserBean")
@SpringViewScoped
public class ManageUserBean extends UserBean implements Serializable{
	

	private static final long serialVersionUID = -7134089636610888947L;
	private User[] selectedUsers = null;
	private UserDataModel userDataModel = null;
	private User selectedUser = null;
	
	
	public UserDataModel getUserDataModel() {
		return userDataModel;
	}

	public void setUserDataModel(UserDataModel userDataModel) {
		this.userDataModel = userDataModel;
	}
	
	public User[] getSelectedUsers() {
		return selectedUsers;
	}

	public void setSelectedUsers(User[] selectedUsers) {
		this.selectedUsers = selectedUsers;
	}

	public User getSelectedUser() {
		return selectedUser;
	}

	public void setSelectedUser(User selectedUser) {
		this.selectedUser = selectedUser;
	}

}
