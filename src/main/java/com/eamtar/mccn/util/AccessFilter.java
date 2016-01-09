package com.eamtar.mccn.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.eamtar.mccn.faces.managedbean.UserBean;
import com.eamtar.mccn.servlets.CustomHeaderServlet;



/**
 * @author adnan ghazanfar
 * @email adnan.ghazanfar@yahoo.com
 * @since 20 SEP, 2014
 * @version 1.0
 */
public class AccessFilter implements Filter {

	private static final String ADMIN_PAGES = "/admin/";
	private static final String PHYSICIAN_PAGES = "/physician/";
	private static final String LOGIN_PAGE = "/login.xhtml";

	private static Logger logger = Logger.getLogger(AccessFilter.class
			.getSimpleName());

	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		HttpSession session = request.getSession(true);

		String sessionTokenId = (String) session
				.getAttribute(CustomHeaderServlet.MCCN_TOKEN_ID);
		String cookieToken = CustomHeaderServlet.getCookieValue(request,
				CustomHeaderServlet.MCCN_TOKEN_ID);
		String requestedURL = request.getRequestURL().toString();

		UserBean userBean = (UserBean) session.getAttribute("userBean");

		if (requestedURL.contains(ADMIN_PAGES)) {
			if (!isLoggedIn(userBean) || !hasAdminAccess(userBean)
					|| !cookieMatches(sessionTokenId, cookieToken)) {
				clearSessionAttributes(session);
				response.sendRedirect(LOGIN_PAGE);
			}
		} else if (requestedURL.contains(PHYSICIAN_PAGES)) {
			if (!isLoggedIn(userBean) || !hasPhysicianAccess(userBean)
					|| !cookieMatches(sessionTokenId, cookieToken)) {
				clearSessionAttributes(session);
				response.sendRedirect(LOGIN_PAGE);
			}
		}

		chain.doFilter(req, res);
	}

	private boolean cookieMatches(String sessionTokenId, String cookieToken) {
		if (sessionTokenId != null && cookieToken != null
				&& sessionTokenId.equalsIgnoreCase(cookieToken))
			return Boolean.TRUE;
		return Boolean.FALSE;
	}

	private boolean isLoggedIn(UserBean userBean) {
		if (userBean != null && userBean.getEmail() != null
				&& userBean.isLoggedIn())
			return Boolean.TRUE;
		return Boolean.FALSE;
	}

	private boolean hasAdminAccess(UserBean userBean) {
		if (userBean != null && userBean.getEmail() != null
				&& userBean.isLoggedIn()
				&& userBean.getUserType().intValue() == ProjectConstant.ADMIN)
			return Boolean.TRUE;
		return Boolean.FALSE;
	}
	
	private boolean hasPhysicianAccess(UserBean userBean) {
		if (userBean != null && userBean.getEmail() != null
				&& userBean.isLoggedIn()
				&& userBean.getUserType().intValue() == ProjectConstant.PHYSICIAN)
			return Boolean.TRUE;
		return Boolean.FALSE;
	}
	
	private void clearSessionAttributes(HttpSession session) {
		session.removeAttribute("userBean");
		session.removeAttribute(CustomHeaderServlet.MCCN_TOKEN_ID);
		
	}

	public void init(FilterConfig config) throws ServletException {

		String testParam = config.getInitParameter("test-param");
		logger.info("Test Param ::: " + testParam);
	}

	public void destroy() {
		logger.info("ACCESS FILTER DESTROY");
	}
}
