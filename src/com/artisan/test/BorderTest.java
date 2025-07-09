package com.artisan.test;

import com.artisan.util.ConsoleUtil;

/**
 * �߿������֤����
 * ������֤���߿��Ƿ���ȫ����
 * 
 * @author llq-artisan
 * @version 1.0
 */
public class BorderTest {
    
    public static void main(String[] args) {
        System.out.println("=== �߿������֤���� ===");
        System.out.println();
        
        // ���Լ򵥱��
        testSimpleTable();
        
        // ���Ը��ӱ��
        testComplexTable();
        
        // �ֶ���֤�߿�
        manualBorderTest();
        
        System.out.println("=== ��֤��� ===");
        System.out.println("�������б����ұ߿��Ƿ���ȫ��ֱ����");
    }
    
    /**
     * ���Լ򵥱��
     */
    private static void testSimpleTable() {
        ConsoleUtil.printTitle("�򵥱��߿����");
        
        String[] headers = {"A", "B", "C"};
        String[][] data = {
            {"1", "2", "3"},
            {"4", "5", "6"},
            {"7", "8", "9"}
        };
        
        ConsoleUtil.printSmartTable(headers, data);
        System.out.println();
    }
    
    /**
     * ���Ը��ӱ��
     */
    private static void testComplexTable() {
        ConsoleUtil.printTitle("���ӱ��߿����");
        
        String[] headers = {"��", "�еȳ���", "����һ���ܳ��ı�ͷ"};
        String[][] data = {
            {"��", "��", "�����ݲ���"},
            {"����", "��������", "����һ���ܳ�������"},
            {"A", "Test", "Mixed��Ӣ��"}
        };
        
        ConsoleUtil.printSmartTable(headers, data);
        System.out.println();
    }
    
    /**
     * �ֶ��߿����
     */
    private static void manualBorderTest() {
        ConsoleUtil.printTitle("�ֶ��߿���֤");
        
        System.out.println("�������ֶ����ƵĲο��ߣ����ڶԱȣ�");
        System.out.println("�������������������Щ����������������Щ�����������������");
        System.out.println("��  �ο�  ��  �ο�  ��  �ο�  ��");
        System.out.println("�������������������੤���������������੤����������������");
        System.out.println("��  ����  ��  ����  ��  ����  ��");
        System.out.println("�������������������ة����������������ة�����������������");
        System.out.println();
        
        System.out.println("���ڲ���ʵ�ʱ��");
        String[] headers = {"����1", "����2", "����3"};
        String[][] data = {
            {"����1", "����2", "����3"},
            {"����1", "����2", "����3"}
        };
        
        ConsoleUtil.printSmartTable(headers, data);
        
        System.out.println();
        System.out.println("��Ա������������ı߿��Ƿ����");
    }
}
