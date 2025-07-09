package com.artisan.model;

// AwardPunish.java (ʵ����)

import java.util.Objects;

/**
 * AwardPunish ʵ���࣬��Ӧ���ݿ��е� award_punish ��
 * ��������ID (aid), ѧ��ID (id), רҵID (mid), ԺϵID (did), �������� (aname), �ͽ�����Ŀ (aproject)��
 */
public class AwardPunish {
    private String aid;      // ����ID
    private String id;       // ѧ��ID (���)
    private String mid;      // רҵID (���)
    private String did;      // ԺϵID (���)
    private String aname;    // �������� (���磺�������ͷ�)
    private String aproject; // ������Ŀ (��������)

    /**
     * Ĭ�Ϲ��캯��
     */
    public AwardPunish() {
    }

    /**
     * �������Ĺ��캯��
     *
     * @param aid      ����ID
     * @param id       ѧ��ID
     * @param mid      רҵID
     * @param did      ԺϵID
     * @param aname    ��������
     * @param aproject ������Ŀ
     */
    public AwardPunish(String aid, String id, String mid, String did, String aname, String aproject) {
        this.aid = aid;
        this.id = id;
        this.mid = mid;
        this.did = did;
        this.aname = aname;
        this.aproject = aproject;
    }

    // --- Getter ���� ---

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

    // --- Setter ���� ---

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

    // --- ��д equals(), hashCode(), toString() ���� ---

    /**
     * ��д equals ���������� aid �ж����� AwardPunish �����Ƿ���ȡ�
     *
     * @param o �Ƚ϶���
     * @return �������򷵻� true�����򷵻� false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AwardPunish that = (AwardPunish) o;
        return Objects.equals(aid, that.aid);
    }

    /**
     * ��д hashCode ���������� aid ���ɹ�ϣ�롣
     *
     * @return ��ϣ��
     */
    @Override
    public int hashCode() {
        return Objects.hash(aid);
    }

    /**
     * ��д toString �����������ӡ AwardPunish ������Ϣ��
     *
     * @return AwardPunish ������ַ�����ʾ
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
