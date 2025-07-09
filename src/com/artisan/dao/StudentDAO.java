// JdbcStudentDAO.java (DAO 类)

package com.artisan.dao; // 假设你的DAO类包名为 com.artisan.dao


import com.artisan.model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * JdbcStudentDAO 是一个 JDBC 数据访问对象类，用于对 Student 实体进行数据操作。
 * 数据库连接由外部传入，不再在 DAO 内部管理。
 */
public class StudentDAO {

	/**
	 * 添加一个新的学生。
	 *
	 * @param con     数据库连接
	 * @param student 要添加的 Student 对象
	 * @return 影响的行数
	 * @throws SQLException 如果发生数据库访问错误
	 */
	public int addStudent(Connection con, Student student) throws SQLException {
		String sql = "INSERT INTO student (id, name, sex, cid, mid, did, nation, age, birthday, location, enroll) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement pstmt = null;
		int rowsAffected = 0;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, student.getId());
			pstmt.setString(2, student.getName());
			pstmt.setString(3, student.getSex());
			pstmt.setString(4, student.getCid());
			pstmt.setString(5, student.getMid());
			pstmt.setString(6, student.getDid());
			pstmt.setString(7, student.getNation());
			pstmt.setInt(8, student.getAge());
			pstmt.setDate(9, new Date(student.getBirthday().getTime())); // 转换 util.Date 为 sql.Date
			pstmt.setString(10, student.getLocation());
			pstmt.setDate(11, new Date(student.getEnroll().getTime())); // 转换 util.Date 为 sql.Date
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
	 * 根据学生ID获取学生信息。
	 *
	 * @param con 数据库连接
	 * @param id  学生ID
	 * @return 对应的 Student 对象，如果不存在则返回 null
	 * @throws SQLException 如果发生数据库访问错误
	 */
	public Student getStudentById(Connection con, String id) throws SQLException {
		String sql = "SELECT id, name, sex, cid, mid, did, nation, age, birthday, location, enroll FROM student WHERE id = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Student student = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				student = new Student();
				student.setId(rs.getString("id"));
				student.setName(rs.getString("name"));
				student.setSex(rs.getString("sex"));
				student.setCid(rs.getString("cid"));
				student.setMid(rs.getString("mid"));
				student.setDid(rs.getString("did"));
				student.setNation(rs.getString("nation"));
				student.setAge(rs.getInt("age"));
				student.setBirthday(rs.getDate("birthday")); // 直接获取 sql.Date，它兼容 util.Date
				student.setLocation(rs.getString("location"));
				student.setEnroll(rs.getDate("enroll"));     // 直接获取 sql.Date，它兼容 util.Date
			}
			return student;
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
	 * 更新一个现有学生的信息。
	 *
	 * @param con     数据库连接
	 * @param student 包含更新信息的 Student 对象
	 * @return 如果更新成功返回 true，否则返回 false
	 * @throws SQLException 如果发生数据库访问错误
	 */
	public boolean updateStudent(Connection con, Student student) throws SQLException {
		String sql = "UPDATE student SET name = ?, sex = ?, cid = ?, mid = ?, did = ?, nation = ?, age = ?, birthday = ?, location = ?, enroll = ? WHERE id = ?";
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, student.getName());
			pstmt.setString(2, student.getSex());
			pstmt.setString(3, student.getCid());
			pstmt.setString(4, student.getMid());
			pstmt.setString(5, student.getDid());
			pstmt.setString(6, student.getNation());
			pstmt.setInt(7, student.getAge());
			pstmt.setDate(8, new Date(student.getBirthday().getTime())); // 转换 util.Date 为 sql.Date
			pstmt.setString(9, student.getLocation());
			pstmt.setDate(10, new Date(student.getEnroll().getTime())); // 转换 util.Date 为 sql.Date
			pstmt.setString(11, student.getId());
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
	 * 根据学生ID删除一个学生。
	 *
	 * @param con 数据库连接
	 * @param id  学生ID
	 * @return 如果删除成功返回 true，否则返回 false
	 * @throws SQLException 如果发生数据库访问错误
	 */
	public boolean deleteStudent(Connection con, String id) throws SQLException {
		String sql = "DELETE FROM student WHERE id = ?";
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
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
	 * 高级查询学生信息。
	 * 根据传入的条件（最大年龄、班级名称、家庭住址关键词）过滤学生。
	 *
	 * @param con             数据库连接
	 * @param maxAge          最大年龄，如果为null则不限制
	 * @param className       班级名称，如果为null则不限制
	 * @param locationKeyword 家庭住址关键词，如果为null则不限制
	 * @return 符合条件的学生列表
	 * @throws SQLException 如果发生数据库访问错误
	 */
	public List<Student> queryStudentsAdvanced(Connection con, Integer maxAge, String className, String locationKeyword) throws SQLException {
		StringBuilder sqlBuilder = new StringBuilder("SELECT s.id, s.name, s.sex, s.cid, s.mid, s.did, s.nation, s.age, s.birthday, s.location, s.enroll FROM student s");
		List<Object> params = new ArrayList<>();
		boolean whereAdded = false;

		// 如果有班级名称条件，需要JOIN class表
		if (className != null && !className.isEmpty()) {
			sqlBuilder.append(" JOIN class c ON s.cid = c.cid");
		}

		if (maxAge != null) {
			sqlBuilder.append(whereAdded ? " AND" : " WHERE").append(" s.age < ?");
			params.add(maxAge);
			whereAdded = true;
		}

		if (className != null && !className.isEmpty()) {
			sqlBuilder.append(whereAdded ? " AND" : " WHERE").append(" c.cname = ?");
			params.add(className);
			whereAdded = true;
		}

		if (locationKeyword != null && !locationKeyword.isEmpty()) {
			sqlBuilder.append(whereAdded ? " AND" : " WHERE").append(" s.location LIKE ?");
			params.add("%" + locationKeyword + "%");
			whereAdded = true;
		}

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Student> studentList = new ArrayList<>();

		try {
			pstmt = con.prepareStatement(sqlBuilder.toString());
			for (int i = 0; i < params.size(); i++) {
				Object param = params.get(i);
				if (param instanceof Integer) {
					pstmt.setInt(i + 1, (Integer) param);
				} else if (param instanceof String) {
					pstmt.setString(i + 1, (String) param);
				}
				// 可以根据需要添加其他参数类型
			}

			rs = pstmt.executeQuery();
			while (rs.next()) {
				Student student = new Student();
				student.setId(rs.getString("id"));
				student.setName(rs.getString("name"));
				student.setSex(rs.getString("sex"));
				student.setCid(rs.getString("cid"));
				student.setMid(rs.getString("mid"));
				student.setDid(rs.getString("did"));
				student.setNation(rs.getString("nation"));
				student.setAge(rs.getInt("age"));
				student.setBirthday(rs.getDate("birthday"));
				student.setLocation(rs.getString("location"));
				student.setEnroll(rs.getDate("enroll"));
				studentList.add(student);
			}
			return studentList;
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
	 * 获取所有学生的信息。
	 *
	 * @param con 数据库连接
	 * @return 包含所有 Student 对象的列表
	 * @throws SQLException 如果发生数据库访问错误
	 */
	public List<Student> getAllStudents(Connection con) throws SQLException {
		String sql = "SELECT id, name, sex, cid, mid, did, nation, age, birthday, location, enroll FROM student";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Student> studentList = new ArrayList<>();
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Student student = new Student();
				student.setId(rs.getString("id"));
				student.setName(rs.getString("name"));
				student.setSex(rs.getString("sex"));
				student.setCid(rs.getString("cid"));
				student.setMid(rs.getString("mid"));
				student.setDid(rs.getString("did"));
				student.setNation(rs.getString("nation"));
				student.setAge(rs.getInt("age"));
				student.setBirthday(rs.getDate("birthday"));
				student.setLocation(rs.getString("location"));
				student.setEnroll(rs.getDate("enroll"));
				studentList.add(student);
			}
			return studentList;
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
