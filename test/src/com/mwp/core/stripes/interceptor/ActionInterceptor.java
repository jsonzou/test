package com.mwp.core.stripes.interceptor;


import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.controller.ExecutionContext;
import net.sourceforge.stripes.controller.Interceptor;
import net.sourceforge.stripes.controller.Intercepts;
import net.sourceforge.stripes.controller.LifecycleStage;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Intercepts(LifecycleStage.EventHandling)
public class ActionInterceptor implements Interceptor{
    private static final Log logger = LogFactory.getLog(Interceptor.class);
    public Resolution intercept(ExecutionContext context) throws Exception {
        HttpServletRequest request = context.getActionBeanContext().getRequest();
        String path = request.getServletPath();
        Method m = context.getHandler();
        if(!m.isAnnotationPresent(DefaultHandler.class)){
            path = path + "?" + m.getName();
        }
        logger.debug("url:"+path);
        ActionBean actionBean = context.getActionBean();
        logger.debug("Action:"+actionBean.getClass().getName());
        logger.debug("Method:"+m.getName());
        
        
        boolean mtil = ServletFileUpload.isMultipartContent(request); 
        String requestType = request.getHeader("X-Requested-With");
        boolean isPageReq = StringUtils.isEmpty(requestType); 
        
         
        
        return context.proceed();
    }
    
    
}
