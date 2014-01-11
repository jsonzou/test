package com.test.core.commons.utils;


import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;

import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
public class MetaObj   {
	

	
	  public static MetaObject forObject(Object object) {
		    if (object == null) {
		      return SystemMetaObject.NULL_META_OBJECT;
		    } else {
		      return MetaObject.forObject(object,new DefaultObjectFactory(),new DefaultObjectWrapperFactory());
		    }
		  }

}
