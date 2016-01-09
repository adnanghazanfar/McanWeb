package com.eamtar.mccn.faces.managedbean;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import com.eamtar.mccn.faces.custom.scope.SpringViewScoped;


/**
 * @author Adnan Ghazanfar
 * @email adnan.ghazanfar@yahoo.com
 * @since 12th NOV, 2014
 */
@Component(value = "resetPasswordBean")
@SpringViewScoped
public class ResetPasswordBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private String emailAddress;
	private String currentPassword;
	private String newPassword;
	private String confirmPassword;
	private Integer passwordChangeStatus;
	private String userId;
	private String passwordRecoveryRequest;
	private String sessionId;
	
	public Integer getPasswordChangeStatus() {
		return passwordChangeStatus;
	}

	public void setPasswordChangeStatus(Integer passwordChangeStatus) {
		this.passwordChangeStatus = passwordChangeStatus;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPasswordRecoveryRequest() {
		return passwordRecoveryRequest;
	}

	public void setPasswordRecoveryRequest(String passwordRecoveryRequest) {
		this.passwordRecoveryRequest = passwordRecoveryRequest;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getCurrentPassword() {
		return currentPassword;
	}

	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}

}
