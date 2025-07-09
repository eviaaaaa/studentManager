package com.artisan.test;

import com.artisan.util.ConsoleUtil;

/**
 * 边框对齐验证工具
 * 用于验证表格边框是否完全对齐
 * 
 * @author llq-artisan
 * @version 1.0
 */
public class BorderTest {
    
    public static void main(String[] args) {
        System.out.println("=== 边框对齐验证测试 ===");
        System.out.println();
        
        // 测试简单表格
        testSimpleTable();
        
        // 测试复杂表格
        testComplexTable();
        
        // 手动验证边框
        manualBorderTest();
        
        System.out.println("=== 验证完成 ===");
        System.out.println("请检查所有表格的右边框是否完全垂直对齐");
    }
    
    /**
     * 测试简单表格
     */
    private static void testSimpleTable() {
        ConsoleUtil.printTitle("简单表格边框测试");
        
        String[] headers = {"A", "B", "C"};
        String[][] data = {
            {"1", "2", "3"},
            {"4", "5", "6"},
            {"7", "8", "9"}
        };
        
        ConsoleUtil.printSmartTable(headers, data);
        System.out.println();
    }
    
    /**
     * 测试复杂表格
     */
    private static void testComplexTable() {
        ConsoleUtil.printTitle("复杂表格边框测试");
        
        String[] headers = {"短", "中等长度", "这是一个很长的表头"};
        String[][] data = {
            {"短", "中", "长内容测试"},
            {"测试", "测试内容", "这是一个很长的内容"},
            {"A", "Test", "Mixed中英文"}
        };
        
        ConsoleUtil.printSmartTable(headers, data);
        System.out.println();
    }
    
    /**
     * 手动边框测试
     */
    private static void manualBorderTest() {
        ConsoleUtil.printTitle("手动边框验证");
        
        System.out.println("下面是手动绘制的参考线，用于对比：");
        System.out.println("┌────────┬────────┬────────┐");
        System.out.println("│  参考  │  参考  │  参考  │");
        System.out.println("├────────┼────────┼────────┤");
        System.out.println("│  线条  │  线条  │  线条  │");
        System.out.println("└────────┴────────┴────────┘");
        System.out.println();
        
        System.out.println("现在测试实际表格：");
        String[] headers = {"测试1", "测试2", "测试3"};
        String[][] data = {
            {"内容1", "内容2", "内容3"},
            {"数据1", "数据2", "数据3"}
        };
        
        ConsoleUtil.printSmartTable(headers, data);
        
        System.out.println();
        System.out.println("请对比上下两个表格的边框是否对齐");
    }
}
