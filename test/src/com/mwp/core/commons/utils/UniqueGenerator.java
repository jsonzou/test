package com.mwp.core.commons.utils;

import java.util.concurrent.atomic.AtomicInteger;
import org.apache.commons.lang3.StringUtils;


/****
 * 
  * <b>Summary: </b>
  *      配置domain主键
  * <b>Remarks: </b>
  *      建议无意义主键为21位
 */
public class UniqueGenerator {
	private static AtomicInteger last16=new AtomicInteger(0);
	static public String getUnique() {

		long id = 0;
		long dt = System.currentTimeMillis();
			id = dt +last16.addAndGet(1);
			if (last16.get() > 99999) {
				last16.set(0);
			}
			return id + "";
		
			
			
	}

	synchronized static public String getUnique(int len) {
		return StringUtils.rightPad(getUnique(), len, '0');
	}

	public static void main(String args[]) {

		last16.addAndGet(1);
		System.out.println(last16.get());
	}
}
