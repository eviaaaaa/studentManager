// JdbcSchoolClassDAO.java (DAO 类)

package com.artisan.dao; // 假设你的DAO类包名为 com.artisan.dao
import com.artisan.model.SchoolClass;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * JdbcSchoolClassDAO 是一个 JDBC 数据访问对象类，用于对 SchoolClass 实体进行数据操作。
 * 数据库连接由外部传入，不再在 DAO 内部管理。
 */
public class SchoolClassDAO {

	/**
	 * 添加一个新的班级。
	 *
	 * @param con        数据库连接
	 * @param schoolClass 要添加的 SchoolClass 对象
	 * @return 影响的行数
	 * @throws SQLException 如果发生数据库访问错误
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
	 * 根据班级ID获取班级信息。
	 *
	 * @param con 数据库连接
	 * @param cid 班级ID
	 * @return 对应的 SchoolClass 对象，如果不存在则返回 null
	 * @throws SQLException 如果发生数据库访问错误
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
	 * 更新一个现有班级的信息。
	 *
	 * @param con        数据库连接
	 * @param schoolClass 包含更新信息的 SchoolClass 对象
	 * @return 如果更新成功返回 true，否则返回 false
	 * @throws SQLException 如果发生数据库访问错误
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
	 * 根据班级ID删除一个班级。
	 *
	 * @param con 数据库连接
	 * @param cid 班级ID
	 * @return 如果删除成功返回 true，否则返回 false
	 * @throws SQLException 如果发生数据库访问错误
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
	 * 获取所有班级的信息。
	 *
	 * @param con 数据库连接
	 * @return 包含所有 SchoolClass 对象的列表
	 * @throws SQLException 如果发生数据库访问错误
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
