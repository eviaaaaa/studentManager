package com.artisan.view;

import com.artisan.dao.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.InputMismatchException;
import java.util.Scanner;

public class DeleteView {
    /**
     * ��ʾɾ�����ݲ˵��������û�ѡ��
     *
     * @param con     ���ݿ�����
     * @param scanner ���ڶ�ȡ�û������Scanner����
     * @throws SQLException ����������ݿ���ʴ���
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
            System.out.println("************** ��ѡ��ɾ����Ŀ ***************");
            System.out.println("********************************************");
            System.out.println("ѡ�����룺");
            System.out.println("*********** '1': ɾ��Ժϵ��Ϣ ************");
            System.out.println("*********** '2': ɾ��רҵ��Ϣ ************");
            System.out.println("*********** '3': ɾ���༶��Ϣ ************");
            System.out.println("*********** '4': ɾ��ѧ����Ϣ ************");
            System.out.println("*********** '5': ɾ���γ���Ϣ ************");
            System.out.println("*********** '6': ɾ���ɼ���Ϣ ************");
            System.out.println("*********** '7': ɾ��������Ϣ ************");
            System.out.println("*********** '0': �������˵� ************");
            System.out.println("********************************************");
            System.out.print("��ѡ��Ҫ���е�ҵ��: ");

            try {
                deleteChoice = scanner.nextInt();
                scanner.nextLine(); // ���ѵ����з�
            } catch (InputMismatchException e) {
                System.out.println("��Ч���룬���������֡�");
                scanner.nextLine(); // ���ѵ����������
                deleteChoice = -1; // ����Ϊ��Чѡ������ѭ��
                continue;
            }

            switch (deleteChoice) {
                case 1: // ɾ��Ժϵ
                    System.out.println("\n--- ɾ��Ժϵ��Ϣ ---");
                    System.out.print("������Ҫɾ����ԺϵID (did): ");
                    String deptDid = scanner.nextLine();
                    if (departmentDAO.deleteDepartment(con, deptDid)) {
                        System.out.println("Ժϵɾ���ɹ�: ID " + deptDid);
                    } else {
                        System.out.println("Ժϵɾ��ʧ�ܣ����ܲ����ڻ�����������ݡ�");
                    }
                    break;
                case 2: // ɾ��רҵ
                    System.out.println("\n--- ɾ��רҵ��Ϣ ---");
                    System.out.print("������Ҫɾ����רҵID (mid): ");
                    String majorMid = scanner.nextLine();
                    if (majorDAO.deleteMajor(con, majorMid)) {
                        System.out.println("רҵɾ���ɹ�: ID " + majorMid);
                    } else {
                        System.out.println("רҵɾ��ʧ�ܣ����ܲ����ڻ�����������ݡ�");
                    }
                    break;
                case 3: // ɾ���༶
                    System.out.println("\n--- ɾ���༶��Ϣ ---");
                    System.out.print("������Ҫɾ���İ༶ID (cid): ");
                    String classCid = scanner.nextLine();
                    if (schoolClassDAO.deleteClass(con, classCid)) {
                        System.out.println("�༶ɾ���ɹ�: ID " + classCid);
                    } else {
                        System.out.println("�༶ɾ��ʧ�ܣ����ܲ����ڻ�����������ݡ�");
                    }
                    break;
                case 4: // ɾ��ѧ��
                    System.out.println("\n--- ɾ��ѧ����Ϣ ---");
                    System.out.print("������Ҫɾ����ѧ��ID (id): ");
                    String studentId = scanner.nextLine();
                    if (studentDAO.deleteStudent(con, studentId)) {
                        System.out.println("ѧ��ɾ���ɹ�: ID " + studentId);
                    } else {
                        System.out.println("ѧ��ɾ��ʧ�ܣ����ܲ����ڻ�����������ݡ�");
                    }
                    break;
                case 5: // ɾ���γ�
                    System.out.println("\n--- ɾ���γ���Ϣ ---");
                    System.out.print("������Ҫɾ���Ŀγ�ID (kid): ");
                    String courseKid = scanner.nextLine();
                    if (courseDAO.deleteCourse(con, courseKid)) {
                        System.out.println("�γ�ɾ���ɹ�: ID " + courseKid);
                    } else {
                        System.out.println("�γ�ɾ��ʧ�ܣ����ܲ����ڻ�����������ݡ�");
                    }
                    break;
                case 6: // ɾ���ɼ�
                    System.out.println("\n--- ɾ���ɼ���Ϣ ---");
                    System.out.print("������Ҫɾ���ĳɼ���¼�Ŀγ�ID (kid): ");
                    String gradeKid = scanner.nextLine();
                    System.out.print("������Ҫɾ���ĳɼ���¼��ѧ��ID (id): ");
                    String gradeStudentId = scanner.nextLine();
                    if (gradeDAO.deleteGrade(con, gradeKid, gradeStudentId)) {
                        System.out.println("�ɼ�ɾ���ɹ�: �γ�ID " + gradeKid + ", ѧ��ID " + gradeStudentId);
                    } else {
                        System.out.println("�ɼ�ɾ��ʧ�ܣ����ܲ����ڡ�");
                    }
                    break;
                case 7: // ɾ������
                    System.out.println("\n--- ɾ��������Ϣ ---");
                    System.out.print("������Ҫɾ���Ľ���ID (aid): ");
                    String apAid = scanner.nextLine();
                    if (awardPunishDAO.deleteAwardPunish(con, apAid)) {
                        System.out.println("����ɾ���ɹ�: ID " + apAid);
                    } else {
                        System.out.println("����ɾ��ʧ�ܣ����ܲ����ڻ�����������ݡ�");
                    }
                    break;
                case 0:
                    System.out.println("\n�������˵���");
                    break;
                default:
                    System.out.println("\n��Ч��ѡ�����������롣");
                    break;
            }
            if (deleteChoice != 0) {
                System.out.println("\n�� Enter ������...");
                scanner.nextLine();
            }
        } while (deleteChoice != 0);
    }
}
