package com.artisan.view;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

public class CreateTableView {
    /**
     * �������ݿ��ṹ��
     *
     * @param con ���ݿ�����
     * @throws SQLException ����������ݿ���ʴ���
     */
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    static void createTables(Connection con) throws SQLException {
        Statement stmt = null;
        try {
            stmt = con.createStatement();

            System.out.println("���ڴ��� department ��...");
            stmt.executeUpdate("CREATE TABLE department (" +
                    "    did VARCHAR(30) PRIMARY KEY NOT NULL," +
                    "    dname VARCHAR(30) NOT NULL" +
                    ")");
            System.out.println("department �����ɹ���");

            System.out.println("���ڴ��� major ��...");
            stmt.executeUpdate("CREATE TABLE major (" +
                    "    mid VARCHAR(30) PRIMARY KEY NOT NULL," +
                    "    did VARCHAR(30)," +
                    "    mname VARCHAR(30) NOT NULL," +
                    "    CONSTRAINT fk_major_did FOREIGN KEY (did) REFERENCES department(did)" +
                    ")");
            System.out.println("major �����ɹ���");

            System.out.println("���ڴ��� class ��...");
            stmt.executeUpdate("CREATE TABLE class (" +
                    "    cid VARCHAR(30) PRIMARY KEY NOT NULL," +
                    "    mid VARCHAR(30)," +
                    "    did VARCHAR(30)," +
                    "    cname VARCHAR(30) NOT NULL," +
                    "    cnumber INTEGER NOT NULL," +
                    "    CONSTRAINT fk_class_mid FOREIGN KEY (mid) REFERENCES major(mid)," +
                    "    CONSTRAINT fk_class_did FOREIGN KEY (did) REFERENCES department(did)" +
                    ")");
            System.out.println("class �����ɹ���");

            System.out.println("���ڴ��� student ��...");
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
            System.out.println("student �����ɹ���");

            System.out.println("���ڴ��� course ��...");
            stmt.executeUpdate("CREATE TABLE course (" +
                    "    kid VARCHAR(30) PRIMARY KEY NOT NULL," +
                    "    kname VARCHAR(30) NOT NULL," +
                    "    kcredit INTEGER NOT NULL," +
                    "    kperiod INTEGER NOT NULL" +
                    ")");
            System.out.println("course �����ɹ���");

            System.out.println("���ڴ��� grade ��...");
            stmt.executeUpdate("CREATE TABLE grade (" +
                    "    kid VARCHAR(30)," +
                    "    id VARCHAR(30)," +
                    "    ggrade INTEGER NOT NULL," +
                    "    CONSTRAINT fk_grade_kid FOREIGN KEY (kid) REFERENCES course(kid)," +
                    "    CONSTRAINT fk_grade_id FOREIGN KEY (id) REFERENCES student(id)" +
                    ")"); // Removed PRIMARY KEY (kid, id) based on user's latest DDL
            System.out.println("grade �����ɹ���");

            System.out.println("���ڴ��� award_punish ��...");
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
            System.out.println("award_punish �����ɹ���");
            // Create Views
            System.out.println("���ڴ��� student_basic_info ��ͼ...");
            stmt.executeUpdate("DROP VIEW IF EXISTS student_basic_info;" + // Drop view before creating
                    "CREATE VIEW student_basic_info AS " +
                    "SELECT " +
                    "    id AS ѧ��," +
                    "    name AS ����," +
                    "    cid AS �༶���," +
                    "    mid AS רҵ���," +
                    "    did AS Ժϵ��� " +
                    "FROM student");
            System.out.println("student_basic_info ��ͼ�����ɹ���");

            System.out.println("���ڴ��� student_grade_info ��ͼ...");
            stmt.executeUpdate("DROP VIEW IF EXISTS student_grade_info;" + // Drop view before creating
                    "CREATE VIEW student_grade_info AS " +
                    "SELECT " +
                    "    s.id AS ѧ��," +
                    "    s.name AS ����," +
                    "    c.kname AS �γ�," +
                    "    g.ggrade AS �ɼ� " +
                    "FROM grade g " +
                    "JOIN student s ON g.id = s.id " +
                    "JOIN course c ON g.kid = c.kid");
            System.out.println("student_grade_info ��ͼ�����ɹ���");


            System.out.println("\n���б�ṹ����ͼ������ɡ�");

        } catch (SQLException e) {
            System.err.println("������ṹʧ��: " + e.getMessage());
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
