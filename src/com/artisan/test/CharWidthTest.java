package com.artisan.test;

import com.artisan.util.ConsoleUtil;

/**
 * �ַ���Ȳ�����
 * ��֤�ȿ��������ַ���ȵ���ȷ����
 * һ��"��"�ַ� = ����Ӣ����ĸ = һ������
 * 
 * @author llq-artisan
 * @version 1.0
 */
public class CharWidthTest {
    
    public static void main(String[] args) {
        System.out.println("=== �ȿ������ַ���Ȳ��� ===");
        System.out.println();
        
        // �����ַ���ȶ���
        testCharacterWidth();
        
        // ���Ա߿����
        testBorderAlignment();
        
        // ���Ա�����
        testTableAlignment();
        
        System.out.println("=== ������� ===");
        System.out.println("�������б߿��Ƿ���ȫ����");
    }
    
    /**
     * �����ַ����
     */
    private static void testCharacterWidth() {
        System.out.println("�ַ���ȶԱȲ���:");
        System.out.println("Ӣ���ַ�: abcdefghijklmnopqrstuvwxyz");
        System.out.println("�߿��ַ�: ����������������������������������������������������������");
        System.out.println("�����ַ�: ����һ�����������ַ���ȵľ���");
        System.out.println("�������: abc����def����ghi����jkl");
        System.out.println();
    }
    
    /**
     * ���Ա߿����
     */
    private static void testBorderAlignment() {
        System.out.println("�߿�������:");
        
        // ���Բ�ͬ���ȵı���
        ConsoleUtil.printTitle("��");
        System.out.println();
        
        ConsoleUtil.printTitle("�еȳ��ȱ���");
        System.out.println();
        
        ConsoleUtil.printTitle("����һ���ܳ��ı������");
        System.out.println();
        
        ConsoleUtil.printTitle("Mixed English ������ Title");
        System.out.println();
    }
    
    /**
     * ���Ա�����
     */
    private static void testTableAlignment() {
        System.out.println("���߿�������:");
        
        // ����1: �򵥱��
        String[] headers1 = {"A", "B", "C"};
        String[][] data1 = {
            {"1", "2", "3"},
            {"4", "5", "6"}
        };
        ConsoleUtil.printSmartTable(headers1, data1);
        System.out.println();
        
        // ����2: ���ı��
        String[] headers2 = {"���", "����", "����"};
        String[][] data2 = {
            {"001", "����", "20"},
            {"002", "����", "21"}
        };
        ConsoleUtil.printSmartTable(headers2, data2);
        System.out.println();
        
        // ����3: ��ϱ��
        String[] headers3 = {"ID", "������", "English", "����"};
        String[][] data3 = {
            {"001", "����", "Zhang", "95"},
            {"002", "����", "Li Si", "87"}
        };
        ConsoleUtil.printSmartTable(headers3, data3);
        System.out.println();
        
        // ����4: �����ݱ��
        String[] headers4 = {"�γ�", "����", "ѧ��"};
        String[][] data4 = {
            {"��ѧ", "�ߵ���ѧ�����γ�", "4"},
            {"Ӣ��", "��ѧӢ���ۺϿγ�", "3"},
            {"�����", "�������ѧ���ۿγ�", "3"}
        };
        ConsoleUtil.printSmartTable(headers4, data4);
    }
}
