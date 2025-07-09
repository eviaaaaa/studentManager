// StudentGradeInfo.java (ʵ����)

package com.artisan.model; // Consistent package for entities

import java.util.Objects;

/**
 * StudentGradeInfo ʵ���࣬��Ӧ���ݿ��е� student_grade_info ��ͼ��
 * ����ѧ�� (studentId), ���� (studentName), �γ� (courseName), �ɼ� (gradeValue)��
 */
public class StudentGradeInfo {
    private String studentId;   // ѧ��
    private String studentName;   // ����
    private String courseName;   // �γ�����
    private Integer gradeValue; // �ɼ�

    /**
     * Ĭ�Ϲ��캯��
     */
    public StudentGradeInfo() {
    }

    /**
     * �������Ĺ��캯��
     *
     * @param studentId ѧ��
     * @param studentName ����
     * @param courseName �γ�����
     * @param gradeValue �ɼ�
     */
    public StudentGradeInfo(String studentId, String studentName, String courseName, Integer gradeValue) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.courseName = courseName;
        this.gradeValue = gradeValue;
    }

    // --- Getter ���� ---

    public String getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getCourseName() {
        return courseName;
    }

    public Integer getGradeValue() {
        return gradeValue;
    }

    // --- Setter ���� ---

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setGradeValue(Integer gradeValue) {
        this.gradeValue = gradeValue;
    }

    // --- ��д equals(), hashCode(), toString() ���� ---

    /**
     * ��д equals ���������� studentId �� courseName �ж����� StudentGradeInfo �����Ƿ���ȡ�
     * (���� studentId �� courseName ���������ͼ����Ψһ�ģ������������ּ�¼)
     *
     * @param o �Ƚ϶���
     * @return �������򷵻� true�����򷵻� false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentGradeInfo that = (StudentGradeInfo) o;
        return Objects.equals(studentId, that.studentId) && Objects.equals(courseName, that.courseName);
    }

    /**
     * ��д hashCode ���������� studentId �� courseName ���ɹ�ϣ�롣
     *
     * @return ��ϣ��
     */
    @Override
    public int hashCode() {
        return Objects.hash(studentId, courseName);
    }

    /**
     * ��д toString �����������ӡ StudentGradeInfo ������Ϣ��
     *
     * @return StudentGradeInfo ������ַ�����ʾ
     */
    @Override
    public String toString() {
        return "StudentGradeInfo{" +
                "studentId='" + studentId + '\'' +
                ", studentName='" + studentName + '\'' +
                ", courseName='" + courseName + '\'' +
                ", gradeValue=" + gradeValue +
                '}';
    }
}
