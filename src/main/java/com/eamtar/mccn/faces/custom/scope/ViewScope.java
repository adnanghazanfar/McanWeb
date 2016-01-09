package com.eamtar.mccn.faces.custom.scope;

/*
* @(#) ViewScope.java Copyright Emirates Group.
* All Rights Reserved. This Software is the proprietary information of Emirates
* Group Use is subject to License terms.
*/


import java.io.Serializable;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.PreDestroyViewMapEvent;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;

/*
 *   This class is used to ...
 *   @author          :	s767388 Sajith Puravankara
 *   @since			  : 3 Jul 2013 16:42:19
 *   @see     		  :	com.emirates.dena.web.custom.scopes.ViewScope
 */
public class ViewScope implements Scope, Serializable, HttpSessionBindingListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3185270071091375441L;
	
	private static Logger LOG = Logger.getLogger(ViewScope.class
			.getSimpleName());
    private final WeakHashMap<HttpSession, Set<ViewScopeViewMapListener>> sessionToListeners = new WeakHashMap<HttpSession, Set<ViewScopeViewMapListener>>();



	@Override
	public void valueBound(HttpSessionBindingEvent event) {
		// TODO Auto-generated method stub
		LOG.debug("Session event bound {} "+ event.getName());
	}

	//TODO
	@Override
	public void valueUnbound(HttpSessionBindingEvent event) {
		// TODO Auto-generated method stub
		LOG.debug("Session event unbound {}"+ event.getName());
        final Set<ViewScopeViewMapListener> listeners;
        synchronized (sessionToListeners) {
            if (sessionToListeners.containsKey(event.getSession())) {
                listeners = sessionToListeners.get(event.getSession());
                sessionToListeners.remove(event.getSession());
            } else {
                listeners = null;
            }
        }
        if (listeners != null) {
            for (ViewScopeViewMapListener listener : listeners) {
                listener.doCallback();
            }
        }
	}

	@Override
	public Object get(String name, ObjectFactory<?> objectFactory) {
		// TODO Auto-generated method stub
		Object retObject = null;
		if (FacesContext.getCurrentInstance().getViewRoot() != null) {
			Map<String, Object> viewMap = FacesContext.getCurrentInstance().getViewRoot().getViewMap();
			
			if(null != viewMap){
				
				synchronized (viewMap) {
		            if (viewMap.containsKey(name)) {
		            	retObject = viewMap.get(name);
		            	 LOG.debug("Going to return an existing bean '{}'"+ name);
		            } else {
		                LOG.debug("Creating bean {}"+ name);
		                Object object = objectFactory.getObject();
		                viewMap.put(name, object);

		                retObject = object;
		            }
		        }
			}
			
		}
		return retObject;
	}

	@Override
	public String getConversationId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void registerDestructionCallback(String name, Runnable callback) {
		// TODO Auto-generated method stub
		LOG.debug("registerDestructionCallback for bean {}"+ name);
        UIViewRoot viewRoot = FacesContext.getCurrentInstance().getViewRoot();
        ViewScopeViewMapListener listener = new ViewScopeViewMapListener(viewRoot, name, callback, this);

        viewRoot.subscribeToViewEvent(PreDestroyViewMapEvent.class, listener);

        HttpSession httpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        final Set<ViewScopeViewMapListener> sessionListeners;
        synchronized (sessionToListeners) {
            if (!sessionToListeners.containsKey(httpSession)) {
                sessionToListeners.put(httpSession, new HashSet<ViewScopeViewMapListener>());
            }
            sessionListeners = sessionToListeners.get(httpSession);
        }
        //noinspection SynchronizationOnLocalVariableOrMethodParameter
        synchronized (sessionListeners) {
            Set<ViewScopeViewMapListener> toRemove = new HashSet<ViewScopeViewMapListener>();
            for (ViewScopeViewMapListener viewMapListener : sessionListeners) {
                if (viewMapListener.checkRoot()) {
                    toRemove.add(viewMapListener);
                }
            }
            sessionListeners.removeAll(toRemove);
            sessionListeners.add(listener);
        }
        if (!FacesContext.getCurrentInstance().getExternalContext().getSessionMap().containsKey("sessionBindingListener")) {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("sessionBindingListener", this);
        }
	}

	@Override
	public Object remove(String arg0) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	@Override
	public Object resolveContextualObject(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	//TODO
	public void clearFromListener(ViewScopeViewMapListener listener) {
        LOG.debug("Removing listener from map");
        HttpSession httpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        if (httpSession != null) {
            synchronized (sessionToListeners) {
                if (sessionToListeners.containsKey(httpSession)) {
                    sessionToListeners.get(httpSession).remove(listener);
                }
            }
        }
    }

}

