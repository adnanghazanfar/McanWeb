package com.eamtar.mccn.util;

import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class CacheControlPhaseListener implements PhaseListener {
	private static final long serialVersionUID = -3590127743506500504L;
	private static final String CREATE_PAGE = "CREATE";
	private static final String UPDATE_PAGE = "UPDATE";
	private static final String REGISTER_PAGE = "REGISTER";

	static Logger logger = Logger.getLogger(CacheControlPhaseListener.class
			.getSimpleName());

	public PhaseId getPhaseId() {
		return PhaseId.RENDER_RESPONSE;
	}

	public void afterPhase(PhaseEvent event) {
		logger.info("After Jsf Phase ::: " + getPhaseId());
		String viewId = FacesContext.getCurrentInstance().getViewRoot()
				.getViewId();
		logger.info("View Id " + viewId);
	}

	public void beforePhase(PhaseEvent event) {
		logger.info("Before Jsf Phase ::: " + getPhaseId());
		FacesContext facesContext = event.getFacesContext();

		String viewId = FacesContext.getCurrentInstance().getViewRoot()
				.getViewId();
		if (viewId != null) {
			if (viewId.toUpperCase().contains(REGISTER_PAGE)
					|| viewId.toUpperCase().contains(CREATE_PAGE)
					|| viewId.toUpperCase().contains(UPDATE_PAGE)) {
				
				/*
				 * These Pages WOnt be Cached.. 
				 */
				
				HttpServletResponse response = (HttpServletResponse) facesContext
						.getExternalContext().getResponse();
				response.addHeader("Pragma", "no-cache");
				response.addHeader("Cache-Control", "no-cache");
				response.addHeader("Cache-Control", "no-store");
				response.addHeader("Cache-Control", "must-revalidate");
				response.setDateHeader("Expires", -1);
				
			}
		}
	}
}