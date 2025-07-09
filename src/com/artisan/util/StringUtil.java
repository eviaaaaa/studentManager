package com.artisan.util;

/**
 * �ַ���������
 * @author Administrator
 *
 */
public class StringUtil {

	/**
	 * �ж��Ƿ��ǿ�
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str){
        return str == null || "".equals(str.trim());
	}
	
	/**
	 * �ж��Ƿ��ǿ�
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty(String str){
        return str != null && !"".equals(str.trim());
	}
}
