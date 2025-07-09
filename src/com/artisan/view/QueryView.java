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
            System.out.println("********************************************");
            System.out.println("************** ��ѡ���ѯ��Ŀ ***************");
            System.out.println("********************************************");
            System.out.println("ѡ�����룺");
            System.out.println("*********** '1': ��ѯ����ѧ��������Ϣ (ͨ����ͼ) ************"); // Updated text
            System.out.println("*********** '2': ��ѯ����ѧ���ɼ���Ϣ (ͨ����ͼ) ************"); // Updated text
            System.out.println("*********** '3': ��ѯ����ѧ��������Ϣ ************");
            System.out.println("*********** '4': ��ѯĳѧ���ɼ��� ************");
            System.out.println("*********** '5': �߼���ѯѧ��������Ϣ ************");
            System.out.println("*********** '0': �������˵� ************"); // �������˵�ѡ��
            System.out.println("********************************************");
            System.out.print("��ѡ��Ҫ���е�ҵ��: ");

            // ��ȡ�û�����
            // ȷ����������������������ܵ�InputMismatchException
            try {
                queryChoice = scanner.nextInt();
                scanner.nextLine(); // ���ѵ����з�
            } catch (InputMismatchException e) {
                System.out.println("��Ч���룬���������֡�");
                scanner.nextLine(); // ���ѵ����������
                queryChoice = -1; // ����Ϊ��Чѡ������ѭ��
                continue;
            }


            switch (queryChoice) {
                case 1:
                    System.out.println("\n--- ��ѯ����ѧ��������Ϣ (ͨ����ͼ) ---");
                    List<StudentBasicInfo> basicStudents = studentBasicInfoDAO.getAllStudentBasicInfo(con);
                    if (basicStudents.isEmpty()) {
                        System.out.println("Ŀǰû��ѧ��������Ϣ��");
                    } else {
                        System.out.println("����ѧ��������Ϣ:");
                        for (StudentBasicInfo studentInfo : basicStudents) {
                            System.out.println(studentInfo); // ��ӡѧ��������Ϣʵ��
                        }
                    }
                    break;
                case 2:
                    System.out.println("\n--- ��ѯ����ѧ���ɼ���Ϣ (ͨ����ͼ) ---");
                    List<StudentGradeInfo> allGradesInfo = studentGradeInfoDAO.getAllStudentGradeInfo(con);
                    if (allGradesInfo.isEmpty()) {
                        System.out.println("Ŀǰû��ѧ���ɼ���Ϣ��");
                    } else {
                        System.out.println("����ѧ���ɼ���Ϣ:");
                        for (StudentGradeInfo gradeInfo : allGradesInfo) {
                            System.out.println(gradeInfo); // ��ӡѧ���ɼ���Ϣʵ��
                        }
                    }
                    break;
                case 3:
                    System.out.println("\n--- ��ѯ����ѧ��������Ϣ ---");
                    List<AwardPunish> awardPunishes = awardPunishDAO.getAllAwardPunishes(con);
                    if (awardPunishes.isEmpty()) {
                        System.out.println("Ŀǰû�н�����Ϣ��");
                    } else {
                        System.out.println("����ѧ��������Ϣ:");
                        for (AwardPunish ap : awardPunishes) {
                            System.out.println(ap); // ��ӡ����ʵ����Ϣ
                        }
                    }
                    break;
                case 4:
                    System.out.println("\n--- ��ѯĳѧ���ɼ��� ---");
                    System.out.print("������Ҫ��ѯ�ɼ�����ѧ��ID: ");
                    String studentIdForTranscript = scanner.nextLine();
                    // Using the view DAO to get student's grades
                    List<StudentGradeInfo> studentTranscriptGrades = studentGradeInfoDAO.getStudentGradeInfoByStudentId(con, studentIdForTranscript);

                    if (studentTranscriptGrades.isEmpty()) {
                        System.out.println("δ�ҵ�IDΪ " + studentIdForTranscript + " ��ѧ�������ѧ��Ŀǰû�гɼ���¼��");
                    } else {
                        // Assuming the first entry has the student's name for display
                        System.out.println("ѧ�� " + studentTranscriptGrades.get(0).getStudentName() + " (ѧ��: " + studentTranscriptGrades.get(0).getStudentId() + ") �ĳɼ���:");
                        for (StudentGradeInfo g : studentTranscriptGrades) {
                            System.out.println("  �γ�: " + g.getCourseName() + ", �ɼ�: " + g.getGradeValue());
                        }
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
                        System.out.println("û���ҵ�����������ѧ����");
                    } else {
                        System.out.println("����������ѧ��������Ϣ:");
                        for (Student s : advancedFilteredStudents) {
                            System.out.println(s);
                        }
                    }
                    break;
                case 0:
                    System.out.println("\n�������˵���");
                    break;
                default:
                    System.out.println("\n��Ч��ѡ�����������롣");
                    break;
    }if (queryChoice != 0) { // ������Ƿ������˵�������ʾ��Enter����
                System.out.println("\n�� Enter ������...");
                scanner.nextLine(); // �ȴ��û��� Enter ��
            }
        } while (queryChoice != 0);
}}


