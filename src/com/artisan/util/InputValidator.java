package com.artisan.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * ������֤������
 * �ṩ����������֤�Ͱ�ȫ���뷽��
 * 
 * @author llq-artisan
 * @version 1.0
 */
public class InputValidator {
    
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    
    // ����������ʽ
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
        "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$");
    private static final Pattern PHONE_PATTERN = Pattern.compile("^1[3-9]\\d{9}$");
    private static final Pattern ID_PATTERN = Pattern.compile("^[a-zA-Z0-9_-]+$");
    
    /**
     * ��ȫ��ȡ��������
     * 
     * @param scanner Scanner����
     * @param prompt ��ʾ��Ϣ
     * @param min ��Сֵ
     * @param max ���ֵ
     * @return ��Ч������
     */
    public static int readInt(Scanner scanner, String prompt, int min, int max) {
        int value;
        while (true) {
            System.out.print(prompt);
            try {
                value = scanner.nextInt();
                scanner.nextLine(); // ���ѻ��з�
                
                if (value >= min && value <= max) {
                    return value;
                } else {
                    ConsoleUtil.printError("����ֵ������ " + min + " �� " + max + " ֮��");
                }
            } catch (Exception e) {
                ConsoleUtil.printError("��������Ч������");
                scanner.nextLine(); // �����������
            }
        }
    }
    
    /**
     * ��ȫ��ȡ�ַ�������
     * 
     * @param scanner Scanner����
     * @param prompt ��ʾ��Ϣ
     * @param allowEmpty �Ƿ������ֵ
     * @param maxLength ��󳤶�
     * @return ��Ч���ַ���
     */
    public static String readString(Scanner scanner, String prompt, boolean allowEmpty, int maxLength) {
        String value;
        while (true) {
            System.out.print(prompt);
            value = scanner.nextLine().trim();
            
            if (!allowEmpty && value.isEmpty()) {
                ConsoleUtil.printError("���벻��Ϊ��");
                continue;
            }
            
            if (value.length() > maxLength) {
                ConsoleUtil.printError("���볤�Ȳ��ܳ��� " + maxLength + " ���ַ�");
                continue;
            }
            
            return value;
        }
    }
    
    /**
     * ��ȡ��������
     * 
     * @param scanner Scanner����
     * @param prompt ��ʾ��Ϣ
     * @return ��Ч������
     */
    public static Date readDate(Scanner scanner, String prompt) {
        String dateStr;
        Date date;
        while (true) {
            System.out.print(prompt + " (��ʽ: yyyy-MM-dd): ");
            dateStr = scanner.nextLine().trim();
            
            try {
                date = DATE_FORMAT.parse(dateStr);
                return date;
            } catch (ParseException e) {
                ConsoleUtil.printError("���ڸ�ʽ����ȷ����ʹ�� yyyy-MM-dd ��ʽ");
            }
        }
    }
    
    /**
     * ��ȡ�Ա�����
     * 
     * @param scanner Scanner����
     * @param prompt ��ʾ��Ϣ
     * @return ��Ч���Ա�
     */
    public static String readGender(Scanner scanner, String prompt) {
        String gender;
        while (true) {
            System.out.print(prompt + " (��/Ů): ");
            gender = scanner.nextLine().trim();
            
            if ("��".equals(gender) || "Ů".equals(gender)) {
                return gender;
            } else {
                ConsoleUtil.printError("�Ա�ֻ������ '��' �� 'Ů'");
            }
        }
    }
    
    /**
     * ��ȡ��������
     * 
     * @param scanner Scanner����
     * @param prompt ��ʾ��Ϣ
     * @return ��Ч������
     */
    public static int readAge(Scanner scanner, String prompt) {
        return readInt(scanner, prompt, 1, 150);
    }
    
    /**
     * ��ȡѧ������
     * 
     * @param scanner Scanner����
     * @param prompt ��ʾ��Ϣ
     * @return ��Ч��ѧ��
     */
    public static int readCredit(Scanner scanner, String prompt) {
        return readInt(scanner, prompt, 1, 10);
    }
    
    /**
     * ��ȡ�ɼ�����
     * 
     * @param scanner Scanner����
     * @param prompt ��ʾ��Ϣ
     * @return ��Ч�ĳɼ�
     */
    public static int readGrade(Scanner scanner, String prompt) {
        return readInt(scanner, prompt, 0, 100);
    }
    
    /**
     * ��ȡID���루ѧ��ID���γ�ID�ȣ�
     * 
     * @param scanner Scanner����
     * @param prompt ��ʾ��Ϣ
     * @return ��Ч��ID
     */
    public static String readId(Scanner scanner, String prompt) {
        String id;
        while (true) {
            System.out.print(prompt);
            id = scanner.nextLine().trim();
            
            if (id.isEmpty()) {
                ConsoleUtil.printError("ID����Ϊ��");
                continue;
            }
            
            if (!ID_PATTERN.matcher(id).matches()) {
                ConsoleUtil.printError("IDֻ�ܰ�����ĸ�����֡��»��ߺ����ַ�");
                continue;
            }
            
            if (id.length() > 30) {
                ConsoleUtil.printError("ID���Ȳ��ܳ���30���ַ�");
                continue;
            }
            
            return id;
        }
    }
    
    /**
     * ��ȡ��������
     * 
     * @param scanner Scanner����
     * @param prompt ��ʾ��Ϣ
     * @param allowEmpty �Ƿ������ֵ
     * @return ��Ч������
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
                ConsoleUtil.printError("���䲻��Ϊ��");
                continue;
            }
            
            if (!EMAIL_PATTERN.matcher(email).matches()) {
                ConsoleUtil.printError("�����ʽ����ȷ");
                continue;
            }
            
            return email;
        }
    }
    
    /**
     * ��ȡ�ֻ�������
     * 
     * @param scanner Scanner����
     * @param prompt ��ʾ��Ϣ
     * @param allowEmpty �Ƿ������ֵ
     * @return ��Ч���ֻ���
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
                ConsoleUtil.printError("�ֻ��Ų���Ϊ��");
                continue;
            }
            
            if (!PHONE_PATTERN.matcher(phone).matches()) {
                ConsoleUtil.printError("�ֻ��Ÿ�ʽ����ȷ��ӦΪ11λ���֣���1��ͷ��");
                continue;
            }
            
            return phone;
        }
    }
    
    /**
     * ȷ�ϲ���
     * 
     * @param scanner Scanner����
     * @param message ȷ����Ϣ
     * @return �Ƿ�ȷ��
     */
    public static boolean confirm(Scanner scanner, String message) {
        String input;
        while (true) {
            System.out.print(message + " (y/n): ");
            input = scanner.nextLine().trim().toLowerCase();
            
            if ("y".equals(input) || "yes".equals(input) || "��".equals(input)) {
                return true;
            } else if ("n".equals(input) || "no".equals(input) || "��".equals(input)) {
                return false;
            } else {
                ConsoleUtil.printError("������ y/n �� ��/��");
            }
        }
    }
    
    /**
     * ��֤�ַ����Ƿ�Ϊ��Ч������
     * 
     * @param str Ҫ��֤���ַ���
     * @return �Ƿ�Ϊ��Ч����
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
     * ��֤�ַ����Ƿ�Ϊ��Ч������
     * 
     * @param dateStr Ҫ��֤�������ַ���
     * @return �Ƿ�Ϊ��Ч����
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
