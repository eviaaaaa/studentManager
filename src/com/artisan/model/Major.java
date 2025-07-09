package com.artisan.model;

import java.util.Objects;

public class Major {
    private String mid;   // 专业ID
    private String did;   // 所属院系ID (外键)
    private String mname; // 专业名称

    /**
     * 默认构造函数
     */
    public Major() {
    }

    /**
     * 带参数的构造函数
     *
     * @param mid   专业ID
     * @param did   所属院系ID
     * @param mname 专业名称
     */
    public Major(String mid, String did, String mname) {
        this.mid = mid;
        this.did = did;
        this.mname = mname;
    }

    // --- Getter 方法 ---

    public String getMid() {
        return mid;
    }

    public String getDid() {
        return did;
    }

    public String getMname() {
        return mname;
    }

    // --- Setter 方法 ---

    public void setMid(String mid) {
        this.mid = mid;
    }

    public void setDid(String did) {
        this.did = did;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    // --- 重写 equals(), hashCode(), toString() 方法 ---

    /**
     * 重写 equals 方法，基于 mid 判断两个 Major 对象是否相等。
     *
     * @param o 比较对象
     * @return 如果相等则返回 true，否则返回 false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Major major = (Major) o;
        return Objects.equals(mid, major.mid);
    }

    /**
     * 重写 hashCode 方法，基于 mid 生成哈希码。
     *
     * @return 哈希码
     */
    @Override
    public int hashCode() {
        return Objects.hash(mid);
    }

    /**
     * 重写 toString 方法，方便打印 Major 对象信息。
     *
     * @return Major 对象的字符串表示
     */
    @Override
    public String toString() {
        return "Major{" +
                "mid='" + mid + '\'' +
                ", did='" + did + '\'' +
                ", mname='" + mname + '\'' +
                '}';
    }
}
