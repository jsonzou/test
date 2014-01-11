

package com.test.core.commons.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.KeyValue;
import org.apache.commons.collections.Predicate;
import org.apache.commons.collections.keyvalue.DefaultKeyValue;
import org.apache.ibatis.reflection.MetaObject;


import com.alibaba.fastjson.JSONObject;

public class ColUtils {

	/***
	 * 转List
	 * 
	 * @param <T>
	 * @param objs
	 * @param key
	 * @param type
	 * @return
	 */
	public static <T> List<T> toList(List<?> objs, String key) {
		List<T> objList = null;
		if (CollectionUtils.isNotEmpty(objs)) {
			objList = new ArrayList<T>(objs.size());
			try {
				for (Object obj : objs) {

					MetaObject me = MetaObj.forObject(obj);
					@SuppressWarnings("unchecked")
					T objVal = (T) me.getValue(key);
					objList.add(objVal);
				}

				return objList;
			} catch (Exception e) {

				e.printStackTrace();
			}
		}
		return objList;

	}
	  @SuppressWarnings("unchecked")
	public static <T> List<T> toList(List<?> objs, String key, Class<T> type)
	    {

	        List<T> objList = null;

	        if (CollectionUtils.isNotEmpty(objs))
	        {
	            objList = new ArrayList<T>(objs.size());
	            try
	            {
	                for (Object obj : objs)
	                {
	                    objList.add((T) BeanUtils.getProperty(obj, key));
	                }

	                return objList;
	            }
	            catch (Exception e)
	            {

	                e.printStackTrace();
	            }
	        }
	        return objList;

	    }

	public static Object find(List<?> objs, final Object obj) {

		return CollectionUtils.find(objs, new Predicate() {

			@Override
			public boolean evaluate(Object arg0) {
				// TODO Auto-generated method stub
			
				return obj.equals(arg0);
			}

		});

	}

	/***
	 * 查询 key为field value为值
	 * 
	 * @param <T>
	 * @param objs
	 * @param key
	 * @param value
	 * @return
	 */
	public static Object find(List<?> objs, final String key, final Object value) {
		return CollectionUtils.find(objs, new Predicate() {
			@Override
			public boolean evaluate(Object object) {
				try {

					MetaObject me = MetaObj.forObject(object);
					if(me.getValue(key)==null)
						return false;
					
					if (me.getValue(key).equals(value)) {
						return true;
					} else {
						return false;
					}

				} catch (Exception e) {

					e.printStackTrace();
					return false;
				}
			}
		});

	}

	/***
	 * 查询具有Map里面key和value条件的数据
	 * 
	 * @param <T>
	 * @param objs
	 * @param key
	 * @param value
	 * @return
	 */
	public static Object find(List<?> objs, final Map<String,Object> param ) {
		return CollectionUtils.find(objs, new Predicate() {
			@Override
			public boolean evaluate(Object object) {
				try {
					
					MetaObject me = MetaObj.forObject(object);
					if(param!=null){
						for (String key:param.keySet()) {
							if (!me.getValue(key).equals(param.get(key))) {
								return false;
							}  
						}
						return true;
					}else{
						return false;
					}
				} catch (Exception e) {
					
					e.printStackTrace();
					return false;
				}
			}
		});

	}

	/***
	 * 查询 key为field value为值
	 * 
	 * @param <T>
	 * @param objs
	 * @param key
	 * @param value
	 * @return
	 */
	public static List<?> filter(List<?> objs, final String key,
			final Object value)

	{

		CollectionUtils.filter(objs, new Predicate() {
			@Override
			public boolean evaluate(Object object) {
				try {

					MetaObject me = MetaObj.forObject(object);
					if(me.getValue(key)==null){return false;}
					if (me.getValue(key).equals(value)) {
						return true;
					} else {
						return false;
					}

				} catch (Exception e) {

					e.printStackTrace();
					return false;
				}
			}
		});
		return objs;

	}
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static List<JSONObject> toJsonList(List<?> objList) {
		List<JSONObject> resList = null;
		if (CollectionUtils.isNotEmpty(objList)) {
			resList = new ArrayList<JSONObject>();
			for (Object object : objList) {
				JSONObject json = null;
				if (object instanceof java.util.Map) {
					json = new JSONObject((Map) object);

				}
				if (object instanceof JSONObject) {
					json = (JSONObject) object;

				} else {
					json=(JSONObject) JSONObject.toJSON(object);
				}

				resList.add(json);
			}
		}
		return resList;
	}
	 public List<KeyValue> ObjectToKeyValue(List<Object> objList, String keyName, String valName) throws Exception
	    {
	        List<KeyValue> keyValues = null;

	        if (CollectionUtils.isNotEmpty(objList))
	        {
	            keyValues = new ArrayList<KeyValue>(objList.size());
	            for (Object object : objList)
	            {
	                MetaObject metaObject = MetaObj.forObject(object);
	                keyValues.add(new DefaultKeyValue(metaObject.getValue(keyName).toString(), metaObject.getValue(valName).toString()));
	            }
	        }
	        return keyValues;
	    }
	 
	 @SuppressWarnings({ "unchecked", "rawtypes" })
	public  static List  sort(List objList,final String orderKey){
		 Comparator compare=new Comparator() {

			@Override
			public int compare(Object o1, Object o2) {
				MetaObject m1 = MetaObj.forObject(o1);
				MetaObject m2 = MetaObj.forObject(o2);
				return m1.getValue(orderKey).toString().compareTo(m2.getValue(orderKey).toString());
			}
			 
			 
			 
		};
		 
		 Collections.sort(objList,compare);
		 return objList;
		
	 }
}
