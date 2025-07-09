package com.artisan.dao;

// JdbcGradeDAO.java (DAO ��)



import com.artisan.model.Grade;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * JdbcGradeDAO ��һ�� JDBC ���ݷ��ʶ����࣬���ڶ� Grade ʵ��������ݲ�����
 * ���ݿ��������ⲿ���룬������ DAO �ڲ�����
 * ע�⣺���� grade ��û�е�һ������getById �� delete ������ʹ�� (kid, id) ����������
 */
public class GradeDAO {

    /**
     * ���һ���µĳɼ���¼��
     *
     * @param con   ���ݿ�����
     * @param grade Ҫ��ӵ� Grade ����
     * @return Ӱ�������
     * @throws SQLException ����������ݿ���ʴ���
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
     * ���ݿγ�ID��ѧ��ID��ȡ�ɼ���Ϣ��
     *
     * @param con ���ݿ�����
     * @param kid �γ�ID
     * @param id  ѧ��ID
     * @return ��Ӧ�� Grade ��������������򷵻� null
     * @throws SQLException ����������ݿ���ʴ���
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
     * ����һ�����гɼ���¼��
     *
     * @param con   ���ݿ�����
     * @param grade ����������Ϣ�� Grade ����
     * @return ������³ɹ����� true�����򷵻� false
     * @throws SQLException ����������ݿ���ʴ���
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
     * ���ݿγ�ID��ѧ��IDɾ��һ���ɼ���¼��
     *
     * @param con ���ݿ�����
     * @param kid �γ�ID
     * @param id  ѧ��ID
     * @return ���ɾ���ɹ����� true�����򷵻� false
     * @throws SQLException ����������ݿ���ʴ���
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
     * ��ȡ���гɼ���¼����Ϣ��
     *
     * @param con ���ݿ�����
     * @return �������� Grade ������б�
     * @throws SQLException ����������ݿ���ʴ���
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

