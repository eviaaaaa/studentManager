package com.artisan.view;

import com.artisan.dao.*;
import com.artisan.model.AwardPunish;
import com.artisan.model.Student;
import com.artisan.model.StudentBasicInfo;
import com.artisan.model.StudentGradeInfo;
import com.artisan.util.ConsoleUtil;
import com.artisan.util.InputValidator;

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
            // 显示查询菜单
            showQueryMenuOptions();

            // 读取用户输入
            queryChoice = InputValidator.readInt(scanner, "", 0, 5);


            switch (queryChoice) {
                case 1:
                    ConsoleUtil.printTitle("查询所有学生基本信息");
                    List<StudentBasicInfo> basicStudents = studentBasicInfoDAO.getAllStudentBasicInfo(con);
                    if (basicStudents.isEmpty()) {
                        ConsoleUtil.printWarning("目前没有学生基本信息。");
                    } else {
                        displayStudentBasicInfoTable(basicStudents);
                    }
                    break;
                case 2:
                    ConsoleUtil.printTitle("查询所有学生成绩信息");
                    List<StudentGradeInfo> allGradesInfo = studentGradeInfoDAO.getAllStudentGradeInfo(con);
                    if (allGradesInfo.isEmpty()) {
                        ConsoleUtil.printWarning("目前没有学生成绩信息。");
                    } else {
                        displayStudentGradeInfoTable(allGradesInfo);
                    }
                    break;
                case 3:
                    ConsoleUtil.printTitle("查询所有学生奖惩信息");
                    List<AwardPunish> awardPunishes = awardPunishDAO.getAllAwardPunishes(con);
                    if (awardPunishes.isEmpty()) {
                        ConsoleUtil.printWarning("目前没有奖惩信息。");
                    } else {
                        displayAwardPunishTable(awardPunishes);
                    }
                    break;
                case 4:
                    ConsoleUtil.printTitle("查询某学生成绩单");
                    String studentIdForTranscript = InputValidator.readId(scanner, "请输入要查询成绩单的学生ID: ");
                    // Using the view DAO to get student's grades
                    List<StudentGradeInfo> studentTranscriptGrades = studentGradeInfoDAO.getStudentGradeInfoByStudentId(con, studentIdForTranscript);

                    if (studentTranscriptGrades.isEmpty()) {
                        ConsoleUtil.printWarning("未找到ID为 " + studentIdForTranscript + " 的学生，或该学生目前没有成绩记录。");
                    } else {
                        // Assuming the first entry has the student's name for display
                        ConsoleUtil.printInfo("学生 " + studentTranscriptGrades.get(0).getStudentName() + " (学号: " + studentTranscriptGrades.get(0).getStudentId() + ") 的成绩单:");
                        displayStudentGradeInfoTable(studentTranscriptGrades);
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
                        ConsoleUtil.printWarning("没有找到符合条件的学生。");
                    } else {
                        ConsoleUtil.printInfo("符合条件的学生详细信息:");
                        displayStudentDetailTable(advancedFilteredStudents);
                    }
                    break;
                case 0:
                    ConsoleUtil.printInfo("返回主菜单。");
                    break;
                default:
                    ConsoleUtil.printError("无效的选择，请重新输入。");
                    break;
            }

            if (queryChoice != 0) { // 如果不是返回主菜单，则提示按Enter继续
                ConsoleUtil.waitForEnter(scanner);
            }
        } while (queryChoice != 0);
    }

    /**
     * 显示查询菜单选项
     */
    private static void showQueryMenuOptions() {
        String[] menuOptions = {
            "1 - 查询所有学生基本信息 (通过视图)",
            "2 - 查询所有学生成绩信息 (通过视图)",
            "3 - 查询所有学生奖惩信息",
            "4 - 查询某学生成绩单",
            "5 - 高级查询学生基本信息",
            "0 - 返回主菜单"
        };

        ConsoleUtil.printMenu("查询菜单", menuOptions);
    }

    /**
     * 以表格形式显示学生基本信息
     *
     * @param students 学生基本信息列表
     */
    private static void displayStudentBasicInfoTable(List<StudentBasicInfo> students) {
        String[] headers = {"学号", "姓名", "班级编号", "专业编号", "院系编号"};

        // 转换数据为二维数组
        String[][] data = new String[students.size()][headers.length];
        for (int i = 0; i < students.size(); i++) {
            StudentBasicInfo student = students.get(i);
            data[i][0] = student.getStudentId() != null ? student.getStudentId() : "";
            data[i][1] = student.getName() != null ? student.getName() : "";
            data[i][2] = student.getClassId() != null ? student.getClassId() : "";
            data[i][3] = student.getMajorId() != null ? student.getMajorId() : "";
            data[i][4] = student.getDepartmentId() != null ? student.getDepartmentId() : "";
        }

        // 使用智能表格显示
        ConsoleUtil.printSmartTable(headers, data);
    }

    /**
     * 以表格形式显示学生成绩信息
     *
     * @param grades 学生成绩信息列表
     */
    private static void displayStudentGradeInfoTable(List<StudentGradeInfo> grades) {
        String[] headers = {"学号", "姓名", "课程名称", "成绩"};

        // 转换数据为二维数组
        String[][] data = new String[grades.size()][headers.length];
        for (int i = 0; i < grades.size(); i++) {
            StudentGradeInfo grade = grades.get(i);
            data[i][0] = grade.getStudentId() != null ? grade.getStudentId() : "";
            data[i][1] = grade.getStudentName() != null ? grade.getStudentName() : "";
            data[i][2] = grade.getCourseName() != null ? grade.getCourseName() : "";
            data[i][3] = grade.getGradeValue() != null ? grade.getGradeValue().toString() : "";
        }

        // 使用智能表格显示
        ConsoleUtil.printSmartTable(headers, data);
    }

    /**
     * 以表格形式显示学生详细信息
     *
     * @param students 学生列表
     */
    private static void displayStudentDetailTable(List<Student> students) {
        String[] headers = {"学号", "姓名", "性别", "年龄", "民族", "所在地", "班级ID", "专业ID", "院系ID"};

        // 转换数据为二维数组
        String[][] data = new String[students.size()][headers.length];
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            data[i][0] = student.getId() != null ? student.getId() : "";
            data[i][1] = student.getName() != null ? student.getName() : "";
            data[i][2] = student.getSex() != null ? student.getSex() : "";
            data[i][3] = student.getAge() != null ? student.getAge().toString() : "";
            data[i][4] = student.getNation() != null ? student.getNation() : "";
            data[i][5] = student.getLocation() != null ? student.getLocation() : "";
            data[i][6] = student.getCid() != null ? student.getCid() : "";
            data[i][7] = student.getMid() != null ? student.getMid() : "";
            data[i][8] = student.getDid() != null ? student.getDid() : "";
        }

        // 使用智能表格显示
        ConsoleUtil.printSmartTable(headers, data);
    }

    /**
     * 以表格形式显示奖惩信息
     *
     * @param awardPunishes 奖惩信息列表
     */
    private static void displayAwardPunishTable(List<AwardPunish> awardPunishes) {
        String[] headers = {"奖惩ID", "学生ID", "专业ID", "院系ID", "奖惩名称", "奖惩项目"};

        // 转换数据为二维数组
        String[][] data = new String[awardPunishes.size()][headers.length];
        for (int i = 0; i < awardPunishes.size(); i++) {
            AwardPunish ap = awardPunishes.get(i);
            data[i][0] = ap.getAid() != null ? ap.getAid() : "";
            data[i][1] = ap.getId() != null ? ap.getId() : "";
            data[i][2] = ap.getMid() != null ? ap.getMid() : "";
            data[i][3] = ap.getDid() != null ? ap.getDid() : "";
            data[i][4] = ap.getAname() != null ? ap.getAname() : "";
            data[i][5] = ap.getAproject() != null ? ap.getAproject() : "";
        }

        // 使用智能表格显示
        ConsoleUtil.printSmartTable(headers, data);
    }
}