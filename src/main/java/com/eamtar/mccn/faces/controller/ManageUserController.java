package com.eamtar.mccn.faces.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.eamtar.mccn.faces.components.UserDataModel;
import com.eamtar.mccn.faces.managedbean.ManageUserBean;
import com.eamtar.mccn.model.User;
import com.eamtar.mccn.service.ManagerService;
import com.eamtar.mccn.service.ServiceManagerFactory;
import com.eamtar.mccn.service.UserService;
import com.eamtar.mccn.util.ProjectConstant;

@Component(value = "manageUserController")
@Scope("request")
public class ManageUserController extends AbstractController {

	@Autowired
	private ManageUserBean manageUserBean;
	@Autowired
	private ManagerService managerService;

	public String viewUsers() {

		User searchUser = new User();
		searchUser.setFirstName(manageUserBean.getFirstName());
		searchUser.setLastName(manageUserBean.getLastName());
		searchUser.setEmailAddress(manageUserBean.getEmail());
		searchUser.setGender(manageUserBean.getGender());
		searchUser.setUserType(manageUserBean.getUserType());

		List<User> users = new ArrayList<User>();
		UserService userService = managerService.getUserService();
		users = userService.findViaCriteria(searchUser);
		manageUserBean.setUserDataModel(new UserDataModel(users));

		return null;
	}

	/*
	 * For multiple user Selection
	 */
	public String activateUsers() {

		User[] users = manageUserBean.getSelectedUsers();
		List<User> selectedUsers = new ArrayList<User>();

		for (User user : users) {
			user.setStatus(ProjectConstant.STATUS_ACTIVE);
			user.setSelected(false);
			selectedUsers.add(user);
		}

		FacesMessage doneMessage = null;
		if (selectedUsers.size() > 0) {
			UserService userService = managerService.getUserService();
			String updated = userService.saveOrUpdate(selectedUsers);
			if (updated.equals("success")) {
				doneMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Selected Users Activated Successfully.",
						null);
			} else {
				doneMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error while Activating Users.", null);
			}
		} else {
			doneMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Select Users!.", null);
		}
		FacesContext.getCurrentInstance().addMessage(null, doneMessage);
		manageUserBean.setSelectedUsers(null);
		return null;
	}

	/*
	 * For multiple user Selection
	 */
	public String deactivateUsers() {

		User[] users = manageUserBean.getSelectedUsers();
		List<User> selectedUsers = new ArrayList<User>();

		for (User user : users) {
			user.setStatus(ProjectConstant.STATUS_INACTIVE);
			user.setSelected(false);
			selectedUsers.add(user);
		}
		FacesMessage doneMessage = null;
		if (selectedUsers.size() > 0) {
			UserService userService = managerService.getUserService();
			String updated = userService.saveOrUpdate(selectedUsers);
			if (updated.equals("success")) {
				doneMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Selected User De-Activated Successfully.",
						null);
			} else {
				doneMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error while De-Activating User.", null);
			}
		} else {
			doneMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Select Users!.", null);
		}
		manageUserBean.setSelectedUsers(null);
		FacesContext.getCurrentInstance().addMessage(null, doneMessage);
		return null;
	}

	/*
	 * For single user Selection
	 */
	public String activateUser() {
		User user = manageUserBean.getSelectedUser();
		FacesMessage doneMessage = null;
		if (user != null) {
			user.setStatus(ProjectConstant.STATUS_INACTIVE);
			user.setSelected(false);
			UserService userService = managerService.getUserService();
			user = userService.update(user);
			if (user != null) {
				doneMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Selected User Activated Successfully.",
						null);
			} else {
				doneMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error while Activating User.", null);
			}
		} else {
			doneMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Select User!.", null);
		}
		FacesContext.getCurrentInstance().addMessage(null, doneMessage);
		manageUserBean.setSelectedUser(null);
		return null;
	}

	/*
	 * For single user Selection
	 */
	public String deactivateUser() {
		User user = manageUserBean.getSelectedUser();
		FacesMessage doneMessage = null;
		if (user != null) {
			user.setStatus(ProjectConstant.STATUS_INACTIVE);
			user.setSelected(false);
			UserService userService = managerService.getUserService();
			user = userService.update(user);
			if (user != null) {
				doneMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Selected Users Activated Successfully.",
						null);
			} else {
				doneMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error while Activating Users.", null);
			}
		} else {
			doneMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Select Users!.", null);
		}
		FacesContext.getCurrentInstance().addMessage(null, doneMessage);
		manageUserBean.setSelectedUser(null);
		return null;
	}

}