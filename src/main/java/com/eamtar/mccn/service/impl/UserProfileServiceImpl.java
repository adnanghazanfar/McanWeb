package com.eamtar.mccn.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eamtar.mccn.dao.ManagerDAO;
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
import com.eamtar.mccn.service.UserProfileService;

@Service
public class UserProfileServiceImpl implements UserProfileService {

	@Autowired
	private ManagerDAO managerDao;

	public ManagerDAO getManagerDao() {
		return managerDao;
	}

	public void setManagerDao(ManagerDAO managerDao) {
		this.managerDao = managerDao;
	}

	@Override
	public UserProfile getCompleteUserProfileById(Integer userId) {
		return managerDao.getUserProfileDao().getCompleteUserProfileById(userId);
	}

	@Override
	public UserProfileDTO getCompleteUserProfileDTOById(int userId) {
		UserProfile userProfile = managerDao.getUserProfileDao().getById(userId);
		List<UserClinicalQualification> userClinicalQualifications = managerDao.getUserClinicalQualificationDao().getUserClinicalQualificationByUserId(userId);
		List<UserAcademic> userAcademics = managerDao.getUserAcademicDao().getUserAcademicByUserId(userId);
		List<UserExperience> userExperiences = managerDao.getUserExperienceDao().getUserExperienceByUserId(userId);
		List<UserSkill> userSkills = managerDao.getUserSkillDao().getUserSkillByUserId(userId);
		List<UserAddress> userAddresses = managerDao.getUserAddressDao().getUserAddressByUserId(userId);
		
		UserProfileDTO userProfileDTO = new UserProfileDTO();
		userProfileDTO.setUserProfile(userProfile);
		userProfileDTO.setUserClinicalQualifications(userClinicalQualifications);
		userProfileDTO.setUserAcademics(userAcademics);
		userProfileDTO.setUserExperiences(userExperiences);
		userProfileDTO.setUserSkills(userSkills);
		userProfileDTO.setUserAddresses(userAddresses);
		return userProfileDTO;
	}

	@Override
	public UserProfile getQuickViewUserProfileById(Integer userId) {
		return managerDao.getUserProfileDao().getQuickViewUserProfileById(userId);
	}

	@Override
	public List<UserProfile> findUserViaCriteria(User userDTO, SearchDTO searchDTO) {
		// TODO Auto-generated method stub
		return managerDao.getUserProfileDao().findUserViaCriteria(userDTO, searchDTO);
	}

	@Override
	public UserProfile saveUserProfile(UserProfile userProfile) {
		return managerDao.getUserProfileDao().save(userProfile);
	}

	@Override
	public UserProfile updateUserProfile(UserProfile userProfile) {
		return managerDao.getUserProfileDao().update(userProfile);
	}

	@Override
	public String deleteUserProfile(UserProfile userProfile) {
		return managerDao.getUserProfileDao().delete(userProfile);
	}

	public UserAddress updateUserAddress(UserAddress address) {
		return managerDao.getUserAddressDao().update(address);
	}

	public UserAddress saveUserAddress(UserAddress address) {
		return managerDao.getUserAddressDao().save(address);
	}

	@Override
	public String deleteUserAddress(UserAddress userAddress) {
		return managerDao.getUserAddressDao().delete(userAddress);
	}

	@Override
	public Location saveLocation(Location location) {
		return managerDao.getLocationDao().save(location);
	}

	@Override
	public Location isLocationExist(Location location) {
		return managerDao.getLocationDao().isLocationExist(location);
	}

	@Override
	public UserAcademic saveUserAcademic(UserAcademic userAcademic) {
		return managerDao.getUserAcademicDao().save(userAcademic);
	}

	@Override
	public UserAcademic updateUserAcademic(UserAcademic userAcademic) {
		return managerDao.getUserAcademicDao().update(userAcademic);
	}

	@Override
	public String deleteUserAcademic(UserAcademic userAcademic) {
		return managerDao.getUserAcademicDao().delete(userAcademic);
	}

	@Override
	public UserSkill saveUserSkill(UserSkill userSkill) {
		return managerDao.getUserSkillDao().save(userSkill);
	}

	@Override
	public UserSkill updateUserSkill(UserSkill userSkill) {
		return managerDao.getUserSkillDao().update(userSkill);
	}

	@Override
	public String deleteUserSkill(UserSkill userSkill) {
		return managerDao.getUserSkillDao().delete(userSkill);
	}

	@Override
	public List<Skill> getAllSkills() {
		return managerDao.getSkillDao().findAll();
	}

	@Override
	public UserExperience saveUserExperience(UserExperience userExperience) {
		return managerDao.getUserExperienceDao().save(userExperience);
	}

	@Override
	public UserExperience updateUserExperience(UserExperience userExperience) {
		return managerDao.getUserExperienceDao().update(userExperience);
	}

	@Override
	public String deleteUserExperience(UserExperience userExperience) {
		return managerDao.getUserExperienceDao().delete(userExperience);
	}

	@Override
	public UserClinicalQualification saveUserClinicalQualification(UserClinicalQualification clinicalQualification) {
		return managerDao.getUserClinicalQualificationDao().save(clinicalQualification);
	}

	@Override
	public UserClinicalQualification updateUserClinicalQualification(UserClinicalQualification clinicalQualification) {
		return managerDao.getUserClinicalQualificationDao().update(clinicalQualification);
	}

	@Override
	public String deleteUserClinicalQualification(UserClinicalQualification clinicalQualification) {
		return managerDao.getUserClinicalQualificationDao().delete(clinicalQualification);
	}

	@Override
	public List<Specialty> getSpecialties(Integer parentId) {
		return managerDao.getSpecialtyDao().findByParentId(parentId);
	}

}