package com.artisan.view;

import com.artisan.dao.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.InputMismatchException;
import java.util.Scanner;

public class DeleteView {
    /**
     * 显示删除数据菜单并处理用户选择。
     *
     * @param con     数据库连接
     * @param scanner 用于读取用户输入的Scanner对象
     * @throws SQLException 如果发生数据库访问错误
     */
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    public static void deleteData(Connection con, Scanner scanner) throws SQLException {
        DepartmentDAO departmentDAO = new DepartmentDAO();
        MajorDAO majorDAO = new MajorDAO();
        SchoolClassDAO schoolClassDAO = new SchoolClassDAO();
        StudentDAO studentDAO = new StudentDAO();
        CourseDAO courseDAO = new CourseDAO();
        GradeDAO gradeDAO = new GradeDAO();
        AwardPunishDAO awardPunishDAO = new AwardPunishDAO();

        int deleteChoice;
        do {
            System.out.println("\n********************************************");
            System.out.println("************** 请选择删除项目 ***************");
            System.out.println("********************************************");
            System.out.println("选择输入：");
            System.out.println("*********** '1': 删除院系信息 ************");
            System.out.println("*********** '2': 删除专业信息 ************");
            System.out.println("*********** '3': 删除班级信息 ************");
            System.out.println("*********** '4': 删除学生信息 ************");
            System.out.println("*********** '5': 删除课程信息 ************");
            System.out.println("*********** '6': 删除成绩信息 ************");
            System.out.println("*********** '7': 删除奖惩信息 ************");
            System.out.println("*********** '0': 返回主菜单 ************");
            System.out.println("********************************************");
            System.out.print("请选择要进行的业务: ");

            try {
                deleteChoice = scanner.nextInt();
                scanner.nextLine(); // 消费掉换行符
            } catch (InputMismatchException e) {
                System.out.println("无效输入，请输入数字。");
                scanner.nextLine(); // 消费掉错误的输入
                deleteChoice = -1; // 设置为无效选择，重新循环
                continue;
            }

            switch (deleteChoice) {
                case 1: // 删除院系
                    System.out.println("\n--- 删除院系信息 ---");
                    System.out.print("请输入要删除的院系ID (did): ");
                    String deptDid = scanner.nextLine();
                    if (departmentDAO.deleteDepartment(con, deptDid)) {
                        System.out.println("院系删除成功: ID " + deptDid);
                    } else {
                        System.out.println("院系删除失败，可能不存在或有相关联数据。");
                    }
                    break;
                case 2: // 删除专业
                    System.out.println("\n--- 删除专业信息 ---");
                    System.out.print("请输入要删除的专业ID (mid): ");
                    String majorMid = scanner.nextLine();
                    if (majorDAO.deleteMajor(con, majorMid)) {
                        System.out.println("专业删除成功: ID " + majorMid);
                    } else {
                        System.out.println("专业删除失败，可能不存在或有相关联数据。");
                    }
                    break;
                case 3: // 删除班级
                    System.out.println("\n--- 删除班级信息 ---");
                    System.out.print("请输入要删除的班级ID (cid): ");
                    String classCid = scanner.nextLine();
                    if (schoolClassDAO.deleteClass(con, classCid)) {
                        System.out.println("班级删除成功: ID " + classCid);
                    } else {
                        System.out.println("班级删除失败，可能不存在或有相关联数据。");
                    }
                    break;
                case 4: // 删除学生
                    System.out.println("\n--- 删除学生信息 ---");
                    System.out.print("请输入要删除的学生ID (id): ");
                    String studentId = scanner.nextLine();
                    if (studentDAO.deleteStudent(con, studentId)) {
                        System.out.println("学生删除成功: ID " + studentId);
                    } else {
                        System.out.println("学生删除失败，可能不存在或有相关联数据。");
                    }
                    break;
                case 5: // 删除课程
                    System.out.println("\n--- 删除课程信息 ---");
                    System.out.print("请输入要删除的课程ID (kid): ");
                    String courseKid = scanner.nextLine();
                    if (courseDAO.deleteCourse(con, courseKid)) {
                        System.out.println("课程删除成功: ID " + courseKid);
                    } else {
                        System.out.println("课程删除失败，可能不存在或有相关联数据。");
                    }
                    break;
                case 6: // 删除成绩
                    System.out.println("\n--- 删除成绩信息 ---");
                    System.out.print("请输入要删除的成绩记录的课程ID (kid): ");
                    String gradeKid = scanner.nextLine();
                    System.out.print("请输入要删除的成绩记录的学生ID (id): ");
                    String gradeStudentId = scanner.nextLine();
                    if (gradeDAO.deleteGrade(con, gradeKid, gradeStudentId)) {
                        System.out.println("成绩删除成功: 课程ID " + gradeKid + ", 学生ID " + gradeStudentId);
                    } else {
                        System.out.println("成绩删除失败，可能不存在。");
                    }
                    break;
                case 7: // 删除奖惩
                    System.out.println("\n--- 删除奖惩信息 ---");
                    System.out.print("请输入要删除的奖惩ID (aid): ");
                    String apAid = scanner.nextLine();
                    if (awardPunishDAO.deleteAwardPunish(con, apAid)) {
                        System.out.println("奖惩删除成功: ID " + apAid);
                    } else {
                        System.out.println("奖惩删除失败，可能不存在或有相关联数据。");
                    }
                    break;
                case 0:
                    System.out.println("\n返回主菜单。");
                    break;
                default:
                    System.out.println("\n无效的选择，请重新输入。");
                    break;
            }
            if (deleteChoice != 0) {
                System.out.println("\n按 Enter 键继续...");
                scanner.nextLine();
            }
        } while (deleteChoice != 0);
    }
}
