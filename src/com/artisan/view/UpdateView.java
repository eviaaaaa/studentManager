package com.artisan.view;

import com.artisan.dao.*;
import com.artisan.model.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.InputMismatchException;
import java.util.Scanner;


public class UpdateView {
    /**
     * 显示更新数据菜单并处理用户选择。
     *
     * @param con     数据库连接
     * @param scanner 用于读取用户输入的Scanner对象
     * @throws SQLException 如果发生数据库访问错误
     */
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    public static void updateData(Connection con, Scanner scanner) throws SQLException {
        DepartmentDAO departmentDAO = new DepartmentDAO();
        MajorDAO majorDAO = new MajorDAO();
        SchoolClassDAO schoolClassDAO = new SchoolClassDAO();
        StudentDAO studentDAO = new StudentDAO();
        CourseDAO courseDAO = new CourseDAO();
        GradeDAO gradeDAO = new GradeDAO();
        AwardPunishDAO awardPunishDAO = new AwardPunishDAO();

        int updateChoice;
        do {
            System.out.println("\n********************************************");
            System.out.println("************** 请选择更新项目 ***************");
            System.out.println("********************************************");
            System.out.println("选择输入：");
            System.out.println("*********** '1': 更新院系信息 ************");
            System.out.println("*********** '2': 更新专业信息 ************");
            System.out.println("*********** '3': 更新班级信息 ************");
            System.out.println("*********** '4': 更新学生信息 ************");
            System.out.println("*********** '5': 更新课程信息 ************");
            System.out.println("*********** '6': 更新成绩信息 ************");
            System.out.println("*********** '7': 更新奖惩信息 ************");
            System.out.println("*********** '0': 返回主菜单 ************");
            System.out.println("********************************************");
            System.out.print("请选择要进行的业务: ");

            try {
                updateChoice = scanner.nextInt();
                scanner.nextLine(); // 消费掉换行符
            } catch (InputMismatchException e) {
                System.out.println("无效输入，请输入数字。");
                scanner.nextLine(); // 消费掉错误的输入
                updateChoice = -1; // 设置为无效选择，重新循环
                continue;
            }

            switch (updateChoice) {
                case 1: // 更新院系
                    System.out.println("\n--- 更新院系信息 ---");
                    System.out.print("请输入要更新的院系ID (did): ");
                    String deptDid = scanner.nextLine();
                    Department deptToUpdate = departmentDAO.getDepartmentById(con, deptDid);
                    if (deptToUpdate != null) {
                        System.out.println("当前院系名称: " + deptToUpdate.getDname());
                        System.out.print("请输入新的院系名称 (dname): ");
                        String newDname = scanner.nextLine();
                        deptToUpdate.setDname(newDname);
                        if (departmentDAO.updateDepartment(con, deptToUpdate)) {
                            System.out.println("院系更新成功: " + deptToUpdate);
                        } else {
                            System.out.println("院系更新失败。");
                        }
                    } else {
                        System.out.println("未找到ID为 " + deptDid + " 的院系。");
                    }
                    break;
                case 2: // 更新专业
                    System.out.println("\n--- 更新专业信息 ---");
                    System.out.print("请输入要更新的专业ID (mid): ");
                    String majorMid = scanner.nextLine();
                    Major majorToUpdate = majorDAO.getMajorById(con, majorMid);
                    if (majorToUpdate != null) {
                        System.out.println("当前所属院系ID: " + (majorToUpdate.getDid() != null ? majorToUpdate.getDid() : "无"));
                        System.out.print("请输入新的所属院系ID (did, 可为空): ");
                        String newMajorDid = scanner.nextLine();
                        majorToUpdate.setDid(newMajorDid.isEmpty() ? null : newMajorDid);

                        System.out.println("当前专业名称: " + majorToUpdate.getMname());
                        System.out.print("请输入新的专业名称 (mname): ");
                        String newMajorMname = scanner.nextLine();
                        majorToUpdate.setMname(newMajorMname);

                        if (majorDAO.updateMajor(con, majorToUpdate)) {
                            System.out.println("专业更新成功: " + majorToUpdate);
                        } else {
                            System.out.println("专业更新失败。");
                        }
                    } else {
                        System.out.println("未找到ID为 " + majorMid + " 的专业。");
                    }
                    break;
                case 3: // 更新班级
                    System.out.println("\n--- 更新班级信息 ---");
                    System.out.print("请输入要更新的班级ID (cid): ");
                    String classCid = scanner.nextLine();
                    SchoolClass classToUpdate = schoolClassDAO.getClassById(con, classCid);
                    if (classToUpdate != null) {
                        System.out.println("当前所属专业ID: " + (classToUpdate.getMid() != null ? classToUpdate.getMid() : "无"));
                        System.out.print("请输入新的所属专业ID (mid, 可为空): ");
                        String newClassMid = scanner.nextLine();
                        classToUpdate.setMid(newClassMid.isEmpty() ? null : newClassMid);

                        System.out.println("当前所属院系ID: " + (classToUpdate.getDid() != null ? classToUpdate.getDid() : "无"));
                        System.out.print("请输入新的所属院系ID (did, 可为空): ");
                        String newClassDid = scanner.nextLine();
                        classToUpdate.setDid(newClassDid.isEmpty() ? null : newClassDid);

                        System.out.println("当前班级名称: " + classToUpdate.getCname());
                        System.out.print("请输入新的班级名称 (cname): ");
                        String newClassCname = scanner.nextLine();
                        classToUpdate.setCname(newClassCname);

                        System.out.println("当前班级人数: " + classToUpdate.getCnumber());
                        System.out.print("请输入新的班级人数 (cnumber): ");
                        int newClassCnumber = 0;
                        try {
                            newClassCnumber = scanner.nextInt();
                            scanner.nextLine();
                        } catch (InputMismatchException e) {
                            System.out.println("班级人数输入无效，更新失败。");
                            scanner.nextLine();
                            break;
                        }
                        classToUpdate.setCnumber(newClassCnumber);

                        if (schoolClassDAO.updateClass(con, classToUpdate)) {
                            System.out.println("班级更新成功: " + classToUpdate);
                        } else {
                            System.out.println("班级更新失败。");
                        }
                    } else {
                        System.out.println("未找到ID为 " + classCid + " 的班级。");
                    }
                    break;
                case 4: // 更新学生
                    System.out.println("\n--- 更新学生信息 ---");
                    System.out.print("请输入要更新的学生ID (id): ");
                    String studentId = scanner.nextLine();
                    Student studentToUpdate = studentDAO.getStudentById(con, studentId);
                    if (studentToUpdate != null) {
                        System.out.println("当前姓名: " + studentToUpdate.getName());
                        System.out.print("请输入新的姓名 (name): ");
                        studentToUpdate.setName(scanner.nextLine());

                        System.out.println("当前性别: " + studentToUpdate.getSex());
                        System.out.print("请输入新的性别 (sex, 例如: 男/女): ");
                        studentToUpdate.setSex(scanner.nextLine());

                        System.out.println("当前班级ID: " + (studentToUpdate.getCid() != null ? studentToUpdate.getCid() : "无"));
                        System.out.print("请输入新的班级ID (cid, 可为空): ");
                        String newStudentCid = scanner.nextLine();
                        studentToUpdate.setCid(newStudentCid.isEmpty() ? null : newStudentCid);

                        System.out.println("当前专业ID: " + (studentToUpdate.getMid() != null ? studentToUpdate.getMid() : "无"));
                        System.out.print("请输入新的专业ID (mid, 可为空): ");
                        String newStudentMid = scanner.nextLine();
                        studentToUpdate.setMid(newStudentMid.isEmpty() ? null : newStudentMid);

                        System.out.println("当前院系ID: " + (studentToUpdate.getDid() != null ? studentToUpdate.getDid() : "无"));
                        System.out.print("请输入新的院系ID (did, 可为空): ");
                        String newStudentDid = scanner.nextLine();
                        studentToUpdate.setDid(newStudentDid.isEmpty() ? null : newStudentDid);

                        System.out.println("当前民族: " + studentToUpdate.getNation());
                        System.out.print("请输入新的民族 (nation): ");
                        studentToUpdate.setNation(scanner.nextLine());

                        System.out.println("当前年龄: " + studentToUpdate.getAge());
                        System.out.print("请输入新的年龄 (age): ");
                        try {
                            studentToUpdate.setAge(scanner.nextInt());
                            scanner.nextLine();
                        } catch (InputMismatchException e) {
                            System.out.println("年龄输入无效，更新失败。");
                            scanner.nextLine();
                            break;
                        }

                        System.out.println("当前生日: " + DATE_FORMAT.format(studentToUpdate.getBirthday()));
                        System.out.print("请输入新的生日 (birthday, 格式:yyyy-MM-dd): ");
                        String birthdayStr = scanner.nextLine();
                        try {
                            studentToUpdate.setBirthday(DATE_FORMAT.parse(birthdayStr));
                        } catch (ParseException e) {
                            System.out.println("生日日期格式不正确，更新失败。");
                            break;
                        }

                        System.out.println("当前所在地: " + studentToUpdate.getLocation());
                        System.out.print("请输入新的所在地 (location): ");
                        studentToUpdate.setLocation(scanner.nextLine());

                        System.out.println("当前入学日期: " + DATE_FORMAT.format(studentToUpdate.getEnroll()));
                        System.out.print("请输入新的入学日期 (enroll, 格式:yyyy-MM-dd): ");
                        String enrollStr = scanner.nextLine();
                        try {
                            studentToUpdate.setEnroll(DATE_FORMAT.parse(enrollStr));
                        } catch (ParseException e) {
                            System.out.println("入学日期格式不正确，更新失败。");
                            break;
                        }

                        if (studentDAO.updateStudent(con, studentToUpdate)) {
                            System.out.println("学生信息更新成功: " + studentToUpdate);
                        } else {
                            System.out.println("学生信息更新失败。");
                        }
                    } else {
                        System.out.println("未找到ID为 " + studentId + " 的学生。");
                    }
                    break;
                case 5: // 更新课程
                    System.out.println("\n--- 更新课程信息 ---");
                    System.out.print("请输入要更新的课程ID (kid): ");
                    String courseKid = scanner.nextLine();
                    Course courseToUpdate = courseDAO.getCourseById(con, courseKid);
                    if (courseToUpdate != null) {
                        System.out.println("当前课程名称: " + courseToUpdate.getKname());
                        System.out.print("请输入新的课程名称 (kname): ");
                        courseToUpdate.setKname(scanner.nextLine());

                        System.out.println("当前学分: " + courseToUpdate.getKcredit());
                        System.out.print("请输入新的学分 (kcredit): ");
                        try {
                            courseToUpdate.setKcredit(scanner.nextInt());
                            scanner.nextLine();
                        } catch (InputMismatchException e) {
                            System.out.println("学分输入无效，更新失败。");
                            scanner.nextLine();
                            break;
                        }

                        System.out.println("当前课时: " + courseToUpdate.getKperiod());
                        System.out.print("请输入新的课时 (kperiod): ");
                        try {
                            courseToUpdate.setKperiod(scanner.nextInt());
                            scanner.nextLine();
                        } catch (InputMismatchException e) {
                            System.out.println("课时输入无效，更新失败。");
                            scanner.nextLine();
                            break;
                        }

                        if (courseDAO.updateCourse(con, courseToUpdate)) {
                            System.out.println("课程信息更新成功: " + courseToUpdate);
                        } else {
                            System.out.println("课程信息更新失败。");
                        }
                    } else {
                        System.out.println("未找到ID为 " + courseKid + " 的课程。");
                    }
                    break;
                case 6: // 更新成绩
                    System.out.println("\n--- 更新成绩信息 ---");
                    System.out.print("请输入要更新的成绩记录的课程ID (kid): ");
                    String gradeKid = scanner.nextLine();
                    System.out.print("请输入要更新的成绩记录的学生ID (id): ");
                    String gradeStudentId = scanner.nextLine();
                    Grade gradeToUpdate = gradeDAO.getGradeByIds(con, gradeKid, gradeStudentId);
                    if (gradeToUpdate != null) {
                        System.out.println("当前成绩: " + gradeToUpdate.getGgrade());
                        System.out.print("请输入新的成绩 (ggrade): ");
                        int newGgrade = 0;
                        try {
                            newGgrade = scanner.nextInt();
                            scanner.nextLine();
                        } catch (InputMismatchException e) {
                            System.out.println("成绩输入无效，更新失败。");
                            scanner.nextLine();
                            break;
                        }
                        gradeToUpdate.setGgrade(newGgrade);
                        if (gradeDAO.updateGrade(con, gradeToUpdate)) {
                            System.out.println("成绩更新成功: " + gradeToUpdate);
                        } else {
                            System.out.println("成绩更新失败。");
                        }
                    } else {
                        System.out.println("未找到课程ID为 " + gradeKid + " 和学生ID为 " + gradeStudentId + " 的成绩记录。");
                    }
                    break;
                case 7: // 更新奖惩
                    System.out.println("\n--- 更新奖惩信息 ---");
                    System.out.print("请输入要更新的奖惩ID (aid): ");
                    String apAid = scanner.nextLine();
                    AwardPunish apToUpdate = awardPunishDAO.getAwardPunishById(con, apAid);
                    if (apToUpdate != null) {
                        System.out.println("当前学生ID: " + (apToUpdate.getId() != null ? apToUpdate.getId() : "无"));
                        System.out.print("请输入新的学生ID (id, 可为空): ");
                        String newApStudentId = scanner.nextLine();
                        apToUpdate.setId(newApStudentId.isEmpty() ? null : newApStudentId);

                        System.out.println("当前专业ID: " + (apToUpdate.getMid() != null ? apToUpdate.getMid() : "无"));
                        System.out.print("请输入新的专业ID (mid, 可为空): ");
                        String newApMid = scanner.nextLine();
                        apToUpdate.setMid(newApMid.isEmpty() ? null : newApMid);

                        System.out.println("当前院系ID: " + (apToUpdate.getDid() != null ? apToUpdate.getDid() : "无"));
                        System.out.print("请输入新的院系ID (did, 可为空): ");
                        String newApDid = scanner.nextLine();
                        apToUpdate.setDid(newApDid.isEmpty() ? null : newApDid);

                        System.out.println("当前奖惩名称: " + apToUpdate.getAname());
                        System.out.print("请输入新的奖惩名称 (aname, 例如: 奖励/惩罚): ");
                        apToUpdate.setAname(scanner.nextLine());

                        System.out.println("当前奖惩项目: " + apToUpdate.getAproject());
                        System.out.print("请输入新的奖惩项目 (aproject): ");
                        apToUpdate.setAproject(scanner.nextLine());

                        if (awardPunishDAO.updateAwardPunish(con, apToUpdate)) {
                            System.out.println("奖惩信息更新成功: " + apToUpdate);
                        } else {
                            System.out.println("奖惩信息更新失败。");
                        }
                    } else {
                        System.out.println("未找到ID为 " + apAid + " 的奖惩记录。");
                    }
                    break;
                case 0:
                    System.out.println("\n返回主菜单。");
                    break;
                default:
                    System.out.println("\n无效的选择，请重新输入。");
                    break;
            }
            if (updateChoice != 0) {
                System.out.println("\n按 Enter 键继续...");
                scanner.nextLine();
            }
        } while (updateChoice != 0);
    }
}
