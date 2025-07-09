// FirstView.java

package com.artisan.view; // Ӧ�ó����������Ϊ com.artisan.view

import com.artisan.util.DbUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

/**
 * ѧ��ѧ������ϵͳ��Ӧ�ó���
 * �ṩ����/ɾ���ṹ������/ɾ��/���������Լ���ѯ���ݵȹ��ܡ�
 */
public class FirstView {

    // ���ڸ�ʽ������
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Connection con = null;
        String mainChoice;


        try {
            // ��ȡ���ݿ����ӣ������Ự�ڼ临��
            con = DbUtil.getCon();
            if (con == null) {
                System.err.println("�޷��������ݿ����ӣ��������ݿ����ú�״̬��");
                return; // �������ʧ�ܣ�ֱ���˳�
            }

            do {
                // ��ӡ���˵�
                System.out.println("********************************************");
                System.out.println("*********** ��ӭʹ��ѧ������ϵͳ ************");
                System.out.println("********************************************");
                System.out.println("ѡ�����룺");
                System.out.println("********** 'C': ���д����ṹ ************");
                System.out.println("********** 'D': ����ɾ���ṹ ************");
                System.out.println("********** 'i': ���в������� ************");
                System.out.println("********** 'd': ����ɾ������ ************");
                System.out.println("********** 'u': ���и������� ************");
                System.out.println("********** 's': ���в�ѯ���� ************");
                System.out.println("********** '0': �˳�ϵͳ ************"); // ����˳�ѡ��
                System.out.println("*******************************************");
                System.out.print("��ѡ��Ҫ���е�ҵ��: ");

                // ��ȡ�û�����
                mainChoice = scanner.nextLine().trim(); // ��ȡ�ַ�����ȥ����β�ո�

                switch (mainChoice) { // ֱ��ʹ�� mainChoice�����ִ�Сд
                    case "C":
                        System.out.println("\n--- ���д����ṹ ---");
                        CreateTableView.createTables(con);
                        break;
                    case "D":
                        System.out.println("\n--- ����ɾ���ṹ ---");
                        DropTableView.dropTables(con);
                        break;
                    case "i":
                        System.out.println("\n--- ���в������� ---");
                        InsertView.insertData(con, scanner);
                        break;
                    case "d":
                        System.out.println("\n--- ����ɾ������ ---");
                        DeleteView.deleteData(con, scanner); // ����ɾ�����ݷ���
                        break;
                    case "u":
                        System.out.println("\n--- ���и������� ---");
                        UpdateView.updateData(con, scanner);
                        break;
                    case "s":
                        // ���ò�ѯ�˵�����
                        QueryView.showQueryMenu(con, scanner);
                        break;
                    case "0":
                        System.out.println("\n�˳�ѧ������ϵͳ����лʹ�ã�");
                        break;
                    default:
                        System.out.println("\n��Ч��ѡ�����������롣");
                        break;
                }
                if (!mainChoice.equals("0")) { // �˳�ʱ������ʾ��Enter
                    System.out.println("\n�� Enter ������...");
                    scanner.nextLine(); // �ȴ��û��� Enter ��
                }
            } while (!mainChoice.equals("0"));

        } catch (SQLException e) {
            System.err.println("���ݿ��������: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("����δ֪����: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // ȷ���ر����ݿ����Ӻ� Scanner
            if (con != null) {
                try {
                    con.close();
                    System.out.println("���ݿ������ѹرա�");
                } catch (SQLException e) {
                    System.err.println("�ر����ݿ�����ʧ��: " + e.getMessage());
                    e.printStackTrace();
                }
            }
            if (scanner != null) {
                scanner.close();
            }
        }
    }
}
