package com.eamtar.mccn.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author ADNAN GHAZANFAR
 * @email  adnan.ghazanfar@yahoo.com
 * @since 15 AUG, 2014
 */

@Component("managerDao")
public class ManagerDAO {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private UserProfileDao userProfileDao;
	@Autowired
	private UserAcademicDao userAcademicDao;
	@Autowired
	private UserAddressDao userAddressDao;
	@Autowired
	private UserSkillDao userSkillDao;
	@Autowired
	private UserExperienceDao userExperienceDao;
	@Autowired
	private UserClinicalQualificationDao clinicalQualificationDao;
	
	@Autowired
	private LocationDao locationDao;
	@Autowired
	private SpecialtyDao specialtyDao;
	@Autowired
	private SkillDao skillDao;
	
	@Autowired
	private MessageDao messageDao;
	@Autowired
	private MessageFeedbackDao messageFeedbackDao;
	
	@Autowired
	private VideoDao videoDao;

	public UserDao getUserDao() {
		return userDao;
	}
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	public UserProfileDao getUserProfileDao() {
		return userProfileDao;
	}
	public void setUserProfileDao(UserProfileDao userProfileDao) {
		this.userProfileDao = userProfileDao;
	}
	public UserAcademicDao getUserAcademicDao() {
		return userAcademicDao;
	}
	public void setUserAcademicDao(UserAcademicDao userAcademicDao) {
		this.userAcademicDao = userAcademicDao;
	}
	public UserAddressDao getUserAddressDao() {
		return userAddressDao;
	}
	public void setUserAddressDao(UserAddressDao userAddressDao) {
		this.userAddressDao = userAddressDao;
	}
	public UserSkillDao getUserSkillDao() {
		return userSkillDao;
	}
	public LocationDao getLocationDao() {
		return locationDao;
	}
	public void setLocationDao(LocationDao locationDao) {
		this.locationDao = locationDao;
	}
	public void setUserSkillDao(UserSkillDao userSkillDao) {
		this.userSkillDao = userSkillDao;
	}
	public SkillDao getSkillDao() {
		return skillDao;
	}
	public void setSkillDao(SkillDao skillDao) {
		this.skillDao = skillDao;
	}
	public UserExperienceDao getUserExperienceDao() {
		return userExperienceDao;
	}
	public void setUserExperienceDao(UserExperienceDao userExperienceDao) {
		this.userExperienceDao = userExperienceDao;
	}
	public UserClinicalQualificationDao getUserClinicalQualificationDao() {
		return clinicalQualificationDao;
	}
	public void setUserClinicalQualificationDao(
			UserClinicalQualificationDao clinicalQualificationDao) {
		this.clinicalQualificationDao = clinicalQualificationDao;
	}
	public SpecialtyDao getSpecialtyDao() {
		return specialtyDao;
	}
	public void setSpecialtyDao(SpecialtyDao specialtyDao) {
		this.specialtyDao = specialtyDao;
	}
	public MessageDao getMessageDao() {
		return messageDao;
	}
	public void setMessageDao(MessageDao messageDao) {
		this.messageDao = messageDao;
	}
	public MessageFeedbackDao getMessageFeedbackDao() {
		return messageFeedbackDao;
	}
	public void setMessageFeedbackDao(MessageFeedbackDao messageFeedbackDao) {
		this.messageFeedbackDao = messageFeedbackDao;
	}
	public VideoDao getVideoDao() {
		return videoDao;
	}
	public void setVideoDao(VideoDao videoDao) {
		this.videoDao = videoDao;
	}

}