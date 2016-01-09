package com.eamtar.mccn.service;
 
import java.util.List;

import com.eamtar.mccn.faces.model.SearchDTO;
import com.eamtar.mccn.faces.model.UserProfileDTO;
import com.eamtar.mccn.model.Location;
import com.eamtar.mccn.model.Skill;
import com.eamtar.mccn.model.Specialty;
import com.eamtar.mccn.model.User;
import com.eamtar.mccn.model.UserAcademic;
import com.eamtar.mccn.model.UserAddress;
import com.eamtar.mccn.model.UserClinicalQualification;
import com.eamtar.mccn.model.UserExperience;
import com.eamtar.mccn.model.UserProfile;
import com.eamtar.mccn.model.UserSkill;
 
public interface UserProfileService{
	
	public UserProfile getCompleteUserProfileById(Integer userId);
	public UserProfileDTO getCompleteUserProfileDTOById(int userId);
	public UserProfile getQuickViewUserProfileById(Integer userId);
	public List<UserProfile> findUserViaCriteria(User userDTO, SearchDTO searchDTO);
	
	public UserProfile saveUserProfile(UserProfile userProfile);
	public UserProfile updateUserProfile(UserProfile userProfile);
	public String deleteUserProfile(UserProfile userProfile); 
	
	public UserAcademic saveUserAcademic(UserAcademic userAcademic);
	public UserAcademic updateUserAcademic(UserAcademic userAcademic);
	public String deleteUserAcademic(UserAcademic userAcademic);
	
	public UserAddress saveUserAddress(UserAddress address);
	public UserAddress updateUserAddress(UserAddress address);
	public String deleteUserAddress(UserAddress address);
	public Location saveLocation(Location location);
	public Location isLocationExist(Location location);
	
	public UserSkill saveUserSkill(UserSkill userSkill);
	public UserSkill updateUserSkill(UserSkill userSkill);
	public String deleteUserSkill(UserSkill userSkill);
	public List<Skill> getAllSkills();
	
	public UserExperience saveUserExperience(UserExperience userExperience);
	public UserExperience updateUserExperience(UserExperience userExperience);
	public String deleteUserExperience(UserExperience userExperience);
	
	public UserClinicalQualification saveUserClinicalQualification(UserClinicalQualification clinicalQualification);
	public UserClinicalQualification updateUserClinicalQualification(UserClinicalQualification clinicalQualification);
	public String deleteUserClinicalQualification(UserClinicalQualification clinicalQualification);
	public List<Specialty> getSpecialties(Integer parentId);

}