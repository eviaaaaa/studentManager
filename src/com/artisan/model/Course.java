package com.artisan.model;

// Course.java (ʵ����)

import java.util.Objects;

/**
 * Course ʵ���࣬��Ӧ���ݿ��е� course ��
 * �����γ�ID (kid), �γ����� (kname), ѧ�� (kcredit), �Ϳ�ʱ (kperiod)��
 */
public class Course {
    private String kid;     // �γ�ID
    private String kname;   // �γ�����
    private Integer kcredit; // ѧ��
    private Integer kperiod; // ��ʱ

    /**
     * Ĭ�Ϲ��캯��
     */
    public Course() {
    }

    /**
     * �������Ĺ��캯��
     *
     * @param kid     �γ�ID
     * @param kname   �γ�����
     * @param kcredit ѧ��
     * @param kperiod ��ʱ
     */
    public Course(String kid, String kname, Integer kcredit, Integer kperiod) {
        this.kid = kid;
        this.kname = kname;
        this.kcredit = kcredit;
        this.kperiod = kperiod;
    }

    // --- Getter ���� ---

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

    // --- Setter ���� ---

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

    // --- ��д equals(), hashCode(), toString() ���� ---

    /**
     * ��д equals ���������� kid �ж����� Course �����Ƿ���ȡ�
     *
     * @param o �Ƚ϶���
     * @return �������򷵻� true�����򷵻� false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Objects.equals(kid, course.kid);
    }

    /**
     * ��д hashCode ���������� kid ���ɹ�ϣ�롣
     *
     * @return ��ϣ��
     */
    @Override
    public int hashCode() {
        return Objects.hash(kid);
    }

    /**
     * ��д toString �����������ӡ Course ������Ϣ��
     *
     * @return Course ������ַ�����ʾ
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

