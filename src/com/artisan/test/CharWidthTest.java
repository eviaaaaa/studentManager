package com.artisan.test;

import com.artisan.util.ConsoleUtil;

/**
 * 字符宽度测试类
 * 验证等宽字体中字符宽度的正确处理
 * 一个"─"字符 = 两个英文字母 = 一个汉字
 * 
 * @author llq-artisan
 * @version 1.0
 */
public class CharWidthTest {
    
    public static void main(String[] args) {
        System.out.println("=== 等宽字体字符宽度测试 ===");
        System.out.println();
        
        // 测试字符宽度对齐
        testCharacterWidth();
        
        // 测试边框对齐
        testBorderAlignment();
        
        // 测试表格对齐
        testTableAlignment();
        
        System.out.println("=== 测试完成 ===");
        System.out.println("请检查所有边框是否完全对齐");
    }
    
    /**
     * 测试字符宽度
     */
    private static void testCharacterWidth() {
        System.out.println("字符宽度对比测试:");
        System.out.println("英文字符: abcdefghijklmnopqrstuvwxyz");
        System.out.println("边框字符: ─────────────────────────────");
        System.out.println("中文字符: 这是一个测试中文字符宽度的句子");
        System.out.println("混合内容: abc中文def测试ghi内容jkl");
        System.out.println();
    }
    
    /**
     * 测试边框对齐
     */
    private static void testBorderAlignment() {
        System.out.println("边框对齐测试:");
        
        // 测试不同长度的标题
        ConsoleUtil.printTitle("短");
        System.out.println();
        
        ConsoleUtil.printTitle("中等长度标题");
        System.out.println();
        
        ConsoleUtil.printTitle("这是一个很长的标题测试");
        System.out.println();
        
        ConsoleUtil.printTitle("Mixed English 和中文 Title");
        System.out.println();
    }
    
    /**
     * 测试表格对齐
     */
    private static void testTableAlignment() {
        System.out.println("表格边框对齐测试:");
        
        // 测试1: 简单表格
        String[] headers1 = {"A", "B", "C"};
        String[][] data1 = {
            {"1", "2", "3"},
            {"4", "5", "6"}
        };
        ConsoleUtil.printSmartTable(headers1, data1);
        System.out.println();
        
        // 测试2: 中文表格
        String[] headers2 = {"编号", "姓名", "年龄"};
        String[][] data2 = {
            {"001", "张三", "20"},
            {"002", "李四", "21"}
        };
        ConsoleUtil.printSmartTable(headers2, data2);
        System.out.println();
        
        // 测试3: 混合表格
        String[] headers3 = {"ID", "中文名", "English", "分数"};
        String[][] data3 = {
            {"001", "张三", "Zhang", "95"},
            {"002", "李四", "Li Si", "87"}
        };
        ConsoleUtil.printSmartTable(headers3, data3);
        System.out.println();
        
        // 测试4: 长内容表格
        String[] headers4 = {"课程", "描述", "学分"};
        String[][] data4 = {
            {"数学", "高等数学基础课程", "4"},
            {"英语", "大学英语综合课程", "3"},
            {"计算机", "计算机科学导论课程", "3"}
        };
        ConsoleUtil.printSmartTable(headers4, data4);
    }
}
