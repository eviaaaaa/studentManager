package com.artisan.model;

// StudentBasicInfo.java (ʵ����)

import java.util.Objects;

/**
 * StudentBasicInfo ʵ���࣬��Ӧ���ݿ��е� student_basic_info ��ͼ��
 * ����ѧ�� (studentId), ���� (name), �༶��� (classId), רҵ��� (majorId), Ժϵ��� (departmentId)��
 */
public class StudentBasicInfo {
    private String studentId;     // ѧ�� (��Ӧ student.id)
    private String name;     // ���� (��Ӧ student.name)
    private String classId;   // �༶��� (��Ӧ student.cid)
    private String majorId;   // רҵ��� (��Ӧ student.mid)
    private String departmentId;   // Ժϵ��� (��Ӧ student.did)

    /**
     * Ĭ�Ϲ��캯��
     */
    public StudentBasicInfo() {
    }

    /**
     * �������Ĺ��캯��
     *
     * @param studentId     ѧ��
     * @param name     ����
     * @param classId �༶���
     * @param majorId רҵ���
     * @param departmentId Ժϵ���
     */
    public StudentBasicInfo(String studentId, String name, String classId, String majorId, String departmentId) {
        this.studentId = studentId;
        this.name = name;
        this.classId = classId;
        this.majorId = majorId;
        this.departmentId = departmentId;
    }

    // --- Getter ���� ---

    public String getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public String getClassId() {
        return classId;
    }

    public String getMajorId() {
        return majorId;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    // --- Setter ���� ---

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public void setMajorId(String majorId) {
        this.majorId = majorId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    // --- ��д equals(), hashCode(), toString() ���� ---

    /**
     * ��д equals ����������ѧ���ж����� StudentBasicInfo �����Ƿ���ȡ�
     *
     * @param o �Ƚ϶���
     * @return �������򷵻� true�����򷵻� false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentBasicInfo that = (StudentBasicInfo) o;
        return Objects.equals(studentId, that.studentId);
    }

    /**
     * ��д hashCode ����������ѧ�����ɹ�ϣ�롣
     *
     * @return ��ϣ��
     */
    @Override
    public int hashCode() {
        return Objects.hash(studentId);
    }

    /**
     * ��д toString �����������ӡ StudentBasicInfo ������Ϣ��
     *
     * @return StudentBasicInfo ������ַ�����ʾ
     */
    @Override
    public String toString() {
        return "StudentBasicInfo{" +
                "studentId='" + studentId + '\'' +
                ", name='" + name + '\'' +
                ", classId='" + classId + '\'' +
                ", majorId='" + majorId + '\'' +
                ", departmentId='" + departmentId + '\'' +
                '}';
    }
}

