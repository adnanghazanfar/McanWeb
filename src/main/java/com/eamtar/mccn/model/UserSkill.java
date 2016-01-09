package com.eamtar.mccn.model;

import java.io.Serializable;
import java.util.Date;

public class UserSkill implements Serializable {

	private static final long serialVersionUID = -1897530065146757291L;
	private Integer userSkillId;
	private String customSkill;
	private Skill skill;
	private UserProfile userProfile;
	private Date createdDate;
	private Date modifiedDate;
	private Integer rating;
	private Integer noOfYears;
	private String description;
	
	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public Integer getNoOfYears() {
		return noOfYears;
	}

	public void setNoOfYears(Integer noOfYears) {
		this.noOfYears = noOfYears;
	}

	public Integer getUserSkillId() {
		return userSkillId;
	}

	public void setUserSkillId(Integer userSkillId) {
		this.userSkillId = userSkillId;
	}

	public String getCustomSkill() {
		return customSkill;
	}

	public void setCustomSkill(String customSkill) {
		this.customSkill = customSkill;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Skill getSkill() {
		return skill;
	}

	public void setSkill(Skill skill) {
		this.skill = skill;
	}

}
