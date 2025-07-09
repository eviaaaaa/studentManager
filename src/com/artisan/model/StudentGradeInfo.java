// StudentGradeInfo.java (实体类)

package com.artisan.model; // Consistent package for entities

import java.util.Objects;

/**
 * StudentGradeInfo 实体类，对应数据库中的 student_grade_info 视图。
 * 包含学号 (studentId), 姓名 (studentName), 课程 (courseName), 成绩 (gradeValue)。
 */
public class StudentGradeInfo {
    private String studentId;   // 学号
    private String studentName;   // 姓名
    private String courseName;   // 课程名称
    private Integer gradeValue; // 成绩

    /**
     * 默认构造函数
     */
    public StudentGradeInfo() {
    }

    /**
     * 带参数的构造函数
     *
     * @param studentId 学号
     * @param studentName 姓名
     * @param courseName 课程名称
     * @param gradeValue 成绩
     */
    public StudentGradeInfo(String studentId, String studentName, String courseName, Integer gradeValue) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.courseName = courseName;
        this.gradeValue = gradeValue;
    }

    // --- Getter 方法 ---

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

    // --- Setter 方法 ---

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

    // --- 重写 equals(), hashCode(), toString() 方法 ---

    /**
     * 重写 equals 方法，基于 studentId 和 courseName 判断两个 StudentGradeInfo 对象是否相等。
     * (假设 studentId 和 courseName 的组合在视图中是唯一的，或者足以区分记录)
     *
     * @param o 比较对象
     * @return 如果相等则返回 true，否则返回 false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentGradeInfo that = (StudentGradeInfo) o;
        return Objects.equals(studentId, that.studentId) && Objects.equals(courseName, that.courseName);
    }

    /**
     * 重写 hashCode 方法，基于 studentId 和 courseName 生成哈希码。
     *
     * @return 哈希码
     */
    @Override
    public int hashCode() {
        return Objects.hash(studentId, courseName);
    }

    /**
     * 重写 toString 方法，方便打印 StudentGradeInfo 对象信息。
     *
     * @return StudentGradeInfo 对象的字符串表示
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
