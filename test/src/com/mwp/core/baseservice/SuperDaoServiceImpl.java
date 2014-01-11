/* 
 * All Rights Reserved 
 * 
 * @project  
 * @author  hrz
 *
 * Modification History: 
 **********************************************************
 * Date		  Author		    Comments
 * 2012-2-15	     hrz			     Create
 **********************************************************
 */

package com.mwp.core.baseservice;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mwp.core.basedao.BatchHander;
import com.mwp.core.basedao.IBaseDao;
import com.mwp.core.mybatis.plugin.paging.page.Page;


@Service("superDaoService")

public class SuperDaoServiceImpl implements SuperDaoService{
    
    private IBaseDao baseDao;
    
    @Resource
    public void setBaseDao(IBaseDao baseDao) {
        this.baseDao = baseDao;
    }
    
    public <T> T getMapper(Class<T> type) {
        return baseDao.getMapper(type);
    }
    
    @SuppressWarnings("rawtypes")
    public <T> int updateBatch(Class clazz, String upType, List<T> list) {
        return baseDao.updateBatch(clazz, upType, list);
    }
    
    public int updateBatasdch(Object obj, BatchHander batchHander) {
        return baseDao.updateBatch(obj, batchHander);
    }
    
    public <T> int updateBatch(String statement, List<T> list) {
        return baseDao.updateBatch(statement, list);
    }
    
    public Object selectOne(String statement, Object parameter) {
        return baseDao.selectOne(statement, parameter);
    }
    
    @SuppressWarnings("rawtypes")
    public List selectList(String statement, Object parameter) {
        return baseDao.selectList(statement, parameter);
    }
    
    @SuppressWarnings("rawtypes")
    public List selectByPage(String statement, Object parameter, Page page) {
        return baseDao.selectByPage(statement, parameter, page);
    }
    
    public int update(String statement, Object parameter) {
        return baseDao.update(statement, parameter);
    }
    
    public int delete(String statement, Object parameter) {
        return baseDao.delete(statement, parameter);
    }
    
    public <K, V> Map<K, V> selectMap(String statement, Object parameter, String key) {
        return baseDao.selectMap(statement, parameter, key);
    }
    public void execute(String sql) throws Exception{
        baseDao.execute(sql);
    }

	@Override
	public Object executeFunction(String funcName, Object params) {
		
	   try {
		return  baseDao.executeFunction(funcName, params);
	     } catch (Exception e) {
	   }
	   
	   return null;
	}
    
}
