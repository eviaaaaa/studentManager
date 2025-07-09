package com.artisan.test;

import com.artisan.util.ConsoleUtil;

/**
 * 等宽字体对齐测试类
 * 测试在等宽字体环境下的各种对齐效果
 * 
 * @author llq-artisan
 * @version 1.0
 */
public class AlignmentTest {
    
    public static void main(String[] args) {
        System.out.println("=== 等宽字体对齐测试 ===");
        System.out.println();
        
        // 测试欢迎横幅
        testWelcomeBanner();
        
        // 测试标题对齐
        testTitleAlignment();
        
        // 测试菜单对齐
        testMenuAlignment();
        
        // 测试表格对齐
        testTableAlignment();
        
        System.out.println("=== 测试完成 ===");
    }
    
    /**
     * 测试欢迎横幅对齐
     */
    private static void testWelcomeBanner() {
        System.out.println("1. 欢迎横幅对齐测试:");
        ConsoleUtil.printWelcomeBanner("学生学籍管理系统", "v2.0");
        
        System.out.println("2. 不同长度系统名测试:");
        ConsoleUtil.printWelcomeBanner("学籍系统", "v1.0");
        
        System.out.println("3. 英文系统名测试:");
        ConsoleUtil.printWelcomeBanner("Student Management System", "v2.0");
    }
    
    /**
     * 测试标题对齐
     */
    private static void testTitleAlignment() {
        System.out.println("\n标题对齐测试:");
        
        ConsoleUtil.printTitle("短标题");
        System.out.println();
        
        ConsoleUtil.printTitle("这是一个比较长的标题");
        System.out.println();
        
        ConsoleUtil.printTitle("Mixed English 和中文 Title");
        System.out.println();
    }
    
    /**
     * 测试菜单对齐
     */
    private static void testMenuAlignment() {
        System.out.println("菜单对齐测试:");
        
        String[] menuOptions = {
            "1 - 查询所有学生基本信息",
            "2 - 查询学生成绩信息", 
            "3 - 查询奖惩信息",
            "4 - 高级查询功能",
            "0 - 退出系统"
        };
        
        ConsoleUtil.printMenu("测试菜单", menuOptions);
        System.out.println();
        System.out.println();
    }
    
    /**
     * 测试表格对齐
     */
    private static void testTableAlignment() {
        System.out.println("表格对齐测试:");
        
        // 测试中英文混合表格
        String[] headers = {"ID", "姓名", "English Name", "分数", "备注"};
        String[][] data = {
            {"001", "张三", "Zhang San", "95", "优秀学生"},
            {"002", "李四", "Li Si", "87", "Good"},
            {"003", "王五", "Wang Wu", "92", "表现良好"},
            {"004", "赵六", "Zhao Liu", "88", "Excellent"}
        };
        
        ConsoleUtil.printSmartTable(headers, data);
        
        System.out.println();
        
        // 测试纯中文表格
        String[] headers2 = {"学号", "姓名", "性别", "年龄", "专业"};
        String[][] data2 = {
            {"2021001", "张三", "男", "20", "计算机科学与技术"},
            {"2021002", "李四", "女", "19", "软件工程"},
            {"2021003", "王五", "男", "21", "信息管理"}
        };
        
        ConsoleUtil.printSmartTable(headers2, data2);
    }
}
