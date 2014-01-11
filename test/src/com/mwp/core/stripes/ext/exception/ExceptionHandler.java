package com.mwp.core.stripes.ext.exception;
import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.controller.StripesConstants;
import net.sourceforge.stripes.exception.AutoExceptionHandler;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.fastjson.JSONObject;



public class ExceptionHandler implements AutoExceptionHandler{
    
    private static final Log logger = LogFactory.getLog(ExceptionHandler.class);
    
    public Resolution handleException(Throwable t, HttpServletRequest req, HttpServletResponse resp) {
    	t.printStackTrace();
    	logger.error(t);
        boolean mtil = ServletFileUpload.isMultipartContent(req);
        String requestType = req.getHeader("X-Requested-With");
        logger.debug(" requestType = " + requestType);
        
        boolean isPageReq = StringUtils.isEmpty(requestType);
        
        if(!isPageReq){
            return handleAjaxException(t, req, resp);
        }else if(mtil){
            return handleUploadException(t, req, resp);
        }else{
            ActionBean bean = (ActionBean) req.getAttribute(StripesConstants.REQ_ATTR_ACTION_BEAN);
            if(bean != null){
            	 try{
                     req.setAttribute("error_msg", ExceptionUtils.getMessage(t));
                 }catch(Throwable e){
                 	e.printStackTrace();
                    req.setAttribute("error_msg", e.getCause().getMessage());
                 }
            }else{
            	req.setAttribute("error_msg", "error!");
            }
            return new ForwardResolution("/pages/exception/error.html");
        }
    }
    
    private Resolution handleAjaxException(Throwable t, HttpServletRequest req, HttpServletResponse resp) {
        String errorMsg;
        try{
            errorMsg = ExceptionUtils.getMessage(t);
            logger.debug(errorMsg);
        }catch(Throwable e){
            errorMsg = e.getMessage();
        }
        JSONObject jsonData = new JSONObject();
        jsonData.put("type", "error");
        jsonData.put("msg", errorMsg);
        jsonData.put("code", "3");//异常代码
        logger.debug(" requestType = json");
        //String str = StrUtils.replace(jsonData.toJSONString(), "\"", "\'");
        
        return new StreamingResolution("application/json", jsonData.toJSONString());
    }
    
    private Resolution handleUploadException(Throwable t, HttpServletRequest req, HttpServletResponse resp) {
        String errorMsg;
        try{
            errorMsg = ExceptionUtils.getMessage(t);
        }catch(Throwable e){
            errorMsg = e.getMessage();
        }
        StringBuilder sb = new StringBuilder("<respond>");
        String key = StringEscapeUtils.escapeXml("type");
        sb.append("<").append(key).append(">");
        sb.append(StringEscapeUtils.escapeXml("error"));
        sb.append("</").append(key).append(">");
        
        String message = StringEscapeUtils.escapeXml("msg");
        sb.append("<").append(message).append(">");
        sb.append(StringEscapeUtils.escapeXml(errorMsg));
        sb.append("</").append(message).append(">");
        sb.append("</respond>");
        logger.debug(" requestType = upload");
        
        return new StreamingResolution("application/xml", sb.toString());
    }
}
