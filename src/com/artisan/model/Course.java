package com.artisan.model;

// Course.java (实体类)

import java.util.Objects;

/**
 * Course 实体类，对应数据库中的 course 表。
 * 包含课程ID (kid), 课程名称 (kname), 学分 (kcredit), 和课时 (kperiod)。
 */
public class Course {
    private String kid;     // 课程ID
    private String kname;   // 课程名称
    private Integer kcredit; // 学分
    private Integer kperiod; // 课时

    /**
     * 默认构造函数
     */
    public Course() {
    }

    /**
     * 带参数的构造函数
     *
     * @param kid     课程ID
     * @param kname   课程名称
     * @param kcredit 学分
     * @param kperiod 课时
     */
    public Course(String kid, String kname, Integer kcredit, Integer kperiod) {
        this.kid = kid;
        this.kname = kname;
        this.kcredit = kcredit;
        this.kperiod = kperiod;
    }

    // --- Getter 方法 ---

    public String getKid() {
        return kid;
    }

    public String getKname() {
        return kname;
    }

    public Integer getKcredit() {
        return kcredit;
    }

    public Integer getKperiod() {
        return kperiod;
    }

    // --- Setter 方法 ---

    public void setKid(String kid) {
        this.kid = kid;
    }

    public void setKname(String kname) {
        this.kname = kname;
    }

    public void setKcredit(Integer kcredit) {
        this.kcredit = kcredit;
    }

    public void setKperiod(Integer kperiod) {
        this.kperiod = kperiod;
    }

    // --- 重写 equals(), hashCode(), toString() 方法 ---

    /**
     * 重写 equals 方法，基于 kid 判断两个 Course 对象是否相等。
     *
     * @param o 比较对象
     * @return 如果相等则返回 true，否则返回 false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Objects.equals(kid, course.kid);
    }

    /**
     * 重写 hashCode 方法，基于 kid 生成哈希码。
     *
     * @return 哈希码
     */
    @Override
    public int hashCode() {
        return Objects.hash(kid);
    }

    /**
     * 重写 toString 方法，方便打印 Course 对象信息。
     *
     * @return Course 对象的字符串表示
     */
    @Override
    public String toString() {
        return "Course{" +
                "kid='" + kid + '\'' +
                ", kname='" + kname + '\'' +
                ", kcredit=" + kcredit +
                ", kperiod=" + kperiod +
                '}';
    }
}

