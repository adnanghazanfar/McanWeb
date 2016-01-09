package com.eamtar.mccn.service;

import javax.servlet.ServletContext;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class ServiceManagerFactory {
	
	private static ManagerService managerService = null;
	private static WebApplicationContext webApplicationContext=null ;
	private ServiceManagerFactory(){
		
	}


	public static ManagerService getServiceManager(ServletContext servletContext){
		
		if(managerService == null)
		{
			if(webApplicationContext == null)
				webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
			managerService = (ManagerService) webApplicationContext.getBean("managerService");
		}
		
		return managerService;
	}
	
}
