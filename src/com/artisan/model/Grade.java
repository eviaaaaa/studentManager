package com.artisan.model;

// Grade.java (ʵ����)

import java.util.Objects;

/**
 * Grade ʵ���࣬��Ӧ���ݿ��е� grade ��
 * �����γ�ID (kid), ѧ��ID (id), �ͳɼ� (ggrade)��
 * ע�⣺grade ��û���Լ���������ͨ���� (kid, id) �����Ϊ����������
 */
public class Grade {
    private String kid;     // �γ�ID (���)
    private String id;      // ѧ��ID (���)
    private Integer ggrade; // �ɼ�

    /**
     * Ĭ�Ϲ��캯��
     */
    public Grade() {
    }

    /**
     * �������Ĺ��캯��
     *
     * @param kid    �γ�ID
     * @param id     ѧ��ID
     * @param ggrade �ɼ�
     */
    public Grade(String kid, String id, Integer ggrade) {
        this.kid = kid;
        this.id = id;
        this.ggrade = ggrade;
    }

    // --- Getter ���� ---

    public String getKid() {
        return kid;
    }

    public String getId() {
        return id;
    }

    public Integer getGgrade() {
        return ggrade;
    }

    // --- Setter ���� ---

    public void setKid(String kid) {
        this.kid = kid;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setGgrade(Integer ggrade) {
        this.ggrade = ggrade;
    }

    // --- ��д equals(), hashCode(), toString() ���� ---

    /**
     * ��д equals ���������� kid �� id �ж����� Grade �����Ƿ���ȡ�
     *
     * @param o �Ƚ϶���
     * @return �������򷵻� true�����򷵻� false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Grade grade = (Grade) o;
        return Objects.equals(kid, grade.kid) && Objects.equals(id, grade.id);
    }

    /**
     * ��д hashCode ���������� kid �� id ���ɹ�ϣ�롣
     *
     * @return ��ϣ��
     */
    @Override
    public int hashCode() {
        return Objects.hash(kid, id);
    }

    /**
     * ��д toString �����������ӡ Grade ������Ϣ��
     *
     * @return Grade ������ַ�����ʾ
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

