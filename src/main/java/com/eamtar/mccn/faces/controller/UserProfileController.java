package com.eamtar.mccn.faces.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.validator.ValidatorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.eamtar.mccn.faces.managedbean.CommonBean;
import com.eamtar.mccn.faces.managedbean.UserBean;
import com.eamtar.mccn.faces.managedbean.UserProfileBean;
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
import com.eamtar.mccn.service.ManagerService;
import com.eamtar.mccn.service.UserProfileService;
import com.eamtar.mccn.service.UserService;
import com.eamtar.mccn.util.ManagedBean;

@Component(value = "userProfileController")
@Scope("request")
public class UserProfileController extends AbstractController {

	@Autowired
	private ManagerService managerService;
	@Autowired
	private UserBean userBean;
	@Autowired
	private UserProfileBean userProfileBean;
	@Autowired
	private CommonBean commonBean;

	private final String ERROR_MESSAGE_EXCEPTION = "An Exception has occured please see logs.";
	private final String ERROR_MESSAGE_SAVE = "An Error has occured while Saving Record";
	private final String ERROR_MESSAGE_DELETE = "An Error has occured while Deleting Record";
	private final String ERROR_MESSAGE_UPDATE = "An Error has occured while Updating Record";
	private final String ERROR_MESSAGE_DUPLICATION = "Duplication Error! Already added to your profile.";
	private final String SUCCESS_MESSAGE_SAVE = "Record Saved Successfully";
	private final String SUCCESS_MESSAGE_DELETE = "Record Deleted Successfully";
	private final String SUCCESS_MESSAGE_UPDATE = "Record Updated Successfully";
	private final String NO_REDIRECTION = null;
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("MMMMM dd, yyyy");

	public void initValues() {
		try {
			if (userBean != null && userBean.getUserId() != null && !userProfileBean.isIntialized()) {
				UserProfileService userProfileService = managerService.getUserProfileService();
				UserProfileDTO userProfileDTO = userProfileService.getCompleteUserProfileDTOById(userBean.getUserId());

				userProfileBean.setIntialized(Boolean.TRUE);
				userProfileBean.setSelectedUserProfile(userProfileDTO);

			}
		} catch (Exception exception) {
			// TODO: handle exception
			logger.error(exception.getMessage());
		}
	}

	/*
	 * DELETE METHODS FOR USER PROFILE
	 */

	public void deleteUserAcademic(UserAcademic userAcademic) {
		String message = null;
		try {
			if (userProfileBean != null && userAcademic != null) {
				UserProfileService userProfileService = managerService.getUserProfileService();
				message = userProfileService.deleteUserAcademic(userAcademic);

				if (message.equals("success")) {
					userProfileBean.getSelectedUserProfile().getUserAcademics().remove(userAcademic);
					message = SUCCESS_MESSAGE_DELETE;
				} else {
					message = ERROR_MESSAGE_DELETE;
				}

			}
		} catch (Exception exception) {
			// TODO: handle exception
			message = ERROR_MESSAGE_EXCEPTION;
			logger.error(exception.getMessage());
		}

		addMessageToFacesContext(message);
	}

	public void deleteUserSkill(UserSkill userSkill) {
		String message = null;
		try {

			if (userProfileBean != null && userSkill != null) {
				UserProfileService userProfileService = managerService.getUserProfileService();
				message = userProfileService.deleteUserSkill(userSkill);

				if (message.equals("success")) {
					userProfileBean.getSelectedUserProfile().getUserSkills().remove(userSkill);
					message = SUCCESS_MESSAGE_DELETE;
				} else {
					message = ERROR_MESSAGE_DELETE;
				}

			}
		} catch (Exception exception) {
			// TODO: handle exception
			message = ERROR_MESSAGE_EXCEPTION;
			logger.error(exception.getMessage());
		}

		addMessageToFacesContext(message);
	}

	public void deleteUserClinicalQualification(UserClinicalQualification userClinicalQualification) {
		String message = null;
		try {
			if (userProfileBean != null && userClinicalQualification != null) {
				UserProfileService userProfileService = managerService.getUserProfileService();
				message = userProfileService.deleteUserClinicalQualification(userClinicalQualification);

				if (message.equals("success")) {
					userProfileBean.getSelectedUserProfile().getUserClinicalQualifications()
							.remove(userClinicalQualification);
					message = SUCCESS_MESSAGE_DELETE;
				} else {
					message = ERROR_MESSAGE_DELETE;
				}

			}
		} catch (Exception exception) {
			// TODO: handle exception
			message = ERROR_MESSAGE_EXCEPTION;
			logger.error(exception.getMessage());
		}

		addMessageToFacesContext(message);

		logger.info("EXITING THIS METHOD \n\n\n");
	}

	public void deleteUserExperience(UserExperience userExperience) {
		String message = null;
		try {
			if (userProfileBean != null && userExperience != null) {

				
				UserProfileService userProfileService = managerService.getUserProfileService();
				message = userProfileService.deleteUserExperience(userExperience);

				if (message.equals("success")) {
					userProfileBean.getSelectedUserProfile().getUserExperiences().remove(userExperience);
					message = SUCCESS_MESSAGE_DELETE;
				} else {
					message = ERROR_MESSAGE_DELETE;
				}

			}
		} catch (Exception exception) {
			// TODO: handle exception
			message = ERROR_MESSAGE_EXCEPTION;
			logger.error(exception.getMessage());
		}

		addMessageToFacesContext(message);
	}

	public void deleteUserAddress(UserAddress userAddress) {

		logger.info("\n\n\nINSIDE \n CLASS == UserProfileController \n METHOD == deleteAddress(Address address); ");

		String message = null;
		try {
			if (userProfileBean != null && userAddress != null) {

				
				UserProfileService userProfileService = managerService.getUserProfileService();
				message = userProfileService.deleteUserAddress(userAddress);

				if (message.equals("success")) {
					userProfileBean.getSelectedUserProfile().getUserAddresses().remove(userAddress);
					message = SUCCESS_MESSAGE_DELETE;
				} else {
					message = ERROR_MESSAGE_DELETE;
				}

			}
		} catch (Exception exception) {
			// TODO: handle exception
			message = ERROR_MESSAGE_EXCEPTION;
			logger.error(exception.getMessage());
		}

		addMessageToFacesContext(message);

		logger.info("EXITING THIS METHOD \n\n\n");

	}

	/*
	 * SAVE OR UPDATE METHODS FOR USER PROFILE
	 */

	public String updateUserProfile() {
		String message = null;
		try {
			if (userProfileBean != null && userProfileBean.getSelectedUserProfile() != null) {

				User user = userBean.getUser();
				UserProfile userProfile = userProfileBean.getSelectedUserProfile().getUserProfile();
				
				UserService userService = managerService.getUserService();
				Date currentDate = new Date();
				user.setModifiedDate(currentDate);
				boolean updated = userService.updateUserComplete(user, userProfile);
				if (updated) {
					message = SUCCESS_MESSAGE_UPDATE;
				} else {
					message = ERROR_MESSAGE_UPDATE;
				}
			}
		} catch (Exception exception) {
			// TODO: handle exception
			message = ERROR_MESSAGE_EXCEPTION;
			logger.error(exception.getMessage());
		}

		addMessageToFacesContext(message);
		return NO_REDIRECTION;
	}

	public String saveOrUpdateUserAcademic() {
		String message = null;

		try {
			if (userProfileBean != null && userProfileBean.getSelectedUserAcademic() != null) {

				UserAcademic userAcademic = userProfileBean.getSelectedUserAcademic();
				UserProfileService userProfileService = managerService.getUserProfileService();

				Date currentDate = new Date();
				if (userAcademic.getAcademicId() == null) {
					try {
						userAcademic.setUserProfile(userProfileBean.getSelectedUserProfile().getUserProfile());
						userAcademic.setCreatedDate(currentDate);
						userAcademic.setModifiedDate(currentDate);
						userAcademic = userProfileService.saveUserAcademic(userAcademic);
						userProfileBean.getSelectedUserProfile().getUserAcademics().add(userAcademic);
						message = SUCCESS_MESSAGE_SAVE;
					} catch (Exception exception) {
						// TODO: handle exception
						message = ERROR_MESSAGE_SAVE;
						logger.error(exception.getMessage());
					}
				} else {
					try {
						userAcademic.setModifiedDate(currentDate);
						userAcademic = userProfileService.updateUserAcademic(userAcademic);
						message = SUCCESS_MESSAGE_UPDATE;
					} catch (Exception exception) {
						// TODO: handle exception
						message = ERROR_MESSAGE_UPDATE;
						logger.error(exception.getMessage());
					}
				}

				if (userAcademic != null) {
					userProfileBean.setNewSelectedUserAcademic(null);
				}

			}
		} catch (Exception exception) {
			// TODO: handle exception
			message = ERROR_MESSAGE_EXCEPTION;
			logger.error(exception.getMessage());
		}

		addMessageToFacesContext(message);
		return NO_REDIRECTION;
	}

	public String saveOrUpdateUserSkill() {
		String message = null;

		try {
			if (userProfileBean != null && userProfileBean.getSelectedUserSkill() != null) {

				UserSkill userSkill = userProfileBean.getSelectedUserSkill();

				
				UserProfileService userProfileService = managerService.getUserProfileService();

				Date currentDate = new Date();
				if (userSkill.getUserSkillId() == null) {
					try {
						userSkill.setUserProfile(userProfileBean.getSelectedUserProfile().getUserProfile());
						userSkill.setCreatedDate(currentDate);
						userSkill.setModifiedDate(currentDate);
						userSkill = userProfileService.saveUserSkill(userSkill);
						userSkill.setSkill(userSkill.getSkill());
						userProfileBean.getSelectedUserProfile().getUserSkills().add(userSkill);
						message = SUCCESS_MESSAGE_SAVE;
					} catch (Exception exception) {
						// TODO: handle exception
						message = ERROR_MESSAGE_SAVE;
						logger.error(exception.getMessage());
					}

				} else {
					try {
						userSkill.setSkill(userSkill.getSkill());
						userSkill.setModifiedDate(currentDate);
						userSkill = userProfileService.updateUserSkill(userSkill);
						message = SUCCESS_MESSAGE_UPDATE;
					} catch (Exception exception) {
						// TODO: handle exception
						message = ERROR_MESSAGE_UPDATE;
						logger.error(exception.getMessage());
					}
				}

				if (userSkill != null) {
					userProfileBean.setNewSelectedUserSkill(null);
				}

			}
		} catch (Exception exception) {
			// TODO: handle exception
			message = ERROR_MESSAGE_EXCEPTION;
			logger.error(exception.getMessage());
		}

		addMessageToFacesContext(message);
		return NO_REDIRECTION;
	}

	public String saveOrUpdateUserClinicalQualification() {

		String message = null;

		try {
			if (userProfileBean != null && userProfileBean.getSelectedUserClinicalQualification() != null) {

				UserClinicalQualification clinicalQualification = userProfileBean
						.getSelectedUserClinicalQualification();
				UserProfileService userProfileService = managerService.getUserProfileService();

				Date currentDate = new Date();
				if (clinicalQualification.getClinicalQualificationId() == null) {
					try {

						boolean isDuplicate = false;
						Integer specialtyId = clinicalQualification.getSpecialty().getSpecialtyId();
						Integer subSpecialtyId = clinicalQualification.getSubSpecialty().getSpecialtyId();
						String customSubSpecialty = clinicalQualification.getCustomSpecialty();

						if (clinicalQualification.getSubSpecialty().getSpecialtyId() != null
								&& clinicalQualification.getSubSpecialty().getSpecialtyId() != 0) {
							isDuplicate = isSubSpecialityAlreadyAdded(userProfileBean, subSpecialtyId);
							if (!isDuplicate) {
								clinicalQualification
										.setSubSpecialty(getSubSpeciality(userProfileBean, subSpecialtyId));
								clinicalQualification.setCustomSpecialty(null);
							}
						} else if (clinicalQualification.getCustomSpecialty() != null
								&& clinicalQualification.getCustomSpecialty().length() > 0) {
							isDuplicate = isCustomSubSpecialityAlreadyAdded(userProfileBean, specialtyId,
									customSubSpecialty);
							clinicalQualification.setSubSpecialty(null);
						} else {
							isDuplicate = isSpecialityAlreadyAdded(userProfileBean, specialtyId);
							clinicalQualification.setSubSpecialty(null);
							clinicalQualification.setCustomSpecialty(null);
						}

						if (!isDuplicate) {
							clinicalQualification.setUserProfile(userProfileBean.getSelectedUserProfile()
									.getUserProfile());
							clinicalQualification.setCreatedDate(currentDate);
							clinicalQualification.setModifiedDate(currentDate);
							clinicalQualification = userProfileService
									.saveUserClinicalQualification(clinicalQualification);
							clinicalQualification.setSpecialty(commonBean.getSpecialtyById(clinicalQualification
									.getSpecialty().getSpecialtyId()));

							userProfileBean.getSelectedUserProfile().getUserClinicalQualifications()
									.add(clinicalQualification);
							message = SUCCESS_MESSAGE_SAVE;
						} else {
							message = ERROR_MESSAGE_DUPLICATION;
						}
					} catch (Exception exception) {
						// TODO: handle exception
						message = ERROR_MESSAGE_SAVE;
						logger.error(exception.getMessage());
					}

				} else {
					try {
						clinicalQualification.setSpecialty(commonBean.getSpecialtyById(clinicalQualification
								.getSpecialty().getSpecialtyId()));
						clinicalQualification.setModifiedDate(currentDate);
						clinicalQualification = userProfileService
								.updateUserClinicalQualification(clinicalQualification);
						message = SUCCESS_MESSAGE_UPDATE;
					} catch (Exception exception) {
						// TODO: handle exception
						message = ERROR_MESSAGE_UPDATE;
						logger.error(exception.getMessage());
					}
				}

				if (clinicalQualification != null) {
					userProfileBean.setNewSelectedUserClinicalQualification(null);
				}

			}
		} catch (Exception exception) {
			// TODO: handle exception
			message = ERROR_MESSAGE_EXCEPTION;
			logger.error(exception.getMessage());
		}

		addMessageToFacesContext(message);
		return NO_REDIRECTION;
	}

	public String saveOrUpdateUserExperience() {
		String message = null;

		try {
			if (userProfileBean != null && userProfileBean.getSelectedUserExperience() != null) {

				UserExperience experience = userProfileBean.getSelectedUserExperience();
				UserProfileService userProfileService = managerService.getUserProfileService();

				if ((experience.getToMonth() == null || experience.getToMonth().length() == 0)
						&& experience.getToYear() == null)
					experience.setIsCurrent(true);

				Date currentDate = new Date();
				if (experience.getExperienceId() == null) {
					try {
						experience.setUserProfile(userProfileBean.getSelectedUserProfile().getUserProfile());
						experience.setCreatedDate(currentDate);
						experience.setModifiedDate(currentDate);
						experience = userProfileService.saveUserExperience(experience);
						userProfileBean.getSelectedUserProfile().getUserExperiences().add(experience);
						message = SUCCESS_MESSAGE_SAVE;
					} catch (Exception exception) {
						// TODO: handle exception
						message = ERROR_MESSAGE_SAVE;
						logger.error(exception.getMessage());
					}
				} else {
					try {
						experience.setModifiedDate(currentDate);
						experience = userProfileService.updateUserExperience(experience);
						message = SUCCESS_MESSAGE_UPDATE;
					} catch (Exception exception) {
						// TODO: handle exception
						message = ERROR_MESSAGE_UPDATE;
						logger.error(exception.getMessage());
					}
				}

				if (experience != null) {
					userProfileBean.setNewSelectedUserExperience(null);
				}

			}
		} catch (Exception exception) {
			// TODO: handle exception
			message = ERROR_MESSAGE_EXCEPTION;
			logger.error(exception.getMessage());
		}

		addMessageToFacesContext(message);
		return NO_REDIRECTION;
	}

	public String saveOrUpdateUserAddress() {
		String message = null;

		try {
			if (userProfileBean != null && userProfileBean.getSelectedUserAddress() != null) {

				UserAddress userAddress = userProfileBean.getSelectedUserAddress();
				Location location = userAddress.getLocation();
				
				UserProfileService userProfileService = managerService.getUserProfileService();
				Location oldLocation = userProfileService.isLocationExist(location);
				Date currentDate = new Date();
				boolean isDuplicate = false;

				if (oldLocation == null) {
					location.setCreatedDate(currentDate);
					location.setModifiedDate(currentDate);
					location.setLocationId(null);
					location = userProfileService.saveLocation(location);
				} else {
					logger.info("LOCATION ALREADY EXISTS ::: " + oldLocation.getLocationId());
					location = oldLocation;
					isDuplicate = addressDuplicationValidate(oldLocation);
				}

				if (!isDuplicate) {
					userAddress.setLocation(location);
					userAddress.setModifiedDate(currentDate);
					userAddress.setUserProfile(userProfileBean.getSelectedUserProfile().getUserProfile());

					if (userAddress.getAddressId() == null) {
						try {
							userAddress.setCreatedDate(currentDate);
							userAddress = userProfileService.saveUserAddress(userAddress);
							userProfileBean.getSelectedUserProfile().getUserAddresses().add(userAddress);
							message = SUCCESS_MESSAGE_SAVE;
						} catch (Exception exception) {
							// TODO: handle exception
							message = ERROR_MESSAGE_SAVE;
							logger.error(exception.getMessage());
						}
					} else {
						try {
							userAddress = userProfileService.updateUserAddress(userAddress);
							message = SUCCESS_MESSAGE_UPDATE;
						} catch (Exception exception) {
							// TODO: handle exception
							message = ERROR_MESSAGE_UPDATE;
							logger.error(exception.getMessage());
						}
					}

					if (userAddress != null) {
						userProfileBean.setNewSelectedUserAddress(null);
					}
				} else {
					message = ERROR_MESSAGE_DUPLICATION;
				}

			}
		} catch (Exception exception) {
			// TODO: handle exception
			message = ERROR_MESSAGE_EXCEPTION;
			logger.error(exception.getMessage());
		}

		addMessageToFacesContext(message);
		return NO_REDIRECTION;
	}

	/*
	 * VALIDATORS FOR USER PROFILE
	 */

	public boolean addressDuplicationValidate(Location loation) {

		boolean found = Boolean.FALSE;

		if (loation != null && loation.getLocationId() != null) {

			for (UserAddress userAddress : userProfileBean.getSelectedUserProfile().getUserAddresses()) {
				if (userAddress.getLocation().getLocationId().intValue() == loation.getLocationId().intValue()) {
					found = Boolean.TRUE;
					break;
				}

			}
		}
		return found;

	}

	public void skillDuplicationValidate(FacesContext context, UIComponent component, Object value) {

		Skill skill = null;
		try {
			skill = (Skill) value;
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage());
		}

		boolean found = Boolean.FALSE;

		for (UserSkill userSkill : userProfileBean.getSelectedUserProfile().getUserSkills()) {
			if (!skill.getTitle().equalsIgnoreCase("Other")
					&& skill.getSkillId().intValue() == userSkill.getSkill().getSkillId().intValue()) {
				found = Boolean.TRUE;
				break;
			}

		}

		if (found) {
			FacesMessage doneMessage = null;
			doneMessage = new FacesMessage("This Skill is already added to your Profile.", "Try with different Skill.");
			doneMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(doneMessage);
		}
	}

	public void otherSkillDuplicationValidate(FacesContext context, UIComponent component, Object value) {

		String customSkill = null;
		try {
			customSkill = (String) value;
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage());
		}

		boolean found = Boolean.FALSE;
		for (UserSkill userSkill : userProfileBean.getSelectedUserProfile().getUserSkills()) {
			if (userSkill.getCustomSkill() != null && userSkill.getCustomSkill().equalsIgnoreCase(customSkill)) {
				found = Boolean.TRUE;
				break;
			}

		}

		if (found) {
			FacesMessage doneMessage = null;
			doneMessage = new FacesMessage("This Skill is already added to your Profile.", "Try with different Skill.");
			doneMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(doneMessage);
		}
	}

	public void specialtyDuplicationValidate(FacesContext context, UIComponent component, Object value) {

		int specialtyId = 0;
		try {
			specialtyId = Integer.parseInt(value.toString());
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage());
		}

		boolean found = Boolean.FALSE;

		for (UserClinicalQualification clinicalQualification : userProfileBean.getSelectedUserProfile()
				.getUserClinicalQualifications()) {
			if (specialtyId == clinicalQualification.getSubSpecialty().getSpecialtyId().intValue()) {
				found = Boolean.TRUE;
				break;
			}
		}

		if (found) {
			FacesMessage doneMessage = null;
			doneMessage = new FacesMessage("This Specialty is already added to your Profile.",
					"Try with different Specialty.");
			doneMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(doneMessage);
		}
	}

	/*
	 * LOAD DROPDOWN DATA
	 */

	public void loadSubSpecialties(AjaxBehaviorEvent event) {
		try {
			if (userProfileBean.getSelectedUserClinicalQualification() != null) {
				int selectedSpecialtyId = userProfileBean.getSelectedUserClinicalQualification().getSpecialty()
						.getSpecialtyId();

				
				UserProfileService userProfileService = managerService.getUserProfileService();
				userProfileBean.setSubSpecialties(userProfileService.getSpecialties(selectedSpecialtyId));
			}
		} catch (Exception exception) {
			// TODO: handle exception
			logger.error(exception.getMessage());
		}
	}

	public String convertToDate(Date date) {
		if (date == null)
			return "";
		return dateFormat.format(date);
	}

	public Specialty getSubSpeciality(UserProfileBean userProfileBean, int subSpecialtyId) {

		Specialty specialty = null;
		for (Specialty subSpecialty : userProfileBean.getSubSpecialties()) {
			if (subSpecialty.getSpecialtyId().intValue() == subSpecialtyId) {
				specialty = subSpecialty;
				break;
			}
		}
		return specialty;
	}

	public boolean isSubSpecialityAlreadyAdded(UserProfileBean userProfileBean, int subSpecialtyId) {

		boolean found = Boolean.FALSE;
		for (UserClinicalQualification clinicalQualification : userProfileBean.getSelectedUserProfile()
				.getUserClinicalQualifications()) {
			if (clinicalQualification.getSubSpecialty() != null
					&& clinicalQualification.getSubSpecialty().getSpecialtyId().intValue() == subSpecialtyId) {
				found = Boolean.TRUE;
				break;
			}
		}

		return found;
	}

	public boolean isSpecialityAlreadyAdded(UserProfileBean userProfileBean, int specialtyId) {

		boolean found = Boolean.FALSE;
		for (UserClinicalQualification clinicalQualification : userProfileBean.getSelectedUserProfile()
				.getUserClinicalQualifications()) {
			if (clinicalQualification.getSpecialty() != null
					&& clinicalQualification.getSpecialty().getSpecialtyId().intValue() == specialtyId) {
				found = Boolean.TRUE;
				break;
			}
		}

		return found;
	}

	public boolean isCustomSubSpecialityAlreadyAdded(UserProfileBean userProfileBean, int specialtyId,
			String customSubSpecialty) {

		boolean found = Boolean.FALSE;
		for (UserClinicalQualification clinicalQualification : userProfileBean.getSelectedUserProfile()
				.getUserClinicalQualifications()) {
			if (clinicalQualification.getCustomSpecialty() != null)
				if (clinicalQualification.getSpecialty().getSpecialtyId().intValue() == specialtyId
						&& clinicalQualification.getCustomSpecialty().equalsIgnoreCase(customSubSpecialty)) {
					found = Boolean.TRUE;
					break;
				}
		}
		return found;
	}

}