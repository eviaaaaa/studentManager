package com.artisan.test;

import com.artisan.util.ConsoleUtil;

/**
 * ������ר�������
 * ר�Ų��Ա��߿����������޸�Ч��
 * 
 * @author llq-artisan
 * @version 1.0
 */
public class TableAlignmentTest {
    
    public static void main(String[] args) {
        ConsoleUtil.printWelcomeBanner("���������", "v2.0");
        
        // ����1: �����ı��
        testChineseTable();
        
        // ����2: ��Ӣ�ı��
        testEnglishTable();
        // ����3: ��Ӣ�Ļ�ϱ��
        testMixedTable();
        
        // ����4: �������ֵı��
        testNumberTable();
        
        // ����5: ���ı��ضϲ���
        testLongTextTable();
        
        ConsoleUtil.printSuccess("�����������ɣ�");
    }
    
    /**
     * ���Դ����ı��
     */
    private static void testChineseTable() {
        ConsoleUtil.printTitle("�����ı�����");
        
        String[] headers = {"ѧ��", "����", "�Ա�", "����", "רҵ"};
        String[][] data = {
            {"ѧ001", "����", "��", "��ʮ", "�����"},
            {"ѧ002", "����", "Ů", "ʮ��", "�������"},
            {"ѧ003", "����", "��", "��ʮһ", "��Ϣ����"},
            {"ѧ004", "����", "Ů", "��ʮ", "���繤��"}
        };
        
        ConsoleUtil.printSmartTable(headers, data);
        System.out.println();
    }
    
    /**
     * ���Դ�Ӣ�ı��
     */
    private static void testEnglishTable() {
        ConsoleUtil.printTitle("Pure English Table Test");
        
        String[] headers = {"ID", "Name", "Gender", "Age", "Major"};
        String[][] data = {
            {"001", "Zhang", "M", "20", "Computer Science"},
            {"002", "Li", "F", "19", "Software Engineering"},
            {"003", "Wang", "M", "21", "Information Management"},
            {"004", "Zhao", "F", "20", "Network Engineering"}
        };
        
        ConsoleUtil.printSmartTable(headers, data);
        System.out.println();
    }
    
    /**
     * ������Ӣ�Ļ�ϱ��
     */
    private static void testMixedTable() {
        ConsoleUtil.printTitle("��Ӣ�Ļ�ϱ�����");
        
        String[] headers = {"ID", "��������", "English Name", "����", "Status"};
        String[][] data = {
            {"001", "����", "Zhang San", "95", "����"},
            {"002", "����", "Li Si", "87", "����"},
            {"003", "����", "Wang Wu", "92", "����"},
            {"004", "����", "Zhao Liu", "88", "����"}
        };
        
        ConsoleUtil.printSmartTable(headers, data);
        System.out.println();
    }
    
    /**
     * ���԰������ֵı�񣨲����Ҷ��룩
     */
    private static void testNumberTable() {
        ConsoleUtil.printTitle("���ֶ�����Ա��");
        
        String[] headers = {"�γ�", "ѧ��", "�ɼ�", "����", "����"};
        String[][] data = {
            {"�ߵ���ѧ", "4", "95.5", "4.0", "1"},
            {"��ѧӢ��", "3", "87.0", "3.7", "15"},
            {"�������", "3", "92.5", "3.9", "5"},
            {"���Դ���", "2", "88.0", "3.8", "12"}
        };
        
        ConsoleUtil.printSmartTable(headers, data);
        System.out.println();
    }
    
    /**
     * ���Գ��ı��ض�
     */
    private static void testLongTextTable() {
        ConsoleUtil.printTitle("���ı��ضϲ���");
        
        String[] headers = {"���", "�γ�����", "�γ�����", "��ʦ", "�ص�"};
        String[][] data = {
            {"CS101", "�������ѧ����", "����һ�Ž��ܼ������ѧ���������ԭ������ſγ�", "�Ž���", "A101"},
            {"MATH201", "�ߵ���ѧA", "΢���֡����Դ�������ѧ�����γ�", "�����", "B203"},
            {"ENG101", "��ѧӢ��", "���ѧ��Ӣ����˵��д����", "����ʦ", "C301"},
            {"PHYS301", "��ѧ����ʵ��", "ͨ��ʵ������������������Ӧ��", "����ʦ", "D201"}
        };
        
        ConsoleUtil.printSmartTable(headers, data);
        System.out.println();
    }
}
