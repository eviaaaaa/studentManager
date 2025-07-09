package com.artisan.dao;

// JdbcMajorDAO.java (DAO ��)

import com.artisan.model.Major;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * JdbcMajorDAO ��һ�� JDBC ���ݷ��ʶ����࣬���ڶ� Major ʵ��������ݲ�����
 * ���ݿ��������ⲿ���룬������ DAO �ڲ�����
 */
public class MajorDAO {

    /**
     * ���һ���µ�רҵ��
     *
     * @param con   ���ݿ�����
     * @param major Ҫ��ӵ� Major ����
     * @return Ӱ�������
     * @throws SQLException ����������ݿ���ʴ���
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
     * ����רҵID��ȡרҵ��Ϣ��
     *
     * @param con ���ݿ�����
     * @param mid רҵID
     * @return ��Ӧ�� Major ��������������򷵻� null
     * @throws SQLException ����������ݿ���ʴ���
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
     * ����һ������רҵ����Ϣ��
     *
     * @param con   ���ݿ�����
     * @param major ����������Ϣ�� Major ����
     * @return ������³ɹ����� true�����򷵻� false
     * @throws SQLException ����������ݿ���ʴ���
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
     * ����רҵIDɾ��һ��רҵ��
     *
     * @param con ���ݿ�����
     * @param mid רҵID
     * @return ���ɾ���ɹ����� true�����򷵻� false
     * @throws SQLException ����������ݿ���ʴ���
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
     * ��ȡ����רҵ����Ϣ��
     *
     * @param con ���ݿ�����
     * @return �������� Major ������б�
     * @throws SQLException ����������ݿ���ʴ���
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
