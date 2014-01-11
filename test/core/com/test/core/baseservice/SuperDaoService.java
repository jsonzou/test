/* 
 * All Rights Reserved 
 * 
 *
 * @author  hrz
 * @date    2012-2-15-上午10:28:58
 *
 * Modification History: 
 **********************************************************
 * Date		  Author		    Comments
 * 2012-2-15	     hrz			     Create
 **********************************************************
 */

package com.test.core.baseservice;

import java.util.List;
import java.util.Map;

import com.test.core.basedao.BatchHander;
import com.test.core.mybatis.plugin.paging.page.Page;

/**
 * @author hrz
 * @date 2012-2-15-上午10:28:58
 */
public interface SuperDaoService{
    
    public <T> T getMapper(Class<T> type);
    
    @SuppressWarnings("rawtypes")
    public <T> int updateBatch(Class clazz, String upType, List<T> list);
    
    public int updateBatasdch(Object obj, BatchHander batchHander);
    
    public <T> int updateBatch(String statement, List<T> list);
    
    public Object selectOne(String statement, Object parameter);
    
    @SuppressWarnings("rawtypes")
    public List selectList(String statement, Object parameter);
    
    @SuppressWarnings("rawtypes")
    public List selectByPage(String statement, Object parameter, Page page);
    
    public int update(String statement, Object parameter);
    
    public int delete(String statement, Object parameter);
    
    public <K, V> Map<K, V> selectMap(String statement, Object parameter, String key);
    
    
    public void execute(String sql) throws Exception;
    
    public Object executeFunction(String funcName, Object params)  ;
}
