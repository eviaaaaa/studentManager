package com.artisan.dao; // Consistent package for DAOs

import com.artisan.model.StudentBasicInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * StudentBasicInfoDAO 是一个 JDBC 数据访问对象类，用于查询 student_basic_info 视图。
 * 数据库连接由外部传入，不再在 DAO 内部管理。
 */
public class StudentBasicInfoDAO {

    /**
     * 获取所有学生基本信息。
     *
     * @param con 数据库连接
     * @return 包含所有 StudentBasicInfo 对象的列表
     * @throws SQLException 如果发生数据库访问错误
     */
    public List<StudentBasicInfo> getAllStudentBasicInfo(Connection con) throws SQLException {
        // Note: The SQL aliases (学号, 姓名, etc.) are used here as column names in the ResultSet
        String sql = "SELECT 学号, 姓名, 班级编号, 专业编号, 院系编号 FROM student_basic_info";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<StudentBasicInfo> basicInfoList = new ArrayList<>();
        try {
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                StudentBasicInfo info = new StudentBasicInfo();
                info.setStudentId(rs.getString("学号"));
                info.setName(rs.getString("姓名"));
                info.setClassId(rs.getString("班级编号"));
                info.setMajorId(rs.getString("专业编号"));
                info.setDepartmentId(rs.getString("院系编号"));
                basicInfoList.add(info);
            }
            return basicInfoList;
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
     * 根据学号获取学生基本信息。
     *
     * @param con  数据库连接
     * @param studentId 学生学号
     * @return 对应的 StudentBasicInfo 对象，如果不存在则返回 null
     * @throws SQLException 如果发生数据库访问错误
     */
    public StudentBasicInfo getStudentBasicInfoByStudentId(Connection con, String studentId) throws SQLException {
        String sql = "SELECT 学号, 姓名, 班级编号, 专业编号, 院系编号 FROM student_basic_info WHERE 学号 = ?";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        StudentBasicInfo info = null;
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, studentId);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                info = new StudentBasicInfo();
                info.setStudentId(rs.getString("学号"));
                info.setName(rs.getString("姓名"));
                info.setClassId(rs.getString("班级编号"));
                info.setMajorId(rs.getString("专业编号"));
                info.setDepartmentId(rs.getString("院系编号"));
            }
            return info;
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
