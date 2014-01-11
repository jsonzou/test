package com.test.core.stripes.ext.validate;

import java.lang.annotation.Annotation;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

import net.sourceforge.stripes.config.Configuration;
import net.sourceforge.stripes.controller.ParameterName;
import net.sourceforge.stripes.validation.DefaultValidationMetadataProvider;
import net.sourceforge.stripes.validation.TypeConverter;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidationMetadata;

public class ValidationMetadataProviderSupport extends
		DefaultValidationMetadataProvider {

	@Override
	public Configuration getConfiguration() {
		// TODO Auto-generated method stub
		return super.getConfiguration();
	}

	@Override
	public ValidationMetadata getValidationMetadata(Class<?> beanType,
			ParameterName field) {
		// TODO Auto-generated method stub
		return super.getValidationMetadata(beanType, field);
		//return null;
	}

	@Override
	public Map<String, ValidationMetadata> getValidationMetadata(
			Class<?> beanType) {
		// TODO Auto-generated method stub
		return super.getValidationMetadata(beanType);
	}

	@Override
	public void init(Configuration configuration) throws Exception {
		// TODO Auto-generated method stub
		super.init(configuration);
	}

	@Override
	protected Map<String, ValidationMetadata> loadForClass(Class<?> arg0) {
		// TODO Auto-generated method stub
		JSONObject jv=new JSONObject();
		jv.put("field","uname");
		jv.put("required",true);
		jv.put("minlength",18);
		
		Validate validate=this.getValidate(jv);
		ValidationMetadata vm=new ValidationMetadata(jv.getString("field"),validate);
		return super.loadForClass(arg0);
	}
	
	
    public Validate getValidate(JSONObject jv){
    	 
    	return new Validate() {
			
			public Class<? extends Annotation> annotationType() {
				// TODO Auto-generated method stub
				return Validate.class;
			}
			
			public boolean trim() {
				// TODO Auto-generated method stub
				return false;
			}
			
			public boolean required() {
				// TODO Auto-generated method stub
				return false;
			}
			
			public String[] on() {
				// TODO Auto-generated method stub
				return null;
			}
			
			public double minvalue() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			public int minlength() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			public double maxvalue() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			public int maxlength() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			public String mask() {
				// TODO Auto-generated method stub
				return null;
			}
			
			public String label() {
				// TODO Auto-generated method stub
				return null;
			}
			
			public boolean ignore() {
				// TODO Auto-generated method stub
				return false;
			}
			
			public String field() {
				// TODO Auto-generated method stub
				return null;
			}
			
			public String expression() {
				// TODO Auto-generated method stub
				return null;
			}
			
			public boolean encrypted() {
				// TODO Auto-generated method stub
				return false;
			}
			
			public Class<? extends TypeConverter> converter() {
				// TODO Auto-generated method stub
				return null;
			}
		};
    }
	 
}
