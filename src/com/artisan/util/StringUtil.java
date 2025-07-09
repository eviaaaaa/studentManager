package com.artisan.util;

/**
 * ???????????
 * @author Administrator
 *
 */
public class StringUtil {

	/**
	 * ?§Ø???????
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str){
        return str == null || "".equals(str.trim());
	}
	
	/**
	 * ?§Ø???????
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty(String str){
        return str != null && !"".equals(str.trim());
	}
}
