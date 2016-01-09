package com.eamtar.mccn.faces.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.eamtar.mccn.model.UserAcademic;
import com.eamtar.mccn.model.UserAddress;
import com.eamtar.mccn.model.UserClinicalQualification;
import com.eamtar.mccn.model.UserExperience;
import com.eamtar.mccn.model.UserProfile;
import com.eamtar.mccn.model.UserSkill;

public class UserProfileDTO implements Serializable {

	private static final long serialVersionUID = 7494770758110604660L;
	private UserProfile userProfile;
	private List<UserAcademic> userAcademics = null;
	private List<UserAddress> userAddresses = null;
	private List<UserSkill> userSkills = null;
	private List<UserExperience> userExperiences = null;
	private List<UserClinicalQualification> userClinicalQualifications = null;

	public UserProfile getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}

	public List<UserAcademic> getUserAcademics() {
		return userAcademics;
	}

	public void setUserAcademics(List<UserAcademic> userAcademics) {
		this.userAcademics = userAcademics;
	}

	public List<UserAddress> getUserAddresses() {
		return userAddresses;
	}

	public void setUserAddresses(List<UserAddress> userAddresses) {
		this.userAddresses = userAddresses;
	}

	public List<UserSkill> getUserSkills() {
		return userSkills;
	}

	public void setUserSkills(List<UserSkill> userSkills) {
		this.userSkills = userSkills;
	}

	public List<UserExperience> getUserExperiences() {
		return userExperiences;
	}

	public void setUserExperiences(List<UserExperience> userExperiences) {
		this.userExperiences = userExperiences;
	}

	public List<UserClinicalQualification> getUserClinicalQualifications() {
		return userClinicalQualifications;
	}

	public void setUserClinicalQualifications(List<UserClinicalQualification> userClinicalQualifications) {
		this.userClinicalQualifications = userClinicalQualifications;
	}

	public static UserProfileDTO convertToFacesObj(UserProfile selectedUserProfile, boolean isQuickView) {

		UserProfileDTO userProfileDTO = new UserProfileDTO();
		userProfileDTO.setUserProfile(selectedUserProfile);

		if (!isQuickView) {
			userProfileDTO.setUserSkills(new ArrayList<UserSkill>(selectedUserProfile.getUserSkills()));
			userProfileDTO.setUserExperiences(new ArrayList<UserExperience>(selectedUserProfile.getUserExperiences()));
			userProfileDTO.setUserAddresses(new ArrayList<UserAddress>(selectedUserProfile.getUserAddresses()));
			userProfileDTO.setUserAcademics(new ArrayList<UserAcademic>(selectedUserProfile.getUserAcademics()));
			userProfileDTO.setUserClinicalQualifications(new ArrayList<UserClinicalQualification>(selectedUserProfile
					.getUserClinicalQualifications()));
		} else {
			userProfileDTO.setUserSkills(new ArrayList<UserSkill>(selectedUserProfile.getUserSkills()));
		}

		return userProfileDTO;
	}

}
