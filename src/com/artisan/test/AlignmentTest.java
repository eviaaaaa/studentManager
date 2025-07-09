package com.artisan.test;

import com.artisan.util.ConsoleUtil;

/**
 * �ȿ�������������
 * �����ڵȿ����廷���µĸ��ֶ���Ч��
 * 
 * @author llq-artisan
 * @version 1.0
 */
public class AlignmentTest {
    
    public static void main(String[] args) {
        System.out.println("=== �ȿ����������� ===");
        System.out.println();
        
        // ���Ի�ӭ���
        testWelcomeBanner();
        
        // ���Ա������
        testTitleAlignment();
        
        // ���Բ˵�����
        testMenuAlignment();
        
        // ���Ա�����
        testTableAlignment();
        
        System.out.println("=== ������� ===");
    }
    
    /**
     * ���Ի�ӭ�������
     */
    private static void testWelcomeBanner() {
        System.out.println("1. ��ӭ����������:");
        ConsoleUtil.printWelcomeBanner("ѧ��ѧ������ϵͳ", "v2.0");
        
        System.out.println("2. ��ͬ����ϵͳ������:");
        ConsoleUtil.printWelcomeBanner("ѧ��ϵͳ", "v1.0");
        
        System.out.println("3. Ӣ��ϵͳ������:");
        ConsoleUtil.printWelcomeBanner("Student Management System", "v2.0");
    }
    
    /**
     * ���Ա������
     */
    private static void testTitleAlignment() {
        System.out.println("\n����������:");
        
        ConsoleUtil.printTitle("�̱���");
        System.out.println();
        
        ConsoleUtil.printTitle("����һ���Ƚϳ��ı���");
        System.out.println();
        
        ConsoleUtil.printTitle("Mixed English ������ Title");
        System.out.println();
    }
    
    /**
     * ���Բ˵�����
     */
    private static void testMenuAlignment() {
        System.out.println("�˵��������:");
        
        String[] menuOptions = {
            "1 - ��ѯ����ѧ��������Ϣ",
            "2 - ��ѯѧ���ɼ���Ϣ", 
            "3 - ��ѯ������Ϣ",
            "4 - �߼���ѯ����",
            "0 - �˳�ϵͳ"
        };
        
        ConsoleUtil.printMenu("���Բ˵�", menuOptions);
        System.out.println();
        System.out.println();
    }
    
    /**
     * ���Ա�����
     */
    private static void testTableAlignment() {
        System.out.println("���������:");
        
        // ������Ӣ�Ļ�ϱ��
        String[] headers = {"ID", "����", "English Name", "����", "��ע"};
        String[][] data = {
            {"001", "����", "Zhang San", "95", "����ѧ��"},
            {"002", "����", "Li Si", "87", "Good"},
            {"003", "����", "Wang Wu", "92", "��������"},
            {"004", "����", "Zhao Liu", "88", "Excellent"}
        };
        
        ConsoleUtil.printSmartTable(headers, data);
        
        System.out.println();
        
        // ���Դ����ı��
        String[] headers2 = {"ѧ��", "����", "�Ա�", "����", "רҵ"};
        String[][] data2 = {
            {"2021001", "����", "��", "20", "�������ѧ�뼼��"},
            {"2021002", "����", "Ů", "19", "�������"},
            {"2021003", "����", "��", "21", "��Ϣ����"}
        };
        
        ConsoleUtil.printSmartTable(headers2, data2);
    }
}
