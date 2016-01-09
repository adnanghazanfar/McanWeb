package com.eamtar.mccn.faces.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.EditableValueHolder;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import  javax.faces.convert.Converter;
import javax.faces.validator.ValidatorException;


/**
 * @author ADNAN GHAZANFAR
 * @email  adnan.ghazanfar@yahoo.com
 * @since 01 JULY, 2014
 */
public class EmptyToNullConverter implements Converter{

	 public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
	        if (value == null || value.trim().length() == 0) {
	            if (component instanceof EditableValueHolder) {
	                ((EditableValueHolder) component).setSubmittedValue(null);
	            }
	            return -1;
	        }
	        
	        float val = Float.parseFloat(value);
	        
	        if(val < 0)
	        {
	        	FacesMessage doneMessage = null;
				doneMessage = new FacesMessage(
						"Invalid Value for Marks (Marks must be >= 0).",
						null);
				doneMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
				FacesContext.getCurrentInstance().addMessage(null, doneMessage);
				throw new ValidatorException(doneMessage);
	        }
	         
	        return value;
	    }

	    public String getAsString(FacesContext facesContext, UIComponent component, Object value) {
	        return (value == null) ? null : value.toString();
	    }

	
}
