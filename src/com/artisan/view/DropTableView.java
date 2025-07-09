package com.artisan.view;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

public class DropTableView {
    /**
     * ɾ�����ݿ��ṹ��
     *
     * @param con ���ݿ�����
     * @throws SQLException ����������ݿ���ʴ���
     */
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    static void dropTables(Connection con) throws SQLException {
        Statement stmt = null;
        try {
            stmt = con.createStatement();

            // Drop Views first to avoid dependency issues
            System.out.println("����ɾ�� student_grade_info ��ͼ...");
            stmt.executeUpdate("DROP VIEW IF EXISTS student_grade_info");
            System.out.println("student_grade_info ��ͼɾ���ɹ���");

            System.out.println("����ɾ�� student_basic_info ��ͼ...");
            stmt.executeUpdate("DROP VIEW IF EXISTS student_basic_info");
            System.out.println("student_basic_info ��ͼɾ���ɹ���");

            System.out.println("����ɾ�� award_punish ��...");
            stmt.executeUpdate("DROP TABLE IF EXISTS award_punish CASCADE");
            System.out.println("award_punish ��ɾ���ɹ���");

            System.out.println("����ɾ�� grade ��...");
            stmt.executeUpdate("DROP TABLE IF EXISTS grade CASCADE");
            System.out.println("grade ��ɾ���ɹ���");

            System.out.println("����ɾ�� student ��...");
            stmt.executeUpdate("DROP TABLE IF EXISTS student CASCADE");
            System.out.println("student ��ɾ���ɹ���");

            System.out.println("����ɾ�� class ��...");
            stmt.executeUpdate("DROP TABLE IF EXISTS class CASCADE");
            System.out.println("class ��ɾ���ɹ���");

            System.out.println("����ɾ�� course ��...");
            stmt.executeUpdate("DROP TABLE IF EXISTS course CASCADE");
            System.out.println("course ��ɾ���ɹ���");

            System.out.println("����ɾ�� major ��...");
            stmt.executeUpdate("DROP TABLE IF EXISTS major CASCADE");
            System.out.println("major ��ɾ���ɹ���");

            System.out.println("����ɾ�� department ��...");
            stmt.executeUpdate("DROP TABLE IF EXISTS department CASCADE");
            System.out.println("department ��ɾ���ɹ���");

            System.out.println("\n���б�ṹɾ����ɡ�");

        } catch (SQLException e) {
            System.err.println("ɾ����ṹʧ��: " + e.getMessage());
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
