package com.eamtar.mccn.faces.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.primefaces.event.RowEditEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.eamtar.mccn.email.EmailBean;
import com.eamtar.mccn.email.EmailService;
import com.eamtar.mccn.faces.managedbean.RegistrationBean;
import com.eamtar.mccn.faces.managedbean.UserBean;
import com.eamtar.mccn.model.User;
import com.eamtar.mccn.model.UserAddress;
import com.eamtar.mccn.model.UserProfile;
import com.eamtar.mccn.service.ManagerService;
import com.eamtar.mccn.service.UserService;
import com.eamtar.mccn.servlets.CustomHeaderServlet;
import com.eamtar.mccn.util.ProjectConstant;


/**
 * @author ADNAN GHAZANFAR
 * @email adnan.ghazanfar@yahoo.com
 * @since 01 JULY, 2014
 */
@Component(value = "userController")
@Scope("request")
public class UserController extends AbstractController {

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
	private final String NO_REDIRECTION = null;
	private final String OUTCOME_BASIC_SEARCH = "/search/basic.xhtml?faces-redirect=true";
	private final String OUTCOME_INDEX_PAGE = "/index.xhtml?faces-redirect=true";
	
	@Autowired
	private ManagerService managerService;
	@Autowired
	private RegistrationBean registrationBean;
	@Autowired
	private UserBean userBean;

	public void resetRegistraionBean() {
		try {
			User user = new User();
			UserProfile userProfile = new UserProfile();
			registrationBean.setUser(user);
			registrationBean.setUserProfile(userProfile);
		} catch (Exception exception) {
			// TODO: handle exception
			logger.error(exception.getMessage());
		}
	}

	public String doLogin() {
		FacesMessage doneMessage = null;
		String outcome = null;
		UserService userService = managerService.getUserService();
		User user = userService.findUser(userBean.getEmail(), userBean.getPassword());
		if (user != null) {

			if (user.getStatus().intValue() == 1) {
				userBean.setUser(user);
				userBean.setLoggedIn(true);
				userBean.setEmail(user.getEmailAddress());
				userBean.setUserId(user.getUserId());
				userBean.setUserType(user.getUserType());

				// CREATE COOKIE ..
				addTokens(getRequest(), getResponse());

				if (user.getUserType().intValue() == ProjectConstant.ADMIN)
					outcome = "admin";
				else if (user.getUserType().intValue() == ProjectConstant.PHYSICIAN)
					outcome = "physician";
				else if (user.getUserType().intValue() == ProjectConstant.USER)
					outcome = "user";
				else {
					outcome = "failure";
				}
			} else {
				doneMessage = new FacesMessage(
						FacesMessage.SEVERITY_ERROR,
						"Your account is currently de-active, Please check your email if it is a new account or consult system admin.",
						null);
				FacesContext.getCurrentInstance().addMessage(null, doneMessage);
				outcome = "failure";
			}

		} else {
			doneMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid email or password. Please Try again.", null);
			FacesContext.getCurrentInstance().addMessage(null, doneMessage);
			outcome = "failure";
		}
		return outcome;
	}

	private synchronized static void addTokens(HttpServletRequest request, HttpServletResponse response) {
		if (request.getSession(false).getAttribute(CustomHeaderServlet.MCCN_TOKEN_ID) == null) {
			logger.info("Add cookie:");
			String value = CustomHeaderServlet.getUniqueName();
			addCookie(CustomHeaderServlet.MCCN_TOKEN_ID, value, response);
			request.getSession(false).setAttribute(CustomHeaderServlet.MCCN_TOKEN_ID, value);
		}
	}

	private synchronized static void addCookie(String name, String value, HttpServletResponse response) {

		Cookie cookie = new Cookie(name, value);
		if (name.equalsIgnoreCase(CustomHeaderServlet.MCCN_TOKEN_ID))
			cookie.setMaxAge(86400 * 3);
		else
			cookie.setMaxAge(86400 * 30 * 6);
		cookie.setPath("/");
		cookie.setSecure(false);
		response.addCookie(cookie);

	}

	public String doLogout() {
		FacesMessage doneMessage = null;
		String outcome = null;
		if (userBean != null) {
			userBean.setEmail(null);
			userBean.setUserId(null);
			userBean.setUserType(null);
			userBean = null;
			invalidateSession();
			outcome = OUTCOME_INDEX_PAGE;
		} else {
			doneMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Failed to Logout Please try Again.", null);
			FacesContext.getCurrentInstance().addMessage(null, doneMessage);
			outcome = "failure";
		}
		return outcome;
	}

	private void resetValues() {
		userBean.setEmail(null);
		userBean.setUserId(null);
		userBean.setUserType(null);
		userBean.setLoggedIn(false);
		userBean.setUser(null);
	}

	public String searchConsultant() {
		String outcome = null;
		if (userBean.getSearhString() != null && userBean.getSearhString().length() > 0)
			outcome = OUTCOME_BASIC_SEARCH;
		return outcome;
	}

	public String addConfirmedUser() {
		boolean added = false; // actual application may fail to add user
		FacesMessage doneMessage = null;
		String outcome = null;
		if (!registrationBean.getUser().getPassword().equals(registrationBean.getUser().getcPassword())) {
			doneMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Passwords don't match.",
					"Passwords don't match.");
			// throw new ValidatorException(doneMessage);
		} else {

			// add user code here ...

			User user = registrationBean.getUser();
			user.setStatus(ProjectConstant.STATUS_INACTIVE);
			user.setUserType(ProjectConstant.PHYSICIAN);
			Date currentdate = new Date();
			user.setCreatedDate(currentdate);
			user.setModifiedDate(currentdate);

			UserProfile userProfile = registrationBean.getUserProfile();
			userProfile.setTotalExperience(0);
			userProfile.setProfileRating(5);
			UserService userService = managerService.getUserService();
			added = userService.saveUserComplete(user, userProfile);

			String requestTimeStr = convertToDate(currentdate);
			String encryptedUserId = Base64.encodeBase64String(user.getUserId().toString().getBytes());
			String encryptedEmailId = Base64.encodeBase64String(user.getEmailAddress().toString().getBytes());
			String encryptedRequestDate = Base64.encodeBase64String(requestTimeStr.getBytes());
			String encryptedSessionId = Base64.encodeBase64String(getSessionId().getBytes());

			String url = getRequest().getScheme() + "://" + getRequest().getServerName() + "/verification?id="
					+ encryptedUserId + "&em=" + encryptedEmailId + "&rq=" + encryptedRequestDate + "&sid="
					+ encryptedSessionId;

			EmailService emailService = managerService.getEmailService();
			EmailBean registerEmailBean = EmailBean.getRegistrationEmailBean(user.getEmailAddress(),
					user.getcPassword(), url);
			emailService.sendMail(registerEmailBean);

			if (added) {
				outcome = "success";
				doneMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Your account has been created, please check your email for an activation link.", null);
			} else {
				outcome = "success";
				doneMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Database Error while creating new user",
						null);
			}

		}
		FacesContext.getCurrentInstance().addMessage(null, doneMessage);
		return outcome;
	}

	public void confirmPasswordValidate(FacesContext context, UIComponent component, Object value) {
		String password = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
				.get("password");
		if (!password.equals(value.toString())) {
			FacesMessage doneMessage = null;
			doneMessage = new FacesMessage("Passwords don't match.", "Passwords don't match.");
			doneMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(doneMessage);
		}
	}

	public void emailDuplicationValidate(FacesContext context, UIComponent component, Object value) {

		UserService userService = managerService.getUserService();

		if (userService.isUserExists(value.toString())) {
			FacesMessage doneMessage = null;
			doneMessage = new FacesMessage("User with this Email already exists.", "Try with different Email.");
			doneMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(doneMessage);
		}
	}

	public void onEdit(RowEditEvent event) {
		UserAddress address = (UserAddress) event.getObject();
		UserService userService = managerService.getUserService();
		// userService.update(address);
		FacesMessage doneMessage = new FacesMessage("Address Edited");
		FacesContext.getCurrentInstance().addMessage(null, doneMessage);
	}

	public void onCancel(RowEditEvent event) {
		FacesMessage doneMessage = new FacesMessage("upDatation Cancelled");
		FacesContext.getCurrentInstance().addMessage(null, doneMessage);
	}

	public void generateResponseMessage() {

		FacesMessage facesMessage = null;
		String responseMessage = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.remove("responseMessage");

		if (responseMessage != null) {
			if (responseMessage.equalsIgnoreCase("incmp_req")) {
				facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Incomplete request. Please try again.",
						null);
			} else if (responseMessage.equalsIgnoreCase("bad_req")) {
				facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Bad request. Please try again.", null);
			} else if (responseMessage.equalsIgnoreCase("invalid_user")) {
				facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"Record against user is incorrect. Please try again.", null);
			} else if (responseMessage.equalsIgnoreCase("link_exp")) {
				facesMessage = new FacesMessage(FacesMessage.SEVERITY_WARN,
						"Link already expired. Input your email address below to send another link.", null);
			} else if (responseMessage.equalsIgnoreCase("inv_req")) {
				facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid request. Please try again.", null);
			} else if (responseMessage.equalsIgnoreCase("success_active")) {
				facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Your account is activated. You can login now.", null);
			}
			FacesContext.getCurrentInstance().addMessage(null, facesMessage);
		}

	}

	public String convertToDate(Date date) {
		if (date == null)
			return "";
		return dateFormat.format(date);
	}
}
