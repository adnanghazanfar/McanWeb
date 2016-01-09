package com.eamtar.mccn.model;

import java.io.Serializable;
import java.util.Date;

public class UserExperience implements Serializable {

	private static final long serialVersionUID = -851539138987908631L;
	private Integer experienceId;
	private String experienceType;
	private String description;
	private String workPlace;
	private Boolean isCurrent = false;
	private Integer fromYear;
	private String fromMonth;
	private Integer toYear;
	private String toMonth;
	private UserProfile userProfile;
	private Date createdDate;
	private Date modifiedDate;
	
	public Integer getExperienceId() {
		return experienceId;
	}
	public void setExperienceId(Integer experienceId) {
		this.experienceId = experienceId;
	}
	public String getExperienceType() {
		return experienceType;
	}
	public void setExperienceType(String experienceType) {
		this.experienceType = experienceType;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getWorkPlace() {
		return workPlace;
	}
	public void setWorkPlace(String workPlace) {
		this.workPlace = workPlace;
	}
	public Boolean getIsCurrent() {
		return isCurrent;
	}
	public void setIsCurrent(Boolean isCurrent) {
		this.isCurrent = isCurrent;
	}
	public Integer getFromYear() {
		return fromYear;
	}
	public void setFromYear(Integer fromYear) {
		this.fromYear = fromYear;
	}
	public String getFromMonth() {
		return fromMonth;
	}
	public void setFromMonth(String fromMonth) {
		this.fromMonth = fromMonth;
	}
	public Integer getToYear() {
		return toYear;
	}
	public void setToYear(Integer toYear) {
		this.toYear = toYear;
	}
	public String getToMonth() {
		return toMonth;
	}
	public void setToMonth(String toMonth) {
		this.toMonth = toMonth;
	}
	public UserProfile getUserProfile() {
		return userProfile;
	}
	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

		
}
