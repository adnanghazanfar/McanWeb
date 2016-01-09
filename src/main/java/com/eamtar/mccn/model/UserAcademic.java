package com.eamtar.mccn.model;

import java.io.Serializable;
import java.util.Date;

public class UserAcademic implements Serializable{

	private static final long serialVersionUID = -6032633152713596893L;
	private Integer academicId;
	private String academicTitle;
	private String academicDescription;
	private String instituteName;
	private UserProfile userProfile;
	private Date createdDate;
	private Date modifiedDate;
	private Integer yearCompleted;
	private Boolean isCurrent;

	public Integer getAcademicId() {
		return academicId;
	}

	public void setAcademicId(Integer academicId) {
		this.academicId = academicId;
	}

	public String getAcademicTitle() {
		return academicTitle;
	}

	public void setAcademicTitle(String academicTitle) {
		this.academicTitle = academicTitle;
	}

	public String getAcademicDescription() {
		return academicDescription;
	}

	public void setAcademicDescription(String academicDescription) {
		this.academicDescription = academicDescription;
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

	public Integer getYearCompleted() {
		return yearCompleted;
	}

	public void setYearCompleted(Integer yearCompleted) {
		this.yearCompleted = yearCompleted;
	}

	public String getInstituteName() {
		return instituteName;
	}

	public void setInstituteName(String instituteName) {
		this.instituteName = instituteName;
	}

	public Boolean getIsCurrent() {
		return isCurrent;
	}

	public void setIsCurrent(Boolean isCurrent) {
		this.isCurrent = isCurrent;
	}

}
