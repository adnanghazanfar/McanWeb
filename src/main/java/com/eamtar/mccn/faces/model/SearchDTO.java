package com.eamtar.mccn.faces.model;

import java.io.Serializable;

public class SearchDTO implements Serializable{
	
	private static final long serialVersionUID = 3925975391855038866L;
	private String firstName = null;
	private String lastName = null;
	private String emailAddress = null;
	private String phoneNo = null;
	private String gender = null;
	
	private String cityName = null;
	private String stateName = null;
	private String countryName = null;
	
	private String academicTitle = null;
	private String instituteName = null;
	
	private String experienceType = null;
	private String companyName = null;
	
	private String universityInstituteName = null;
	
	
	private Integer minValue = 0;
	private Integer maxValue = 5;
	private Integer skillId = null;
	private Integer specialtyId = null;
	private Integer subSpecialtyId = null;
	private String customSpecialty;
	
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
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	
	public String getAcademicTitle() {
		return academicTitle;
	}
	public void setAcademicTitle(String academicTitle) {
		this.academicTitle = academicTitle;
	}
	public String getInstituteName() {
		return instituteName;
	}
	public void setInstituteName(String instituteName) {
		this.instituteName = instituteName;
	}
	public String getExperienceType() {
		return experienceType;
	}
	public void setExperienceType(String experienceType) {
		this.experienceType = experienceType;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getUniversityInstituteName() {
		return universityInstituteName;
	}
	public void setUniversityInstituteName(String universityInstituteName) {
		this.universityInstituteName = universityInstituteName;
	}
	public Integer getMinValue() {
		return minValue;
	}
	public void setMinValue(Integer minValue) {
		this.minValue = minValue;
	}
	public Integer getMaxValue() {
		return maxValue;
	}
	public void setMaxValue(Integer maxValue) {
		this.maxValue = maxValue;
	}
	public Integer getSkillId() {
		return skillId;
	}
	public void setSkillId(Integer skillId) {
		this.skillId = skillId;
	}
	public Integer getSpecialtyId() {
		return specialtyId;
	}
	public void setSpecialtyId(Integer specialtyId) {
		this.specialtyId = specialtyId;
	}
	public Integer getSubSpecialtyId() {
		return subSpecialtyId;
	}
	public void setSubSpecialtyId(Integer subSpecialtyId) {
		this.subSpecialtyId = subSpecialtyId;
	}
	public String getCustomSpecialty() {
		return customSpecialty;
	}
	public void setCustomSpecialty(String customSpecialty) {
		this.customSpecialty = customSpecialty;
	}

}
