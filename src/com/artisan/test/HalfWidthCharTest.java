package com.artisan.test;

import com.artisan.util.ConsoleUtil;

/**
 * ����ַ�������
 * ����ʹ�ð�Ǳ߿��ַ�����ʾЧ��
 * 
 * @author llq-artisan
 * @version 1.0
 */
public class HalfWidthCharTest {
    
    public static void main(String[] args) {
        System.out.println("=== ����ַ��߿���� ===");
        System.out.println("�������б߿��ַ���ʹ�ð���ַ���ÿ���ַ�ռ1��λ��");
        System.out.println();
        
        // ����1: ��ӭ���
        testWelcomeBanner();
        
        // ����2: ����
        testTitles();
        
        // ����3: �˵�
        testMenus();
        
        // ����4: ���
        testTables();
        
        // ����5: �ָ���
        testSeparators();
        
        // ����6: �ַ��Ա�
        testCharacterComparison();
        
        System.out.println("=== ������� ===");
        System.out.println("�������б߿��Ƿ���ȷ����");
    }
    
    /**
     * ���Ի�ӭ���
     */
    private static void testWelcomeBanner() {
        System.out.println("1. ��ӭ������ԣ�ʹ�ð�� = �ַ���:");
        ConsoleUtil.printWelcomeBanner("ѧ������ϵͳ", "v2.0");
    }
    
    /**
     * ���Ա���
     */
    private static void testTitles() {
        System.out.println("2. ������ԣ�ʹ�ð�� = �ַ���:");
        
        ConsoleUtil.printTitle("�̱���");
        System.out.println();
        
        ConsoleUtil.printTitle("�еȳ��ȱ���");
        System.out.println();
        
        ConsoleUtil.printTitle("����һ���ܳ��ı������");
        System.out.println();
    }
    
    /**
     * ���Բ˵�
     */
    private static void testMenus() {
        System.out.println("3. �˵����ԣ�ʹ�ð�� - �ַ���:");
        
        String[] menuOptions = {
            "1 - ��ѯѧ����Ϣ",
            "2 - ��ѯ�ɼ���Ϣ",
            "3 - ��ѯ������Ϣ",
            "0 - �˳�ϵͳ"
        };
        
        ConsoleUtil.printMenu("���Բ˵�", menuOptions);
        System.out.println();
        System.out.println();
    }
    
    /**
     * ���Ա��
     */
    private static void testTables() {
        System.out.println("4. �����ԣ�ʹ�ð�� - �ַ���:");
        
        String[] headers = {"ѧ��", "����", "רҵ", "�ɼ�"};
        String[][] data = {
            {"2021001", "����", "�������ѧ", "95"},
            {"2021002", "����", "�������", "87"},
            {"2021003", "����", "��Ϣ����", "92"}
        };
        
        ConsoleUtil.printSmartTable(headers, data);
        System.out.println();
    }
    
    /**
     * ���Էָ���
     */
    private static void testSeparators() {
        System.out.println("5. �ָ��߲��ԣ�ʹ�ð�� - �ַ���:");
        ConsoleUtil.printSeparator();
        System.out.println("������60����� - �ַ���ɵķָ���");
        System.out.println();
    }
    
    /**
     * �����ַ��Ա�
     */
    private static void testCharacterComparison() {
        System.out.println("6. �ַ���ȶԱȲ���:");
        System.out.println("����ַ�: abcdefghijklmnopqrstuvwxyz0123456789");
        System.out.println("��Ǳ߿�: ----------------------------------------");
        System.out.println("�����ַ�: ����һ�����������ַ���ȵľ���");
        System.out.println("�������: abc����def����ghi����jkl");
        System.out.println();
        
        System.out.println("�ַ����˵��:");
        System.out.println("- ����ַ���a-z, 0-9, -, =��: ռ1���ַ�λ��");
        System.out.println("- �����ַ�: ռ2���ַ�λ��");
        System.out.println("- �������б߿�ʹ�ð���ַ���ȷ����ȷ����");
        System.out.println();
    }
}
