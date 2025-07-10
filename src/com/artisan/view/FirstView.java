// FirstView.java

package com.artisan.view; // 应用程序主类包名为 com.artisan.view

import com.artisan.util.DbUtil;
import com.artisan.util.ConsoleUtil;
import com.artisan.util.InputValidator;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

/**
 * 学生学籍管理系统主应用程序 - 美化版
 * 提供创建/删除结构、插入/删除/更新数据以及查询数据等功能。
 *我的
 * @author llq-artisan
 * @version 2.0
 */
public class FirstView {

    // 日期格式化工具
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private static final String SYSTEM_NAME = "学生学籍管理系统";
    private static final String VERSION = "v2.0";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Connection con = null;
        String mainChoice;

        // 显示欢迎界面
        ConsoleUtil.clearScreen();
        ConsoleUtil.printWelcomeBanner(SYSTEM_NAME, VERSION);

        try {
            // 测试数据库连接
            ConsoleUtil.printInfo("正在连接数据库...");
            con = DbUtil.getCon();
            if (con == null) {
                ConsoleUtil.printError("无法建立数据库连接，请检查数据库配置和状态。");
                ConsoleUtil.printInfo("配置信息: " + DbUtil.getConfigInfo());
                return; // 如果连接失败，直接退出
            }
            ConsoleUtil.printSuccess("数据库连接成功！");
            Thread.sleep(1000); // 短暂停顿以显示连接成功信息

            do {
                // 显示主菜单
                showMainMenu();

                // 读取用户输入
                System.out.print(ConsoleUtil.CYAN + "请选择要进行的业务: " + ConsoleUtil.RESET);
                mainChoice = scanner.nextLine().trim(); // 读取字符串并去除首尾空格

                switch (mainChoice) { // 直接使用 mainChoice，区分大小写
                    case "C":
                        ConsoleUtil.printInfo("进行创建结构");
                        CreateTableView.createTables(con);
                        break;
                    case "D":
                        ConsoleUtil.printWarning("进行删除结构");
                        if (InputValidator.confirm(scanner, "确定要删除所有表结构吗？此操作不可恢复")) {
                            DropTableView.dropTables(con);
                        } else {
                            ConsoleUtil.printInfo("操作已取消");
                        }
                        break;
                    case "i":
                        ConsoleUtil.printInfo("进行插入数据");
                        InsertView.insertData(con, scanner);
                        break;
                    case "d":
                        ConsoleUtil.printInfo("进行删除数据");
                        DeleteView.deleteData(con, scanner); // 调用删除数据方法
                        break;
                    case "u":
                        ConsoleUtil.printInfo("进行更新数据");
                        UpdateView.updateData(con, scanner);
                        break;
                    case "s":
                        ConsoleUtil.printInfo("进行查询数据");
                        // 调用查询菜单方法
                        QueryView.showQueryMenu(con, scanner);
                        break;
                    case "0":
                        ConsoleUtil.printSuccess("退出学籍管理系统。感谢使用！");
                        break;
                    default:
                        ConsoleUtil.printError("无效的选择，请重新输入。");
                        break;
                }
                if (!mainChoice.equals("0")) { // 退出时不再提示按Enter
                    ConsoleUtil.waitForEnter(scanner);
                }
            } while (!mainChoice.equals("0"));

        } catch (SQLException e) {
            ConsoleUtil.printError("数据库操作错误: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            ConsoleUtil.printError("发生未知错误: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // 确保关闭数据库连接和 Scanner
            if (con != null) {
                try {
                    con.close();
                    ConsoleUtil.printInfo("数据库连接已关闭。");
                } catch (SQLException e) {
                    ConsoleUtil.printError("关闭数据库连接失败: " + e.getMessage());
                    e.printStackTrace();
                }
            }
            if (scanner != null) {
                scanner.close();
            }
        }
    }

    /**
     * 显示主菜单
     */
    private static void showMainMenu() {
        String[] menuOptions = {
            "C - 创建数据库表结构",
            "D - 删除数据库表结构",
            "i - 插入数据",
            "d - 删除数据",
            "u - 更新数据",
            "s - 查询数据",
            "0 - 退出系统"
        };

        ConsoleUtil.printMenu("主菜单", menuOptions);
    }
}
