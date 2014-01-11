package com.mwp.core.stripes.ext.contextfactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.config.Configuration;
import net.sourceforge.stripes.controller.DefaultActionBeanContextFactory;

public class ActionContextFactory extends DefaultActionBeanContextFactory {

	@Override
	protected Configuration getConfiguration() {
		// TODO Auto-generated method stub
		return super.getConfiguration();
	}

	@Override
	public ActionBeanContext getContextInstance(HttpServletRequest arg0,
			HttpServletResponse arg1) throws ServletException {
		// TODO Auto-generated method stub
		return super.getContextInstance(arg0, arg1);
	}

	@Override
	public void init(Configuration configuration) throws Exception {
		// TODO Auto-generated method stub
		super.init(configuration);
	}

	@Override
	protected void setConfiguration(Configuration configuration) {
		// TODO Auto-generated method stub
		super.setConfiguration(configuration);
	}

 
	 

}