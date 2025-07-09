package com.artisan.test;

import com.artisan.util.ConsoleUtil;

/**
 * ����̨����Ч��������
 * ���ڲ��Բ˵��ͱ�����ʾЧ��
 * 
 * @author llq-artisan
 * @version 1.0
 */
public class ConsoleTest {
    
    public static void main(String[] args) {
        // ���Ի�ӭ���
        ConsoleUtil.printWelcomeBanner("ѧ��ѧ������ϵͳ", "v2.0");
        
        // ���Ը�����Ϣ����
        ConsoleUtil.printSuccess("���ǳɹ���Ϣ");
        ConsoleUtil.printError("���Ǵ�����Ϣ");
        ConsoleUtil.printWarning("���Ǿ�����Ϣ");
        ConsoleUtil.printInfo("������Ϣ��Ϣ");
        
        System.out.println();
        
        // ���Ա���
        ConsoleUtil.printTitle("���Ա���");
        
        System.out.println();
        
        // ���Բ˵�
        String[] menuOptions = {
            "1 - ��ѯ����ѧ��������Ϣ",
            "2 - ��ѯ����ѧ���ɼ���Ϣ", 
            "3 - ��ѯ����ѧ��������Ϣ",
            "4 - ��ѯĳѧ���ɼ���",
            "5 - �߼���ѯѧ��������Ϣ",
            "0 - �������˵�"
        };
        
        ConsoleUtil.printMenu("��ѯ�˵�", menuOptions);
        
        System.out.println();
        System.out.println();
        
        // �������ܱ�� - ��������
        ConsoleUtil.printTitle("����������");
        String[] headers1 = {"ѧ��", "����", "�Ա�", "����", "�༶"};
        String[][] testData1 = {
            {"2021001", "����", "��", "20", "�������ѧ�뼼��1��"},
            {"2021002", "����", "Ů", "19", "�����1��"},
            {"2021003", "����", "��", "21", "�������1��"},
            {"2021004", "����", "Ů", "20", "��Ϣ����1��"}
        };

        ConsoleUtil.printSmartTable(headers1, testData1);

        System.out.println();

        // �������ܱ�� - �������ı�������
        ConsoleUtil.printTitle("�������ݱ�����");
        String[] headers2 = {"�γ̱��", "�γ�����", "ѧ��", "�ɼ�", "��ע"};
        String[][] testData2 = {
            {"CS101", "�������ѧ����", "3", "95", "����γ̣����ݷḻ"},
            {"MATH201", "�ߵ���ѧA", "4", "87", "������ѧ�γ�"},
            {"ENG101", "��ѧӢ��", "2", "92", ""},
            {"PHYS301", "��ѧ����ʵ��", "1", "88", "ʵ��γ̣���������ǿ"}
        };

        ConsoleUtil.printSmartTable(headers2, testData2);

        System.out.println();

        // �������ܱ�� - ��Ӣ�Ļ��
        ConsoleUtil.printTitle("��Ӣ�Ļ�ϱ�����");
        String[] headers3 = {"ID", "Name", "��������", "Score", "����"};
        String[][] testData3 = {
            {"001", "Zhang San", "����", "95.5", "�ǳ������ѧ��"},
            {"002", "Li Si", "����", "87.0", "Good"},
            {"003", "Wang Wu", "����", "92.5", "��������"},
            {"004", "Zhao Liu", "����", "88.0", "Excellent performance"}
        };

        ConsoleUtil.printSmartTable(headers3, testData3);
        
        System.out.println();
        ConsoleUtil.printSeparator();
        ConsoleUtil.printInfo("������ɣ�");
    }
}
