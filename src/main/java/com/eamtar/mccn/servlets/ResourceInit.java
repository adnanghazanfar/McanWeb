package com.eamtar.mccn.servlets;

import javax.servlet.GenericServlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.log4j.Logger;

import com.eamtar.mccn.properties.PropertiesManager;

/**
 * This servlet initalizes the ResourceManager and configures the various
 * resources that it manages.
 */

public class ResourceInit extends GenericServlet {

	private static final long serialVersionUID = 1L;
	private static String WEB_DIR = null;
	private static Logger logger = Logger.getLogger(ResourceInit.class
			.getSimpleName());

	@Override
	public void init(final ServletConfig config) throws ServletException {
		super.init(config);
		try {
			logger.info("******* RESOURCE INTIALIZATION STARTS ********");
			
			WEB_DIR = this.getServletContext().getRealPath("/");
			try {
				String fname = PropertiesManager.DEFAULT_FILE_PATH;
				PropertiesManager.loadProperties(fname);
				
			} catch (final Exception e) {
				logger.error(ResourceInit.class.getName() + ": fatal error setting up resources");
				e.printStackTrace(System.err);
				throw new ServletException();
			}
			logger.info("******* RESOURCE INTIALIZATION ENDS ********");
		} catch (final Exception e) {
			System.err.println(SERVLET_NAME
					+ ": fatal error setting up resources");
			e.printStackTrace(System.err);
			throw new ServletException();
		}
	}
	
	public static String getWebDirectory() {
		return WEB_DIR;
	}

	@Override
	public void service(final ServletRequest req, final ServletResponse res)
			throws ServletException {
	}


	@Override
	public void destroy() {
		// tell the ResourceManager to cleanup and remove the resouces
		super.destroy();
	}


	@Override
	public String toString() {
		return SERVLET_NAME;
	}

	private static final String SERVLET_NAME = ResourceInit.class.getName();

}
