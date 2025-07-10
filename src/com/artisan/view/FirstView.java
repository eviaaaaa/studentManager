// FirstView.java

package com.artisan.view; // Ӧ�ó����������Ϊ com.artisan.view

import com.artisan.util.DbUtil;
import com.artisan.util.ConsoleUtil;
import com.artisan.util.InputValidator;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

/**
 * ѧ��ѧ������ϵͳ��Ӧ�ó��� - ������
 * �ṩ����/ɾ���ṹ������/ɾ��/���������Լ���ѯ���ݵȹ��ܡ�
 *�ҵ�
 * @author llq-artisan
 * @version 2.0
 */
public class FirstView {

    // ���ڸ�ʽ������
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private static final String SYSTEM_NAME = "ѧ��ѧ������ϵͳ";
    private static final String VERSION = "v2.0";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Connection con = null;
        String mainChoice;

        // ��ʾ��ӭ����
        ConsoleUtil.clearScreen();
        ConsoleUtil.printWelcomeBanner(SYSTEM_NAME, VERSION);

        try {
            // �������ݿ�����
            ConsoleUtil.printInfo("�����������ݿ�...");
            con = DbUtil.getCon();
            if (con == null) {
                ConsoleUtil.printError("�޷��������ݿ����ӣ��������ݿ����ú�״̬��");
                ConsoleUtil.printInfo("������Ϣ: " + DbUtil.getConfigInfo());
                return; // �������ʧ�ܣ�ֱ���˳�
            }
            ConsoleUtil.printSuccess("���ݿ����ӳɹ���");
            Thread.sleep(1000); // ����ͣ������ʾ���ӳɹ���Ϣ

            do {
                // ��ʾ���˵�
                showMainMenu();

                // ��ȡ�û�����
                System.out.print(ConsoleUtil.CYAN + "��ѡ��Ҫ���е�ҵ��: " + ConsoleUtil.RESET);
                mainChoice = scanner.nextLine().trim(); // ��ȡ�ַ�����ȥ����β�ո�

                switch (mainChoice) { // ֱ��ʹ�� mainChoice�����ִ�Сд
                    case "C":
                        ConsoleUtil.printInfo("���д����ṹ");
                        CreateTableView.createTables(con);
                        break;
                    case "D":
                        ConsoleUtil.printWarning("����ɾ���ṹ");
                        if (InputValidator.confirm(scanner, "ȷ��Ҫɾ�����б�ṹ�𣿴˲������ɻָ�")) {
                            DropTableView.dropTables(con);
                        } else {
                            ConsoleUtil.printInfo("������ȡ��");
                        }
                        break;
                    case "i":
                        ConsoleUtil.printInfo("���в�������");
                        InsertView.insertData(con, scanner);
                        break;
                    case "d":
                        ConsoleUtil.printInfo("����ɾ������");
                        DeleteView.deleteData(con, scanner); // ����ɾ�����ݷ���
                        break;
                    case "u":
                        ConsoleUtil.printInfo("���и�������");
                        UpdateView.updateData(con, scanner);
                        break;
                    case "s":
                        ConsoleUtil.printInfo("���в�ѯ����");
                        // ���ò�ѯ�˵�����
                        QueryView.showQueryMenu(con, scanner);
                        break;
                    case "0":
                        ConsoleUtil.printSuccess("�˳�ѧ������ϵͳ����лʹ�ã�");
                        break;
                    default:
                        ConsoleUtil.printError("��Ч��ѡ�����������롣");
                        break;
                }
                if (!mainChoice.equals("0")) { // �˳�ʱ������ʾ��Enter
                    ConsoleUtil.waitForEnter(scanner);
                }
            } while (!mainChoice.equals("0"));

        } catch (SQLException e) {
            ConsoleUtil.printError("���ݿ��������: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            ConsoleUtil.printError("����δ֪����: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // ȷ���ر����ݿ����Ӻ� Scanner
            if (con != null) {
                try {
                    con.close();
                    ConsoleUtil.printInfo("���ݿ������ѹرա�");
                } catch (SQLException e) {
                    ConsoleUtil.printError("�ر����ݿ�����ʧ��: " + e.getMessage());
                    e.printStackTrace();
                }
            }
            if (scanner != null) {
                scanner.close();
            }
        }
    }

    /**
     * ��ʾ���˵�
     */
    private static void showMainMenu() {
        String[] menuOptions = {
            "C - �������ݿ��ṹ",
            "D - ɾ�����ݿ��ṹ",
            "i - ��������",
            "d - ɾ������",
            "u - ��������",
            "s - ��ѯ����",
            "0 - �˳�ϵͳ"
        };

        ConsoleUtil.printMenu("���˵�", menuOptions);
    }
}
