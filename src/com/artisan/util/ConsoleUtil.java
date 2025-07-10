package com.artisan.util;

import java.util.List;
import java.util.Scanner;

/**
 * 控制台美化工具类
 * 提供美化的控制台输出、表格显示、颜色支持等功能
 * 
 * @author llq-artisan
 * @version 1.0
 */
public class ConsoleUtil {
    
    // ANSI颜色代码
    public static final String RESET = "\u001B[0m";
    public static final String BLACK = "\u001B[30m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";
    
    // 背景颜色
    public static final String BLACK_BG = "\u001B[40m";
    public static final String RED_BG = "\u001B[41m";
    public static final String GREEN_BG = "\u001B[42m";
    public static final String YELLOW_BG = "\u001B[43m";
    public static final String BLUE_BG = "\u001B[44m";
    public static final String PURPLE_BG = "\u001B[45m";
    public static final String CYAN_BG = "\u001B[46m";
    public static final String WHITE_BG = "\u001B[47m";
    
    // 文本样式
    public static final String BOLD = "\u001B[1m";
    public static final String UNDERLINE = "\u001B[4m";
    
    /**
     * 打印彩色文本
     * 
     * @param text 要打印的文本
     * @param color 颜色代码
     */
    public static void printColored(String text, String color) {
        System.out.println(color + text + RESET);
    }
    
    /**
     * 打印成功消息
     * 
     * @param message 消息内容
     */
    public static void printSuccess(String message) {
        printColored("? " + message, GREEN);
    }
    
    /**
     * 打印错误消息
     * 
     * @param message 消息内容
     */
    public static void printError(String message) {
        printColored("? " + message, RED);
    }
    
    /**
     * 打印警告消息
     * 
     * @param message 消息内容
     */
    public static void printWarning(String message) {
        printColored("? " + message, YELLOW);
    }
    
    /**
     * 打印信息消息
     * 
     * @param message 消息内容
     */
    public static void printInfo(String message) {
        printColored("? " + message, CYAN);
    }
    
    /**
     * 打印标题
     * 
     * @param title 标题内容
     */
    public static void printTitle(String title) {
        int titleWidth = getDisplayWidthForTable(title) + 4; // 左右各2个空格的padding
        // 边框字符现在占1个位置，不需要偶数限制

        String border = "=".repeat(titleWidth); // "="字符占1个位置
        String titleRow = "║" + centerTextForTable(title, titleWidth) + "║";

        printColored("╔" + border + "╗", BLUE);
        printColored(titleRow, BLUE + BOLD);
        printColored("╚" + border + "╝", BLUE);
    }
    
    /**
     * 打印分隔线
     */
    public static void printSeparator() {
        // "-"字符占1个位置，所以60个"-"字符 = 60个字符宽度
        printColored("-".repeat(60), CYAN);
    }
    
    /**
     * 打印美化的菜单
     *
     * @param title 菜单标题
     * @param options 菜单选项
     */
    public static void printMenu(String title, String[] options) {
        System.out.println();
        printTitle(title);
        System.out.println();

        // 创建菜单框 - 使用正确的字符宽度计算
        int titleWidth = getDisplayWidthForTable(title);
        int maxOptionWidth = getMaxOptionDisplayWidth(options);
        int maxLength = Math.max(titleWidth, maxOptionWidth) + 4; // 左右各2个空格padding

        // 边框字符现在占1个位置，不需要偶数限制

        String topBorder = "┌" + "-".repeat(maxLength) + "┐";
        String bottomBorder = "└" + "-".repeat(maxLength) + "┘";

        printColored(topBorder, CYAN);

        // 打印菜单选项
        for (String option : options) {
            String paddedOption = "│ " + leftAlignForTable(option, maxLength - 2) + " │";
            if (option.startsWith("0") || option.contains("退出") || option.contains("返回")) {
                printColored(paddedOption, YELLOW);
            } else {
                printColored(paddedOption, WHITE);
            }
        }

        printColored(bottomBorder, CYAN);
        System.out.println();
        System.out.print(CYAN + "请选择: " + RESET);
    }

    /**
     * 获取选项中最长的显示宽度
     *
     * @param options 选项数组
     * @return 最长选项的显示宽度
     */
    private static int getMaxOptionDisplayWidth(String[] options) {
        int maxWidth = 0;
        for (String option : options) {
            // 移除ANSI颜色代码来计算实际长度
            String cleanOption = option.replaceAll("\u001B\\[[;\\d]*m", "");
            int displayWidth = getDisplayWidthForTable(cleanOption);
            if (displayWidth > maxWidth) {
                maxWidth = displayWidth;
            }
        }
        return maxWidth;
    }
    
    /**
     * 打印表格头部
     *
     * @param headers 表头
     * @param widths 列宽
     */
    public static void printTableHeader(String[] headers, int[] widths) {
        // 打印顶部边框
        printTableBorder(widths, "┌", "┬", "┐");

        // 打印表头
        System.out.print("│");
        for (int i = 0; i < headers.length; i++) {
            String header = centerTextForTable(headers[i], widths[i] - 2);
            System.out.print(" " + BOLD + CYAN + header + RESET + " │");
        }
        System.out.println();

        // 打印分隔线
        printTableBorder(widths, "├", "┼", "┤");
    }
    
    /**
     * 打印表格行
     *
     * @param data 行数据
     * @param widths 列宽
     */
    public static void printTableRow(String[] data, int[] widths) {
        System.out.print("│");
        for (int i = 0; i < data.length && i < widths.length; i++) {
            String cellContent = data[i] != null ? data[i] : "";
            String formattedCell = formatCellContentForTable(cellContent, widths[i] - 2);
            System.out.print(" " + formattedCell + " │");
        }
        System.out.println();
    }
    
    /**
     * 打印表格底部
     * 
     * @param widths 列宽
     */
    public static void printTableFooter(int[] widths) {
        printTableBorder(widths, "└", "┴", "┘");
    }
    
    /**
     * 打印表格边框
     * 
     * @param widths 列宽
     * @param left 左边框字符
     * @param middle 中间字符
     * @param right 右边框字符
     */
    private static void printTableBorder(int[] widths, String left, String middle, String right) {
        System.out.print(left);
        for (int i = 0; i < widths.length; i++) {
            // 使用"-"字符，占1个位置
            System.out.print("-".repeat(widths[i]));
            if (i < widths.length - 1) {
                System.out.print(middle);
            }
        }
        System.out.println(right);
    }
    
    /**
     * 等待用户按回车继续
     * 
     * @param scanner Scanner对象
     */
    public static void waitForEnter(Scanner scanner) {
        printColored("\n按回车键继续...", YELLOW);
        scanner.nextLine();
    }
    
    /**
     * 清屏（模拟）
     */
    public static void clearScreen() {
        // 打印多个换行符来模拟清屏
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }
    
    /**
     * 打印欢迎横幅
     * 
     * @param systemName 系统名称
     * @param version 版本号
     */
    public static void printWelcomeBanner(String systemName, String version) {
        // 针对等宽字体优化的横幅设计
        int bannerWidth = 60; // 固定宽度60字符

        // 准备要显示的文本行（避免emoji在等宽字体中的显示问题）
        String titleLine = "  " + systemName + "  ";
        String versionLine = "版本: " + version;
        String welcomeLine = "欢迎使用学生学籍管理系统！";

        // 创建边框（"="字符占1个位置）
        String topBorder = "╔" + "=".repeat(bannerWidth) + "╗";
        String bottomBorder = "╚" + "=".repeat(bannerWidth) + "╝";
        String emptyLine = "║" + " ".repeat(bannerWidth) + "║";

        // 创建内容行 - 使用表格专用的居中对齐方法
        String titleRow = "║" + centerTextForTable(titleLine, bannerWidth) + "║";
        String versionRow = "║" + centerTextForTable(versionLine, bannerWidth) + "║";
        String welcomeRow = "║" + centerTextForTable(welcomeLine, bannerWidth) + "║";

        String[] banner = {
            topBorder,
            emptyLine,
            titleRow,
            emptyLine,
            versionRow,
            emptyLine,
            welcomeRow,
            emptyLine,
            bottomBorder
        };

        System.out.println();
        for (String line : banner) {
            printColored(line, BLUE + BOLD);
        }
        System.out.println();
    }



    /**
     * 自动计算表格列宽（针对等宽字体优化）
     *
     * @param headers 表头数组
     * @param data 数据行数组
     * @return 计算出的列宽数组
     */
    public static int[] calculateColumnWidths(String[] headers, String[][] data) {
        int[] widths = new int[headers.length];

        // 初始化为表头长度
        for (int i = 0; i < headers.length; i++) {
            widths[i] = getDisplayWidthForTable(headers[i]) + 4; // 加4为左右padding和边框
        }

        // 检查数据行，找出最大宽度
        for (String[] row : data) {
            for (int i = 0; i < row.length && i < widths.length; i++) {
                if (row[i] != null) {
                    int dataWidth = getDisplayWidthForTable(row[i]) + 4;
                    if (dataWidth > widths[i]) {
                        widths[i] = dataWidth;
                    }
                }
            }
        }

        // 设置最小宽度限制（边框字符现在占1个位置，不需要偶数限制）
        for (int i = 0; i < widths.length; i++) {
            widths[i] = Math.max(10, widths[i]); // 最小10，不设置最大限制以显示完整内容
        }

        return widths;
    }

    /**
     * 针对表格的显示宽度计算（等宽字体优化）
     * 在等宽字体中：一个"─"字符 = 两个英文字母 = 一个汉字
     *
     * @param text 文本内容
     * @return 显示宽度
     */
    private static int getDisplayWidthForTable(String text) {
        if (text == null) return 0;

        int width = 0;
        for (char c : text.toCharArray()) {
            if (isFullWidthChar(c)) {
                // 全角字符（包括中文、全角符号等）占2个位置
                width += 2;
            } else {
                // 半角字符（英文、数字、半角符号等）占1个位置
                width += 1;
            }
        }
        return width;
    }

    /**
     * 判断是否为全角字符
     *
     * @param c 字符
     * @return 是否为全角字符
     */
    private static boolean isFullWidthChar(char c) {
        // 中文字符范围
        if (c >= 0x4E00 && c <= 0x9FFF) return true;
        // 中文标点符号
        if (c >= 0x3000 && c <= 0x303F) return true;
        // 全角ASCII字符
        if (c >= 0xFF00 && c <= 0xFFEF) return true;
        // 其他常见全角字符
        if (c >= 0x2E80 && c <= 0x2EFF) return true; // CJK部首补充
        if (c >= 0x3400 && c <= 0x4DBF) return true; // CJK扩展A
        if (c >= 0x20000 && c <= 0x2A6DF) return true; // CJK扩展B

        return false;
    }

    /**
     * 针对表格的单元格内容格式化
     *
     * @param content 原始内容
     * @param width 可用宽度
     * @return 格式化后的内容
     */
    private static String formatCellContentForTable(String content, int width) {
        if (content == null) content = "";

        // 不截断内容，完整显示
        if (isNumeric(content)) {
            // 数字右对齐
            return rightAlignForTable(content, width);
        } else {
            // 文本左对齐
            return leftAlignForTable(content, width);
        }
    }

    /**
     * 针对表格的居中对齐
     *
     * @param text 文本内容
     * @param width 总宽度
     * @return 居中对齐的文本
     */
    private static String centerTextForTable(String text, int width) {
        if (text == null) text = "";

        int displayWidth = getDisplayWidthForTable(text);
        if (displayWidth >= width) {
            // 内容比列宽长，直接返回原文本，不截断
            return text;
        }

        int padding = width - displayWidth;
        int leftPadding = padding / 2;
        int rightPadding = padding - leftPadding;

        return " ".repeat(leftPadding) + text + " ".repeat(rightPadding);
    }

    /**
     * 针对表格的左对齐
     *
     * @param text 文本内容
     * @param width 总宽度
     * @return 左对齐的文本
     */
    private static String leftAlignForTable(String text, int width) {
        if (text == null) text = "";

        int displayWidth = getDisplayWidthForTable(text);
        if (displayWidth >= width) {
            // 内容比列宽长，直接返回原文本，不截断
            return text;
        }

        return text + " ".repeat(width - displayWidth);
    }

    /**
     * 针对表格的右对齐
     *
     * @param text 文本内容
     * @param width 总宽度
     * @return 右对齐的文本
     */
    private static String rightAlignForTable(String text, int width) {
        if (text == null) text = "";

        int displayWidth = getDisplayWidthForTable(text);
        if (displayWidth >= width) {
            // 内容比列宽长，直接返回原文本，不截断
            return text;
        }

        return " ".repeat(width - displayWidth) + text;
    }

    /**
     * 针对表格的文本截断
     *
     * @param text 原始文本
     * @param maxWidth 最大宽度
     * @return 截断后的文本
     */
    private static String truncateTextForTable(String text, int maxWidth) {
        if (text == null || maxWidth <= 0) return "";

        int currentWidth = 0;
        StringBuilder result = new StringBuilder();

        for (char c : text.toCharArray()) {
            int charWidth = (c >= 0x4E00 && c <= 0x9FFF) ? 2 : 1;
            if (currentWidth + charWidth > maxWidth) {
                break;
            }
            result.append(c);
            currentWidth += charWidth;
        }

        return result.toString();
    }



    /**
     * 判断字符串是否为数字
     *
     * @param str 字符串
     * @return 是否为数字
     */
    private static boolean isNumeric(String str) {
        if (str == null || str.trim().isEmpty()) {
            return false;
        }
        try {
            Double.parseDouble(str.trim());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * 智能表格显示 - 自动计算列宽并格式化显示
     * 支持长文本完整显示，自动调整表格宽度
     *
     * @param headers 表头
     * @param data 数据行
     */
    public static void printSmartTable(String[] headers, String[][] data) {
        if (headers == null || data == null || headers.length == 0) {
            printWarning("没有数据可显示");
            return;
        }

        // 检查是否有超长文本需要特殊处理
        boolean hasLongText = checkForLongText(headers, data);

        if (hasLongText) {
            printAdaptiveTable(headers, data);
        } else {
            printStandardTable(headers, data);
        }

        // 显示统计信息
        printSuccess("共显示 " + data.length + " 条记录");
    }

    /**
     * 检查是否有超长文本
     *
     * @param headers 表头
     * @param data 数据
     * @return 是否有超长文本
     */
    private static boolean checkForLongText(String[] headers, String[][] data) {
        // 检查表头
        for (String header : headers) {
            if (getDisplayWidthForTable(header) > 20) {
                return true;
            }
        }

        // 检查数据
        for (String[] row : data) {
            for (String cell : row) {
                if (cell != null && getDisplayWidthForTable(cell) > 20) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * 打印标准表格（无超长文本）
     *
     * @param headers 表头
     * @param data 数据
     */
    private static void printStandardTable(String[] headers, String[][] data) {
        // 自动计算列宽，设置合理的最大宽度
        int[] widths = calculateStandardColumnWidths(headers, data);

        // 打印表格
        printTableHeader(headers, widths);
        for (String[] row : data) {
            printTableRow(row, widths);
        }
        printTableFooter(widths);
    }

    /**
     * 打印自适应表格（有超长文本）
     *
     * @param headers 表头
     * @param data 数据
     */
    private static void printAdaptiveTable(String[] headers, String[][] data) {
        // 计算完整列宽，不限制最大宽度
        int[] widths = calculateColumnWidths(headers, data);

        // 检查总宽度是否超过合理范围
        int totalWidth = 0;
        for (int width : widths) {
            totalWidth += width + 1; // +1 for border
        }

        if (totalWidth > 120) { // 如果总宽度超过120字符
            printCompactTable(headers, data);
        } else {
            printTableHeader(headers, widths);
            for (String[] row : data) {
                printTableRow(row, widths);
            }
            printTableFooter(widths);
        }
    }

    /**
     * 打印紧凑表格（超宽内容的特殊处理）
     *
     * @param headers 表头
     * @param data 数据
     */
    private static void printCompactTable(String[] headers, String[][] data) {
        printInfo("检测到超长内容，使用紧凑显示模式：");
        System.out.println();

        for (int i = 0; i < data.length; i++) {
            printColored("记录 " + (i + 1) + ":", BOLD + CYAN);
            for (int j = 0; j < headers.length && j < data[i].length; j++) {
                String value = data[i][j] != null ? data[i][j] : "";
                System.out.println("  " + headers[j] + ": " + value);
            }
            if (i < data.length - 1) {
                printColored("  " + "-".repeat(30), CYAN);
            }
        }
    }

    /**
     * 计算标准列宽（有最大宽度限制）
     *
     * @param headers 表头
     * @param data 数据
     * @return 列宽数组
     */
    private static int[] calculateStandardColumnWidths(String[] headers, String[][] data) {
        int[] widths = new int[headers.length];

        // 初始化为表头长度
        for (int i = 0; i < headers.length; i++) {
            widths[i] = getDisplayWidthForTable(headers[i]) + 4;
        }

        // 检查数据行
        for (String[] row : data) {
            for (int i = 0; i < row.length && i < widths.length; i++) {
                if (row[i] != null) {
                    int dataWidth = getDisplayWidthForTable(row[i]) + 4;
                    if (dataWidth > widths[i]) {
                        widths[i] = dataWidth;
                    }
                }
            }
        }

        // 设置最小和最大宽度限制（边框字符现在占1个位置，不需要偶数限制）
        for (int i = 0; i < widths.length; i++) {
            widths[i] = Math.max(10, Math.min(widths[i], 30)); // 最小10，最大30
        }

        return widths;
    }
}
