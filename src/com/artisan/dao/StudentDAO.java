// JdbcStudentDAO.java (DAO ��)

package com.artisan.dao; // �������DAO�����Ϊ com.artisan.dao


import com.artisan.model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * JdbcStudentDAO ��һ�� JDBC ���ݷ��ʶ����࣬���ڶ� Student ʵ��������ݲ�����
 * ���ݿ��������ⲿ���룬������ DAO �ڲ�����
 */
public class StudentDAO {

	/**
	 * ���һ���µ�ѧ����
	 *
	 * @param con     ���ݿ�����
	 * @param student Ҫ��ӵ� Student ����
	 * @return Ӱ�������
	 * @throws SQLException ����������ݿ���ʴ���
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
			pstmt.setDate(9, new Date(student.getBirthday().getTime())); // ת�� util.Date Ϊ sql.Date
			pstmt.setString(10, student.getLocation());
			pstmt.setDate(11, new Date(student.getEnroll().getTime())); // ת�� util.Date Ϊ sql.Date
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
	 * ����ѧ��ID��ȡѧ����Ϣ��
	 *
	 * @param con ���ݿ�����
	 * @param id  ѧ��ID
	 * @return ��Ӧ�� Student ��������������򷵻� null
	 * @throws SQLException ����������ݿ���ʴ���
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
				student.setBirthday(rs.getDate("birthday")); // ֱ�ӻ�ȡ sql.Date�������� util.Date
				student.setLocation(rs.getString("location"));
				student.setEnroll(rs.getDate("enroll"));     // ֱ�ӻ�ȡ sql.Date�������� util.Date
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
	 * ����һ������ѧ������Ϣ��
	 *
	 * @param con     ���ݿ�����
	 * @param student ����������Ϣ�� Student ����
	 * @return ������³ɹ����� true�����򷵻� false
	 * @throws SQLException ����������ݿ���ʴ���
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
			pstmt.setDate(8, new Date(student.getBirthday().getTime())); // ת�� util.Date Ϊ sql.Date
			pstmt.setString(9, student.getLocation());
			pstmt.setDate(10, new Date(student.getEnroll().getTime())); // ת�� util.Date Ϊ sql.Date
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
	 * ����ѧ��IDɾ��һ��ѧ����
	 *
	 * @param con ���ݿ�����
	 * @param id  ѧ��ID
	 * @return ���ɾ���ɹ����� true�����򷵻� false
	 * @throws SQLException ����������ݿ���ʴ���
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
	 * �߼���ѯѧ����Ϣ��
	 * ���ݴ����������������䡢�༶���ơ���ͥסַ�ؼ��ʣ�����ѧ����
	 *
	 * @param con             ���ݿ�����
	 * @param maxAge          ������䣬���Ϊnull������
	 * @param className       �༶���ƣ����Ϊnull������
	 * @param locationKeyword ��ͥסַ�ؼ��ʣ����Ϊnull������
	 * @return ����������ѧ���б�
	 * @throws SQLException ����������ݿ���ʴ���
	 */
	public List<Student> queryStudentsAdvanced(Connection con, Integer maxAge, String className, String locationKeyword) throws SQLException {
		StringBuilder sqlBuilder = new StringBuilder("SELECT s.id, s.name, s.sex, s.cid, s.mid, s.did, s.nation, s.age, s.birthday, s.location, s.enroll FROM student s");
		List<Object> params = new ArrayList<>();
		boolean whereAdded = false;

		// ����а༶������������ҪJOIN class��
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
				// ���Ը�����Ҫ���������������
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
	 * ��ȡ����ѧ������Ϣ��
	 *
	 * @param con ���ݿ�����
	 * @return �������� Student ������б�
	 * @throws SQLException ����������ݿ���ʴ���
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
