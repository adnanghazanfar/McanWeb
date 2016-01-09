package com.eamtar.mccn.util;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;
 
public class SessionListener implements HttpSessionListener {
    private int sessionCount = 0;
    private static Logger logger = Logger.getLogger(SessionListener.class
			.getSimpleName());
 
    public void sessionCreated(HttpSessionEvent event) {
        synchronized (this) {
            sessionCount++;
        }
 
        logger.info("Session Created: " + event.getSession().getId());
        logger.info("Total Sessions: " + sessionCount);
    }
 
    public void sessionDestroyed(HttpSessionEvent event) {
        synchronized (this) {
            sessionCount--;
        }
        String sessionId = event.getSession().getId();
        logger.info("Session Destroyed: " + sessionId);
        logger.info("Total Sessions: " + sessionCount);
    }
}