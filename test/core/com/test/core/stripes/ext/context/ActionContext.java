package com.test.core.stripes.ext.context;

import java.util.List;

import java.util.Locale;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.action.Message;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.exception.SourcePageNotFoundException;
import net.sourceforge.stripes.validation.ValidationErrors;

public class ActionContext extends ActionBeanContext{

	@Override
	public String getEventName() {
		// TODO Auto-generated method stub
		return super.getEventName();
	}

	@Override
	public Locale getLocale() {
		// TODO Auto-generated method stub
		return super.getLocale();
	}

	@Override
	public List<Message> getMessages() {
		// TODO Auto-generated method stub
		return super.getMessages();
	}

	@Override
	public List<Message> getMessages(String arg0) {
		// TODO Auto-generated method stub
		return super.getMessages(arg0);
	}

	@Override
	public HttpServletRequest getRequest() {
		// TODO Auto-generated method stub
		return super.getRequest();
	}

	@Override
	public HttpServletResponse getResponse() {
		// TODO Auto-generated method stub
		return super.getResponse();
	}

	@Override
	public ServletContext getServletContext() {
		// TODO Auto-generated method stub
		return super.getServletContext();
	}

	@Override
	public String getSourcePage() {
		// TODO Auto-generated method stub
		return super.getSourcePage();
	}

	@Override
	public Resolution getSourcePageResolution()
			throws SourcePageNotFoundException {
		// TODO Auto-generated method stub
		return super.getSourcePageResolution();
	}

	@Override
	public ValidationErrors getValidationErrors() {
		// TODO Auto-generated method stub
		return super.getValidationErrors();
	}

	@Override
	public void setEventName(String eventName) {
		// TODO Auto-generated method stub
		super.setEventName(eventName);
	}

	@Override
	public void setRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		super.setRequest(request);
	}

	@Override
	public void setResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		super.setResponse(response);
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		// TODO Auto-generated method stub
		super.setServletContext(servletContext);
	}

	@Override
	public void setValidationErrors(ValidationErrors validationErrors) {
		// TODO Auto-generated method stub
		super.setValidationErrors(validationErrors);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
 
}