// FirstView.java

package com.artisan.view; // 应用程序主类包名为 com.artisan.view

import com.artisan.util.DbUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

/**
 * 学生学籍管理系统主应用程序。
 * 提供创建/删除结构、插入/删除/更新数据以及查询数据等功能。
 */
public class FirstView {

    // 日期格式化工具
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Connection con = null;
        String mainChoice;


        try {
            // 获取数据库连接，整个会话期间复用
            con = DbUtil.getCon();
            if (con == null) {
                System.err.println("无法建立数据库连接，请检查数据库配置和状态。");
                return; // 如果连接失败，直接退出
            }

            do {
                // 打印主菜单
                System.out.println("********************************************");
                System.out.println("*********** 欢迎使用学籍管理系统 ************");
                System.out.println("********************************************");
                System.out.println("选择输入：");
                System.out.println("********** 'C': 进行创建结构 ************");
                System.out.println("********** 'D': 进行删除结构 ************");
                System.out.println("********** 'i': 进行插入数据 ************");
                System.out.println("********** 'd': 进行删除数据 ************");
                System.out.println("********** 'u': 进行更新数据 ************");
                System.out.println("********** 's': 进行查询数据 ************");
                System.out.println("********** '0': 退出系统 ************"); // 添加退出选项
                System.out.println("*******************************************");
                System.out.print("请选择要进行的业务: ");

                // 读取用户输入
                mainChoice = scanner.nextLine().trim(); // 读取字符串并去除首尾空格

                switch (mainChoice) { // 直接使用 mainChoice，区分大小写
                    case "C":
                        System.out.println("\n--- 进行创建结构 ---");
                        CreateTableView.createTables(con);
                        break;
                    case "D":
                        System.out.println("\n--- 进行删除结构 ---");
                        DropTableView.dropTables(con);
                        break;
                    case "i":
                        System.out.println("\n--- 进行插入数据 ---");
                        InsertView.insertData(con, scanner);
                        break;
                    case "d":
                        System.out.println("\n--- 进行删除数据 ---");
                        DeleteView.deleteData(con, scanner); // 调用删除数据方法
                        break;
                    case "u":
                        System.out.println("\n--- 进行更新数据 ---");
                        UpdateView.updateData(con, scanner);
                        break;
                    case "s":
                        // 调用查询菜单方法
                        QueryView.showQueryMenu(con, scanner);
                        break;
                    case "0":
                        System.out.println("\n退出学籍管理系统。感谢使用！");
                        break;
                    default:
                        System.out.println("\n无效的选择，请重新输入。");
                        break;
                }
                if (!mainChoice.equals("0")) { // 退出时不再提示按Enter
                    System.out.println("\n按 Enter 键继续...");
                    scanner.nextLine(); // 等待用户按 Enter 键
                }
            } while (!mainChoice.equals("0"));

        } catch (SQLException e) {
            System.err.println("数据库操作错误: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("发生未知错误: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // 确保关闭数据库连接和 Scanner
            if (con != null) {
                try {
                    con.close();
                    System.out.println("数据库连接已关闭。");
                } catch (SQLException e) {
                    System.err.println("关闭数据库连接失败: " + e.getMessage());
                    e.printStackTrace();
                }
            }
            if (scanner != null) {
                scanner.close();
            }
        }
    }
}
