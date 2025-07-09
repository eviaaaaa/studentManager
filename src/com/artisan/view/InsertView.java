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
     * 显示插入数据菜单并处理用户选择。
     *
     * @param con     数据库连接
     * @param scanner 用于读取用户输入的Scanner对象
     * @throws SQLException 如果发生数据库访问错误
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
            // 显示插入菜单
            showInsertMenu();

            // 读取用户输入
            insertChoice = InputValidator.readInt(scanner, "", 0, 7);

            switch (insertChoice) {
                case 1: // 插入院系
                    System.out.println("\n--- 插入院系信息 ---");
                    System.out.print("请输入院系ID (did): ");
                    String deptDid = scanner.nextLine();
                    System.out.print("请输入院系名称 (dname): ");
                    String deptDname = scanner.nextLine();
                    Department newDept = new Department(deptDid, deptDname);
                    if (departmentDAO.addDepartment(con, newDept) > 0) {
                        System.out.println("院系插入成功: " + newDept);
                    } else {
                        System.out.println("院系插入失败。");
                    }
                    break;
                case 2: // 插入专业
                    System.out.println("\n--- 插入专业信息 ---");
                    System.out.print("请输入专业ID (mid): ");
                    String majorMid = scanner.nextLine();
                    System.out.print("请输入所属院系ID (did, 可为空): ");
                    String majorDid = scanner.nextLine();
                    if (majorDid.isEmpty()) majorDid = null; // 处理空输入
                    System.out.print("请输入专业名称 (mname): ");
                    String majorMname = scanner.nextLine();
                    Major newMajor = new Major(majorMid, majorDid, majorMname);
                    if (majorDAO.addMajor(con, newMajor) > 0) {
                        System.out.println("专业插入成功: " + newMajor);
                    } else {
                        System.out.println("专业插入失败。");
                    }
                    break;
                case 3: // 插入班级
                    System.out.println("\n--- 插入班级信息 ---");
                    System.out.print("请输入班级ID (cid): ");
                    String classCid = scanner.nextLine();
                    System.out.print("请输入所属专业ID (mid, 可为空): ");
                    String classMid = scanner.nextLine();
                    if (classMid.isEmpty()) classMid = null;
                    System.out.print("请输入所属院系ID (did, 可为空): ");
                    String classDid = scanner.nextLine();
                    if (classDid.isEmpty()) classDid = null;
                    System.out.print("请输入班级名称 (cname): ");
                    String classCname = scanner.nextLine();
                    System.out.print("请输入班级人数 (cnumber): ");
                    int classCnumber = 0;
                    try {
                        classCnumber = scanner.nextInt();
                        scanner.nextLine();
                    } catch (InputMismatchException e) {
                        System.out.println("班级人数输入无效，请重新输入。");
                        scanner.nextLine();
                        break;
                    }
                    SchoolClass newClass = new SchoolClass(classCid, classMid, classDid, classCname, classCnumber);
                    if (schoolClassDAO.addClass(con, newClass) > 0) {
                        System.out.println("班级插入成功: " + newClass);
                    } else {
                        System.out.println("班级插入失败。");
                    }
                    break;
                case 4: // 插入学生
                    ConsoleUtil.printTitle("插入学生信息");
                    try {
                        String studentId = InputValidator.readId(scanner, "请输入学生ID: ");
                        String studentName = InputValidator.readString(scanner, "请输入姓名: ", false, 30);
                        String studentSex = InputValidator.readGender(scanner, "请输入性别");
                        String studentCid = InputValidator.readString(scanner, "请输入班级ID (可为空): ", true, 30);
                        if (studentCid.isEmpty()) studentCid = null;
                        String studentMid = InputValidator.readString(scanner, "请输入专业ID (可为空): ", true, 30);
                        if (studentMid.isEmpty()) studentMid = null;
                        String studentDid = InputValidator.readString(scanner, "请输入院系ID (可为空): ", true, 30);
                        if (studentDid.isEmpty()) studentDid = null;
                        String studentNation = InputValidator.readString(scanner, "请输入民族: ", false, 20);
                        int studentAge = InputValidator.readAge(scanner, "请输入年龄: ");
                        Date studentBirthday = InputValidator.readDate(scanner, "请输入生日");
                        String studentLocation = InputValidator.readString(scanner, "请输入所在地: ", false, 20);
                        Date studentEnroll = InputValidator.readDate(scanner, "请输入入学日期");

                        Student newStudent = new Student(studentId, studentName, studentSex, studentCid, studentMid, studentDid,
                                studentNation, studentAge, studentBirthday, studentLocation, studentEnroll);

                        if (studentDAO.addStudent(con, newStudent) > 0) {
                            ConsoleUtil.printSuccess("学生插入成功: " + newStudent);
                        } else {
                            ConsoleUtil.printError("学生插入失败。");
                        }
                    } catch (Exception e) {
                        ConsoleUtil.printError("插入学生信息时发生错误: " + e.getMessage());
                    }
                    break;
                case 5: // 插入课程
                    System.out.println("\n--- 插入课程信息 ---");
                    System.out.print("请输入课程ID (kid): ");
                    String courseKid = scanner.nextLine();
                    System.out.print("请输入课程名称 (kname): ");
                    String courseKname = scanner.nextLine();
                    System.out.print("请输入学分 (kcredit): ");
                    int courseKcredit = 0;
                    try {
                        courseKcredit = scanner.nextInt();
                        scanner.nextLine();
                    } catch (InputMismatchException e) {
                        System.out.println("学分输入无效，请重新输入。");
                        scanner.nextLine();
                        break;
                    }
                    System.out.print("请输入课时 (kperiod): ");
                    int courseKperiod = 0;
                    try {
                        courseKperiod = scanner.nextInt();
                        scanner.nextLine();
                    } catch (InputMismatchException e) {
                        System.out.println("课时输入无效，请重新输入。");
                        scanner.nextLine();
                        break;
                    }
                    Course newCourse = new Course(courseKid, courseKname, courseKcredit, courseKperiod);
                    if (courseDAO.addCourse(con, newCourse) > 0) {
                        System.out.println("课程插入成功: " + newCourse);
                    } else {
                        System.out.println("课程插入失败。");
                    }
                    break;
                case 6: // 插入成绩
                    System.out.println("\n--- 插入成绩信息 ---");
                    System.out.print("请输入课程ID (kid): ");
                    String gradeKid = scanner.nextLine();
                    System.out.print("请输入学生ID (id): ");
                    String gradeStudentId = scanner.nextLine();
                    System.out.print("请输入成绩 (ggrade): ");
                    int gradeGgrade = 0;
                    try {
                        gradeGgrade = scanner.nextInt();
                        scanner.nextLine();
                    } catch (InputMismatchException e) {
                        System.out.println("成绩输入无效，请重新输入。");
                        scanner.nextLine();
                        break;
                    }
                    Grade newGrade = new Grade(gradeKid, gradeStudentId, gradeGgrade);
                    if (gradeDAO.addGrade(con, newGrade) > 0) {
                        System.out.println("成绩插入成功: " + newGrade);
                    } else {
                        System.out.println("成绩插入失败。");
                    }
                    break;
                case 7: // 插入奖惩
                    System.out.println("\n--- 插入奖惩信息 ---");
                    System.out.print("请输入奖惩ID (aid): ");
                    String apAid = scanner.nextLine();
                    System.out.print("请输入学生ID (id, 可为空): ");
                    String apStudentId = scanner.nextLine();
                    if (apStudentId.isEmpty()) apStudentId = null;
                    System.out.print("请输入专业ID (mid, 可为空): ");
                    String apMid = scanner.nextLine();
                    if (apMid.isEmpty()) apMid = null;
                    System.out.print("请输入院系ID (did, 可为空): ");
                    String apDid = scanner.nextLine();
                    if (apDid.isEmpty()) apDid = null;
                    System.out.print("请输入奖惩名称 (aname, 例如: 奖励/惩罚): ");
                    String apAname = scanner.nextLine();
                    System.out.print("请输入奖惩项目 (aproject): ");
                    String apAproject = scanner.nextLine();
                    AwardPunish newAwardPunish = new AwardPunish(apAid, apStudentId, apMid, apDid, apAname, apAproject);
                    if (awardPunishDAO.addAwardPunish(con, newAwardPunish) > 0) {
                        System.out.println("奖惩插入成功: " + newAwardPunish);
                    } else {
                        System.out.println("奖惩插入失败。");
                    }
                    break;
                case 0:
                    System.out.println("\n返回主菜单。");
                    break;
                default:
                    System.out.println("\n无效的选择，请重新输入。");
                    break;
            }
            if (insertChoice != 0) {
                ConsoleUtil.waitForEnter(scanner);
            }
        } while (insertChoice != 0);
    }

    /**
     * 显示插入菜单选项
     */
    private static void showInsertMenu() {
        String[] menuOptions = {
            "1 - 插入院系信息",
            "2 - 插入专业信息",
            "3 - 插入班级信息",
            "4 - 插入学生信息",
            "5 - 插入课程信息",
            "6 - 插入成绩信息",
            "7 - 插入奖惩信息",
            "0 - 返回主菜单"
        };

        ConsoleUtil.printMenu("数据插入菜单", menuOptions);
    }
}
