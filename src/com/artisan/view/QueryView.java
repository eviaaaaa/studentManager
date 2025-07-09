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
     * ��ʾѧ����Ϣ��ѯ�˵��������û�ѡ��
     *
     * @param con     ���ݿ�����
     * @param scanner ���ڶ�ȡ�û������Scanner����
     * @throws SQLException ����������ݿ���ʴ���
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
            // ��ʾ��ѯ�˵�
            showQueryMenuOptions();

            // ��ȡ�û�����
            queryChoice = InputValidator.readInt(scanner, "", 0, 5);


            switch (queryChoice) {
                case 1:
                    ConsoleUtil.printTitle("��ѯ����ѧ��������Ϣ");
                    List<StudentBasicInfo> basicStudents = studentBasicInfoDAO.getAllStudentBasicInfo(con);
                    if (basicStudents.isEmpty()) {
                        ConsoleUtil.printWarning("Ŀǰû��ѧ��������Ϣ��");
                    } else {
                        displayStudentBasicInfoTable(basicStudents);
                    }
                    break;
                case 2:
                    ConsoleUtil.printTitle("��ѯ����ѧ���ɼ���Ϣ");
                    List<StudentGradeInfo> allGradesInfo = studentGradeInfoDAO.getAllStudentGradeInfo(con);
                    if (allGradesInfo.isEmpty()) {
                        ConsoleUtil.printWarning("Ŀǰû��ѧ���ɼ���Ϣ��");
                    } else {
                        displayStudentGradeInfoTable(allGradesInfo);
                    }
                    break;
                case 3:
                    ConsoleUtil.printTitle("��ѯ����ѧ��������Ϣ");
                    List<AwardPunish> awardPunishes = awardPunishDAO.getAllAwardPunishes(con);
                    if (awardPunishes.isEmpty()) {
                        ConsoleUtil.printWarning("Ŀǰû�н�����Ϣ��");
                    } else {
                        displayAwardPunishTable(awardPunishes);
                    }
                    break;
                case 4:
                    ConsoleUtil.printTitle("��ѯĳѧ���ɼ���");
                    String studentIdForTranscript = InputValidator.readId(scanner, "������Ҫ��ѯ�ɼ�����ѧ��ID: ");
                    // Using the view DAO to get student's grades
                    List<StudentGradeInfo> studentTranscriptGrades = studentGradeInfoDAO.getStudentGradeInfoByStudentId(con, studentIdForTranscript);

                    if (studentTranscriptGrades.isEmpty()) {
                        ConsoleUtil.printWarning("δ�ҵ�IDΪ " + studentIdForTranscript + " ��ѧ�������ѧ��Ŀǰû�гɼ���¼��");
                    } else {
                        // Assuming the first entry has the student's name for display
                        ConsoleUtil.printInfo("ѧ�� " + studentTranscriptGrades.get(0).getStudentName() + " (ѧ��: " + studentTranscriptGrades.get(0).getStudentId() + ") �ĳɼ���:");
                        displayStudentGradeInfoTable(studentTranscriptGrades);
                    }
                    break;
                case 5: // �߼���ѯѧ��������Ϣ
                    System.out.println("\n--- �߼���ѯѧ��������Ϣ ---");
                    Integer maxAge = null;
                    String className = null;
                    String locationKeyword = null;

                    System.out.print("������������� (����������): ");
                    String ageInput = scanner.nextLine();
                    if (!ageInput.isEmpty()) {
                        try {
                            maxAge = Integer.parseInt(ageInput);
                        } catch (NumberFormatException e) {
                            System.out.println("����������Ч������������������");
                        }
                    }

                    System.out.print("������༶���� (����������): ");
                    className = scanner.nextLine();
                    if (className.isEmpty()) {
                        className = null;
                    }

                    System.out.print("�������ͥסַ�ؼ��� (����������): ");
                    locationKeyword = scanner.nextLine();
                    if (locationKeyword.isEmpty()) {
                        locationKeyword = null;
                    }

                    List<Student> advancedFilteredStudents = studentDAO.queryStudentsAdvanced(con, maxAge, className, locationKeyword);

                    if (advancedFilteredStudents.isEmpty()) {
                        ConsoleUtil.printWarning("û���ҵ�����������ѧ����");
                    } else {
                        ConsoleUtil.printInfo("����������ѧ����ϸ��Ϣ:");
                        displayStudentDetailTable(advancedFilteredStudents);
                    }
                    break;
                case 0:
                    ConsoleUtil.printInfo("�������˵���");
                    break;
                default:
                    ConsoleUtil.printError("��Ч��ѡ�����������롣");
                    break;
            }

            if (queryChoice != 0) { // ������Ƿ������˵�������ʾ��Enter����
                ConsoleUtil.waitForEnter(scanner);
            }
        } while (queryChoice != 0);
    }

    /**
     * ��ʾ��ѯ�˵�ѡ��
     */
    private static void showQueryMenuOptions() {
        String[] menuOptions = {
            "1 - ��ѯ����ѧ��������Ϣ (ͨ����ͼ)",
            "2 - ��ѯ����ѧ���ɼ���Ϣ (ͨ����ͼ)",
            "3 - ��ѯ����ѧ��������Ϣ",
            "4 - ��ѯĳѧ���ɼ���",
            "5 - �߼���ѯѧ��������Ϣ",
            "0 - �������˵�"
        };

        ConsoleUtil.printMenu("��ѯ�˵�", menuOptions);
    }

    /**
     * �Ա����ʽ��ʾѧ��������Ϣ
     *
     * @param students ѧ��������Ϣ�б�
     */
    private static void displayStudentBasicInfoTable(List<StudentBasicInfo> students) {
        String[] headers = {"ѧ��", "����", "�༶���", "רҵ���", "Ժϵ���"};

        // ת������Ϊ��ά����
        String[][] data = new String[students.size()][headers.length];
        for (int i = 0; i < students.size(); i++) {
            StudentBasicInfo student = students.get(i);
            data[i][0] = student.getStudentId() != null ? student.getStudentId() : "";
            data[i][1] = student.getName() != null ? student.getName() : "";
            data[i][2] = student.getClassId() != null ? student.getClassId() : "";
            data[i][3] = student.getMajorId() != null ? student.getMajorId() : "";
            data[i][4] = student.getDepartmentId() != null ? student.getDepartmentId() : "";
        }

        // ʹ�����ܱ����ʾ
        ConsoleUtil.printSmartTable(headers, data);
    }

    /**
     * �Ա����ʽ��ʾѧ���ɼ���Ϣ
     *
     * @param grades ѧ���ɼ���Ϣ�б�
     */
    private static void displayStudentGradeInfoTable(List<StudentGradeInfo> grades) {
        String[] headers = {"ѧ��", "����", "�γ�����", "�ɼ�"};

        // ת������Ϊ��ά����
        String[][] data = new String[grades.size()][headers.length];
        for (int i = 0; i < grades.size(); i++) {
            StudentGradeInfo grade = grades.get(i);
            data[i][0] = grade.getStudentId() != null ? grade.getStudentId() : "";
            data[i][1] = grade.getStudentName() != null ? grade.getStudentName() : "";
            data[i][2] = grade.getCourseName() != null ? grade.getCourseName() : "";
            data[i][3] = grade.getGradeValue() != null ? grade.getGradeValue().toString() : "";
        }

        // ʹ�����ܱ����ʾ
        ConsoleUtil.printSmartTable(headers, data);
    }

    /**
     * �Ա����ʽ��ʾѧ����ϸ��Ϣ
     *
     * @param students ѧ���б�
     */
    private static void displayStudentDetailTable(List<Student> students) {
        String[] headers = {"ѧ��", "����", "�Ա�", "����", "����", "���ڵ�", "�༶ID", "רҵID", "ԺϵID"};

        // ת������Ϊ��ά����
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

        // ʹ�����ܱ����ʾ
        ConsoleUtil.printSmartTable(headers, data);
    }

    /**
     * �Ա����ʽ��ʾ������Ϣ
     *
     * @param awardPunishes ������Ϣ�б�
     */
    private static void displayAwardPunishTable(List<AwardPunish> awardPunishes) {
        String[] headers = {"����ID", "ѧ��ID", "רҵID", "ԺϵID", "��������", "������Ŀ"};

        // ת������Ϊ��ά����
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

        // ʹ�����ܱ����ʾ
        ConsoleUtil.printSmartTable(headers, data);
    }
}