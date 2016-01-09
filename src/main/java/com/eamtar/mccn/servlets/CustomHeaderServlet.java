package com.eamtar.mccn.servlets;

import java.util.Random;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**   
 * @author ADNAN GHAZANFAR
 * @email  adnan.ghazanfar@yahoo.com
 * @since  19 SEP, 2014
 * @version 1.0
 * @Description Cookie Creation and Deletion Code
*/
public class CustomHeaderServlet {

	public static final String MCCN_TOKEN_ID = "MccnTokenCode";
	private static Logger logger = Logger.getLogger(CustomHeaderServlet.class
			.getSimpleName());

	public static void removeCookie(HttpServletResponse response, String name,
			HttpServletRequest request) {
		logger.info("Remove cookie " + name + " from customHeader");
		Cookie cookie = new Cookie(name, "");
		cookie.setPath("/");
		cookie.setMaxAge(0);
		cookie.setComment("EXPIRING COOKIE at " + System.currentTimeMillis());
		response.addCookie(cookie);
	}

	public synchronized static String getUniqueName() {
		Random ran = new Random();
		String random = "" + ran.nextInt(9) + ran.nextInt(9) + ran.nextInt(9)
				+ ran.nextInt(9);
		if (random.length() > 4)
			random = random.substring(0, 4);
		String fileUniqueName = random + "-" + System.nanoTime();
		return fileUniqueName;
	}

	public static String getCookieValue(HttpServletRequest request, String name) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie != null && name.equals(cookie.getName())) {
					return cookie.getValue();
				}
			}
		}
		return null;
	}

}
