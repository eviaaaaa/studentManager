package com.artisan.dao; // Consistent package for DAOs

import com.artisan.model.StudentBasicInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * StudentBasicInfoDAO ��һ�� JDBC ���ݷ��ʶ����࣬���ڲ�ѯ student_basic_info ��ͼ��
 * ���ݿ��������ⲿ���룬������ DAO �ڲ�����
 */
public class StudentBasicInfoDAO {

    /**
     * ��ȡ����ѧ��������Ϣ��
     *
     * @param con ���ݿ�����
     * @return �������� StudentBasicInfo ������б�
     * @throws SQLException ����������ݿ���ʴ���
     */
    public List<StudentBasicInfo> getAllStudentBasicInfo(Connection con) throws SQLException {
        // Note: The SQL aliases (ѧ��, ����, etc.) are used here as column names in the ResultSet
        String sql = "SELECT ѧ��, ����, �༶���, רҵ���, Ժϵ��� FROM student_basic_info";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<StudentBasicInfo> basicInfoList = new ArrayList<>();
        try {
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                StudentBasicInfo info = new StudentBasicInfo();
                info.setStudentId(rs.getString("ѧ��"));
                info.setName(rs.getString("����"));
                info.setClassId(rs.getString("�༶���"));
                info.setMajorId(rs.getString("רҵ���"));
                info.setDepartmentId(rs.getString("Ժϵ���"));
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
     * ����ѧ�Ż�ȡѧ��������Ϣ��
     *
     * @param con  ���ݿ�����
     * @param studentId ѧ��ѧ��
     * @return ��Ӧ�� StudentBasicInfo ��������������򷵻� null
     * @throws SQLException ����������ݿ���ʴ���
     */
    public StudentBasicInfo getStudentBasicInfoByStudentId(Connection con, String studentId) throws SQLException {
        String sql = "SELECT ѧ��, ����, �༶���, רҵ���, Ժϵ��� FROM student_basic_info WHERE ѧ�� = ?";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        StudentBasicInfo info = null;
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, studentId);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                info = new StudentBasicInfo();
                info.setStudentId(rs.getString("ѧ��"));
                info.setName(rs.getString("����"));
                info.setClassId(rs.getString("�༶���"));
                info.setMajorId(rs.getString("רҵ���"));
                info.setDepartmentId(rs.getString("Ժϵ���"));
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
