package com.test.core.stripes.ext.resolver;

import java.util.List;
import java.util.Set;

import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.controller.NameBasedActionResolver;

public class ActionResolver extends NameBasedActionResolver {
	@Override
	protected List<String> getFindViewAttempts(String arg0) {
		// TODO Auto-generated method stub
		return super.getFindViewAttempts(arg0);
	}

	@Override
	protected Resolution findView(String arg0) {
		// TODO Auto-generated method stub
		return super.findView(arg0);
	}

	@Override
	protected List<String> getActionBeanSuffixes() {
		// TODO Auto-generated method stub
		return super.getActionBeanSuffixes();
	}

	@Override
	protected Set<String> getBasePackages() {
		// TODO Auto-generated method stub
		return super.getBasePackages();
	}

	@Override
	protected String getBindingSuffix() {
		// TODO Auto-generated method stub
		//return super.getBindingSuffix();
		return "";
	}

	@Override
	protected String getUrlBinding(String arg0) {
		// TODO Auto-generated method stub
		String urlBinding=super.getUrlBinding(arg0);
		String actionName=urlBinding.substring(urlBinding.lastIndexOf("/")+1);
		String modelPath=urlBinding.substring(0,urlBinding.lastIndexOf("/")+1);
		System.out.println("urlBinding:"+modelPath+firstLeterToLowerCase(actionName));
		return modelPath+firstLeterToLowerCase(actionName);
	}
	/**
	 * 首字母小写
	 * @param str
	 * @return
	 */
	private String firstLeterToLowerCase(String str){
		String firstLeter=str.charAt(0)+"";
		return firstLeter.toLowerCase()+str.substring(1);
	}

	@Override
	protected ActionBean handleActionBeanNotFound(ActionBeanContext context,
			String urlBinding) {
		// TODO Auto-generated method stub
		return super.handleActionBeanNotFound(context, urlBinding);
	}

}