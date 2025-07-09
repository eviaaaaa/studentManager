// SchoolClass.java (实体类)

package com.artisan.model;

import java.util.Objects;

/**
 * SchoolClass 实体类，对应数据库中的 class 表。
 * 包含班级ID (cid), 所属专业ID (mid), 所属院系ID (did), 班级名称 (cname), 和班级人数 (cnumber)。
 */
public class SchoolClass {
    private String cid;     // 班级ID
    private String mid;     // 所属专业ID (外键)
    private String did;     // 所属院系ID (外键)
    private String cname;   // 班级名称
    private Integer cnumber; // 班级人数

    /**
     * 默认构造函数
     */
    public SchoolClass() {
    }

    /**
     * 带参数的构造函数
     *
     * @param cid     班级ID
     * @param mid     所属专业ID
     * @param did     所属院系ID
     * @param cname   班级名称
     * @param cnumber 班级人数
     */
    public SchoolClass(String cid, String mid, String did, String cname, Integer cnumber) {
        this.cid = cid;
        this.mid = mid;
        this.did = did;
        this.cname = cname;
        this.cnumber = cnumber;
    }

    // --- Getter 方法 ---

    public String getCid() {
        return cid;
    }

    public String getMid() {
        return mid;
    }

    public String getDid() {
        return did;
    }

    public String getCname() {
        return cname;
    }

    public Integer getCnumber() {
        return cnumber;
    }

    // --- Setter 方法 ---

    public void setCid(String cid) {
        this.cid = cid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public void setDid(String did) {
        this.did = did;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public void setCnumber(Integer cnumber) {
        this.cnumber = cnumber;
    }

    // --- 重写 equals(), hashCode(), toString() 方法 ---

    /**
     * 重写 equals 方法，基于 cid 判断两个 SchoolClass 对象是否相等。
     *
     * @param o 比较对象
     * @return 如果相等则返回 true，否则返回 false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SchoolClass that = (SchoolClass) o;
        return Objects.equals(cid, that.cid);
    }

    /**
     * 重写 hashCode 方法，基于 cid 生成哈希码。
     *
     * @return 哈希码
     */
    @Override
    public int hashCode() {
        return Objects.hash(cid);
    }

    /**
     * 重写 toString 方法，方便打印 SchoolClass 对象信息。
     *
     * @return SchoolClass 对象的字符串表示
     */
    @Override
    public String toString() {
        return "SchoolClass{" +
                "cid='" + cid + '\'' +
                ", mid='" + mid + '\'' +
                ", did='" + did + '\'' +
                ", cname='" + cname + '\'' +
                ", cnumber=" + cnumber +
                '}';
    }
}
