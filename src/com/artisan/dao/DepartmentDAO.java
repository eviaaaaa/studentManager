package com.artisan.dao;

import com.artisan.model.Department;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * DepartmentDAO ��һ�� JDBC ���ݷ��ʶ����࣬���ڶ� Department ʵ��������ݲ�����
 * ��ֱ�Ӱ����������ݲ�������������ʵ�ֽӿڡ�
 * ���ݿ��������ⲿ���룬������ DAO �ڲ�����
 */
public class DepartmentDAO {

    /**
     * ���һ���µ�Ժϵ��
     *
     * @param con        ���ݿ�����
     * @param department Ҫ��ӵ� Department ����
     * @return Ӱ�������
     * @throws SQLException ����������ݿ���ʴ���
     */
    public int addDepartment(Connection con, Department department) throws SQLException {
        String sql = "INSERT INTO department (did, dname) VALUES (?, ?)";
        PreparedStatement pstmt = null;
        int rowsAffected = 0;
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, department.getDid());
            pstmt.setString(2, department.getDname());
            rowsAffected = pstmt.executeUpdate();
            return rowsAffected;
        } finally {
            // ֻ�ر� PreparedStatement��Connection �ɵ����߹���
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
     * ����ԺϵID��ȡԺϵ��Ϣ��
     *
     * @param con ���ݿ�����
     * @param did ԺϵID
     * @return ��Ӧ�� Department ��������������򷵻� null
     * @throws SQLException ����������ݿ���ʴ���
     */
    public Department getDepartmentById(Connection con, String did) throws SQLException {
        String sql = "SELECT did, dname FROM department WHERE did = ?";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Department department = null;
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, did);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                department = new Department();
                department.setDid(rs.getString("did"));
                department.setDname(rs.getString("dname"));
            }
            return department;
        } finally {
            // ֻ�ر� ResultSet �� PreparedStatement��Connection �ɵ����߹���
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
     * ����һ������Ժϵ����Ϣ��
     *
     * @param con        ���ݿ�����
     * @param department ����������Ϣ�� Department ����
     * @return ������³ɹ����� true�����򷵻� false
     * @throws SQLException ����������ݿ���ʴ���
     */
    public boolean updateDepartment(Connection con, Department department) throws SQLException {
        String sql = "UPDATE department SET dname = ? WHERE did = ?";
        PreparedStatement pstmt = null;
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, department.getDname());
            pstmt.setString(2, department.getDid());
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } finally {
            // ֻ�ر� PreparedStatement��Connection �ɵ����߹���
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
     * ����ԺϵIDɾ��һ��Ժϵ��
     *
     * @param con ���ݿ�����
     * @param did ԺϵID
     * @return ���ɾ���ɹ����� true�����򷵻� false
     * @throws SQLException ����������ݿ���ʴ���
     */
    public boolean deleteDepartment(Connection con, String did) throws SQLException {
        String sql = "DELETE FROM department WHERE did = ?";
        PreparedStatement pstmt = null;
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, did);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } finally {
            // ֻ�ر� PreparedStatement��Connection �ɵ����߹���
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
     * ��ȡ����Ժϵ����Ϣ��
     *
     * @param con ���ݿ�����
     * @return �������� Department ������б�
     * @throws SQLException ����������ݿ���ʴ���
     */
    public List<Department> getAllDepartments(Connection con) throws SQLException {
        String sql = "SELECT did, dname FROM department";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Department> departmentList = new ArrayList<>();
        try {
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Department department = new Department();
                department.setDid(rs.getString("did"));
                department.setDname(rs.getString("dname"));
                departmentList.add(department);
            }
            return departmentList;
        } finally {
            // ֻ�ر� ResultSet �� PreparedStatement��Connection �ɵ����߹���
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