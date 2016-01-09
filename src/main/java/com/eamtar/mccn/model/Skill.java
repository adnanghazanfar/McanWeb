package com.eamtar.mccn.model;

import java.io.Serializable;
import java.util.Date;

public class Skill implements Serializable {

	private static final long serialVersionUID = -4438982791041423216L;
	private Integer skillId;
	private String title;
	private Date createdDate;
	private Date modifiedDate;
	

	public Integer getSkillId() {
		return skillId;
	}
	public void setSkillId(Integer skillId) {
		this.skillId = skillId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	

	
	@Override
	public boolean equals(Object obj) {
	    if (obj == null) {
	        return false;
	    }
	    if (getClass() != obj.getClass()) {
	        return false;
	    }
	    final Skill other = (Skill) obj;
	    if ((this.skillId == null) ? (other.skillId != null) : !this.skillId.equals(other.skillId)) {
	        return false;
	    }
	    if (this.skillId.intValue() != other.skillId.intValue()) {
	        return false;
	    }
	    return true;
	}

}
