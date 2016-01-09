package com.eamtar.mccn.util;

public enum ManagedBean {

	UserBean("userBean"), RegistrationBean("registrationBean"), ManageUserBean("manageUserBean"), CommonBean(
			"commonBean"), MessageBean("messageBean"), UserProfileBean("userProfileBean"), SearchBean("searchBean"), ResetPasswordBean(
			"resetPasswordBean"), VideoBean("videoBean");

	private String name = null;

	private ManagedBean(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

}
