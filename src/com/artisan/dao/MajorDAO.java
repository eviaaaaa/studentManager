package com.artisan.dao;

// JdbcMajorDAO.java (DAO 类)

import com.artisan.model.Major;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * JdbcMajorDAO 是一个 JDBC 数据访问对象类，用于对 Major 实体进行数据操作。
 * 数据库连接由外部传入，不再在 DAO 内部管理。
 */
public class MajorDAO {

    /**
     * 添加一个新的专业。
     *
     * @param con   数据库连接
     * @param major 要添加的 Major 对象
     * @return 影响的行数
     * @throws SQLException 如果发生数据库访问错误
     */
    public int addMajor(Connection con, Major major) throws SQLException {
        String sql = "INSERT INTO major (mid, did, mname) VALUES (?, ?, ?)";
        PreparedStatement pstmt = null;
        int rowsAffected = 0;
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, major.getMid());
            pstmt.setString(2, major.getDid());
            pstmt.setString(3, major.getMname());
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
     * 根据专业ID获取专业信息。
     *
     * @param con 数据库连接
     * @param mid 专业ID
     * @return 对应的 Major 对象，如果不存在则返回 null
     * @throws SQLException 如果发生数据库访问错误
     */
    public Major getMajorById(Connection con, String mid) throws SQLException {
        String sql = "SELECT mid, did, mname FROM major WHERE mid = ?";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Major major = null;
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, mid);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                major = new Major();
                major.setMid(rs.getString("mid"));
                major.setDid(rs.getString("did"));
                major.setMname(rs.getString("mname"));
            }
            return major;
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
     * 更新一个现有专业的信息。
     *
     * @param con   数据库连接
     * @param major 包含更新信息的 Major 对象
     * @return 如果更新成功返回 true，否则返回 false
     * @throws SQLException 如果发生数据库访问错误
     */
    public boolean updateMajor(Connection con, Major major) throws SQLException {
        String sql = "UPDATE major SET did = ?, mname = ? WHERE mid = ?";
        PreparedStatement pstmt = null;
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, major.getDid());
            pstmt.setString(2, major.getMname());
            pstmt.setString(3, major.getMid());
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
     * 根据专业ID删除一个专业。
     *
     * @param con 数据库连接
     * @param mid 专业ID
     * @return 如果删除成功返回 true，否则返回 false
     * @throws SQLException 如果发生数据库访问错误
     */
    public boolean deleteMajor(Connection con, String mid) throws SQLException {
        String sql = "DELETE FROM major WHERE mid = ?";
        PreparedStatement pstmt = null;
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, mid);
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
     * 获取所有专业的信息。
     *
     * @param con 数据库连接
     * @return 包含所有 Major 对象的列表
     * @throws SQLException 如果发生数据库访问错误
     */
    public List<Major> getAllMajors(Connection con) throws SQLException {
        String sql = "SELECT mid, did, mname FROM major";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Major> majorList = new ArrayList<>();
        try {
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Major major = new Major();
                major.setMid(rs.getString("mid"));
                major.setDid(rs.getString("did"));
                major.setMname(rs.getString("mname"));
                majorList.add(major);
            }
            return majorList;
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
