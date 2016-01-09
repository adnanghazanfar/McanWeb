package com.eamtar.mccn.faces.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.faces.event.AjaxBehaviorEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.eamtar.mccn.faces.managedbean.SearchBean;
import com.eamtar.mccn.faces.model.SearchDTO;
import com.eamtar.mccn.faces.model.UserProfileDTO;
import com.eamtar.mccn.model.User;
import com.eamtar.mccn.model.UserProfile;
import com.eamtar.mccn.service.ManagerService;
import com.eamtar.mccn.service.ServiceManagerFactory;
import com.eamtar.mccn.service.UserProfileService;
import com.eamtar.mccn.service.UserService;
import com.eamtar.mccn.util.ProjectConstant;

@Component(value = "searchController")
@Scope("request")
public class SearchController extends AbstractController {

	private final String ERROR_MESSAGE_EXCEPTION = "An Exception has occured please see logs.";
	private final String NO_REDIRECTION = null;
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
	
	@Autowired
	private SearchBean searchBean;
	@Autowired
	private ManagerService managerService;	
	
	public void initializeValues() {
		try {
			if (!searchBean.isIntialized()) {

				// SEARCH DTO INJECTION
				User userDTO = new User();
				searchBean.setUserDTO(userDTO);
				SearchDTO searchDTO = new SearchDTO();
				searchBean.setSearchDTO(searchDTO);

				searchBean.setIntialized(Boolean.TRUE);
			}

		} catch (Exception exception) {
			logger.error(exception.getMessage());
		}
	}

	public void loadQuickViewUserProfile(User selectedUser) {

		String message = null;
		try {
			UserProfileService userProfileService = managerService.getUserProfileService();
			UserProfile selectedUserProfile = userProfileService.getQuickViewUserProfileById(selectedUser.getUserId());
			searchBean.setUserProfileDTO(UserProfileDTO.convertToFacesObj(selectedUserProfile, Boolean.TRUE));

		} catch (Exception exception) {
			message = ERROR_MESSAGE_EXCEPTION;
			logger.error(exception.getMessage());
		}

		addMessageToFacesContext(message);
	}

	public void loadCompleteUserProfile() {
		String message = null;
		try {

			String todo = getParamInString("todo");
			if (todo != null && todo.equalsIgnoreCase(ProjectConstant.VIEW_COMPLETE_PROFILE)) {
				searchBean.setUserExists(Boolean.TRUE);

				String selectedUserIdStr = getParamInString("userId");
				Integer selectedUserId = null;
				if (selectedUserIdStr != null) {
					selectedUserId = Integer.parseInt(selectedUserIdStr);
				} else {
					message = "Please Select a User.";
					addMessageToFacesContext(message);
					searchBean.setUserExists(Boolean.FALSE);
					return;
				}

				UserService userService = managerService.getUserService();
				UserProfileService userProfileService = managerService.getUserProfileService();

				User selectedUser = userService.getById(selectedUserId);
				if (selectedUser != null && selectedUser.getUserType().intValue() == ProjectConstant.PHYSICIAN) {

					UserProfileDTO selectedUserProfile = userProfileService
							.getCompleteUserProfileDTOById(selectedUserId);
					searchBean.setUserProfileDTO(selectedUserProfile);
					selectedUserProfile = null;
				} else {
					message = "Invalid User Selected.";
					logger.error("/ViewUserProfile.xhtml?" + selectedUserIdStr);
					addMessageToFacesContext(message);
					searchBean.setUserExists(Boolean.FALSE);
					return;
				}
			}

		} catch (Exception exception) {
			message = ERROR_MESSAGE_EXCEPTION;
			logger.error(exception.getMessage());
		}

		addMessageToFacesContext(message);
	}

	public void searchUsers() {

		String message = null;
		try {
			User userDTO = searchBean.getUserDTO();
			SearchDTO searchDTO = searchBean.getSearchDTO();

			userDTO.setStatus(ProjectConstant.STATUS_ACTIVE);
			userDTO.setUserType(ProjectConstant.PHYSICIAN);

			UserProfileService userProfileService = managerService.getUserProfileService();

			List<UserProfile> users = userProfileService.findUserViaCriteria(userDTO, searchDTO);
			searchBean.setUsers(users);

		} catch (Exception exception) {
			message = ERROR_MESSAGE_EXCEPTION;
			logger.error(exception.getMessage());
		}

		addMessageToFacesContext(message);

	}

	public void loadSubSpecialties(AjaxBehaviorEvent event) {
		try {
			Integer selectedSpecialtyId = searchBean.getSearchDTO().getSpecialtyId();

			if (selectedSpecialtyId != null) {
				UserProfileService userProfileService = managerService.getUserProfileService();
				searchBean.setSubSpecialties(userProfileService.getSpecialties(selectedSpecialtyId));
			} else {
				searchBean.setSubSpecialties(null);
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

	public String getLimitedText(String data) {
		if (data == null)
			return "";
		if (data.length() > 100)
			data = data.substring(0, 100).concat("...");
		return data;
	}

}
