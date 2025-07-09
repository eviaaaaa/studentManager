package com.artisan.util;


import java.sql.Connection;
import java.sql.DriverManager;


/**
 * ���ݿ⹤����
 * @author Administrator
 *
 */
public class DbUtil {

	private static final String dbUrl="jdbc:uxdb://192.168.29.130:52025/uxdb?currentSchema=university_schema"; // ���ݿ����ӵ�ַ
	private static final String dbUserName="uxdb"; // �û���,��д���Լ������ݿ��û���
	private static final String dbPassword="123"; // ����
	private static final String jdbcName="com.uxsino.uxdb.Driver"; // ��������
	
	/**
	 * ��ȡ���ݿ�����
	 * @return
	 * @throws Exception
	 */
	public static Connection getCon()throws Exception{
		Class.forName(jdbcName);
		Connection con=DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
		return con;
	}
	
	/**
	 * �ر����ݿ�����
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
			System.out.println("���ݿ����ӳɹ���");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("���ݿ�����ʧ��");
		}
	}
	
}
