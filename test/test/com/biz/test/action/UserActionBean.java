package com.biz.test.action;

import java.util.List;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.integration.spring.SpringBean;

import com.biz.test.entity.User;
import com.biz.test.service.IUserService;
import com.mwp.core.baseaction.ActionBeanSupport;

public class UserActionBean extends  ActionBeanSupport {
	private  final String LIST = "/page/user/index.html";
	private  final String VIEW = "/page/user/view.html";
	private  final String ADD = "/page/user/add.html";
	private  final String EDIT = "/page/user/edit.html";
	@SpringBean("userService")
	private IUserService userService;
	@DefaultHandler
	public Resolution list() {
		List<User> list=userService.list();
		 System.out.println(list.toString());
		return new ForwardResolution(LIST);
	}
	public Resolution view() {
		
		return new ForwardResolution(VIEW);
	}
	public Resolution add() {
		return new ForwardResolution(ADD);
	}
	
	public Resolution edit() {
		return new ForwardResolution(EDIT);
	}
 
}