package com.artisan.model;

import java.util.Date;
import java.util.Objects;

/**
 * 学生实体
 * @author llq-artisan
 *
 */
public class Student {
	private String id;       // 学生ID
	private String name;     // 姓名
	private String sex;      // 性别
	private String cid;      // 班级ID (外键)
	private String mid;      // 专业ID (外键)
	private String did;      // 院系ID (外键)
	private String nation;   // 民族
	private Integer age;     // 年龄
	private Date birthday;   // 生日
	private String location; // 所在地
	private Date enroll;     // 入学日期

	/**
	 * 默认构造函数
	 */
	public Student() {
	}

	/**
	 * 带参数的构造函数
	 *
	 * @param id       学生ID
	 * @param name     姓名
	 * @param sex      性别
	 * @param cid      班级ID
	 * @param mid      专业ID
	 * @param did      院系ID
	 * @param nation   民族
	 * @param age      年龄
	 * @param birthday 生日
	 * @param location 所在地
	 * @param enroll   入学日期
	 */
	public Student(String id, String name, String sex, String cid, String mid, String did,
				   String nation, Integer age, Date birthday, String location, Date enroll) {
		this.id = id;
		this.name = name;
		this.sex = sex;
		this.cid = cid;
		this.mid = mid;
		this.did = did;
		this.nation = nation;
		this.age = age;
		this.birthday = birthday;
		this.location = location;
		this.enroll = enroll;
	}

	// --- Getter 方法 ---

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getSex() {
		return sex;
	}

	public String getCid() {
		return cid;
	}

	public String getMid() {
		return mid;
	}

	public String getDid() {
		return did;
	}

	public String getNation() {
		return nation;
	}

	public Integer getAge() {
		return age;
	}

	public Date getBirthday() {
		return birthday;
	}

	public String getLocation() {
		return location;
	}

	public Date getEnroll() {
		return enroll;
	}

	// --- Setter 方法 ---

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public void setDid(String did) {
		this.did = did;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void setEnroll(Date enroll) {
		this.enroll = enroll;
	}

	// --- 重写 equals(), hashCode(), toString() 方法 ---

	/**
	 * 重写 equals 方法，基于 id 判断两个 Student 对象是否相等。
	 *
	 * @param o 比较对象
	 * @return 如果相等则返回 true，否则返回 false
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Student student = (Student) o;
		return Objects.equals(id, student.id);
	}

	/**
	 * 重写 hashCode 方法，基于 id 生成哈希码。
	 *
	 * @return 哈希码
	 */
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	/**
	 * 重写 toString 方法，方便打印 Student 对象信息。
	 *
	 * @return Student 对象的字符串表示
	 */
	@Override
	public String toString() {
		return "Student{" +
				"id='" + id + '\'' +
				", name='" + name + '\'' +
				", sex='" + sex + '\'' +
				", cid='" + cid + '\'' +
				", mid='" + mid + '\'' +
				", did='" + did + '\'' +
				", nation='" + nation + '\'' +
				", age=" + age +
				", birthday=" + birthday +
				", location='" + location + '\'' +
				", enroll=" + enroll +
				'}';
	}
}