package com.artisan.test;

import com.artisan.util.ConsoleUtil;

/**
 * 表格对齐专项测试类
 * 专门测试表格边框对齐问题的修复效果
 * 
 * @author llq-artisan
 * @version 1.0
 */
public class TableAlignmentTest {
    
    public static void main(String[] args) {
        ConsoleUtil.printWelcomeBanner("表格对齐测试", "v2.0");
        
        // 测试1: 纯中文表格
        testChineseTable();
        
        // 测试2: 纯英文表格
        testEnglishTable();
        // 测试3: 中英文混合表格
        testMixedTable();
        
        // 测试4: 包含数字的表格
        testNumberTable();
        
        // 测试5: 长文本截断测试
        testLongTextTable();
        
        ConsoleUtil.printSuccess("表格对齐测试完成！");
    }
    
    /**
     * 测试纯中文表格
     */
    private static void testChineseTable() {
        ConsoleUtil.printTitle("纯中文表格测试");
        
        String[] headers = {"学号", "姓名", "性别", "年龄", "专业"};
        String[][] data = {
            {"学001", "张三", "男", "二十", "计算机"},
            {"学002", "李四", "女", "十九", "软件工程"},
            {"学003", "王五", "男", "二十一", "信息管理"},
            {"学004", "赵六", "女", "二十", "网络工程"}
        };
        
        ConsoleUtil.printSmartTable(headers, data);
        System.out.println();
    }
    
    /**
     * 测试纯英文表格
     */
    private static void testEnglishTable() {
        ConsoleUtil.printTitle("Pure English Table Test");
        
        String[] headers = {"ID", "Name", "Gender", "Age", "Major"};
        String[][] data = {
            {"001", "Zhang", "M", "20", "Computer Science"},
            {"002", "Li", "F", "19", "Software Engineering"},
            {"003", "Wang", "M", "21", "Information Management"},
            {"004", "Zhao", "F", "20", "Network Engineering"}
        };
        
        ConsoleUtil.printSmartTable(headers, data);
        System.out.println();
    }
    
    /**
     * 测试中英文混合表格
     */
    private static void testMixedTable() {
        ConsoleUtil.printTitle("中英文混合表格测试");
        
        String[] headers = {"ID", "中文姓名", "English Name", "分数", "Status"};
        String[][] data = {
            {"001", "张三", "Zhang San", "95", "优秀"},
            {"002", "李四", "Li Si", "87", "良好"},
            {"003", "王五", "Wang Wu", "92", "优秀"},
            {"004", "赵六", "Zhao Liu", "88", "良好"}
        };
        
        ConsoleUtil.printSmartTable(headers, data);
        System.out.println();
    }
    
    /**
     * 测试包含数字的表格（测试右对齐）
     */
    private static void testNumberTable() {
        ConsoleUtil.printTitle("数字对齐测试表格");
        
        String[] headers = {"课程", "学分", "成绩", "绩点", "排名"};
        String[][] data = {
            {"高等数学", "4", "95.5", "4.0", "1"},
            {"大学英语", "3", "87.0", "3.7", "15"},
            {"程序设计", "3", "92.5", "3.9", "5"},
            {"线性代数", "2", "88.0", "3.8", "12"}
        };
        
        ConsoleUtil.printSmartTable(headers, data);
        System.out.println();
    }
    
    /**
     * 测试长文本截断
     */
    private static void testLongTextTable() {
        ConsoleUtil.printTitle("长文本截断测试");
        
        String[] headers = {"编号", "课程名称", "课程描述", "教师", "地点"};
        String[][] data = {
            {"CS101", "计算机科学导论", "这是一门介绍计算机科学基本概念和原理的入门课程", "张教授", "A101"},
            {"MATH201", "高等数学A", "微积分、线性代数等数学基础课程", "李教授", "B203"},
            {"ENG101", "大学英语", "提高学生英语听说读写能力", "王老师", "C301"},
            {"PHYS301", "大学物理实验", "通过实验加深对物理概念的理解和应用", "赵老师", "D201"}
        };
        
        ConsoleUtil.printSmartTable(headers, data);
        System.out.println();
    }
}
