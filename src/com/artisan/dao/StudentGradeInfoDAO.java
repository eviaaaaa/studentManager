package com.artisan.dao;

// StudentGradeInfoDAO.java (DAO 类)

import com.artisan.model.StudentGradeInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * StudentGradeInfoDAO 是一个 JDBC 数据访问对象类，用于查询 student_grade_info 视图。
 * 数据库连接由外部传入，不再在 DAO 内部管理。
 */
public class StudentGradeInfoDAO {

    /**
     * 获取所有学生成绩信息。
     *
     * @param con 数据库连接
     * @return 包含所有 StudentGradeInfo 对象的列表
     * @throws SQLException 如果发生数据库访问错误
     */
    public List<StudentGradeInfo> getAllStudentGradeInfo(Connection con) throws SQLException {
        // Note: The SQL aliases (学号, 姓名, 课程, 成绩) are used here as column names in the ResultSet
        String sql = "SELECT 学号, 姓名, 课程, 成绩 FROM student_grade_info";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<StudentGradeInfo> gradeInfoList = new ArrayList<>();
        try {
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                StudentGradeInfo info = new StudentGradeInfo();
                info.setStudentId(rs.getString("学号"));
                info.setStudentName(rs.getString("姓名"));
                info.setCourseName(rs.getString("课程"));
                info.setGradeValue(rs.getInt("成绩"));
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
     * 根据学号获取某学生的所有成绩信息。
     *
     * @param con  数据库连接
     * @param studentId 学生学号
     * @return 包含该学生所有成绩的 StudentGradeInfo 对象的列表
     * @throws SQLException 如果发生数据库访问错误
     */
    public List<StudentGradeInfo> getStudentGradeInfoByStudentId(Connection con, String studentId) throws SQLException {
        String sql = "SELECT 学号, 姓名, 课程, 成绩 FROM student_grade_info WHERE 学号 = ?";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<StudentGradeInfo> gradeInfoList = new ArrayList<>();
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, studentId);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                StudentGradeInfo info = new StudentGradeInfo();
                info.setStudentId(rs.getString("学号"));
                info.setStudentName(rs.getString("姓名"));
                info.setCourseName(rs.getString("课程"));
                info.setGradeValue(rs.getInt("成绩"));
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

