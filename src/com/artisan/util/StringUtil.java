package com.artisan.util;

/**
 * ???????????
 * @author Administrator
 *
 */
public class StringUtil {

	/**
	 * ?��???????
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str){
        return str == null || "".equals(str.trim());
	}
	
	/**
	 * ?��???????
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty(String str){
        return str != null && !"".equals(str.trim());
	}
}
