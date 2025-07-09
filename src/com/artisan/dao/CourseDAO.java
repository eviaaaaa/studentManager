package com.artisan.dao;

// JdbcCourseDAO.java (DAO ��)

import com.artisan.model.Course;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * JdbcCourseDAO ��һ�� JDBC ���ݷ��ʶ����࣬���ڶ� Course ʵ��������ݲ�����
 * ���ݿ��������ⲿ���룬������ DAO �ڲ�����
 */
public class CourseDAO {

    /**
     * ���һ���µĿγ̡�
     *
     * @param con    ���ݿ�����
     * @param course Ҫ��ӵ� Course ����
     * @return Ӱ�������
     * @throws SQLException ����������ݿ���ʴ���
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
     * ���ݿγ�ID��ȡ�γ���Ϣ��
     *
     * @param con ���ݿ�����
     * @param kid �γ�ID
     * @return ��Ӧ�� Course ��������������򷵻� null
     * @throws SQLException ����������ݿ���ʴ���
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
     * ����һ�����пγ̵���Ϣ��
     *
     * @param con    ���ݿ�����
     * @param course ����������Ϣ�� Course ����
     * @return ������³ɹ����� true�����򷵻� false
     * @throws SQLException ����������ݿ���ʴ���
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
     * ���ݿγ�IDɾ��һ���γ̡�
     *
     * @param con ���ݿ�����
     * @param kid �γ�ID
     * @return ���ɾ���ɹ����� true�����򷵻� false
     * @throws SQLException ����������ݿ���ʴ���
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
     * ��ȡ���пγ̵���Ϣ��
     *
     * @param con ���ݿ�����
     * @return �������� Course ������б�
     * @throws SQLException ����������ݿ���ʴ���
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

