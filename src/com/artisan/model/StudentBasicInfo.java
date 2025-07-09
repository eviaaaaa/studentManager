package com.artisan.model;

// StudentBasicInfo.java (实体类)

import java.util.Objects;

/**
 * StudentBasicInfo 实体类，对应数据库中的 student_basic_info 视图。
 * 包含学号 (studentId), 姓名 (name), 班级编号 (classId), 专业编号 (majorId), 院系编号 (departmentId)。
 */
public class StudentBasicInfo {
    private String studentId;     // 学号 (对应 student.id)
    private String name;     // 姓名 (对应 student.name)
    private String classId;   // 班级编号 (对应 student.cid)
    private String majorId;   // 专业编号 (对应 student.mid)
    private String departmentId;   // 院系编号 (对应 student.did)

    /**
     * 默认构造函数
     */
    public StudentBasicInfo() {
    }

    /**
     * 带参数的构造函数
     *
     * @param studentId     学号
     * @param name     姓名
     * @param classId 班级编号
     * @param majorId 专业编号
     * @param departmentId 院系编号
     */
    public StudentBasicInfo(String studentId, String name, String classId, String majorId, String departmentId) {
        this.studentId = studentId;
        this.name = name;
        this.classId = classId;
        this.majorId = majorId;
        this.departmentId = departmentId;
    }

    // --- Getter 方法 ---

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

    // --- Setter 方法 ---

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

    // --- 重写 equals(), hashCode(), toString() 方法 ---

    /**
     * 重写 equals 方法，基于学号判断两个 StudentBasicInfo 对象是否相等。
     *
     * @param o 比较对象
     * @return 如果相等则返回 true，否则返回 false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentBasicInfo that = (StudentBasicInfo) o;
        return Objects.equals(studentId, that.studentId);
    }

    /**
     * 重写 hashCode 方法，基于学号生成哈希码。
     *
     * @return 哈希码
     */
    @Override
    public int hashCode() {
        return Objects.hash(studentId);
    }

    /**
     * 重写 toString 方法，方便打印 StudentBasicInfo 对象信息。
     *
     * @return StudentBasicInfo 对象的字符串表示
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

