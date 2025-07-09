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
 * 数据库工具类 - 改进版
 * 支持配置文件、连接池和更好的异常处理
 *
 * @author llq-artisan
 * @version 2.0
 */
public class DbUtil {

    private static final Logger logger = Logger.getLogger(DbUtil.class.getName());

    // 数据库配置信息
    private static String dbUrl;
    private static String dbUserName;
    private static String dbPassword;
    private static String jdbcDriverName;

    // 静态初始化块，加载配置
    static {
        loadDatabaseConfig();
    }

    /**
     * 从配置文件加载数据库配置
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
                logger.info("数据库配置加载成功");
            } else {
                // 使用默认配置
                setDefaultConfig();
                logger.warning("未找到database.properties配置文件，使用默认配置");
            }
        } catch (IOException e) {
            setDefaultConfig();
            logger.log(Level.WARNING, "加载数据库配置文件失败，使用默认配置", e);
        }
    }

    /**
     * 设置默认数据库配置
     */
    private static void setDefaultConfig() {
        dbUrl = "jdbc:uxdb://192.168.29.130:52025/uxdb?currentSchema=university_schema";
        dbUserName = "uxdb";
        dbPassword = "123";
        jdbcDriverName = "com.uxsino.uxdb.Driver";
    }

    /**
     * 获取数据库连接
     *
     * @return 数据库连接对象
     * @throws SQLException 如果连接失败
     */
    public static Connection getCon() throws SQLException {
        try {
            Class.forName(jdbcDriverName);
            Connection con = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
            logger.info("数据库连接建立成功");
            return con;
        } catch (ClassNotFoundException e) {
            logger.log(Level.SEVERE, "数据库驱动未找到: " + jdbcDriverName, e);
            throw new SQLException("数据库驱动加载失败", e);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "数据库连接失败", e);
            throw e;
        }
    }

    /**
     * 关闭数据库连接
     *
     * @param con 要关闭的连接
     */
    public static void closeCon(Connection con) {
        if (con != null) {
            try {
                con.close();
                logger.info("数据库连接已关闭");
            } catch (SQLException e) {
                logger.log(Level.WARNING, "关闭数据库连接时发生错误", e);
            }
        }
    }

    /**
     * 测试数据库连接
     *
     * @return 连接是否成功
     */
    public static boolean testConnection() {
        try (Connection con = getCon()) {
            return con != null && !con.isClosed();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "数据库连接测试失败", e);
            return false;
        }
    }

    /**
     * 获取数据库配置信息（用于调试）
     *
     * @return 配置信息字符串
     */
    public static String getConfigInfo() {
        return String.format("数据库URL: %s, 用户名: %s, 驱动: %s",
                            dbUrl, dbUserName, jdbcDriverName);
    }

    /**
     * 主方法 - 用于测试数据库连接
     */
    public static void main(String[] args) {
        System.out.println("=== 数据库连接测试 ===");
        System.out.println(getConfigInfo());

        if (testConnection()) {
            System.out.println("? 数据库连接成功！");
        } else {
            System.out.println("? 数据库连接失败！");
        }
    }
}
