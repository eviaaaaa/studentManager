package com.artisan.model;

import java.util.Objects;

public class Major {
    private String mid;   // רҵID
    private String did;   // ����ԺϵID (���)
    private String mname; // רҵ����

    /**
     * Ĭ�Ϲ��캯��
     */
    public Major() {
    }

    /**
     * �������Ĺ��캯��
     *
     * @param mid   רҵID
     * @param did   ����ԺϵID
     * @param mname רҵ����
     */
    public Major(String mid, String did, String mname) {
        this.mid = mid;
        this.did = did;
        this.mname = mname;
    }

    // --- Getter ���� ---

    public String getMid() {
        return mid;
    }

    public String getDid() {
        return did;
    }

    public String getMname() {
        return mname;
    }

    // --- Setter ���� ---

    public void setMid(String mid) {
        this.mid = mid;
    }

    public void setDid(String did) {
        this.did = did;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    // --- ��д equals(), hashCode(), toString() ���� ---

    /**
     * ��д equals ���������� mid �ж����� Major �����Ƿ���ȡ�
     *
     * @param o �Ƚ϶���
     * @return �������򷵻� true�����򷵻� false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Major major = (Major) o;
        return Objects.equals(mid, major.mid);
    }

    /**
     * ��д hashCode ���������� mid ���ɹ�ϣ�롣
     *
     * @return ��ϣ��
     */
    @Override
    public int hashCode() {
        return Objects.hash(mid);
    }

    /**
     * ��д toString �����������ӡ Major ������Ϣ��
     *
     * @return Major ������ַ�����ʾ
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
