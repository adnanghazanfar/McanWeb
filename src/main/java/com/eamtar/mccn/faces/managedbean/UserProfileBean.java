package com.eamtar.mccn.faces.managedbean;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Component;

import com.eamtar.mccn.faces.custom.scope.SpringViewScoped;
import com.eamtar.mccn.faces.model.UserProfileDTO;
import com.eamtar.mccn.model.Location;
import com.eamtar.mccn.model.Skill;
import com.eamtar.mccn.model.Specialty;
import com.eamtar.mccn.model.UserAcademic;
import com.eamtar.mccn.model.UserAddress;
import com.eamtar.mccn.model.UserClinicalQualification;
import com.eamtar.mccn.model.UserExperience;
import com.eamtar.mccn.model.UserSkill;

/**
 * @author ADNAN GHAZANFAR
 * @email adnan.ghazanfar@yahoo.com
 * @since 24 SEP, 2014
 */
@Component(value = "userProfileBean")
@SpringViewScoped
public class UserProfileBean implements Serializable {

	private static final long serialVersionUID = -7569133384873678658L;
	private boolean intialized = false;
	private String medicalSchool = "EMS";
	private UserProfileDTO selectedUserProfile;
	private List<Specialty> subSpecialties = null;

	private UserAcademic selectedUserAcademic = null;
	private UserAddress selectedUserAddress = null;
	private UserSkill selectedUserSkill = null;
	private UserExperience selectedUserExperience = null;
	private UserClinicalQualification selectedUserClinicalQualification = null;

	public boolean isIntialized() {
		return intialized;
	}

	public void setIntialized(boolean intialized) {
		this.intialized = intialized;
	}

	public List<Specialty> getSubSpecialties() {
		return subSpecialties;
	}

	public void setSubSpecialties(List<Specialty> subSpecialties) {
		this.subSpecialties = subSpecialties;
	}

	public UserProfileDTO getSelectedUserProfile() {
		return selectedUserProfile;
	}

	public void setSelectedUserProfile(UserProfileDTO selectedUserProfile) {
		this.selectedUserProfile = selectedUserProfile;
	}

	public UserAcademic getSelectedUserAcademic() {
		return selectedUserAcademic;
	}

	public void setSelectedUserAcademic(UserAcademic selectedUserAcademic) {
		this.selectedUserAcademic = selectedUserAcademic;
	}

	public UserAddress getSelectedUserAddress() {
		return selectedUserAddress;
	}

	public void setSelectedUserAddress(UserAddress selectedUserAddress) {
		this.selectedUserAddress = selectedUserAddress;
	}

	public UserSkill getSelectedUserSkill() {
		return selectedUserSkill;
	}

	public void setSelectedUserSkill(UserSkill selectedUserSkill) {
		this.selectedUserSkill = selectedUserSkill;
	}

	public UserExperience getSelectedUserExperience() {
		return selectedUserExperience;
	}

	public void setSelectedUserExperience(UserExperience selectedUserExperience) {
		this.selectedUserExperience = selectedUserExperience;
	}

	public UserClinicalQualification getSelectedUserClinicalQualification() {
		return selectedUserClinicalQualification;
	}

	public void setSelectedClinicalQualification(
			UserClinicalQualification selectedUserClinicalQualification) {
		this.selectedUserClinicalQualification = selectedUserClinicalQualification;
	}

	/*
	 * For handling New Records
	 */
	public void setNewSelectedUserAcademic(UserAcademic selectedUserAcademic) {
		this.selectedUserAcademic = new UserAcademic();
	}

	public void setNewSelectedUserExperience(
			UserExperience selectedUserExperience) {
		this.selectedUserExperience = new UserExperience();
	}

	public void setNewSelectedUserSkill(UserSkill selectedUserSkill) {
		this.selectedUserSkill = new UserSkill();
		this.selectedUserSkill.setSkill(new Skill());
	}

	public void setNewSelectedUserAddress(UserAddress selectedUserAddress) {
		this.selectedUserAddress = new UserAddress();
		this.selectedUserAddress.setLocation(new Location());
	}

	public void setNewSelectedUserClinicalQualification(
			UserClinicalQualification userClinicalQualification) {
		this.selectedUserClinicalQualification = new UserClinicalQualification();
		this.selectedUserClinicalQualification.setSpecialty(new Specialty());
		this.selectedUserClinicalQualification.setSubSpecialty(new Specialty());
	}

	/*
	 * For handling Different Labels upon selection
	 */

	public String getInstituteLabel() {
		String label = "University/Institution/Exam/:*";
		if (selectedUserAcademic != null) {
			String academicTitle = selectedUserAcademic.getAcademicTitle();
			if (academicTitle != null) {
				academicTitle = academicTitle.toUpperCase();
				if (academicTitle.contains("DEGREE"))
					label = "University / Institution:*";
				else if (academicTitle.contains("EXAM"))
					label = "Name of Exam:*";
				else if (academicTitle.contains("SCHOLARSHIP"))
					label = "Name of Institution:*";
			}
		}
		return label;
	}

	public String getYearLabel() {
		String label = "Year of graduation, exam or scholarship :*";
		if (selectedUserAcademic != null) {
			String academicTitle = selectedUserAcademic.getAcademicTitle();
			if (academicTitle != null) {
				academicTitle = academicTitle.toUpperCase();
				if (academicTitle.contains("DEGREE"))
					label = "Year of Graduation:*";
				else if (academicTitle.contains("EXAM"))
					label = "Year of Exam:*";
				else if (academicTitle.contains("SCHOLARSHIP"))
					label = "Year of Grant or Scholarship:*";
			}
		}
		return label;
	}

	public String getDescriptionLabel() {
		String label = "More details about this Degree , exam or scholarship :";
		if (selectedUserAcademic != null) {
			String academicTitle = selectedUserAcademic.getAcademicTitle();
			if (academicTitle != null) {
				academicTitle = academicTitle.toUpperCase();
				if (academicTitle.contains("MEDICAL"))
					label = "Any details about your medical school experience including leadership positions , research activities  and other significant accomplishments :";
				else if (academicTitle.contains("MASTER"))
					label = "Specify the field of study of your Master's Degree :";
				else if (academicTitle.contains("PHD"))
					label = "Specify the field of study of your PhD Degree :";
				else if (academicTitle.contains("OTHER DEGREE"))
					label = "What kind of Degree and what is the field of study?:";
				else if (academicTitle.contains("EXAM"))
					label = "More details about this Exam ( Score or link of document or video with your experience):*";
				else if (academicTitle.contains("SCHOLARSHIP"))
					label = "More details about this Grant or Scholarship :";
			}
		}
		return label;
	}

	public String getSubSpecialtyLabel() {
		String label = "Sub-specialty :";
		if (selectedUserClinicalQualification != null
				&& selectedUserClinicalQualification.getSpecialty() != null) {
			String spcialtyName = selectedUserClinicalQualification
					.getSpecialty().getName();
			if (spcialtyName != null) {
				spcialtyName = spcialtyName.toUpperCase();
				if (spcialtyName.contains("MEDICAL STUDENT"))
					label = "Specify which year in medical school :";
				else if (spcialtyName.contains("OTHER SPECIALTY"))
					label = "Specify your specialty :";
			}
		}
		return label;
	}

	public String getMedicalSchool() {
		return medicalSchool;
	}

	public void setMedicalSchool(String medicalSchool) {
		this.medicalSchool = medicalSchool;
	}
}
