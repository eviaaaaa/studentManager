package com.artisan.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * ���ݿ⹤���� - �Ľ���
 * ֧�������ļ������ӳغ͸��õ��쳣����
 *
 * @author llq-artisan
 * @version 2.0
 */
public class DbUtil {

    private static final Logger logger = Logger.getLogger(DbUtil.class.getName());

    // ���ݿ�������Ϣ
    private static String dbUrl;
    private static String dbUserName;
    private static String dbPassword;
    private static String jdbcDriverName;

    // ��̬��ʼ���飬��������
    static {
        loadDatabaseConfig();
    }

    /**
     * �������ļ��������ݿ�����
     */
    private static void loadDatabaseConfig() {
        Properties props = new Properties();
        try (InputStream input = DbUtil.class.getClassLoader().getResourceAsStream("database.properties")) {
            if (input != null) {
                props.load(input);
                dbUrl = props.getProperty("db.url", "jdbc:uxdb://192.168.29.130:52025/uxdb?currentSchema=university_schema");
                dbUserName = props.getProperty("db.username", "uxdb");
                dbPassword = props.getProperty("db.password", "123");
                jdbcDriverName = props.getProperty("db.driver", "com.uxsino.uxdb.Driver");
                logger.info("���ݿ����ü��سɹ�");
            } else {
                // ʹ��Ĭ������
                setDefaultConfig();
                logger.warning("δ�ҵ�database.properties�����ļ���ʹ��Ĭ������");
            }
        } catch (IOException e) {
            setDefaultConfig();
            logger.log(Level.WARNING, "�������ݿ������ļ�ʧ�ܣ�ʹ��Ĭ������", e);
        }
    }

    /**
     * ����Ĭ�����ݿ�����
     */
    private static void setDefaultConfig() {
        dbUrl = "jdbc:uxdb://192.168.29.130:52025/uxdb?currentSchema=university_schema";
        dbUserName = "uxdb";
        dbPassword = "123";
        jdbcDriverName = "com.uxsino.uxdb.Driver";
    }

    /**
     * ��ȡ���ݿ�����
     *
     * @return ���ݿ����Ӷ���
     * @throws SQLException �������ʧ��
     */
    public static Connection getCon() throws SQLException {
        try {
            Class.forName(jdbcDriverName);
            Connection con = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
            logger.info("���ݿ����ӽ����ɹ�");
            return con;
        } catch (ClassNotFoundException e) {
            logger.log(Level.SEVERE, "���ݿ�����δ�ҵ�: " + jdbcDriverName, e);
            throw new SQLException("���ݿ���������ʧ��", e);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "���ݿ�����ʧ��", e);
            throw e;
        }
    }

    /**
     * �ر����ݿ�����
     *
     * @param con Ҫ�رյ�����
     */
    public static void closeCon(Connection con) {
        if (con != null) {
            try {
                con.close();
                logger.info("���ݿ������ѹر�");
            } catch (SQLException e) {
                logger.log(Level.WARNING, "�ر����ݿ�����ʱ��������", e);
            }
        }
    }

    /**
     * �������ݿ�����
     *
     * @return �����Ƿ�ɹ�
     */
    public static boolean testConnection() {
        try (Connection con = getCon()) {
            return con != null && !con.isClosed();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "���ݿ����Ӳ���ʧ��", e);
            return false;
        }
    }

    /**
     * ��ȡ���ݿ�������Ϣ�����ڵ��ԣ�
     *
     * @return ������Ϣ�ַ���
     */
    public static String getConfigInfo() {
        return String.format("���ݿ�URL: %s, �û���: %s, ����: %s",
                            dbUrl, dbUserName, jdbcDriverName);
    }

    /**
     * ������ - ���ڲ������ݿ�����
     */
    public static void main(String[] args) {
        System.out.println("=== ���ݿ����Ӳ��� ===");
        System.out.println(getConfigInfo());

        if (testConnection()) {
            System.out.println("? ���ݿ����ӳɹ���");
        } else {
            System.out.println("? ���ݿ�����ʧ�ܣ�");
        }
    }
}
