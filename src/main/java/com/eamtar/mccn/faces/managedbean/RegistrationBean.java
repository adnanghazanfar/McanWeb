package com.eamtar.mccn.faces.managedbean;

import java.util.List;

import org.springframework.stereotype.Component;

import com.eamtar.mccn.faces.custom.scope.SpringViewScoped;
import com.eamtar.mccn.model.User;
import com.eamtar.mccn.model.UserProfile;

@Component(value = "registrationBean")
@SpringViewScoped
public class RegistrationBean {

	private User user = null;
	private UserProfile userProfile = null;
	private List<String> userTypes = null;
	private List<String> genders = null;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<String> getUserTypes() {
		return userTypes;
	}

	public void setUserTypes(List<String> userTypes) {
		this.userTypes = userTypes;
	}

	public List<String> getGenders() {
		return genders;
	}

	public void setGenders(List<String> genders) {
		this.genders = genders;
	}

	public UserProfile getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}

}
