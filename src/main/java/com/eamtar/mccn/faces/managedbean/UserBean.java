package com.eamtar.mccn.faces.managedbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.eamtar.mccn.model.User;

/**
 * @author ADNAN GHAZANFAR
 * @email  adnan.ghazanfar@yahoo.com
 * @since 01 JULY, 2014
 */
@Component(value = "userBean")
@Scope("session")
public class UserBean implements Serializable{


	private static final long serialVersionUID = 8063825471256649587L;
	private boolean loggedIn = false ;
	private Integer userId;
	private Integer userType;
	private String email;
	private String password;
	private String firstName;
	private String lastName;
	private String fatherName;
	private String gender;
	private String cnic;
	private String searhString;
	private User user;
	private List<String> genders = new ArrayList<String>();
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFatherName() {
		return fatherName;
	}
	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getCnic() {
		return cnic;
	}
	public void setCnic(String cnic) {
		this.cnic = cnic;
	}
	public String getSearhString() {
		return searhString;
	}
	public void setSearhString(String searhString) {
		this.searhString = searhString;
	}
	public boolean isLoggedIn() {
		return loggedIn;
	}
	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public List<String> getGenders() {
		return genders;
	}
	public void setGenders(List<String> genders) {
		this.genders = genders;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getUserType() {
		return userType;
	}
	public void setUserType(Integer userType) {
		this.userType = userType;
	}
	public Long getCurrentTimeInMilli() {
		return System.currentTimeMillis();
	}
	
}

