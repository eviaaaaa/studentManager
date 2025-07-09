package com.artisan.view;

import com.artisan.dao.*;
import com.artisan.model.AwardPunish;
import com.artisan.model.Student;
import com.artisan.model.StudentBasicInfo;
import com.artisan.model.StudentGradeInfo;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class QueryView {
    /**
     * 显示学生信息查询菜单并处理用户选择。
     *
     * @param con     数据库连接
     * @param scanner 用于读取用户输入的Scanner对象
     * @throws SQLException 如果发生数据库访问错误
     */
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    static void showQueryMenu(Connection con, Scanner scanner) throws SQLException {
        StudentDAO studentDAO = new StudentDAO();
        GradeDAO gradeDAO = new GradeDAO();
        AwardPunishDAO awardPunishDAO = new AwardPunishDAO();
        SchoolClassDAO schoolClassDAO = new SchoolClassDAO(); // Added for class name lookup
        StudentBasicInfoDAO studentBasicInfoDAO = new StudentBasicInfoDAO(); // Initialize StudentBasicInfoDAO
        StudentGradeInfoDAO studentGradeInfoDAO = new StudentGradeInfoDAO(); // Initialize StudentGradeInfoDAO
        int queryChoice;

        do {
            System.out.println("********************************************");
            System.out.println("************** 请选择查询项目 ***************");
            System.out.println("********************************************");
            System.out.println("选择输入：");
            System.out.println("*********** '1': 查询所有学生基本信息 (通过视图) ************"); // Updated text
            System.out.println("*********** '2': 查询所有学生成绩信息 (通过视图) ************"); // Updated text
            System.out.println("*********** '3': 查询所有学生奖惩信息 ************");
            System.out.println("*********** '4': 查询某学生成绩单 ************");
            System.out.println("*********** '5': 高级查询学生基本信息 ************");
            System.out.println("*********** '0': 返回主菜单 ************"); // 返回主菜单选项
            System.out.println("********************************************");
            System.out.print("请选择要进行的业务: ");

            // 读取用户输入
            // 确保输入是整数，并处理可能的InputMismatchException
            try {
                queryChoice = scanner.nextInt();
                scanner.nextLine(); // 消费掉换行符
            } catch (InputMismatchException e) {
                System.out.println("无效输入，请输入数字。");
                scanner.nextLine(); // 消费掉错误的输入
                queryChoice = -1; // 设置为无效选择，重新循环
                continue;
            }


            switch (queryChoice) {
                case 1:
                    System.out.println("\n--- 查询所有学生基本信息 (通过视图) ---");
                    List<StudentBasicInfo> basicStudents = studentBasicInfoDAO.getAllStudentBasicInfo(con);
                    if (basicStudents.isEmpty()) {
                        System.out.println("目前没有学生基本信息。");
                    } else {
                        System.out.println("所有学生基本信息:");
                        for (StudentBasicInfo studentInfo : basicStudents) {
                            System.out.println(studentInfo); // 打印学生基本信息实体
                        }
                    }
                    break;
                case 2:
                    System.out.println("\n--- 查询所有学生成绩信息 (通过视图) ---");
                    List<StudentGradeInfo> allGradesInfo = studentGradeInfoDAO.getAllStudentGradeInfo(con);
                    if (allGradesInfo.isEmpty()) {
                        System.out.println("目前没有学生成绩信息。");
                    } else {
                        System.out.println("所有学生成绩信息:");
                        for (StudentGradeInfo gradeInfo : allGradesInfo) {
                            System.out.println(gradeInfo); // 打印学生成绩信息实体
                        }
                    }
                    break;
                case 3:
                    System.out.println("\n--- 查询所有学生奖惩信息 ---");
                    List<AwardPunish> awardPunishes = awardPunishDAO.getAllAwardPunishes(con);
                    if (awardPunishes.isEmpty()) {
                        System.out.println("目前没有奖惩信息。");
                    } else {
                        System.out.println("所有学生奖惩信息:");
                        for (AwardPunish ap : awardPunishes) {
                            System.out.println(ap); // 打印奖惩实体信息
                        }
                    }
                    break;
                case 4:
                    System.out.println("\n--- 查询某学生成绩单 ---");
                    System.out.print("请输入要查询成绩单的学生ID: ");
                    String studentIdForTranscript = scanner.nextLine();
                    // Using the view DAO to get student's grades
                    List<StudentGradeInfo> studentTranscriptGrades = studentGradeInfoDAO.getStudentGradeInfoByStudentId(con, studentIdForTranscript);

                    if (studentTranscriptGrades.isEmpty()) {
                        System.out.println("未找到ID为 " + studentIdForTranscript + " 的学生，或该学生目前没有成绩记录。");
                    } else {
                        // Assuming the first entry has the student's name for display
                        System.out.println("学生 " + studentTranscriptGrades.get(0).getStudentName() + " (学号: " + studentTranscriptGrades.get(0).getStudentId() + ") 的成绩单:");
                        for (StudentGradeInfo g : studentTranscriptGrades) {
                            System.out.println("  课程: " + g.getCourseName() + ", 成绩: " + g.getGradeValue());
                        }
                    }
                    break;
                case 5: // 高级查询学生基本信息
                    System.out.println("\n--- 高级查询学生基本信息 ---");
                    Integer maxAge = null;
                    String className = null;
                    String locationKeyword = null;

                    System.out.print("请输入最大年龄 (留空则不限制): ");
                    String ageInput = scanner.nextLine();
                    if (!ageInput.isEmpty()) {
                        try {
                            maxAge = Integer.parseInt(ageInput);
                        } catch (NumberFormatException e) {
                            System.out.println("年龄输入无效，将忽略年龄条件。");
                        }
                    }

                    System.out.print("请输入班级名称 (留空则不限制): ");
                    className = scanner.nextLine();
                    if (className.isEmpty()) {
                        className = null;
                    }

                    System.out.print("请输入家庭住址关键词 (留空则不限制): ");
                    locationKeyword = scanner.nextLine();
                    if (locationKeyword.isEmpty()) {
                        locationKeyword = null;
                    }

                    List<Student> advancedFilteredStudents = studentDAO.queryStudentsAdvanced(con, maxAge, className, locationKeyword);

                    if (advancedFilteredStudents.isEmpty()) {
                        System.out.println("没有找到符合条件的学生。");
                    } else {
                        System.out.println("符合条件的学生基本信息:");
                        for (Student s : advancedFilteredStudents) {
                            System.out.println(s);
                        }
                    }
                    break;
                case 0:
                    System.out.println("\n返回主菜单。");
                    break;
                default:
                    System.out.println("\n无效的选择，请重新输入。");
                    break;
    }if (queryChoice != 0) { // 如果不是返回主菜单，则提示按Enter继续
                System.out.println("\n按 Enter 键继续...");
                scanner.nextLine(); // 等待用户按 Enter 键
            }
        } while (queryChoice != 0);
}}


