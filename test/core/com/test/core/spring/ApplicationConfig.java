package com.test.core.spring;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.test.core.log.Log;

/**
 *
 * 
 */
public class ApplicationConfig  {
	private ApplicationContext applicationContext;
	private ApplicationConfig() {
		try {
			initConfig();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	static ApplicationConfig singleton;
	static{		
		try {
		singleton= new ApplicationConfig();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static ApplicationConfig getInstance() {
		return singleton;
	}

	private void initConfig() throws Exception {

	}



	public void destroy() {

	}

	public ApplicationContext setApplicationContext(ApplicationContext context) {

		return applicationContext = context;
	}

	public ApplicationContext getApplicationContext() {
		 
		if (applicationContext != null) {
			return applicationContext;
		} else {
			synchronized (this) {
				String appxmlpath = "spring/spring-all.xml";
				try {
					Log.getInstance(this.getClass()).info("加载: ", appxmlpath);
					applicationContext = new ClassPathXmlApplicationContext(
							appxmlpath);
				} catch (Exception e) {
					Log.getInstance(this.getClass()).info(e);
					throw new RuntimeException("加载SPRING失败");

				}

			}

		}
		 
		return applicationContext;

	}

	 
}
