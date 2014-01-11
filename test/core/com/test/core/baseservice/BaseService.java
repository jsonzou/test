package com.test.core.baseservice;
import javax.annotation.Resource;

import com.test.core.basedao.IBaseDao;


 

public class BaseService {
	    protected IBaseDao baseDao;	    
	    @Resource(name="baseDao")
	    public void setBaseDao(IBaseDao baseDao) {
	        this.baseDao =baseDao;
	    }
}

