package com.eamtar.mccn.faces.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.eamtar.mccn.faces.managedbean.ChangePasswordBean;
import com.eamtar.mccn.faces.managedbean.UserBean;
import com.eamtar.mccn.model.User;
import com.eamtar.mccn.service.ManagerService;
import com.eamtar.mccn.service.ServiceManagerFactory;
import com.eamtar.mccn.service.UserService;

/**
 * @author Adnan Ghazanfar
 * @email adnan.ghazanfar@yahoo.com
 * @since 12th NOV, 2014
 */
@Component(value = "changePasswordController")
@Scope("request")
public class ChangePasswordController extends AbstractController {

	private final String NO_REDIRECTION = null;
	private final String ERROR_MESSAGE_EXCEPTION = "An Exception has occured please see logs.";
	private final String SUCCESS_MESSAGE_UPDATE = "Account's password successfully updated.";
	private final String ERROR_MESSAGE_UPDATE = "ERROR! Invalid old Password, or new Passwords dont match.";
	
	@Autowired
	private UserBean userBean;
	@Autowired
	private ChangePasswordBean changePasswordBean;
	@Autowired
	private ManagerService managerService;
	
	public String resetPassword() {

		String message = null;
		
		try {

			String currentPassword = changePasswordBean.getCurrentPassword();
			String newPassword = changePasswordBean.getNewPassword();
			String confirmPassword = changePasswordBean.getConfirmPassword();

			User user = userBean.getUser();
			if (currentPassword != null && currentPassword.equals(user.getPassword())
					&& newPassword.equals(confirmPassword)) {

				UserService userService = managerService.getUserService();

				user.setPassword(newPassword);
				user.setcPassword(confirmPassword);

				user = userService.update(user);
				
				if(user!=null)
					message = SUCCESS_MESSAGE_UPDATE;

			} else {
				message = ERROR_MESSAGE_UPDATE;
			}
		} catch (Exception exception) {
			message = ERROR_MESSAGE_EXCEPTION;
			logger.error(exception.getMessage());
		}
		
		addMessageToFacesContext(message);
		
		return NO_REDIRECTION;
	}

}
