package com.artisan.model;

// AwardPunish.java (实体类)

import java.util.Objects;

/**
 * AwardPunish 实体类，对应数据库中的 award_punish 表。
 * 包含奖惩ID (aid), 学生ID (id), 专业ID (mid), 院系ID (did), 奖惩名称 (aname), 和奖惩项目 (aproject)。
 */
public class AwardPunish {
    private String aid;      // 奖惩ID
    private String id;       // 学生ID (外键)
    private String mid;      // 专业ID (外键)
    private String did;      // 院系ID (外键)
    private String aname;    // 奖惩名称 (例如：奖励、惩罚)
    private String aproject; // 奖惩项目 (具体内容)

    /**
     * 默认构造函数
     */
    public AwardPunish() {
    }

    /**
     * 带参数的构造函数
     *
     * @param aid      奖惩ID
     * @param id       学生ID
     * @param mid      专业ID
     * @param did      院系ID
     * @param aname    奖惩名称
     * @param aproject 奖惩项目
     */
    public AwardPunish(String aid, String id, String mid, String did, String aname, String aproject) {
        this.aid = aid;
        this.id = id;
        this.mid = mid;
        this.did = did;
        this.aname = aname;
        this.aproject = aproject;
    }

    // --- Getter 方法 ---

    public String getAid() {
        return aid;
    }

    public String getId() {
        return id;
    }

    public String getMid() {
        return mid;
    }

    public String getDid() {
        return did;
    }

    public String getAname() {
        return aname;
    }

    public String getAproject() {
        return aproject;
    }

    // --- Setter 方法 ---

    public void setAid(String aid) {
        this.aid = aid;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public void setDid(String did) {
        this.did = did;
    }

    public void setAname(String aname) {
        this.aname = aname;
    }

    public void setAproject(String aproject) {
        this.aproject = aproject;
    }

    // --- 重写 equals(), hashCode(), toString() 方法 ---

    /**
     * 重写 equals 方法，基于 aid 判断两个 AwardPunish 对象是否相等。
     *
     * @param o 比较对象
     * @return 如果相等则返回 true，否则返回 false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AwardPunish that = (AwardPunish) o;
        return Objects.equals(aid, that.aid);
    }

    /**
     * 重写 hashCode 方法，基于 aid 生成哈希码。
     *
     * @return 哈希码
     */
    @Override
    public int hashCode() {
        return Objects.hash(aid);
    }

    /**
     * 重写 toString 方法，方便打印 AwardPunish 对象信息。
     *
     * @return AwardPunish 对象的字符串表示
     */
    @Override
    public String toString() {
        return "AwardPunish{" +
                "aid='" + aid + '\'' +
                ", id='" + id + '\'' +
                ", mid='" + mid + '\'' +
                ", did='" + did + '\'' +
                ", aname='" + aname + '\'' +
                ", aproject='" + aproject + '\'' +
                '}';
    }
}
