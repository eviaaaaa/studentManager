package com.artisan.test;

import com.artisan.util.ConsoleUtil;

/**
 * �������������
 * ȫ���������UI����Ķ���Ч��
 * 
 * @author llq-artisan
 * @version 1.0
 */
public class CompleteAlignmentTest {
    
    public static void main(String[] args) {
        System.out.println("=== ����������� ===");
        System.out.println("����ϸ������б߿��Ƿ���ȫ����");
        System.out.println();
        
        // 1. ���Ի�ӭ���
        testWelcomeBanner();
        
        // 2. ���Ա���
        testTitles();
        
        // 3. ���Բ˵�
        testMenus();
        
        // 4. ���Ա��
        testTables();
        
        // 5. ���Էָ���
        testSeparators();
        
        System.out.println("=== ������� ===");
        System.out.println("������б߿���ȫ���룬˵���޸��ɹ���");
    }
    
    /**
     * ���Ի�ӭ���
     */
    private static void testWelcomeBanner() {
        System.out.println("1. ��ӭ�������:");
        ConsoleUtil.printWelcomeBanner("ѧ��ѧ������ϵͳ", "v2.0");
    }
    
    /**
     * ���Ա���
     */
    private static void testTitles() {
        System.out.println("2. ����������:");
        
        ConsoleUtil.printTitle("�̱���");
        System.out.println();
        
        ConsoleUtil.printTitle("�еȳ��ȵı���");
        System.out.println();
        
        ConsoleUtil.printTitle("����һ���ܳ��ı����������Զ���");
        System.out.println();
        
        ConsoleUtil.printTitle("Mixed English ������ Title");
        System.out.println();
    }
    
    /**
     * ���Բ˵�
     */
    private static void testMenus() {
        System.out.println("3. �˵��������:");
        
        String[] menuOptions1 = {
            "1 - ��ѡ��",
            "2 - �еȳ���ѡ��",
            "3 - ����һ���ܳ��Ĳ˵�ѡ��",
            "0 - �˳�"
        };
        ConsoleUtil.printMenu("���Բ˵�", menuOptions1);
        System.out.println();
        System.out.println();
        
        String[] menuOptions2 = {
            "1 - Query Student Info",
            "2 - ��ѯѧ���ɼ���Ϣ",
            "3 - Mixed English ������ѡ��",
            "0 - �������˵�"
        };
        ConsoleUtil.printMenu("������Բ˵�", menuOptions2);
        System.out.println();
        System.out.println();
    }
    
    /**
     * ���Ա��
     */
    private static void testTables() {
        System.out.println("4. ���������:");
        
        // �򵥱��
        String[] headers1 = {"ID", "Name", "Age"};
        String[][] data1 = {
            {"001", "����", "20"},
            {"002", "����", "21"}
        };
        ConsoleUtil.printSmartTable(headers1, data1);
        System.out.println();
        
        // ���ӱ��
        String[] headers2 = {"ѧ��", "����", "English Name", "�ɼ�", "��ע"};
        String[][] data2 = {
            {"2021001", "����", "Zhang San", "95", "����ѧ��"},
            {"2021002", "����", "Li Si", "87", "����"},
            {"2021003", "����", "Wang Wu", "92", "��������"},
            {"2021004", "����", "Zhao Liu", "88", "Good performance"}
        };
        ConsoleUtil.printSmartTable(headers2, data2);
        System.out.println();
        
        // ���ı����
        String[] headers3 = {"�γ�", "����", "ѧ��"};
        String[][] data3 = {
            {"�ߵ���ѧ", "����һ����Ҫ����ѧ�����γ�", "4"},
            {"��ѧӢ��", "English comprehensive course", "3"},
            {"�������ѧ", "�������ѧ�뼼��רҵ���Ŀγ�", "3"}
        };
        ConsoleUtil.printSmartTable(headers3, data3);
        System.out.println();
    }
    
    /**
     * ���Էָ���
     */
    private static void testSeparators() {
        System.out.println("5. �ָ��߲���:");
        ConsoleUtil.printSeparator();
        System.out.println("����Ӧ����һ��60�ַ���ȵķָ���");
        ConsoleUtil.printSeparator();
        System.out.println();
    }
}
