package com.artisan.util;

import java.util.List;
import java.util.Scanner;

/**
 * ����̨����������
 * �ṩ�����Ŀ���̨����������ʾ����ɫ֧�ֵȹ���
 * 
 * @author llq-artisan
 * @version 1.0
 */
public class ConsoleUtil {
    
    // ANSI��ɫ����
    public static final String RESET = "\u001B[0m";
    public static final String BLACK = "\u001B[30m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";
    
    // ������ɫ
    public static final String BLACK_BG = "\u001B[40m";
    public static final String RED_BG = "\u001B[41m";
    public static final String GREEN_BG = "\u001B[42m";
    public static final String YELLOW_BG = "\u001B[43m";
    public static final String BLUE_BG = "\u001B[44m";
    public static final String PURPLE_BG = "\u001B[45m";
    public static final String CYAN_BG = "\u001B[46m";
    public static final String WHITE_BG = "\u001B[47m";
    
    // �ı���ʽ
    public static final String BOLD = "\u001B[1m";
    public static final String UNDERLINE = "\u001B[4m";
    
    /**
     * ��ӡ��ɫ�ı�
     * 
     * @param text Ҫ��ӡ���ı�
     * @param color ��ɫ����
     */
    public static void printColored(String text, String color) {
        System.out.println(color + text + RESET);
    }
    
    /**
     * ��ӡ�ɹ���Ϣ
     * 
     * @param message ��Ϣ����
     */
    public static void printSuccess(String message) {
        printColored("? " + message, GREEN);
    }
    
    /**
     * ��ӡ������Ϣ
     * 
     * @param message ��Ϣ����
     */
    public static void printError(String message) {
        printColored("? " + message, RED);
    }
    
    /**
     * ��ӡ������Ϣ
     * 
     * @param message ��Ϣ����
     */
    public static void printWarning(String message) {
        printColored("? " + message, YELLOW);
    }
    
    /**
     * ��ӡ��Ϣ��Ϣ
     * 
     * @param message ��Ϣ����
     */
    public static void printInfo(String message) {
        printColored("? " + message, CYAN);
    }
    
    /**
     * ��ӡ����
     * 
     * @param title ��������
     */
    public static void printTitle(String title) {
        int titleWidth = getDisplayWidthForTable(title) + 4; // ���Ҹ�2���ո��padding
        // �߿��ַ�����ռ1��λ�ã�����Ҫż������

        String border = "=".repeat(titleWidth); // "="�ַ�ռ1��λ��
        String titleRow = "�U" + centerTextForTable(title, titleWidth) + "�U";

        printColored("�X" + border + "�[", BLUE);
        printColored(titleRow, BLUE + BOLD);
        printColored("�^" + border + "�a", BLUE);
    }
    
    /**
     * ��ӡ�ָ���
     */
    public static void printSeparator() {
        // "-"�ַ�ռ1��λ�ã�����60��"-"�ַ� = 60���ַ����
        printColored("-".repeat(60), CYAN);
    }
    
    /**
     * ��ӡ�����Ĳ˵�
     *
     * @param title �˵�����
     * @param options �˵�ѡ��
     */
    public static void printMenu(String title, String[] options) {
        System.out.println();
        printTitle(title);
        System.out.println();

        // �����˵��� - ʹ����ȷ���ַ���ȼ���
        int titleWidth = getDisplayWidthForTable(title);
        int maxOptionWidth = getMaxOptionDisplayWidth(options);
        int maxLength = Math.max(titleWidth, maxOptionWidth) + 4; // ���Ҹ�2���ո�padding

        // �߿��ַ�����ռ1��λ�ã�����Ҫż������

        String topBorder = "��" + "-".repeat(maxLength) + "��";
        String bottomBorder = "��" + "-".repeat(maxLength) + "��";

        printColored(topBorder, CYAN);

        // ��ӡ�˵�ѡ��
        for (String option : options) {
            String paddedOption = "�� " + leftAlignForTable(option, maxLength - 2) + " ��";
            if (option.startsWith("0") || option.contains("�˳�") || option.contains("����")) {
                printColored(paddedOption, YELLOW);
            } else {
                printColored(paddedOption, WHITE);
            }
        }

        printColored(bottomBorder, CYAN);
        System.out.println();
        System.out.print(CYAN + "��ѡ��: " + RESET);
    }

    /**
     * ��ȡѡ���������ʾ���
     *
     * @param options ѡ������
     * @return �ѡ�����ʾ���
     */
    private static int getMaxOptionDisplayWidth(String[] options) {
        int maxWidth = 0;
        for (String option : options) {
            // �Ƴ�ANSI��ɫ����������ʵ�ʳ���
            String cleanOption = option.replaceAll("\u001B\\[[;\\d]*m", "");
            int displayWidth = getDisplayWidthForTable(cleanOption);
            if (displayWidth > maxWidth) {
                maxWidth = displayWidth;
            }
        }
        return maxWidth;
    }
    
    /**
     * ��ӡ���ͷ��
     *
     * @param headers ��ͷ
     * @param widths �п�
     */
    public static void printTableHeader(String[] headers, int[] widths) {
        // ��ӡ�����߿�
        printTableBorder(widths, "��", "��", "��");

        // ��ӡ��ͷ
        System.out.print("��");
        for (int i = 0; i < headers.length; i++) {
            String header = centerTextForTable(headers[i], widths[i] - 2);
            System.out.print(" " + BOLD + CYAN + header + RESET + " ��");
        }
        System.out.println();

        // ��ӡ�ָ���
        printTableBorder(widths, "��", "��", "��");
    }
    
    /**
     * ��ӡ�����
     *
     * @param data ������
     * @param widths �п�
     */
    public static void printTableRow(String[] data, int[] widths) {
        System.out.print("��");
        for (int i = 0; i < data.length && i < widths.length; i++) {
            String cellContent = data[i] != null ? data[i] : "";
            String formattedCell = formatCellContentForTable(cellContent, widths[i] - 2);
            System.out.print(" " + formattedCell + " ��");
        }
        System.out.println();
    }
    
    /**
     * ��ӡ���ײ�
     * 
     * @param widths �п�
     */
    public static void printTableFooter(int[] widths) {
        printTableBorder(widths, "��", "��", "��");
    }
    
    /**
     * ��ӡ���߿�
     * 
     * @param widths �п�
     * @param left ��߿��ַ�
     * @param middle �м��ַ�
     * @param right �ұ߿��ַ�
     */
    private static void printTableBorder(int[] widths, String left, String middle, String right) {
        System.out.print(left);
        for (int i = 0; i < widths.length; i++) {
            // ʹ��"-"�ַ���ռ1��λ��
            System.out.print("-".repeat(widths[i]));
            if (i < widths.length - 1) {
                System.out.print(middle);
            }
        }
        System.out.println(right);
    }
    
    /**
     * �ȴ��û����س�����
     * 
     * @param scanner Scanner����
     */
    public static void waitForEnter(Scanner scanner) {
        printColored("\n���س�������...", YELLOW);
        scanner.nextLine();
    }
    
    /**
     * ������ģ�⣩
     */
    public static void clearScreen() {
        // ��ӡ������з���ģ������
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }
    
    /**
     * ��ӡ��ӭ���
     * 
     * @param systemName ϵͳ����
     * @param version �汾��
     */
    public static void printWelcomeBanner(String systemName, String version) {
        // ��Եȿ������Ż��ĺ�����
        int bannerWidth = 60; // �̶����60�ַ�

        // ׼��Ҫ��ʾ���ı��У�����emoji�ڵȿ������е���ʾ���⣩
        String titleLine = "  " + systemName + "  ";
        String versionLine = "�汾: " + version;
        String welcomeLine = "��ӭʹ��ѧ��ѧ������ϵͳ��";

        // �����߿�"="�ַ�ռ1��λ�ã�
        String topBorder = "�X" + "=".repeat(bannerWidth) + "�[";
        String bottomBorder = "�^" + "=".repeat(bannerWidth) + "�a";
        String emptyLine = "�U" + " ".repeat(bannerWidth) + "�U";

        // ���������� - ʹ�ñ��ר�õľ��ж��뷽��
        String titleRow = "�U" + centerTextForTable(titleLine, bannerWidth) + "�U";
        String versionRow = "�U" + centerTextForTable(versionLine, bannerWidth) + "�U";
        String welcomeRow = "�U" + centerTextForTable(welcomeLine, bannerWidth) + "�U";

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
     * �Զ��������п���Եȿ������Ż���
     *
     * @param headers ��ͷ����
     * @param data ����������
     * @return ��������п�����
     */
    public static int[] calculateColumnWidths(String[] headers, String[][] data) {
        int[] widths = new int[headers.length];

        // ��ʼ��Ϊ��ͷ����
        for (int i = 0; i < headers.length; i++) {
            widths[i] = getDisplayWidthForTable(headers[i]) + 4; // ��4Ϊ����padding�ͱ߿�
        }

        // ��������У��ҳ������
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

        // ������С������ƣ��߿��ַ�����ռ1��λ�ã�����Ҫż�����ƣ�
        for (int i = 0; i < widths.length; i++) {
            widths[i] = Math.max(10, widths[i]); // ��С10�������������������ʾ��������
        }

        return widths;
    }

    /**
     * ��Ա�����ʾ��ȼ��㣨�ȿ������Ż���
     * �ڵȿ������У�һ��"��"�ַ� = ����Ӣ����ĸ = һ������
     *
     * @param text �ı�����
     * @return ��ʾ���
     */
    private static int getDisplayWidthForTable(String text) {
        if (text == null) return 0;

        int width = 0;
        for (char c : text.toCharArray()) {
            if (isFullWidthChar(c)) {
                // ȫ���ַ����������ġ�ȫ�Ƿ��ŵȣ�ռ2��λ��
                width += 2;
            } else {
                // ����ַ���Ӣ�ġ����֡���Ƿ��ŵȣ�ռ1��λ��
                width += 1;
            }
        }
        return width;
    }

    /**
     * �ж��Ƿ�Ϊȫ���ַ�
     *
     * @param c �ַ�
     * @return �Ƿ�Ϊȫ���ַ�
     */
    private static boolean isFullWidthChar(char c) {
        // �����ַ���Χ
        if (c >= 0x4E00 && c <= 0x9FFF) return true;
        // ���ı�����
        if (c >= 0x3000 && c <= 0x303F) return true;
        // ȫ��ASCII�ַ�
        if (c >= 0xFF00 && c <= 0xFFEF) return true;
        // ��������ȫ���ַ�
        if (c >= 0x2E80 && c <= 0x2EFF) return true; // CJK���ײ���
        if (c >= 0x3400 && c <= 0x4DBF) return true; // CJK��չA
        if (c >= 0x20000 && c <= 0x2A6DF) return true; // CJK��չB

        return false;
    }

    /**
     * ��Ա��ĵ�Ԫ�����ݸ�ʽ��
     *
     * @param content ԭʼ����
     * @param width ���ÿ��
     * @return ��ʽ���������
     */
    private static String formatCellContentForTable(String content, int width) {
        if (content == null) content = "";

        // ���ض����ݣ�������ʾ
        if (isNumeric(content)) {
            // �����Ҷ���
            return rightAlignForTable(content, width);
        } else {
            // �ı������
            return leftAlignForTable(content, width);
        }
    }

    /**
     * ��Ա��ľ��ж���
     *
     * @param text �ı�����
     * @param width �ܿ��
     * @return ���ж�����ı�
     */
    private static String centerTextForTable(String text, int width) {
        if (text == null) text = "";

        int displayWidth = getDisplayWidthForTable(text);
        if (displayWidth >= width) {
            // ���ݱ��п���ֱ�ӷ���ԭ�ı������ض�
            return text;
        }

        int padding = width - displayWidth;
        int leftPadding = padding / 2;
        int rightPadding = padding - leftPadding;

        return " ".repeat(leftPadding) + text + " ".repeat(rightPadding);
    }

    /**
     * ��Ա��������
     *
     * @param text �ı�����
     * @param width �ܿ��
     * @return �������ı�
     */
    private static String leftAlignForTable(String text, int width) {
        if (text == null) text = "";

        int displayWidth = getDisplayWidthForTable(text);
        if (displayWidth >= width) {
            // ���ݱ��п���ֱ�ӷ���ԭ�ı������ض�
            return text;
        }

        return text + " ".repeat(width - displayWidth);
    }

    /**
     * ��Ա����Ҷ���
     *
     * @param text �ı�����
     * @param width �ܿ��
     * @return �Ҷ�����ı�
     */
    private static String rightAlignForTable(String text, int width) {
        if (text == null) text = "";

        int displayWidth = getDisplayWidthForTable(text);
        if (displayWidth >= width) {
            // ���ݱ��п���ֱ�ӷ���ԭ�ı������ض�
            return text;
        }

        return " ".repeat(width - displayWidth) + text;
    }

    /**
     * ��Ա����ı��ض�
     *
     * @param text ԭʼ�ı�
     * @param maxWidth �����
     * @return �ضϺ���ı�
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
     * �ж��ַ����Ƿ�Ϊ����
     *
     * @param str �ַ���
     * @return �Ƿ�Ϊ����
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
     * ���ܱ����ʾ - �Զ������п���ʽ����ʾ
     * ֧�ֳ��ı�������ʾ���Զ����������
     *
     * @param headers ��ͷ
     * @param data ������
     */
    public static void printSmartTable(String[] headers, String[][] data) {
        if (headers == null || data == null || headers.length == 0) {
            printWarning("û�����ݿ���ʾ");
            return;
        }

        // ����Ƿ��г����ı���Ҫ���⴦��
        boolean hasLongText = checkForLongText(headers, data);

        if (hasLongText) {
            printAdaptiveTable(headers, data);
        } else {
            printStandardTable(headers, data);
        }

        // ��ʾͳ����Ϣ
        printSuccess("����ʾ " + data.length + " ����¼");
    }

    /**
     * ����Ƿ��г����ı�
     *
     * @param headers ��ͷ
     * @param data ����
     * @return �Ƿ��г����ı�
     */
    private static boolean checkForLongText(String[] headers, String[][] data) {
        // ����ͷ
        for (String header : headers) {
            if (getDisplayWidthForTable(header) > 20) {
                return true;
            }
        }

        // �������
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
     * ��ӡ��׼����޳����ı���
     *
     * @param headers ��ͷ
     * @param data ����
     */
    private static void printStandardTable(String[] headers, String[][] data) {
        // �Զ������п����ú���������
        int[] widths = calculateStandardColumnWidths(headers, data);

        // ��ӡ���
        printTableHeader(headers, widths);
        for (String[] row : data) {
            printTableRow(row, widths);
        }
        printTableFooter(widths);
    }

    /**
     * ��ӡ����Ӧ����г����ı���
     *
     * @param headers ��ͷ
     * @param data ����
     */
    private static void printAdaptiveTable(String[] headers, String[][] data) {
        // ���������п������������
        int[] widths = calculateColumnWidths(headers, data);

        // ����ܿ���Ƿ񳬹�����Χ
        int totalWidth = 0;
        for (int width : widths) {
            totalWidth += width + 1; // +1 for border
        }

        if (totalWidth > 120) { // ����ܿ�ȳ���120�ַ�
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
     * ��ӡ���ձ�񣨳������ݵ����⴦��
     *
     * @param headers ��ͷ
     * @param data ����
     */
    private static void printCompactTable(String[] headers, String[][] data) {
        printInfo("��⵽�������ݣ�ʹ�ý�����ʾģʽ��");
        System.out.println();

        for (int i = 0; i < data.length; i++) {
            printColored("��¼ " + (i + 1) + ":", BOLD + CYAN);
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
     * �����׼�п�����������ƣ�
     *
     * @param headers ��ͷ
     * @param data ����
     * @return �п�����
     */
    private static int[] calculateStandardColumnWidths(String[] headers, String[][] data) {
        int[] widths = new int[headers.length];

        // ��ʼ��Ϊ��ͷ����
        for (int i = 0; i < headers.length; i++) {
            widths[i] = getDisplayWidthForTable(headers[i]) + 4;
        }

        // ���������
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

        // ������С����������ƣ��߿��ַ�����ռ1��λ�ã�����Ҫż�����ƣ�
        for (int i = 0; i < widths.length; i++) {
            widths[i] = Math.max(10, Math.min(widths[i], 30)); // ��С10�����30
        }

        return widths;
    }
}
