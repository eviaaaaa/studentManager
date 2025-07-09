package com.artisan.view;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

public class DropTableView {
    /**
     * 删除数据库表结构。
     *
     * @param con 数据库连接
     * @throws SQLException 如果发生数据库访问错误
     */
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    static void dropTables(Connection con) throws SQLException {
        Statement stmt = null;
        try {
            stmt = con.createStatement();

            // Drop Views first to avoid dependency issues
            System.out.println("正在删除 student_grade_info 视图...");
            stmt.executeUpdate("DROP VIEW IF EXISTS student_grade_info");
            System.out.println("student_grade_info 视图删除成功。");

            System.out.println("正在删除 student_basic_info 视图...");
            stmt.executeUpdate("DROP VIEW IF EXISTS student_basic_info");
            System.out.println("student_basic_info 视图删除成功。");

            System.out.println("正在删除 award_punish 表...");
            stmt.executeUpdate("DROP TABLE IF EXISTS award_punish CASCADE");
            System.out.println("award_punish 表删除成功。");

            System.out.println("正在删除 grade 表...");
            stmt.executeUpdate("DROP TABLE IF EXISTS grade CASCADE");
            System.out.println("grade 表删除成功。");

            System.out.println("正在删除 student 表...");
            stmt.executeUpdate("DROP TABLE IF EXISTS student CASCADE");
            System.out.println("student 表删除成功。");

            System.out.println("正在删除 class 表...");
            stmt.executeUpdate("DROP TABLE IF EXISTS class CASCADE");
            System.out.println("class 表删除成功。");

            System.out.println("正在删除 course 表...");
            stmt.executeUpdate("DROP TABLE IF EXISTS course CASCADE");
            System.out.println("course 表删除成功。");

            System.out.println("正在删除 major 表...");
            stmt.executeUpdate("DROP TABLE IF EXISTS major CASCADE");
            System.out.println("major 表删除成功。");

            System.out.println("正在删除 department 表...");
            stmt.executeUpdate("DROP TABLE IF EXISTS department CASCADE");
            System.out.println("department 表删除成功。");

            System.out.println("\n所有表结构删除完成。");

        } catch (SQLException e) {
            System.err.println("删除表结构失败: " + e.getMessage());
            throw e;
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
