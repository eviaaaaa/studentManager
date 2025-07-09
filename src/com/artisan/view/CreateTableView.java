package com.artisan.view;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

public class CreateTableView {
    /**
     * 创建数据库表结构。
     *
     * @param con 数据库连接
     * @throws SQLException 如果发生数据库访问错误
     */
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    static void createTables(Connection con) throws SQLException {
        Statement stmt = null;
        try {
            stmt = con.createStatement();

            System.out.println("正在创建 department 表...");
            stmt.executeUpdate("CREATE TABLE department (" +
                    "    did VARCHAR(30) PRIMARY KEY NOT NULL," +
                    "    dname VARCHAR(30) NOT NULL" +
                    ")");
            System.out.println("department 表创建成功。");

            System.out.println("正在创建 major 表...");
            stmt.executeUpdate("CREATE TABLE major (" +
                    "    mid VARCHAR(30) PRIMARY KEY NOT NULL," +
                    "    did VARCHAR(30)," +
                    "    mname VARCHAR(30) NOT NULL," +
                    "    CONSTRAINT fk_major_did FOREIGN KEY (did) REFERENCES department(did)" +
                    ")");
            System.out.println("major 表创建成功。");

            System.out.println("正在创建 class 表...");
            stmt.executeUpdate("CREATE TABLE class (" +
                    "    cid VARCHAR(30) PRIMARY KEY NOT NULL," +
                    "    mid VARCHAR(30)," +
                    "    did VARCHAR(30)," +
                    "    cname VARCHAR(30) NOT NULL," +
                    "    cnumber INTEGER NOT NULL," +
                    "    CONSTRAINT fk_class_mid FOREIGN KEY (mid) REFERENCES major(mid)," +
                    "    CONSTRAINT fk_class_did FOREIGN KEY (did) REFERENCES department(did)" +
                    ")");
            System.out.println("class 表创建成功。");

            System.out.println("正在创建 student 表...");
            stmt.executeUpdate("CREATE TABLE student (" +
                    "    id VARCHAR(30) PRIMARY KEY NOT NULL," +
                    "    name VARCHAR(30) NOT NULL," +
                    "    sex CHAR(2) NOT NULL," +
                    "    cid VARCHAR(30)," +
                    "    mid VARCHAR(30)," +
                    "    did VARCHAR(30)," +
                    "    nation VARCHAR(20) NOT NULL," +
                    "    age INTEGER NOT NULL," +
                    "    birthday DATE NOT NULL," +
                    "    location VARCHAR(20) NOT NULL," +
                    "    enroll DATE NOT NULL," +
                    "    CONSTRAINT fk_student_cid FOREIGN KEY (cid) REFERENCES class(cid)," +
                    "    CONSTRAINT fk_student_mid FOREIGN KEY (mid) REFERENCES major(mid)," +
                    "    CONSTRAINT fk_student_did FOREIGN KEY (did) REFERENCES department(did)" +
                    ")");
            System.out.println("student 表创建成功。");

            System.out.println("正在创建 course 表...");
            stmt.executeUpdate("CREATE TABLE course (" +
                    "    kid VARCHAR(30) PRIMARY KEY NOT NULL," +
                    "    kname VARCHAR(30) NOT NULL," +
                    "    kcredit INTEGER NOT NULL," +
                    "    kperiod INTEGER NOT NULL" +
                    ")");
            System.out.println("course 表创建成功。");

            System.out.println("正在创建 grade 表...");
            stmt.executeUpdate("CREATE TABLE grade (" +
                    "    kid VARCHAR(30)," +
                    "    id VARCHAR(30)," +
                    "    ggrade INTEGER NOT NULL," +
                    "    CONSTRAINT fk_grade_kid FOREIGN KEY (kid) REFERENCES course(kid)," +
                    "    CONSTRAINT fk_grade_id FOREIGN KEY (id) REFERENCES student(id)" +
                    ")"); // Removed PRIMARY KEY (kid, id) based on user's latest DDL
            System.out.println("grade 表创建成功。");

            System.out.println("正在创建 award_punish 表...");
            stmt.executeUpdate("CREATE TABLE award_punish (" +
                    "    aid VARCHAR(20) PRIMARY KEY NOT NULL," +
                    "    id VARCHAR(30)," +
                    "    mid VARCHAR(30)," +
                    "    did VARCHAR(30)," +
                    "    aname VARCHAR(30) NOT NULL," +
                    "    aproject VARCHAR(100) NOT NULL," +
                    "    CONSTRAINT fk_award_punish_id FOREIGN KEY (id) REFERENCES student(id)," +
                    "    CONSTRAINT fk_award_punish_mid FOREIGN KEY (mid) REFERENCES major(mid)," +
                    "    CONSTRAINT fk_award_punish_did FOREIGN KEY (did) REFERENCES department(did)" +
                    ")");
            System.out.println("award_punish 表创建成功。");
            // Create Views
            System.out.println("正在创建 student_basic_info 视图...");
            stmt.executeUpdate("DROP VIEW IF EXISTS student_basic_info;" + // Drop view before creating
                    "CREATE VIEW student_basic_info AS " +
                    "SELECT " +
                    "    id AS 学号," +
                    "    name AS 姓名," +
                    "    cid AS 班级编号," +
                    "    mid AS 专业编号," +
                    "    did AS 院系编号 " +
                    "FROM student");
            System.out.println("student_basic_info 视图创建成功。");

            System.out.println("正在创建 student_grade_info 视图...");
            stmt.executeUpdate("DROP VIEW IF EXISTS student_grade_info;" + // Drop view before creating
                    "CREATE VIEW student_grade_info AS " +
                    "SELECT " +
                    "    s.id AS 学号," +
                    "    s.name AS 姓名," +
                    "    c.kname AS 课程," +
                    "    g.ggrade AS 成绩 " +
                    "FROM grade g " +
                    "JOIN student s ON g.id = s.id " +
                    "JOIN course c ON g.kid = c.kid");
            System.out.println("student_grade_info 视图创建成功。");


            System.out.println("\n所有表结构和视图创建完成。");

        } catch (SQLException e) {
            System.err.println("创建表结构失败: " + e.getMessage());
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
