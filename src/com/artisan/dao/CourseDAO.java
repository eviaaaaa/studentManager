package com.artisan.dao;

// JdbcCourseDAO.java (DAO 类)

import com.artisan.model.Course;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * JdbcCourseDAO 是一个 JDBC 数据访问对象类，用于对 Course 实体进行数据操作。
 * 数据库连接由外部传入，不再在 DAO 内部管理。
 */
public class CourseDAO {

    /**
     * 添加一个新的课程。
     *
     * @param con    数据库连接
     * @param course 要添加的 Course 对象
     * @return 影响的行数
     * @throws SQLException 如果发生数据库访问错误
     */
    public int addCourse(Connection con, Course course) throws SQLException {
        String sql = "INSERT INTO course (kid, kname, kcredit, kperiod) VALUES (?, ?, ?, ?)";
        PreparedStatement pstmt = null;
        int rowsAffected = 0;
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, course.getKid());
            pstmt.setString(2, course.getKname());
            pstmt.setInt(3, course.getKcredit());
            pstmt.setInt(4, course.getKperiod());
            rowsAffected = pstmt.executeUpdate();
            return rowsAffected;
        } finally {
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
     * 根据课程ID获取课程信息。
     *
     * @param con 数据库连接
     * @param kid 课程ID
     * @return 对应的 Course 对象，如果不存在则返回 null
     * @throws SQLException 如果发生数据库访问错误
     */
    public Course getCourseById(Connection con, String kid) throws SQLException {
        String sql = "SELECT kid, kname, kcredit, kperiod FROM course WHERE kid = ?";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Course course = null;
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, kid);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                course = new Course();
                course.setKid(rs.getString("kid"));
                course.setKname(rs.getString("kname"));
                course.setKcredit(rs.getInt("kcredit"));
                course.setKperiod(rs.getInt("kperiod"));
            }
            return course;
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
     * 更新一个现有课程的信息。
     *
     * @param con    数据库连接
     * @param course 包含更新信息的 Course 对象
     * @return 如果更新成功返回 true，否则返回 false
     * @throws SQLException 如果发生数据库访问错误
     */
    public boolean updateCourse(Connection con, Course course) throws SQLException {
        String sql = "UPDATE course SET kname = ?, kcredit = ?, kperiod = ? WHERE kid = ?";
        PreparedStatement pstmt = null;
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, course.getKname());
            pstmt.setInt(2, course.getKcredit());
            pstmt.setInt(3, course.getKperiod());
            pstmt.setString(4, course.getKid());
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } finally {
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
     * 根据课程ID删除一个课程。
     *
     * @param con 数据库连接
     * @param kid 课程ID
     * @return 如果删除成功返回 true，否则返回 false
     * @throws SQLException 如果发生数据库访问错误
     */
    public boolean deleteCourse(Connection con, String kid) throws SQLException {
        String sql = "DELETE FROM course WHERE kid = ?";
        PreparedStatement pstmt = null;
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, kid);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } finally {
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
     * 获取所有课程的信息。
     *
     * @param con 数据库连接
     * @return 包含所有 Course 对象的列表
     * @throws SQLException 如果发生数据库访问错误
     */
    public List<Course> getAllCourses(Connection con) throws SQLException {
        String sql = "SELECT kid, kname, kcredit, kperiod FROM course";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Course> courseList = new ArrayList<>();
        try {
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Course course = new Course();
                course.setKid(rs.getString("kid"));
                course.setKname(rs.getString("kname"));
                course.setKcredit(rs.getInt("kcredit"));
                course.setKperiod(rs.getInt("kperiod"));
                courseList.add(course);
            }
            return courseList;
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

