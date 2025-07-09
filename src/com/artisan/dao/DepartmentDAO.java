package com.artisan.dao;

import com.artisan.model.Department;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * DepartmentDAO 是一个 JDBC 数据访问对象类，用于对 Department 实体进行数据操作。
 * 它直接包含所有数据操作方法，不再实现接口。
 * 数据库连接由外部传入，不再在 DAO 内部管理。
 */
public class DepartmentDAO {

    /**
     * 添加一个新的院系。
     *
     * @param con        数据库连接
     * @param department 要添加的 Department 对象
     * @return 影响的行数
     * @throws SQLException 如果发生数据库访问错误
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
            // 只关闭 PreparedStatement，Connection 由调用者管理
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
     * 根据院系ID获取院系信息。
     *
     * @param con 数据库连接
     * @param did 院系ID
     * @return 对应的 Department 对象，如果不存在则返回 null
     * @throws SQLException 如果发生数据库访问错误
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
            // 只关闭 ResultSet 和 PreparedStatement，Connection 由调用者管理
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
     * 更新一个现有院系的信息。
     *
     * @param con        数据库连接
     * @param department 包含更新信息的 Department 对象
     * @return 如果更新成功返回 true，否则返回 false
     * @throws SQLException 如果发生数据库访问错误
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
            // 只关闭 PreparedStatement，Connection 由调用者管理
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
     * 根据院系ID删除一个院系。
     *
     * @param con 数据库连接
     * @param did 院系ID
     * @return 如果删除成功返回 true，否则返回 false
     * @throws SQLException 如果发生数据库访问错误
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
            // 只关闭 PreparedStatement，Connection 由调用者管理
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
     * 获取所有院系的信息。
     *
     * @param con 数据库连接
     * @return 包含所有 Department 对象的列表
     * @throws SQLException 如果发生数据库访问错误
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
            // 只关闭 ResultSet 和 PreparedStatement，Connection 由调用者管理
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