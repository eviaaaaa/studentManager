package com.artisan.util;


import java.sql.Connection;
import java.sql.DriverManager;


/**
 * 数据库工具类
 * @author Administrator
 *
 */
public class DbUtil {

	private static final String dbUrl="jdbc:uxdb://192.168.29.130:52025/uxdb?currentSchema=university_schema"; // 数据库连接地址
	private static final String dbUserName="uxdb"; // 用户名,填写你自己的数据库用户名
	private static final String dbPassword="123"; // 密码
	private static final String jdbcName="com.uxsino.uxdb.Driver"; // 驱动名称
	
	/**
	 * 获取数据库连接
	 * @return
	 * @throws Exception
	 */
	public static Connection getCon()throws Exception{
		Class.forName(jdbcName);
		Connection con=DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
		return con;
	}
	
	/**
	 * 关闭数据库连接
	 * @param con
	 * @throws Exception
	 */
	public static void closeCon(Connection con)throws Exception{
		if(con!=null){
			con.close();
		}
	}
	
	public static void main(String[] args) {
		DbUtil dbUtil=new DbUtil();
		try {
			getCon();
			System.out.println("数据库连接成功！");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("数据库连接失败");
		}
	}
	
}
