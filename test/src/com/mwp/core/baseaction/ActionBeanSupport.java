package com.mwp.core.baseaction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mwp.core.commons.utils.ReflectUtil;
import com.mwp.core.freemarker.FreemarkerServletSupport;

import freemarker.ext.servlet.FreemarkerServlet;

import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;

public abstract class ActionBeanSupport implements ActionBean {
	private ActionBeanContext actionBeanContext;
	protected HttpServletRequest request;
	protected HttpServletResponse response;

	public ActionBeanContext getContext() {
		return actionBeanContext;
	}

	public void setContext(ActionBeanContext actionBeanContext) {
		this.actionBeanContext = actionBeanContext;
		this.request=actionBeanContext.getRequest();
		this.response=actionBeanContext.getResponse();
	}
}