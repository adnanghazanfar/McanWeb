package com.eamtar.mccn.faces.custom.scope;

/*
* @(#) ViewScopeViewMapListener.java Copyright Emirates Group.
* All Rights Reserved. This Software is the proprietary information of Emirates
* Group Use is subject to License terms.
*/


import java.lang.ref.WeakReference;

import javax.faces.component.UIViewRoot;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.PreDestroyViewMapEvent;
import javax.faces.event.SystemEvent;
import javax.faces.event.ViewMapListener;

import org.apache.log4j.Logger;

import com.eamtar.mccn.util.AccessFilter;

/*
 *   This class is used to ...
 *   @author          :	s767388 Sajith Puravankara
 *   @since			  : 3 Jul 2013 16:49:09
 *   @see     		  :	com.emirates.dena.web.custom.scopes.ViewScopeViewMapListener
 */
public class ViewScopeViewMapListener implements ViewMapListener {

	private static Logger LOG = Logger.getLogger(ViewScopeViewMapListener.class
			.getSimpleName());
    private String name;
    private Runnable callback;
    private boolean callbackCalled = false;
    private WeakReference<UIViewRoot> uiViewRootWeakReference;
    private ViewScope viewScope;
	
    
    
	public ViewScopeViewMapListener(UIViewRoot root, String name, Runnable callback, ViewScope viewScope) {
		this.name = name;
		this.callback = callback;
		this.uiViewRootWeakReference = new WeakReference<UIViewRoot>(root);
		this.viewScope = viewScope;
		
	}
	
	@Override
	public boolean isListenerForSource(Object source) {
		// TODO Auto-generated method stub
		return (source == uiViewRootWeakReference.get());
	}

	@Override
	public void processEvent(SystemEvent event) throws AbortProcessingException {
		// TODO Auto-generated method stub
		if(event instanceof PreDestroyViewMapEvent){
			LOG.debug("Going call callback for bean {}"+ name);
			doCallback();
			//TODO
            viewScope.clearFromListener(this);
		}
	}
	
	public synchronized void doCallback(){
		LOG.debug("Going call callback for bean {}"+ name);
		if (!callbackCalled) {
            try {
                callback.run();
            } finally {
                callbackCalled = true;
            }
        }
	}
	
	public boolean checkRoot() {
        if (uiViewRootWeakReference.get() == null) {
            doCallback();
            return true;
        }
        return false;
    }

}

