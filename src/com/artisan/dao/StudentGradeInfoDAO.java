package com.artisan.dao;

// StudentGradeInfoDAO.java (DAO ��)

import com.artisan.model.StudentGradeInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * StudentGradeInfoDAO ��һ�� JDBC ���ݷ��ʶ����࣬���ڲ�ѯ student_grade_info ��ͼ��
 * ���ݿ��������ⲿ���룬������ DAO �ڲ�����
 */
public class StudentGradeInfoDAO {

    /**
     * ��ȡ����ѧ���ɼ���Ϣ��
     *
     * @param con ���ݿ�����
     * @return �������� StudentGradeInfo ������б�
     * @throws SQLException ����������ݿ���ʴ���
     */
    public List<StudentGradeInfo> getAllStudentGradeInfo(Connection con) throws SQLException {
        // Note: The SQL aliases (ѧ��, ����, �γ�, �ɼ�) are used here as column names in the ResultSet
        String sql = "SELECT ѧ��, ����, �γ�, �ɼ� FROM student_grade_info";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<StudentGradeInfo> gradeInfoList = new ArrayList<>();
        try {
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                StudentGradeInfo info = new StudentGradeInfo();
                info.setStudentId(rs.getString("ѧ��"));
                info.setStudentName(rs.getString("����"));
                info.setCourseName(rs.getString("�γ�"));
                info.setGradeValue(rs.getInt("�ɼ�"));
                gradeInfoList.add(info);
            }
            return gradeInfoList;
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * ����ѧ�Ż�ȡĳѧ�������гɼ���Ϣ��
     *
     * @param con  ���ݿ�����
     * @param studentId ѧ��ѧ��
     * @return ������ѧ�����гɼ��� StudentGradeInfo ������б�
     * @throws SQLException ����������ݿ���ʴ���
     */
    public List<StudentGradeInfo> getStudentGradeInfoByStudentId(Connection con, String studentId) throws SQLException {
        String sql = "SELECT ѧ��, ����, �γ�, �ɼ� FROM student_grade_info WHERE ѧ�� = ?";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<StudentGradeInfo> gradeInfoList = new ArrayList<>();
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, studentId);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                StudentGradeInfo info = new StudentGradeInfo();
                info.setStudentId(rs.getString("ѧ��"));
                info.setStudentName(rs.getString("����"));
                info.setCourseName(rs.getString("�γ�"));
                info.setGradeValue(rs.getInt("�ɼ�"));
                gradeInfoList.add(info);
            }
            return gradeInfoList;
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

