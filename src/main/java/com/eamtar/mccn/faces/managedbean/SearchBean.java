package com.eamtar.mccn.faces.managedbean;

import java.io.Serializable;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.eamtar.mccn.faces.custom.scope.SpringViewScoped;
import com.eamtar.mccn.faces.model.SearchDTO;
import com.eamtar.mccn.faces.model.UserProfileDTO;
import com.eamtar.mccn.model.Specialty;
import com.eamtar.mccn.model.User;
import com.eamtar.mccn.model.UserProfile;

@Component(value = "searchBean")
@Scope("session")
public class SearchBean implements Serializable{
	
	private static final long serialVersionUID = -1743256043947563928L;
	private boolean intialized = false;
	private boolean userExists = true;
	
	private User userDTO = null;
	private UserProfileDTO userProfileDTO;
	private SearchDTO searchDTO = null;
	
	private List<UserProfile> users = null;
	private List<Specialty> subSpecialties = null;
	
	public boolean isIntialized() {
		return intialized;
	}
	public void setIntialized(boolean intialized) {
		this.intialized = intialized;
	}
	
	public User getUserDTO() {
		return userDTO;
	}
	public void setUserDTO(User userDTO) {
		this.userDTO = userDTO;
	}
	
	public SearchDTO getSearchDTO() {
		return searchDTO;
	}
	
	public void setSearchDTO(SearchDTO searchDTO) {
		this.searchDTO = searchDTO;
	}
	
	public UserProfileDTO getUserProfileDTO() {
		return userProfileDTO;
	}
	public void setUserProfileDTO(UserProfileDTO userProfileDTO) {
		this.userProfileDTO = userProfileDTO;
	}

	public List<UserProfile> getUsers() {
		return users;
	}
	public void setUsers(List<UserProfile> users) {
		this.users = users;
	}
	
	public List<Specialty> getSubSpecialties() {
		return subSpecialties;
	}
	public void setSubSpecialties(List<Specialty> subSpecialties) {
		this.subSpecialties = subSpecialties;
	}
	
	public boolean isUserExists() {
		return userExists;
	}
	public void setUserExists(boolean userExists) {
		this.userExists = userExists;
	}
	
}
