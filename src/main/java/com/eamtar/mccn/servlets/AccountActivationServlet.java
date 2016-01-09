package com.eamtar.mccn.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;

import com.eamtar.mccn.model.User;
import com.eamtar.mccn.service.ManagerService;
import com.eamtar.mccn.service.ServiceManagerFactory;
import com.eamtar.mccn.service.UserService;
import com.eamtar.mccn.util.ProjectConstant;

public class AccountActivationServlet extends HttpServlet {
	private static Logger logger = Logger
			.getLogger(AccountActivationServlet.class.getSimpleName());
	private static final long serialVersionUID = 1L;
	private ManagerService managerService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AccountActivationServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		logger.info("\n\n\nINSIDE \n CLASS == AccountActivationServlet \n METHOD == doGet(); ");
		
		
		String encryptedUserId = request.getParameter("id");
		String encryptedEmail = request.getParameter("em");
		String todo = request.getRequestURI();
		
		logger.info("ACTION todo  = "+todo);

		if (encryptedUserId == null || encryptedEmail == null) {
			// invalid parameters
			request.getSession().setAttribute("responseMessage", "incmp_req");
			response.sendRedirect("login.xhtml");
		} else {
			// decrypt user id provided in parameter
			String decryptUserId = new String(
					Base64.decodeBase64(encryptedUserId));
			
			String decryptedEmail = new String(Base64.decodeBase64(encryptedEmail));
			try {
				
				managerService = ServiceManagerFactory
						.getServiceManager(getServletContext());
				UserService userService = managerService.getUserService();

				User user = userService.getById(Integer
						.parseInt(decryptUserId));
				
				if(user == null) {
					// invalid record
					request.getSession().setAttribute("responseMessage", "incmp_req");
					response.sendRedirect("login.xhtml");
				} else {
					//check email address provided in parameter and active field
					if(user.getEmailAddress().equals(decryptedEmail) && user.getStatus() == ProjectConstant.STATUS_INACTIVE) {
						request.getSession().setAttribute("responseMessage", "success_active");
						user.setStatus(ProjectConstant.STATUS_ACTIVE);
						userService.update(user);
						response.sendRedirect("login.xhtml");
					} else {
						request.getSession().setAttribute("responseMessage", "incmp_req");
						response.sendRedirect("login.xhtml");
					}
				}
			} catch (Exception ex) {
				logger.error("User id is invalid :: Parsing error ", ex);
			}
		}
		
		logger.info("EXITING THIS METHOD \n\n\n");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
