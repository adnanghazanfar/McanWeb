package com.eamtar.mccn.model;

import java.io.Serializable;
import java.util.Set;

public class UserProfile implements Serializable {

	private static final long serialVersionUID = 5834125677481714026L;
	private Integer userId;
	private String summary;
	private String description;
	private Integer profileRating;
	private Integer totalExperience;
	
	private User user;
	
	private Set<UserSkill> userSkills;
	private Set<UserExperience> userExperiences;
	private Set<UserAddress> userAddresses;
	private Set<UserAcademic> userAcademics;
	private Set<UserClinicalQualification> userClinicalQualifications;

	

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getProfileRating() {
		return profileRating;
	}

	public void setProfileRating(Integer profileRating) {
		this.profileRating = profileRating;
	}

	public Integer getTotalExperience() {
		return totalExperience;
	}

	public void setTotalExperience(Integer totalExperience) {
		this.totalExperience = totalExperience;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<UserSkill> getUserSkills() {
		return userSkills;
	}

	public void setUserSkills(Set<UserSkill> userSkills) {
		this.userSkills = userSkills;
	}

	public Set<UserExperience> getUserExperiences() {
		return userExperiences;
	}

	public void setUserExperiences(Set<UserExperience> userExperiences) {
		this.userExperiences = userExperiences;
	}

	public Set<UserClinicalQualification> getUserClinicalQualifications() {
		return userClinicalQualifications;
	}

	public void setUserClinicalQualifications(Set<UserClinicalQualification> userClinicalQualifications) {
		this.userClinicalQualifications = userClinicalQualifications;
	}

	public Set<UserAddress> getUserAddresses() {
		return userAddresses;
	}

	public void setUserAddresses(Set<UserAddress> userAddresses) {
		this.userAddresses = userAddresses;
	}

	public Set<UserAcademic> getUserAcademics() {
		return userAcademics;
	}

	public void setUserAcademics(Set<UserAcademic> userAcademics) {
		this.userAcademics = userAcademics;
	}
}
