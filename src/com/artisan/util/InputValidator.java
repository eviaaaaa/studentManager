package com.artisan.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * 输入验证工具类
 * 提供各种输入验证和安全输入方法
 * 
 * @author llq-artisan
 * @version 1.0
 */
public class InputValidator {
    
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    
    // 常用正则表达式
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
        "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$");
    private static final Pattern PHONE_PATTERN = Pattern.compile("^1[3-9]\\d{9}$");
    private static final Pattern ID_PATTERN = Pattern.compile("^[a-zA-Z0-9_-]+$");
    
    /**
     * 安全读取整数输入
     * 
     * @param scanner Scanner对象
     * @param prompt 提示信息
     * @param min 最小值
     * @param max 最大值
     * @return 有效的整数
     */
    public static int readInt(Scanner scanner, String prompt, int min, int max) {
        int value;
        while (true) {
            System.out.print(prompt);
            try {
                value = scanner.nextInt();
                scanner.nextLine(); // 消费换行符
                
                if (value >= min && value <= max) {
                    return value;
                } else {
                    ConsoleUtil.printError("输入值必须在 " + min + " 到 " + max + " 之间");
                }
            } catch (Exception e) {
                ConsoleUtil.printError("请输入有效的数字");
                scanner.nextLine(); // 清除错误输入
            }
        }
    }
    
    /**
     * 安全读取字符串输入
     * 
     * @param scanner Scanner对象
     * @param prompt 提示信息
     * @param allowEmpty 是否允许空值
     * @param maxLength 最大长度
     * @return 有效的字符串
     */
    public static String readString(Scanner scanner, String prompt, boolean allowEmpty, int maxLength) {
        String value;
        while (true) {
            System.out.print(prompt);
            value = scanner.nextLine().trim();
            
            if (!allowEmpty && value.isEmpty()) {
                ConsoleUtil.printError("输入不能为空");
                continue;
            }
            
            if (value.length() > maxLength) {
                ConsoleUtil.printError("输入长度不能超过 " + maxLength + " 个字符");
                continue;
            }
            
            return value;
        }
    }
    
    /**
     * 读取日期输入
     * 
     * @param scanner Scanner对象
     * @param prompt 提示信息
     * @return 有效的日期
     */
    public static Date readDate(Scanner scanner, String prompt) {
        String dateStr;
        Date date;
        while (true) {
            System.out.print(prompt + " (格式: yyyy-MM-dd): ");
            dateStr = scanner.nextLine().trim();
            
            try {
                date = DATE_FORMAT.parse(dateStr);
                return date;
            } catch (ParseException e) {
                ConsoleUtil.printError("日期格式不正确，请使用 yyyy-MM-dd 格式");
            }
        }
    }
    
    /**
     * 读取性别输入
     * 
     * @param scanner Scanner对象
     * @param prompt 提示信息
     * @return 有效的性别
     */
    public static String readGender(Scanner scanner, String prompt) {
        String gender;
        while (true) {
            System.out.print(prompt + " (男/女): ");
            gender = scanner.nextLine().trim();
            
            if ("男".equals(gender) || "女".equals(gender)) {
                return gender;
            } else {
                ConsoleUtil.printError("性别只能输入 '男' 或 '女'");
            }
        }
    }
    
    /**
     * 读取年龄输入
     * 
     * @param scanner Scanner对象
     * @param prompt 提示信息
     * @return 有效的年龄
     */
    public static int readAge(Scanner scanner, String prompt) {
        return readInt(scanner, prompt, 1, 150);
    }
    
    /**
     * 读取学分输入
     * 
     * @param scanner Scanner对象
     * @param prompt 提示信息
     * @return 有效的学分
     */
    public static int readCredit(Scanner scanner, String prompt) {
        return readInt(scanner, prompt, 1, 10);
    }
    
    /**
     * 读取成绩输入
     * 
     * @param scanner Scanner对象
     * @param prompt 提示信息
     * @return 有效的成绩
     */
    public static int readGrade(Scanner scanner, String prompt) {
        return readInt(scanner, prompt, 0, 100);
    }
    
    /**
     * 读取ID输入（学生ID、课程ID等）
     * 
     * @param scanner Scanner对象
     * @param prompt 提示信息
     * @return 有效的ID
     */
    public static String readId(Scanner scanner, String prompt) {
        String id;
        while (true) {
            System.out.print(prompt);
            id = scanner.nextLine().trim();
            
            if (id.isEmpty()) {
                ConsoleUtil.printError("ID不能为空");
                continue;
            }
            
            if (!ID_PATTERN.matcher(id).matches()) {
                ConsoleUtil.printError("ID只能包含字母、数字、下划线和连字符");
                continue;
            }
            
            if (id.length() > 30) {
                ConsoleUtil.printError("ID长度不能超过30个字符");
                continue;
            }
            
            return id;
        }
    }
    
    /**
     * 读取邮箱输入
     * 
     * @param scanner Scanner对象
     * @param prompt 提示信息
     * @param allowEmpty 是否允许空值
     * @return 有效的邮箱
     */
    public static String readEmail(Scanner scanner, String prompt, boolean allowEmpty) {
        String email;
        while (true) {
            System.out.print(prompt);
            email = scanner.nextLine().trim();
            
            if (allowEmpty && email.isEmpty()) {
                return email;
            }
            
            if (email.isEmpty()) {
                ConsoleUtil.printError("邮箱不能为空");
                continue;
            }
            
            if (!EMAIL_PATTERN.matcher(email).matches()) {
                ConsoleUtil.printError("邮箱格式不正确");
                continue;
            }
            
            return email;
        }
    }
    
    /**
     * 读取手机号输入
     * 
     * @param scanner Scanner对象
     * @param prompt 提示信息
     * @param allowEmpty 是否允许空值
     * @return 有效的手机号
     */
    public static String readPhone(Scanner scanner, String prompt, boolean allowEmpty) {
        String phone;
        while (true) {
            System.out.print(prompt);
            phone = scanner.nextLine().trim();
            
            if (allowEmpty && phone.isEmpty()) {
                return phone;
            }
            
            if (phone.isEmpty()) {
                ConsoleUtil.printError("手机号不能为空");
                continue;
            }
            
            if (!PHONE_PATTERN.matcher(phone).matches()) {
                ConsoleUtil.printError("手机号格式不正确（应为11位数字，以1开头）");
                continue;
            }
            
            return phone;
        }
    }
    
    /**
     * 确认操作
     * 
     * @param scanner Scanner对象
     * @param message 确认消息
     * @return 是否确认
     */
    public static boolean confirm(Scanner scanner, String message) {
        String input;
        while (true) {
            System.out.print(message + " (y/n): ");
            input = scanner.nextLine().trim().toLowerCase();
            
            if ("y".equals(input) || "yes".equals(input) || "是".equals(input)) {
                return true;
            } else if ("n".equals(input) || "no".equals(input) || "否".equals(input)) {
                return false;
            } else {
                ConsoleUtil.printError("请输入 y/n 或 是/否");
            }
        }
    }
    
    /**
     * 验证字符串是否为有效的数字
     * 
     * @param str 要验证的字符串
     * @return 是否为有效数字
     */
    public static boolean isValidNumber(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    /**
     * 验证字符串是否为有效的日期
     * 
     * @param dateStr 要验证的日期字符串
     * @return 是否为有效日期
     */
    public static boolean isValidDate(String dateStr) {
        try {
            DATE_FORMAT.parse(dateStr);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
}
