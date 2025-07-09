package com.artisan.test;

import com.artisan.util.ConsoleUtil;

/**
 * 长文本表格显示测试类
 * 测试各种长文本内容的完整显示效果
 * 
 * @author llq-artisan
 * @version 1.0
 */
public class LongTextTableTest {
    
    public static void main(String[] args) {
        ConsoleUtil.printWelcomeBanner("长文本表格测试", "v1.0");
        
        // 测试1: 标准长度表格
        testStandardTable();
        
        // 测试2: 中等长度文本表格
        testMediumTextTable();
        
        // 测试3: 长文本表格
        testLongTextTable();
        
        // 测试4: 超长文本表格
        testVeryLongTextTable();
        
        // 测试5: 混合长度表格
        testMixedLengthTable();
        
        ConsoleUtil.printSuccess("长文本表格测试完成！");
    }
    
    /**
     * 测试标准长度表格
     */
    private static void testStandardTable() {
        ConsoleUtil.printTitle("标准长度表格测试");
        
        String[] headers = {"学号", "姓名", "专业", "成绩"};
        String[][] data = {
            {"2021001", "张三", "计算机", "95"},
            {"2021002", "李四", "软件工程", "87"},
            {"2021003", "王五", "信息管理", "92"}
        };
        
        ConsoleUtil.printSmartTable(headers, data);
        System.out.println();
    }
    
    /**
     * 测试中等长度文本表格
     */
    private static void testMediumTextTable() {
        ConsoleUtil.printTitle("中等长度文本表格测试");
        
        String[] headers = {"课程编号", "课程名称", "任课教师", "上课地点", "课程描述"};
        String[][] data = {
            {"CS101", "计算机科学导论", "张教授", "教学楼A101", "介绍计算机科学基本概念"},
            {"MATH201", "高等数学A", "李教授", "教学楼B203", "微积分和线性代数基础"},
            {"ENG101", "大学英语", "王老师", "外语楼C301", "提高英语综合应用能力"}
        };
        
        ConsoleUtil.printSmartTable(headers, data);
        System.out.println();
    }
    
    /**
     * 测试长文本表格
     */
    private static void testLongTextTable() {
        ConsoleUtil.printTitle("长文本表格测试");
        
        String[] headers = {"项目名称", "项目描述", "负责人", "项目状态"};
        String[][] data = {
            {
                "学生管理系统开发项目", 
                "这是一个基于Java技术栈开发的学生学籍管理系统，包含学生信息管理、成绩管理、课程管理等核心功能模块", 
                "张三", 
                "进行中"
            },
            {
                "在线学习平台建设", 
                "构建一个现代化的在线学习平台，支持视频课程、在线考试、作业提交、师生互动等功能，采用微服务架构", 
                "李四", 
                "已完成"
            },
            {
                "智能图书馆系统", 
                "利用人工智能技术开发的智能图书馆管理系统，包括图书推荐、智能检索、自动分类等先进功能", 
                "王五", 
                "规划中"
            }
        };
        
        ConsoleUtil.printSmartTable(headers, data);
        System.out.println();
    }
    
    /**
     * 测试超长文本表格
     */
    private static void testVeryLongTextTable() {
        ConsoleUtil.printTitle("超长文本表格测试");
        
        String[] headers = {"需求编号", "需求详细描述", "优先级", "预计工期"};
        String[][] data = {
            {
                "REQ-001", 
                "开发一个功能完整的学生学籍管理系统，该系统需要支持学生基本信息管理、学籍变更管理、成绩录入与查询、课程安排管理、教师信息管理、班级管理、专业管理、院系管理等核心业务功能。同时系统还需要具备数据导入导出、统计报表生成、权限管理、日志审计等辅助功能。系统应采用B/S架构，支持多用户并发访问，具备良好的用户体验和系统性能。", 
                "高", 
                "3个月"
            },
            {
                "REQ-002", 
                "构建一个现代化的在线教育平台，平台需要支持多种教学模式包括直播教学、录播课程、互动讨论、在线考试、作业管理等。平台应具备完善的用户管理体系，支持学生、教师、管理员等多种角色。同时需要集成支付系统、消息通知系统、数据分析系统等第三方服务。平台应采用微服务架构，支持高并发访问，具备良好的扩展性和稳定性。", 
                "中", 
                "6个月"
            }
        };
        
        ConsoleUtil.printSmartTable(headers, data);
        System.out.println();
    }
    
    /**
     * 测试混合长度表格
     */
    private static void testMixedLengthTable() {
        ConsoleUtil.printTitle("混合长度表格测试");
        
        String[] headers = {"ID", "标题", "内容", "状态", "创建时间"};
        String[][] data = {
            {
                "1", 
                "短标题", 
                "这是一个相对较短的内容描述", 
                "完成", 
                "2024-01-01"
            },
            {
                "2", 
                "这是一个比较长的标题用来测试表格的自适应能力", 
                "这是一个非常详细的内容描述，包含了大量的信息和细节，用来测试系统对长文本的处理能力。内容可能包括技术规格、实施方案、风险评估、时间安排等多个方面的详细说明。", 
                "进行中", 
                "2024-01-15"
            },
            {
                "3", 
                "中等标题", 
                "中等长度的内容描述，包含一些重要信息", 
                "待开始", 
                "2024-02-01"
            }
        };
        
        ConsoleUtil.printSmartTable(headers, data);
        System.out.println();
    }
}
