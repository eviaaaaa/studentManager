package com.artisan.model;

// Grade.java (实体类)

import java.util.Objects;

/**
 * Grade 实体类，对应数据库中的 grade 表。
 * 包含课程ID (kid), 学生ID (id), 和成绩 (ggrade)。
 * 注意：grade 表没有自己的主键，通常是 (kid, id) 组合作为联合主键。
 */
public class Grade {
    private String kid;     // 课程ID (外键)
    private String id;      // 学生ID (外键)
    private Integer ggrade; // 成绩

    /**
     * 默认构造函数
     */
    public Grade() {
    }

    /**
     * 带参数的构造函数
     *
     * @param kid    课程ID
     * @param id     学生ID
     * @param ggrade 成绩
     */
    public Grade(String kid, String id, Integer ggrade) {
        this.kid = kid;
        this.id = id;
        this.ggrade = ggrade;
    }

    // --- Getter 方法 ---

    public String getKid() {
        return kid;
    }

    public String getId() {
        return id;
    }

    public Integer getGgrade() {
        return ggrade;
    }

    // --- Setter 方法 ---

    public void setKid(String kid) {
        this.kid = kid;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setGgrade(Integer ggrade) {
        this.ggrade = ggrade;
    }

    // --- 重写 equals(), hashCode(), toString() 方法 ---

    /**
     * 重写 equals 方法，基于 kid 和 id 判断两个 Grade 对象是否相等。
     *
     * @param o 比较对象
     * @return 如果相等则返回 true，否则返回 false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Grade grade = (Grade) o;
        return Objects.equals(kid, grade.kid) && Objects.equals(id, grade.id);
    }

    /**
     * 重写 hashCode 方法，基于 kid 和 id 生成哈希码。
     *
     * @return 哈希码
     */
    @Override
    public int hashCode() {
        return Objects.hash(kid, id);
    }

    /**
     * 重写 toString 方法，方便打印 Grade 对象信息。
     *
     * @return Grade 对象的字符串表示
     */
    @Override
    public String toString() {
        return "Grade{" +
                "kid='" + kid + '\'' +
                ", id='" + id + '\'' +
                ", ggrade=" + ggrade +
                '}';
    }
}

