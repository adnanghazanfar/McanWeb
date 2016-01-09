package com.eamtar.mccn.util;

import javax.el.ELContext;
import javax.el.ValueExpression;
import javax.faces.context.FacesContext;

public class FacesUtil {
	
	public static Object getControllerObject(String controllerName, Class clazz) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ELContext elcontext = facesContext.getELContext();
		ValueExpression ve = facesContext
				.getApplication()
				.getExpressionFactory()
				.createValueExpression(elcontext, "#{" + controllerName + "}",
						clazz);
		return ve.getValue(elcontext);
	}

}
