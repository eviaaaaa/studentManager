package com.artisan.view;

import com.artisan.dao.*;
import com.artisan.model.*;
import com.artisan.util.ConsoleUtil;
import com.artisan.util.InputValidator;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class InsertView {
    /**
     * ��ʾ�������ݲ˵��������û�ѡ��
     *
     * @param con     ���ݿ�����
     * @param scanner ���ڶ�ȡ�û������Scanner����
     * @throws SQLException ����������ݿ���ʴ���
     */
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    public static void insertData(Connection con, Scanner scanner) throws SQLException {
        DepartmentDAO departmentDAO = new DepartmentDAO();
        MajorDAO majorDAO = new MajorDAO();
        SchoolClassDAO schoolClassDAO = new SchoolClassDAO();
        StudentDAO studentDAO = new StudentDAO();
        CourseDAO courseDAO = new CourseDAO();
        GradeDAO gradeDAO = new GradeDAO();
        AwardPunishDAO awardPunishDAO = new AwardPunishDAO();

        int insertChoice;
        do {
            // ��ʾ����˵�
            showInsertMenu();

            // ��ȡ�û�����
            insertChoice = InputValidator.readInt(scanner, "", 0, 7);

            switch (insertChoice) {
                case 1: // ����Ժϵ
                    System.out.println("\n--- ����Ժϵ��Ϣ ---");
                    System.out.print("������ԺϵID (did): ");
                    String deptDid = scanner.nextLine();
                    System.out.print("������Ժϵ���� (dname): ");
                    String deptDname = scanner.nextLine();
                    Department newDept = new Department(deptDid, deptDname);
                    if (departmentDAO.addDepartment(con, newDept) > 0) {
                        System.out.println("Ժϵ����ɹ�: " + newDept);
                    } else {
                        System.out.println("Ժϵ����ʧ�ܡ�");
                    }
                    break;
                case 2: // ����רҵ
                    System.out.println("\n--- ����רҵ��Ϣ ---");
                    System.out.print("������רҵID (mid): ");
                    String majorMid = scanner.nextLine();
                    System.out.print("����������ԺϵID (did, ��Ϊ��): ");
                    String majorDid = scanner.nextLine();
                    if (majorDid.isEmpty()) majorDid = null; // ���������
                    System.out.print("������רҵ���� (mname): ");
                    String majorMname = scanner.nextLine();
                    Major newMajor = new Major(majorMid, majorDid, majorMname);
                    if (majorDAO.addMajor(con, newMajor) > 0) {
                        System.out.println("רҵ����ɹ�: " + newMajor);
                    } else {
                        System.out.println("רҵ����ʧ�ܡ�");
                    }
                    break;
                case 3: // ����༶
                    System.out.println("\n--- ����༶��Ϣ ---");
                    System.out.print("������༶ID (cid): ");
                    String classCid = scanner.nextLine();
                    System.out.print("����������רҵID (mid, ��Ϊ��): ");
                    String classMid = scanner.nextLine();
                    if (classMid.isEmpty()) classMid = null;
                    System.out.print("����������ԺϵID (did, ��Ϊ��): ");
                    String classDid = scanner.nextLine();
                    if (classDid.isEmpty()) classDid = null;
                    System.out.print("������༶���� (cname): ");
                    String classCname = scanner.nextLine();
                    System.out.print("������༶���� (cnumber): ");
                    int classCnumber = 0;
                    try {
                        classCnumber = scanner.nextInt();
                        scanner.nextLine();
                    } catch (InputMismatchException e) {
                        System.out.println("�༶����������Ч�����������롣");
                        scanner.nextLine();
                        break;
                    }
                    SchoolClass newClass = new SchoolClass(classCid, classMid, classDid, classCname, classCnumber);
                    if (schoolClassDAO.addClass(con, newClass) > 0) {
                        System.out.println("�༶����ɹ�: " + newClass);
                    } else {
                        System.out.println("�༶����ʧ�ܡ�");
                    }
                    break;
                case 4: // ����ѧ��
                    ConsoleUtil.printTitle("����ѧ����Ϣ");
                    try {
                        String studentId = InputValidator.readId(scanner, "������ѧ��ID: ");
                        String studentName = InputValidator.readString(scanner, "����������: ", false, 30);
                        String studentSex = InputValidator.readGender(scanner, "�������Ա�");
                        String studentCid = InputValidator.readString(scanner, "������༶ID (��Ϊ��): ", true, 30);
                        if (studentCid.isEmpty()) studentCid = null;
                        String studentMid = InputValidator.readString(scanner, "������רҵID (��Ϊ��): ", true, 30);
                        if (studentMid.isEmpty()) studentMid = null;
                        String studentDid = InputValidator.readString(scanner, "������ԺϵID (��Ϊ��): ", true, 30);
                        if (studentDid.isEmpty()) studentDid = null;
                        String studentNation = InputValidator.readString(scanner, "����������: ", false, 20);
                        int studentAge = InputValidator.readAge(scanner, "����������: ");
                        Date studentBirthday = InputValidator.readDate(scanner, "����������");
                        String studentLocation = InputValidator.readString(scanner, "���������ڵ�: ", false, 20);
                        Date studentEnroll = InputValidator.readDate(scanner, "��������ѧ����");

                        Student newStudent = new Student(studentId, studentName, studentSex, studentCid, studentMid, studentDid,
                                studentNation, studentAge, studentBirthday, studentLocation, studentEnroll);

                        if (studentDAO.addStudent(con, newStudent) > 0) {
                            ConsoleUtil.printSuccess("ѧ������ɹ�: " + newStudent);
                        } else {
                            ConsoleUtil.printError("ѧ������ʧ�ܡ�");
                        }
                    } catch (Exception e) {
                        ConsoleUtil.printError("����ѧ����Ϣʱ��������: " + e.getMessage());
                    }
                    break;
                case 5: // ����γ�
                    System.out.println("\n--- ����γ���Ϣ ---");
                    System.out.print("������γ�ID (kid): ");
                    String courseKid = scanner.nextLine();
                    System.out.print("������γ����� (kname): ");
                    String courseKname = scanner.nextLine();
                    System.out.print("������ѧ�� (kcredit): ");
                    int courseKcredit = 0;
                    try {
                        courseKcredit = scanner.nextInt();
                        scanner.nextLine();
                    } catch (InputMismatchException e) {
                        System.out.println("ѧ��������Ч�����������롣");
                        scanner.nextLine();
                        break;
                    }
                    System.out.print("�������ʱ (kperiod): ");
                    int courseKperiod = 0;
                    try {
                        courseKperiod = scanner.nextInt();
                        scanner.nextLine();
                    } catch (InputMismatchException e) {
                        System.out.println("��ʱ������Ч�����������롣");
                        scanner.nextLine();
                        break;
                    }
                    Course newCourse = new Course(courseKid, courseKname, courseKcredit, courseKperiod);
                    if (courseDAO.addCourse(con, newCourse) > 0) {
                        System.out.println("�γ̲���ɹ�: " + newCourse);
                    } else {
                        System.out.println("�γ̲���ʧ�ܡ�");
                    }
                    break;
                case 6: // ����ɼ�
                    System.out.println("\n--- ����ɼ���Ϣ ---");
                    System.out.print("������γ�ID (kid): ");
                    String gradeKid = scanner.nextLine();
                    System.out.print("������ѧ��ID (id): ");
                    String gradeStudentId = scanner.nextLine();
                    System.out.print("������ɼ� (ggrade): ");
                    int gradeGgrade = 0;
                    try {
                        gradeGgrade = scanner.nextInt();
                        scanner.nextLine();
                    } catch (InputMismatchException e) {
                        System.out.println("�ɼ�������Ч�����������롣");
                        scanner.nextLine();
                        break;
                    }
                    Grade newGrade = new Grade(gradeKid, gradeStudentId, gradeGgrade);
                    if (gradeDAO.addGrade(con, newGrade) > 0) {
                        System.out.println("�ɼ�����ɹ�: " + newGrade);
                    } else {
                        System.out.println("�ɼ�����ʧ�ܡ�");
                    }
                    break;
                case 7: // ���뽱��
                    System.out.println("\n--- ���뽱����Ϣ ---");
                    System.out.print("�����뽱��ID (aid): ");
                    String apAid = scanner.nextLine();
                    System.out.print("������ѧ��ID (id, ��Ϊ��): ");
                    String apStudentId = scanner.nextLine();
                    if (apStudentId.isEmpty()) apStudentId = null;
                    System.out.print("������רҵID (mid, ��Ϊ��): ");
                    String apMid = scanner.nextLine();
                    if (apMid.isEmpty()) apMid = null;
                    System.out.print("������ԺϵID (did, ��Ϊ��): ");
                    String apDid = scanner.nextLine();
                    if (apDid.isEmpty()) apDid = null;
                    System.out.print("�����뽱������ (aname, ����: ����/�ͷ�): ");
                    String apAname = scanner.nextLine();
                    System.out.print("�����뽱����Ŀ (aproject): ");
                    String apAproject = scanner.nextLine();
                    AwardPunish newAwardPunish = new AwardPunish(apAid, apStudentId, apMid, apDid, apAname, apAproject);
                    if (awardPunishDAO.addAwardPunish(con, newAwardPunish) > 0) {
                        System.out.println("���Ͳ���ɹ�: " + newAwardPunish);
                    } else {
                        System.out.println("���Ͳ���ʧ�ܡ�");
                    }
                    break;
                case 0:
                    System.out.println("\n�������˵���");
                    break;
                default:
                    System.out.println("\n��Ч��ѡ�����������롣");
                    break;
            }
            if (insertChoice != 0) {
                ConsoleUtil.waitForEnter(scanner);
            }
        } while (insertChoice != 0);
    }

    /**
     * ��ʾ����˵�ѡ��
     */
    private static void showInsertMenu() {
        String[] menuOptions = {
            "1 - ����Ժϵ��Ϣ",
            "2 - ����רҵ��Ϣ",
            "3 - ����༶��Ϣ",
            "4 - ����ѧ����Ϣ",
            "5 - ����γ���Ϣ",
            "6 - ����ɼ���Ϣ",
            "7 - ���뽱����Ϣ",
            "0 - �������˵�"
        };

        ConsoleUtil.printMenu("���ݲ���˵�", menuOptions);
    }
}
