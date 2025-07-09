package com.artisan.model;

import java.util.Date;
import java.util.Objects;

/**
 * ѧ��ʵ��
 * @author llq-artisan
 *
 */
public class Student {
	private String id;       // ѧ��ID
	private String name;     // ����
	private String sex;      // �Ա�
	private String cid;      // �༶ID (���)
	private String mid;      // רҵID (���)
	private String did;      // ԺϵID (���)
	private String nation;   // ����
	private Integer age;     // ����
	private Date birthday;   // ����
	private String location; // ���ڵ�
	private Date enroll;     // ��ѧ����

	/**
	 * Ĭ�Ϲ��캯��
	 */
	public Student() {
	}

	/**
	 * �������Ĺ��캯��
	 *
	 * @param id       ѧ��ID
	 * @param name     ����
	 * @param sex      �Ա�
	 * @param cid      �༶ID
	 * @param mid      רҵID
	 * @param did      ԺϵID
	 * @param nation   ����
	 * @param age      ����
	 * @param birthday ����
	 * @param location ���ڵ�
	 * @param enroll   ��ѧ����
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

	// --- Getter ���� ---

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

	// --- Setter ���� ---

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

	// --- ��д equals(), hashCode(), toString() ���� ---

	/**
	 * ��д equals ���������� id �ж����� Student �����Ƿ���ȡ�
	 *
	 * @param o �Ƚ϶���
	 * @return �������򷵻� true�����򷵻� false
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Student student = (Student) o;
		return Objects.equals(id, student.id);
	}

	/**
	 * ��д hashCode ���������� id ���ɹ�ϣ�롣
	 *
	 * @return ��ϣ��
	 */
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	/**
	 * ��д toString �����������ӡ Student ������Ϣ��
	 *
	 * @return Student ������ַ�����ʾ
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