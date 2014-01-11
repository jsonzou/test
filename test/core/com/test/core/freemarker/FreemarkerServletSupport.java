package com.test.core.freemarker;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.util.StringUtil;

import freemarker.cache.MultiTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.ext.servlet.FreemarkerServlet;
import freemarker.template.Template;
import freemarker.template.TemplateModel;

public class FreemarkerServletSupport extends FreemarkerServlet{
    
    private static final String CONTEXT_PATH     = "contextPath";
    
    private static final long   serialVersionUID = 41223567936673121L;
    
    @Override
    protected boolean preTemplateProcess(HttpServletRequest request, HttpServletResponse response, Template template, TemplateModel data)
            throws ServletException, IOException {
        request.setAttribute(CONTEXT_PATH, request.getContextPath());
        return super.preTemplateProcess(request, response, template, data);
    }
    
   @Override
    protected TemplateLoader createTemplateLoader(String templatePath) throws IOException {
         return super.createTemplateLoader(templatePath);
    }
}
