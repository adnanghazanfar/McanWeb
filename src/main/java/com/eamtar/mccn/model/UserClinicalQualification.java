package com.eamtar.mccn.model;

import java.io.Serializable;
import java.util.Date;

public class UserClinicalQualification implements Serializable {
	
	private static final long serialVersionUID = -2262272287845520268L;
	private Integer clinicalQualificationId;
	private Specialty specialty;
	private Specialty subSpecialty;
	private String customSpecialty;
	private UserProfile userProfile;
	private Date createdDate;
	private Date modifiedDate;
	
	public Integer getClinicalQualificationId() {
		return clinicalQualificationId;
	}
	public void setClinicalQualificationId(Integer clinicalQualificationId) {
		this.clinicalQualificationId = clinicalQualificationId;
	}
	public Specialty getSpecialty() {
		return specialty;
	}
	public void setSpecialty(Specialty specialty) {
		this.specialty = specialty;
	}
	public Specialty getSubSpecialty() {
		return subSpecialty;
	}
	public void setSubSpecialty(Specialty subSpecialty) {
		this.subSpecialty = subSpecialty;
	}
	public String getCustomSpecialty() {
		return customSpecialty;
	}
	public void setCustomSpecialty(String customSpecialty) {
		this.customSpecialty = customSpecialty;
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
