package com.artisan.test;

import com.artisan.util.ConsoleUtil;

/**
 * ���ı������ʾ������
 * ���Ը��ֳ��ı����ݵ�������ʾЧ��
 * 
 * @author llq-artisan
 * @version 1.0
 */
public class LongTextTableTest {
    
    public static void main(String[] args) {
        ConsoleUtil.printWelcomeBanner("���ı�������", "v1.0");
        
        // ����1: ��׼���ȱ��
        testStandardTable();
        
        // ����2: �еȳ����ı����
        testMediumTextTable();
        
        // ����3: ���ı����
        testLongTextTable();
        
        // ����4: �����ı����
        testVeryLongTextTable();
        
        // ����5: ��ϳ��ȱ��
        testMixedLengthTable();
        
        ConsoleUtil.printSuccess("���ı���������ɣ�");
    }
    
    /**
     * ���Ա�׼���ȱ��
     */
    private static void testStandardTable() {
        ConsoleUtil.printTitle("��׼���ȱ�����");
        
        String[] headers = {"ѧ��", "����", "רҵ", "�ɼ�"};
        String[][] data = {
            {"2021001", "����", "�����", "95"},
            {"2021002", "����", "�������", "87"},
            {"2021003", "����", "��Ϣ����", "92"}
        };
        
        ConsoleUtil.printSmartTable(headers, data);
        System.out.println();
    }
    
    /**
     * �����еȳ����ı����
     */
    private static void testMediumTextTable() {
        ConsoleUtil.printTitle("�еȳ����ı�������");
        
        String[] headers = {"�γ̱��", "�γ�����", "�ον�ʦ", "�Ͽεص�", "�γ�����"};
        String[][] data = {
            {"CS101", "�������ѧ����", "�Ž���", "��ѧ¥A101", "���ܼ������ѧ��������"},
            {"MATH201", "�ߵ���ѧA", "�����", "��ѧ¥B203", "΢���ֺ����Դ�������"},
            {"ENG101", "��ѧӢ��", "����ʦ", "����¥C301", "���Ӣ���ۺ�Ӧ������"}
        };
        
        ConsoleUtil.printSmartTable(headers, data);
        System.out.println();
    }
    
    /**
     * ���Գ��ı����
     */
    private static void testLongTextTable() {
        ConsoleUtil.printTitle("���ı�������");
        
        String[] headers = {"��Ŀ����", "��Ŀ����", "������", "��Ŀ״̬"};
        String[][] data = {
            {
                "ѧ������ϵͳ������Ŀ", 
                "����һ������Java����ջ������ѧ��ѧ������ϵͳ������ѧ����Ϣ�����ɼ������γ̹���Ⱥ��Ĺ���ģ��", 
                "����", 
                "������"
            },
            {
                "����ѧϰƽ̨����", 
                "����һ���ִ���������ѧϰƽ̨��֧����Ƶ�γ̡����߿��ԡ���ҵ�ύ��ʦ�������ȹ��ܣ�����΢����ܹ�", 
                "����", 
                "�����"
            },
            {
                "����ͼ���ϵͳ", 
                "�����˹����ܼ�������������ͼ��ݹ���ϵͳ������ͼ���Ƽ������ܼ������Զ�������Ƚ�����", 
                "����", 
                "�滮��"
            }
        };
        
        ConsoleUtil.printSmartTable(headers, data);
        System.out.println();
    }
    
    /**
     * ���Գ����ı����
     */
    private static void testVeryLongTextTable() {
        ConsoleUtil.printTitle("�����ı�������");
        
        String[] headers = {"������", "������ϸ����", "���ȼ�", "Ԥ�ƹ���"};
        String[][] data = {
            {
                "REQ-001", 
                "����һ������������ѧ��ѧ������ϵͳ����ϵͳ��Ҫ֧��ѧ��������Ϣ����ѧ����������ɼ�¼�����ѯ���γ̰��Ź�����ʦ��Ϣ�����༶����רҵ����Ժϵ����Ⱥ���ҵ���ܡ�ͬʱϵͳ����Ҫ�߱����ݵ��뵼����ͳ�Ʊ������ɡ�Ȩ�޹�����־��Ƶȸ������ܡ�ϵͳӦ����B/S�ܹ���֧�ֶ��û��������ʣ��߱����õ��û������ϵͳ���ܡ�", 
                "��", 
                "3����"
            },
            {
                "REQ-002", 
                "����һ���ִ��������߽���ƽ̨��ƽ̨��Ҫ֧�ֶ��ֽ�ѧģʽ����ֱ����ѧ��¼���γ̡��������ۡ����߿��ԡ���ҵ����ȡ�ƽ̨Ӧ�߱����Ƶ��û�������ϵ��֧��ѧ������ʦ������Ա�ȶ��ֽ�ɫ��ͬʱ��Ҫ����֧��ϵͳ����Ϣ֪ͨϵͳ�����ݷ���ϵͳ�ȵ���������ƽ̨Ӧ����΢����ܹ���֧�ָ߲������ʣ��߱����õ���չ�Ժ��ȶ��ԡ�", 
                "��", 
                "6����"
            }
        };
        
        ConsoleUtil.printSmartTable(headers, data);
        System.out.println();
    }
    
    /**
     * ���Ի�ϳ��ȱ��
     */
    private static void testMixedLengthTable() {
        ConsoleUtil.printTitle("��ϳ��ȱ�����");
        
        String[] headers = {"ID", "����", "����", "״̬", "����ʱ��"};
        String[][] data = {
            {
                "1", 
                "�̱���", 
                "����һ����Խ϶̵���������", 
                "���", 
                "2024-01-01"
            },
            {
                "2", 
                "����һ���Ƚϳ��ı����������Ա�������Ӧ����", 
                "����һ���ǳ���ϸ�����������������˴�������Ϣ��ϸ�ڣ���������ϵͳ�Գ��ı��Ĵ������������ݿ��ܰ����������ʵʩ����������������ʱ�䰲�ŵȶ���������ϸ˵����", 
                "������", 
                "2024-01-15"
            },
            {
                "3", 
                "�еȱ���", 
                "�еȳ��ȵ���������������һЩ��Ҫ��Ϣ", 
                "����ʼ", 
                "2024-02-01"
            }
        };
        
        ConsoleUtil.printSmartTable(headers, data);
        System.out.println();
    }
}
