package com.eamtar.mccn.faces.custom.scope;

/*
* @(#) SpringViewScoped.java Copyright Emirates Group.
* All Rights Reserved. This Software is the proprietary information of Emirates
* Group Use is subject to License terms.
*/


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;

/*
 *   This class is used to ...
 *   @author          :	S426024 
 *   @since			  : 273 Nov 2014 
 *   @see     		  :	com.emirates.health.custom.scope.SpringViewScoped
 */
@Qualifier
@Scope("view")
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface SpringViewScoped {

}

