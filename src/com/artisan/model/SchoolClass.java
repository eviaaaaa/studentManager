// SchoolClass.java (ʵ����)

package com.artisan.model;

import java.util.Objects;

/**
 * SchoolClass ʵ���࣬��Ӧ���ݿ��е� class ��
 * �����༶ID (cid), ����רҵID (mid), ����ԺϵID (did), �༶���� (cname), �Ͱ༶���� (cnumber)��
 */
public class SchoolClass {
    private String cid;     // �༶ID
    private String mid;     // ����רҵID (���)
    private String did;     // ����ԺϵID (���)
    private String cname;   // �༶����
    private Integer cnumber; // �༶����

    /**
     * Ĭ�Ϲ��캯��
     */
    public SchoolClass() {
    }

    /**
     * �������Ĺ��캯��
     *
     * @param cid     �༶ID
     * @param mid     ����רҵID
     * @param did     ����ԺϵID
     * @param cname   �༶����
     * @param cnumber �༶����
     */
    public SchoolClass(String cid, String mid, String did, String cname, Integer cnumber) {
        this.cid = cid;
        this.mid = mid;
        this.did = did;
        this.cname = cname;
        this.cnumber = cnumber;
    }

    // --- Getter ���� ---

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

    // --- Setter ���� ---

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

    // --- ��д equals(), hashCode(), toString() ���� ---

    /**
     * ��д equals ���������� cid �ж����� SchoolClass �����Ƿ���ȡ�
     *
     * @param o �Ƚ϶���
     * @return �������򷵻� true�����򷵻� false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SchoolClass that = (SchoolClass) o;
        return Objects.equals(cid, that.cid);
    }

    /**
     * ��д hashCode ���������� cid ���ɹ�ϣ�롣
     *
     * @return ��ϣ��
     */
    @Override
    public int hashCode() {
        return Objects.hash(cid);
    }

    /**
     * ��д toString �����������ӡ SchoolClass ������Ϣ��
     *
     * @return SchoolClass ������ַ�����ʾ
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
