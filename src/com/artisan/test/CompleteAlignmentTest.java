package com.artisan.test;

import com.artisan.util.ConsoleUtil;

/**
 * 完整对齐测试类
 * 全面测试所有UI组件的对齐效果
 * 
 * @author llq-artisan
 * @version 1.0
 */
public class CompleteAlignmentTest {
    
    public static void main(String[] args) {
        System.out.println("=== 完整对齐测试 ===");
        System.out.println("请仔细检查所有边框是否完全对齐");
        System.out.println();
        
        // 1. 测试欢迎横幅
        testWelcomeBanner();
        
        // 2. 测试标题
        testTitles();
        
        // 3. 测试菜单
        testMenus();
        
        // 4. 测试表格
        testTables();
        
        // 5. 测试分隔线
        testSeparators();
        
        System.out.println("=== 测试完成 ===");
        System.out.println("如果所有边框都完全对齐，说明修复成功！");
    }
    
    /**
     * 测试欢迎横幅
     */
    private static void testWelcomeBanner() {
        System.out.println("1. 欢迎横幅测试:");
        ConsoleUtil.printWelcomeBanner("学生学籍管理系统", "v2.0");
    }
    
    /**
     * 测试标题
     */
    private static void testTitles() {
        System.out.println("2. 标题对齐测试:");
        
        ConsoleUtil.printTitle("短标题");
        System.out.println();
        
        ConsoleUtil.printTitle("中等长度的标题");
        System.out.println();
        
        ConsoleUtil.printTitle("这是一个很长的标题用来测试对齐");
        System.out.println();
        
        ConsoleUtil.printTitle("Mixed English 和中文 Title");
        System.out.println();
    }
    
    /**
     * 测试菜单
     */
    private static void testMenus() {
        System.out.println("3. 菜单对齐测试:");
        
        String[] menuOptions1 = {
            "1 - 短选项",
            "2 - 中等长度选项",
            "3 - 这是一个很长的菜单选项",
            "0 - 退出"
        };
        ConsoleUtil.printMenu("测试菜单", menuOptions1);
        System.out.println();
        System.out.println();
        
        String[] menuOptions2 = {
            "1 - Query Student Info",
            "2 - 查询学生成绩信息",
            "3 - Mixed English 和中文选项",
            "0 - 返回主菜单"
        };
        ConsoleUtil.printMenu("混合语言菜单", menuOptions2);
        System.out.println();
        System.out.println();
    }
    
    /**
     * 测试表格
     */
    private static void testTables() {
        System.out.println("4. 表格对齐测试:");
        
        // 简单表格
        String[] headers1 = {"ID", "Name", "Age"};
        String[][] data1 = {
            {"001", "张三", "20"},
            {"002", "李四", "21"}
        };
        ConsoleUtil.printSmartTable(headers1, data1);
        System.out.println();
        
        // 复杂表格
        String[] headers2 = {"学号", "姓名", "English Name", "成绩", "备注"};
        String[][] data2 = {
            {"2021001", "张三", "Zhang San", "95", "优秀学生"},
            {"2021002", "李四", "Li Si", "87", "良好"},
            {"2021003", "王五", "Wang Wu", "92", "表现优秀"},
            {"2021004", "赵六", "Zhao Liu", "88", "Good performance"}
        };
        ConsoleUtil.printSmartTable(headers2, data2);
        System.out.println();
        
        // 长文本表格
        String[] headers3 = {"课程", "描述", "学分"};
        String[][] data3 = {
            {"高等数学", "这是一门重要的数学基础课程", "4"},
            {"大学英语", "English comprehensive course", "3"},
            {"计算机科学", "计算机科学与技术专业核心课程", "3"}
        };
        ConsoleUtil.printSmartTable(headers3, data3);
        System.out.println();
    }
    
    /**
     * 测试分隔线
     */
    private static void testSeparators() {
        System.out.println("5. 分隔线测试:");
        ConsoleUtil.printSeparator();
        System.out.println("上面应该是一条60字符宽度的分隔线");
        ConsoleUtil.printSeparator();
        System.out.println();
    }
}
