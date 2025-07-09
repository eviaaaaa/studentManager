package com.artisan.dao;

// JdbcGradeDAO.java (DAO 类)



import com.artisan.model.Grade;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * JdbcGradeDAO 是一个 JDBC 数据访问对象类，用于对 Grade 实体进行数据操作。
 * 数据库连接由外部传入，不再在 DAO 内部管理。
 * 注意：由于 grade 表没有单一主键，getById 和 delete 方法将使用 (kid, id) 联合主键。
 */
public class GradeDAO {

    /**
     * 添加一个新的成绩记录。
     *
     * @param con   数据库连接
     * @param grade 要添加的 Grade 对象
     * @return 影响的行数
     * @throws SQLException 如果发生数据库访问错误
     */
    public int addGrade(Connection con, Grade grade) throws SQLException {
        String sql = "INSERT INTO grade (kid, id, ggrade) VALUES (?, ?, ?)";
        PreparedStatement pstmt = null;
        int rowsAffected = 0;
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, grade.getKid());
            pstmt.setString(2, grade.getId());
            pstmt.setInt(3, grade.getGgrade());
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
     * 根据课程ID和学生ID获取成绩信息。
     *
     * @param con 数据库连接
     * @param kid 课程ID
     * @param id  学生ID
     * @return 对应的 Grade 对象，如果不存在则返回 null
     * @throws SQLException 如果发生数据库访问错误
     */
    public Grade getGradeByIds(Connection con, String kid, String id) throws SQLException {
        String sql = "SELECT kid, id, ggrade FROM grade WHERE kid = ? AND id = ?";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Grade grade = null;
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, kid);
            pstmt.setString(2, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                grade = new Grade();
                grade.setKid(rs.getString("kid"));
                grade.setId(rs.getString("id"));
                grade.setGgrade(rs.getInt("ggrade"));
            }
            return grade;
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
     * 更新一个现有成绩记录。
     *
     * @param con   数据库连接
     * @param grade 包含更新信息的 Grade 对象
     * @return 如果更新成功返回 true，否则返回 false
     * @throws SQLException 如果发生数据库访问错误
     */
    public boolean updateGrade(Connection con, Grade grade) throws SQLException {
        String sql = "UPDATE grade SET ggrade = ? WHERE kid = ? AND id = ?";
        PreparedStatement pstmt = null;
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, grade.getGgrade());
            pstmt.setString(2, grade.getKid());
            pstmt.setString(3, grade.getId());
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
     * 根据课程ID和学生ID删除一个成绩记录。
     *
     * @param con 数据库连接
     * @param kid 课程ID
     * @param id  学生ID
     * @return 如果删除成功返回 true，否则返回 false
     * @throws SQLException 如果发生数据库访问错误
     */
    public boolean deleteGrade(Connection con, String kid, String id) throws SQLException {
        String sql = "DELETE FROM grade WHERE kid = ? AND id = ?";
        PreparedStatement pstmt = null;
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, kid);
            pstmt.setString(2, id);
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
     * 获取所有成绩记录的信息。
     *
     * @param con 数据库连接
     * @return 包含所有 Grade 对象的列表
     * @throws SQLException 如果发生数据库访问错误
     */
    public List<Grade> getAllGrades(Connection con) throws SQLException {
        String sql = "SELECT kid, id, ggrade FROM grade";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Grade> gradeList = new ArrayList<>();
        try {
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Grade grade = new Grade();
                grade.setKid(rs.getString("kid"));
                grade.setId(rs.getString("id"));
                grade.setGgrade(rs.getInt("ggrade"));
                gradeList.add(grade);
            }
            return gradeList;
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

