package com.eamtar.mccn.faces.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.validator.ValidatorException;

import com.eamtar.mccn.faces.managedbean.CommonBean;
import com.eamtar.mccn.model.Skill;
import com.eamtar.mccn.util.FacesUtil;
import com.eamtar.mccn.util.ManagedBean;

public class SkillConverter implements Converter{
	 
	
	public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
		
		if (value == null) {
			return null;
		}
		
		try {
			Integer skillId = Integer.parseInt(value);
			if (skillId == null || skillId < 0) {
				FacesMessage doneMessage = null;
				doneMessage = new FacesMessage("Invalid Skill.", null);
				doneMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
				FacesContext.getCurrentInstance().addMessage(null, doneMessage);
				throw new ValidatorException(doneMessage);
			} else {
				return getSkillFromCommonBean(skillId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		

		return value;
	}

	public String getAsString(FacesContext facesContext, UIComponent component, Object value) {
		
		if (!(value instanceof Skill)) {
            return null;
        }

       String skillIdStr =  String.valueOf(((Skill) value).getSkillId());
       return skillIdStr;
	}
	
	
	private Skill getSkillFromCommonBean(Integer skillId){
		CommonBean commonBean = (CommonBean)FacesUtil.getControllerObject(ManagedBean.CommonBean.getName(), CommonBean.class);
		Skill skill = commonBean.getSkillsMap().get(skillId);
		return skill;
	}
	
}
