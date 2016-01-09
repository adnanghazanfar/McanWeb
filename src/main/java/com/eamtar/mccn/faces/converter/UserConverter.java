package com.eamtar.mccn.faces.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.validator.ValidatorException;
import javax.servlet.ServletContext;

import com.eamtar.mccn.model.User;
import com.eamtar.mccn.service.ManagerService;
import com.eamtar.mccn.service.ServiceManagerFactory;
import com.eamtar.mccn.service.UserService;

public class UserConverter implements Converter{
	 
	
	public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
		
		if (value == null) {
			return null;
		}
		
		try {
			Integer userId = Integer.parseInt(value);
			if (userId == null || userId < 0) {
				FacesMessage doneMessage = null;
				doneMessage = new FacesMessage("Invalid User.", null);
				doneMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
				FacesContext.getCurrentInstance().addMessage(null, doneMessage);
				throw new ValidatorException(doneMessage);
			} else {
				return getUserById(userId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		

		return value;
	}

	public String getAsString(FacesContext facesContext, UIComponent component, Object value) {
		
		if (!(value instanceof User)) {
            return null;
        }

       String userIdStr =  String.valueOf(((User) value).getUserId());
       return userIdStr;
	}
	
	
	private User getUserById(Integer userId){
		ManagerService managerService = ServiceManagerFactory.getServiceManager(getServletContext());
		UserService userService = managerService.getUserService();
		return userService.getById(userId);
	}
	
	public ServletContext getServletContext() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ServletContext servletContext = (ServletContext) facesContext
				.getExternalContext().getContext();
		return servletContext;
	}
	
}
