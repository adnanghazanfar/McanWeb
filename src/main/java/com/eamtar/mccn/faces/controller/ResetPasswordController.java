package com.eamtar.mccn.faces.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.eamtar.mccn.email.EmailBean;
import com.eamtar.mccn.email.EmailService;
import com.eamtar.mccn.faces.managedbean.ResetPasswordBean;
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
@Component(value = "resetPasswordController")
@Scope("request")
public class ResetPasswordController extends AbstractController {

	private final String NO_REDIRECTION = null;
	private final String ERROR_MESSAGE_EXCEPTION = "An Exception has occured please see logs.";
	private final String SUCCESS_MESSAGE_UPDATE = "Account's password successfully updated.";
	private final String ERROR_MESSAGE_UPDATE = "ERROR! Invalid old Password, or new Passwords dont match.";
	
	private final String SUCCESS_MESSAGE_SEND= "Account's password successfully sent.";
	private final String ERROR_MESSAGE_USER_INVALID = "ERROR! No record found for this email.";
	private final String ERROR_MESSAGE_EMAIL_INVALID = "ERROR! Invalid email.";

	@Autowired
	private ManagerService managerService;
	@Autowired
	private ResetPasswordBean changePasswordBean;
	@Autowired
	private UserBean userBean;
	
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
	
	
	public String sendPasswordViaEmail() {
		String message = null;
		
		try {

			String emailAddress = changePasswordBean.getEmailAddress();

			if (emailAddress != null && emailAddress.length() > 0) {

				UserService userService = managerService.getUserService();
				User user = userService.getUserByEmail(emailAddress);
				if(user!=null){
					EmailService emailService = managerService.getEmailService();
					EmailBean forgotPasswordEmailBean = EmailBean.getForgotPasswordEmailBean(user.getEmailAddress(), user.getPassword());
					emailService.sendMail(forgotPasswordEmailBean);
					message = SUCCESS_MESSAGE_SEND;
				} else {
					message = ERROR_MESSAGE_USER_INVALID;
				}

			} else {
				message = ERROR_MESSAGE_EMAIL_INVALID;
			}
		} catch (Exception exception) {
			message = ERROR_MESSAGE_EXCEPTION;
			logger.error(exception.getMessage());
		}
		
		addMessageToFacesContext(message);
		
		return NO_REDIRECTION;
	}

}
