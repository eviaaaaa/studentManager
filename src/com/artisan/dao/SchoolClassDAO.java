// JdbcSchoolClassDAO.java (DAO ��)

package com.artisan.dao; // �������DAO�����Ϊ com.artisan.dao
import com.artisan.model.SchoolClass;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * JdbcSchoolClassDAO ��һ�� JDBC ���ݷ��ʶ����࣬���ڶ� SchoolClass ʵ��������ݲ�����
 * ���ݿ��������ⲿ���룬������ DAO �ڲ�����
 */
public class SchoolClassDAO {

	/**
	 * ���һ���µİ༶��
	 *
	 * @param con        ���ݿ�����
	 * @param schoolClass Ҫ��ӵ� SchoolClass ����
	 * @return Ӱ�������
	 * @throws SQLException ����������ݿ���ʴ���
	 */
	public int addClass(Connection con, SchoolClass schoolClass) throws SQLException {
		String sql = "INSERT INTO class (cid, mid, did, cname, cnumber) VALUES (?, ?, ?, ?, ?)";
		PreparedStatement pstmt = null;
		int rowsAffected = 0;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, schoolClass.getCid());
			pstmt.setString(2, schoolClass.getMid());
			pstmt.setString(3, schoolClass.getDid());
			pstmt.setString(4, schoolClass.getCname());
			pstmt.setInt(5, schoolClass.getCnumber());
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
	 * ���ݰ༶ID��ȡ�༶��Ϣ��
	 *
	 * @param con ���ݿ�����
	 * @param cid �༶ID
	 * @return ��Ӧ�� SchoolClass ��������������򷵻� null
	 * @throws SQLException ����������ݿ���ʴ���
	 */
	public SchoolClass getClassById(Connection con, String cid) throws SQLException {
		String sql = "SELECT cid, mid, did, cname, cnumber FROM class WHERE cid = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		SchoolClass schoolClass = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, cid);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				schoolClass = new SchoolClass();
				schoolClass.setCid(rs.getString("cid"));
				schoolClass.setMid(rs.getString("mid"));
				schoolClass.setDid(rs.getString("did"));
				schoolClass.setCname(rs.getString("cname"));
				schoolClass.setCnumber(rs.getInt("cnumber"));
			}
			return schoolClass;
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
	 * ����һ�����а༶����Ϣ��
	 *
	 * @param con        ���ݿ�����
	 * @param schoolClass ����������Ϣ�� SchoolClass ����
	 * @return ������³ɹ����� true�����򷵻� false
	 * @throws SQLException ����������ݿ���ʴ���
	 */
	public boolean updateClass(Connection con, SchoolClass schoolClass) throws SQLException {
		String sql = "UPDATE class SET mid = ?, did = ?, cname = ?, cnumber = ? WHERE cid = ?";
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, schoolClass.getMid());
			pstmt.setString(2, schoolClass.getDid());
			pstmt.setString(3, schoolClass.getCname());
			pstmt.setInt(4, schoolClass.getCnumber());
			pstmt.setString(5, schoolClass.getCid());
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
	 * ���ݰ༶IDɾ��һ���༶��
	 *
	 * @param con ���ݿ�����
	 * @param cid �༶ID
	 * @return ���ɾ���ɹ����� true�����򷵻� false
	 * @throws SQLException ����������ݿ���ʴ���
	 */
	public boolean deleteClass(Connection con, String cid) throws SQLException {
		String sql = "DELETE FROM class WHERE cid = ?";
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, cid);
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
	 * ��ȡ���а༶����Ϣ��
	 *
	 * @param con ���ݿ�����
	 * @return �������� SchoolClass ������б�
	 * @throws SQLException ����������ݿ���ʴ���
	 */
	public List<SchoolClass> getAllClasses(Connection con) throws SQLException {
		String sql = "SELECT cid, mid, did, cname, cnumber FROM class";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<SchoolClass> classList = new ArrayList<>();
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				SchoolClass schoolClass = new SchoolClass();
				schoolClass.setCid(rs.getString("cid"));
				schoolClass.setMid(rs.getString("mid"));
				schoolClass.setDid(rs.getString("did"));
				schoolClass.setCname(rs.getString("cname"));
				schoolClass.setCnumber(rs.getInt("cnumber"));
				classList.add(schoolClass);
			}
			return classList;
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
