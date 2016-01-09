package com.eamtar.mccn.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.eamtar.mccn.dao.AbstractHibernateDAO;
import com.eamtar.mccn.dao.UserProfileDao;
import com.eamtar.mccn.faces.model.SearchDTO;
import com.eamtar.mccn.model.User;
import com.eamtar.mccn.model.UserProfile;

@Repository
public class UserProfileDaoImpl extends AbstractHibernateDAO<UserProfile, Integer> implements UserProfileDao {

	@Override
	public UserProfile getQuickViewUserProfileById(Integer userId) {
		// TODO Auto-generated method stub
		if (userId == null)
			return null;
		String hqlStr = "from UserProfile userProfile "
				+ "left join fetch userProfile.userSkills as userSkills "
				+ "where userProfile.userId=:userId "
				+ "order by userSkills.userSkillId desc";
		Query query = getSession().createQuery(hqlStr);
		query.setInteger("userId", userId);
		UserProfile userProfile = (UserProfile) query.uniqueResult();
		return userProfile;
	}

	public UserProfile getCompleteUserProfileById(Integer userId) {
		if (userId == null)
			return null;
		String hqlStr = "from UserProfile userProfile "
				+ "left join fetch userProfile.userAcademics as userAcademics "
				+ "left join fetch userProfile.userSkills as userSkills "
				+ "left join fetch userProfile.userExperiences as userExperiences "
				+ "left join fetch userProfile.userAddresses as userAddresses "
				+ "left join fetch userProfile.userClinicalQualifications as userClinicalQualifications "
				+ "where userProfile.userId=:userId "
				+ "order by userAcademics.yearCompleted, userSkills.userSkillId, userExperiences.fromYear,userAddresses.createdDate, userClinicalQualifications.clinicalQualificationId desc";
		Query query = getSession().createQuery(hqlStr);
		query.setInteger("userId", userId);
		UserProfile userProfile = (UserProfile) query.uniqueResult();
		return userProfile;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserProfile> findUserViaCriteria(User userDTO, SearchDTO searchDTO) {

		String hqlStr = "SELECT DISTINCT userProfile FROM UserProfile userProfile "
				+ "LEFT JOIN userProfile.userAddresses AS userAddresses "
				+ "LEFT JOIN userAddresses.location AS location "
				+ "LEFT JOIN userProfile.userSkills AS userSkills "
				+ "LEFT JOIN userProfile.userClinicalQualifications AS userClinicalQualifications "
				+ "LEFT JOIN userClinicalQualifications.subSpecialty AS subSpecialty "
				+ "LEFT JOIN userProfile.userAcademics AS userAcademics "
				+ "LEFT JOIN userProfile.userExperiences AS userExperiences " + "WHERE 1=1 ";

		/*
		 * BASIC INFO SEARCH
		 */

		if (userDTO.getFirstName() != null && userDTO.getFirstName().length() > 0)
			hqlStr += " AND userProfile.user.firstName LIKE '" + userDTO.getFirstName() + "%'";
		if (userDTO.getLastName() != null && userDTO.getLastName().length() > 0)
			hqlStr += " AND userProfile.user.lastName LIKE '" + userDTO.getLastName() + "%'";
		if (userDTO.getEmailAddress() != null && userDTO.getEmailAddress().length() > 0)
			hqlStr += " AND userProfile.user.emailAddress LIKE '" + userDTO.getEmailAddress() + "%'";
		if (userDTO.getContactNumber() != null && userDTO.getContactNumber().length() > 0)
			hqlStr += " AND userProfile.user.contactNumber LIKE '" + userDTO.getContactNumber() + "'";
		if (userDTO.getGender() != null && userDTO.getGender().length() > 0)
			hqlStr += " AND userProfile.user.gender = '" + userDTO.getGender().substring(0, 1) + "'";
		if (userDTO.getStatus() != null && userDTO.getStatus().intValue() != -1)
			hqlStr += " AND userProfile.user.status = " + userDTO.getStatus();
		if (userDTO.getUserType() != null && userDTO.getUserType().intValue() != -1)
			hqlStr += " AND userProfile.user.userType = " + userDTO.getUserType();

		/*
		 * ADDRESSES SEARCH
		 */

		if (searchDTO.getCountryName() != null && searchDTO.getCountryName().length() > 0)
			hqlStr += " AND location.countryName LIKE '" + searchDTO.getCountryName() + "'";
		if (searchDTO.getStateName() != null && searchDTO.getStateName().length() > 0)
			hqlStr += " AND location.stateName LIKE '%" + searchDTO.getStateName() + "%'";
		if (searchDTO.getCityName() != null && searchDTO.getCityName().length() > 0)
			hqlStr += " AND location.cityName LIKE '%" + searchDTO.getCityName() + "%'";

		/*
		 * SKILLS & SPECIALTY SEARCH
		 */

		if (searchDTO.getSkillId() != null && searchDTO.getSkillId().intValue() != 0)
			hqlStr += " AND userSkills.skill.skillId = " + searchDTO.getSkillId().intValue();
		if (searchDTO.getSpecialtyId() != null && searchDTO.getSpecialtyId().intValue() != 0)
			hqlStr += " AND userClinicalQualifications.specialty.specialtyId = " + searchDTO.getSpecialtyId().intValue();
		if (searchDTO.getSubSpecialtyId() != null && searchDTO.getSubSpecialtyId().intValue() != 0)
			hqlStr += " AND userClinicalQualifications.subSpecialty.specialtyId = "
					+ searchDTO.getSubSpecialtyId().intValue();
		if (searchDTO.getCustomSpecialty() != null && searchDTO.getCustomSpecialty().length() > 0){
			hqlStr += " AND ( userClinicalQualifications.customSpecialty LIKE '%" + searchDTO.getCustomSpecialty()
					+ "%'";
			hqlStr += " OR subSpecialty.name LIKE '%" + searchDTO.getCustomSpecialty()
					+ "%' )";		
		}

		/*
		 * ACADEMIC TRAINGING/EXPERIENCE
		 */

		if (searchDTO.getAcademicTitle() != null && searchDTO.getAcademicTitle().length() > 0)
			hqlStr += " AND userAcademics.academicTitle = '" + replaceSpecialCharacter(searchDTO.getAcademicTitle()) + "'";
		if (searchDTO.getInstituteName() != null && searchDTO.getInstituteName().length() > 0)
			hqlStr += " AND userAcademics.instituteName LIKE '%" + searchDTO.getInstituteName() + "%'";

		if (searchDTO.getExperienceType() != null && searchDTO.getExperienceType().length() > 0)
			hqlStr += " AND userExperiences.experienceType = '" + searchDTO.getExperienceType() + "'";
		if (searchDTO.getCompanyName() != null && searchDTO.getCompanyName().length() > 0)
			hqlStr += " AND userExperiences.workPlace LIKE '%" + searchDTO.getCompanyName() + "%'";
		
		if(searchDTO.getUniversityInstituteName() != null && searchDTO.getUniversityInstituteName().length() > 0)
			hqlStr += " AND ( userExperiences.workPlace LIKE '%" + searchDTO.getUniversityInstituteName() + "%' OR userAcademics.instituteName LIKE '%" + searchDTO.getUniversityInstituteName() + "%')";

		hqlStr += " AND userProfile.profileRating BETWEEN " + searchDTO.getMinValue() + " AND "
				+ searchDTO.getMaxValue();
		hqlStr += " ORDER BY userProfile.profileRating";

		Query query = getSession().createQuery(hqlStr);
		List<UserProfile> users = query.list();
		return users;
	}
	
	
	private static String replaceSpecialCharacter(String field){
		field = field.replace("'", "'\'");
		return field;
	}


}