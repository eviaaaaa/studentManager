package com.artisan.test;

import com.artisan.util.ConsoleUtil;

/**
 * 控制台美化效果测试类
 * 用于测试菜单和表格的显示效果
 * 
 * @author llq-artisan
 * @version 1.0
 */
public class ConsoleTest {
    
    public static void main(String[] args) {
        // 测试欢迎横幅
        ConsoleUtil.printWelcomeBanner("学生学籍管理系统", "v2.0");
        
        // 测试各种消息类型
        ConsoleUtil.printSuccess("这是成功消息");
        ConsoleUtil.printError("这是错误消息");
        ConsoleUtil.printWarning("这是警告消息");
        ConsoleUtil.printInfo("这是信息消息");
        
        System.out.println();
        
        // 测试标题
        ConsoleUtil.printTitle("测试标题");
        
        System.out.println();
        
        // 测试菜单
        String[] menuOptions = {
            "1 - 查询所有学生基本信息",
            "2 - 查询所有学生成绩信息", 
            "3 - 查询所有学生奖惩信息",
            "4 - 查询某学生成绩单",
            "5 - 高级查询学生基本信息",
            "0 - 返回主菜单"
        };
        
        ConsoleUtil.printMenu("查询菜单", menuOptions);
        
        System.out.println();
        System.out.println();
        
        // 测试智能表格 - 基本数据
        ConsoleUtil.printTitle("基本表格测试");
        String[] headers1 = {"学号", "姓名", "性别", "年龄", "班级"};
        String[][] testData1 = {
            {"2021001", "张三", "男", "20", "计算机科学与技术1班"},
            {"2021002", "李四", "女", "19", "计算机1班"},
            {"2021003", "王五", "男", "21", "软件工程1班"},
            {"2021004", "赵六", "女", "20", "信息管理1班"}
        };

        ConsoleUtil.printSmartTable(headers1, testData1);

        System.out.println();

        // 测试智能表格 - 包含长文本和数字
        ConsoleUtil.printTitle("复杂数据表格测试");
        String[] headers2 = {"课程编号", "课程名称", "学分", "成绩", "备注"};
        String[][] testData2 = {
            {"CS101", "计算机科学导论", "3", "95", "优秀课程，内容丰富"},
            {"MATH201", "高等数学A", "4", "87", "基础数学课程"},
            {"ENG101", "大学英语", "2", "92", ""},
            {"PHYS301", "大学物理实验", "1", "88", "实验课程，动手能力强"}
        };

        ConsoleUtil.printSmartTable(headers2, testData2);

        System.out.println();

        // 测试智能表格 - 中英文混合
        ConsoleUtil.printTitle("中英文混合表格测试");
        String[] headers3 = {"ID", "Name", "中文姓名", "Score", "评价"};
        String[][] testData3 = {
            {"001", "Zhang San", "张三", "95.5", "非常优秀的学生"},
            {"002", "Li Si", "李四", "87.0", "Good"},
            {"003", "Wang Wu", "王五", "92.5", "表现良好"},
            {"004", "Zhao Liu", "赵六", "88.0", "Excellent performance"}
        };

        ConsoleUtil.printSmartTable(headers3, testData3);
        
        System.out.println();
        ConsoleUtil.printSeparator();
        ConsoleUtil.printInfo("测试完成！");
    }
}
