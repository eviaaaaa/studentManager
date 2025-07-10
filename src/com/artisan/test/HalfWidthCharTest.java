package com.artisan.test;

import com.artisan.util.ConsoleUtil;

/**
 * 半角字符测试类
 * 测试使用半角边框字符的显示效果
 * 
 * @author llq-artisan
 * @version 1.0
 */
public class HalfWidthCharTest {
    
    public static void main(String[] args) {
        System.out.println("=== 半角字符边框测试 ===");
        System.out.println("现在所有边框字符都使用半角字符，每个字符占1个位置");
        System.out.println();
        
        // 测试1: 欢迎横幅
        testWelcomeBanner();
        
        // 测试2: 标题
        testTitles();
        
        // 测试3: 菜单
        testMenus();
        
        // 测试4: 表格
        testTables();
        
        // 测试5: 分隔线
        testSeparators();
        
        // 测试6: 字符对比
        testCharacterComparison();
        
        System.out.println("=== 测试完成 ===");
        System.out.println("请检查所有边框是否正确对齐");
    }
    
    /**
     * 测试欢迎横幅
     */
    private static void testWelcomeBanner() {
        System.out.println("1. 欢迎横幅测试（使用半角 = 字符）:");
        ConsoleUtil.printWelcomeBanner("学生管理系统", "v2.0");
    }
    
    /**
     * 测试标题
     */
    private static void testTitles() {
        System.out.println("2. 标题测试（使用半角 = 字符）:");
        
        ConsoleUtil.printTitle("短标题");
        System.out.println();
        
        ConsoleUtil.printTitle("中等长度标题");
        System.out.println();
        
        ConsoleUtil.printTitle("这是一个很长的标题测试");
        System.out.println();
    }
    
    /**
     * 测试菜单
     */
    private static void testMenus() {
        System.out.println("3. 菜单测试（使用半角 - 字符）:");
        
        String[] menuOptions = {
            "1 - 查询学生信息",
            "2 - 查询成绩信息",
            "3 - 查询奖惩信息",
            "0 - 退出系统"
        };
        
        ConsoleUtil.printMenu("测试菜单", menuOptions);
        System.out.println();
        System.out.println();
    }
    
    /**
     * 测试表格
     */
    private static void testTables() {
        System.out.println("4. 表格测试（使用半角 - 字符）:");
        
        String[] headers = {"学号", "姓名", "专业", "成绩"};
        String[][] data = {
            {"2021001", "张三", "计算机科学", "95"},
            {"2021002", "李四", "软件工程", "87"},
            {"2021003", "王五", "信息管理", "92"}
        };
        
        ConsoleUtil.printSmartTable(headers, data);
        System.out.println();
    }
    
    /**
     * 测试分隔线
     */
    private static void testSeparators() {
        System.out.println("5. 分隔线测试（使用半角 - 字符）:");
        ConsoleUtil.printSeparator();
        System.out.println("上面是60个半角 - 字符组成的分隔线");
        System.out.println();
    }
    
    /**
     * 测试字符对比
     */
    private static void testCharacterComparison() {
        System.out.println("6. 字符宽度对比测试:");
        System.out.println("半角字符: abcdefghijklmnopqrstuvwxyz0123456789");
        System.out.println("半角边框: ----------------------------------------");
        System.out.println("中文字符: 这是一个测试中文字符宽度的句子");
        System.out.println("混合内容: abc中文def测试ghi内容jkl");
        System.out.println();
        
        System.out.println("字符宽度说明:");
        System.out.println("- 半角字符（a-z, 0-9, -, =）: 占1个字符位置");
        System.out.println("- 中文字符: 占2个字符位置");
        System.out.println("- 现在所有边框都使用半角字符，确保精确对齐");
        System.out.println();
    }
}
