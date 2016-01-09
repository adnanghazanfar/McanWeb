package com.eamtar.mccn.faces.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.validator.ValidatorException;

import com.eamtar.mccn.faces.managedbean.CommonBean;
import com.eamtar.mccn.model.Specialty;
import com.eamtar.mccn.util.FacesUtil;
import com.eamtar.mccn.util.ManagedBean;

public class SpecialtyConverter implements Converter{
	 
	
	public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
		
		if (value == null) {
			return null;
		}
		
		try {
			Integer specialtyId = Integer.parseInt(value);
			if (specialtyId == null || specialtyId < 0) {
				FacesMessage doneMessage = null;
				doneMessage = new FacesMessage("Invalid Specialty.", null);
				doneMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
				FacesContext.getCurrentInstance().addMessage(null, doneMessage);
				throw new ValidatorException(doneMessage);
			} else {
				return getSpecialtyFromCommonBean(specialtyId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		

		return value;
	}

	public String getAsString(FacesContext facesContext, UIComponent component, Object value) {
		
		if (!(value instanceof Specialty)) {
            return null;
        }

       String skillIdStr =  String.valueOf(((Specialty) value).getSpecialtyId());
       return skillIdStr;
	}
	
	
	private Specialty getSpecialtyFromCommonBean(Integer specialtyId){
		CommonBean commonBean = (CommonBean)FacesUtil.getControllerObject(ManagedBean.CommonBean.getName(), CommonBean.class);
		Specialty specialty = commonBean.getSpecialtyMap().get(specialtyId);
		return specialty;
	}
	
}
