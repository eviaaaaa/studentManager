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
     * ��ʾ�������ݲ˵��������û�ѡ��
     *
     * @param con     ���ݿ�����
     * @param scanner ���ڶ�ȡ�û������Scanner����
     * @throws SQLException ����������ݿ���ʴ���
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
            System.out.println("************** ��ѡ�������Ŀ ***************");
            System.out.println("********************************************");
            System.out.println("ѡ�����룺");
            System.out.println("*********** '1': ����Ժϵ��Ϣ ************");
            System.out.println("*********** '2': ����רҵ��Ϣ ************");
            System.out.println("*********** '3': ���°༶��Ϣ ************");
            System.out.println("*********** '4': ����ѧ����Ϣ ************");
            System.out.println("*********** '5': ���¿γ���Ϣ ************");
            System.out.println("*********** '6': ���³ɼ���Ϣ ************");
            System.out.println("*********** '7': ���½�����Ϣ ************");
            System.out.println("*********** '0': �������˵� ************");
            System.out.println("********************************************");
            System.out.print("��ѡ��Ҫ���е�ҵ��: ");

            try {
                updateChoice = scanner.nextInt();
                scanner.nextLine(); // ���ѵ����з�
            } catch (InputMismatchException e) {
                System.out.println("��Ч���룬���������֡�");
                scanner.nextLine(); // ���ѵ����������
                updateChoice = -1; // ����Ϊ��Чѡ������ѭ��
                continue;
            }

            switch (updateChoice) {
                case 1: // ����Ժϵ
                    System.out.println("\n--- ����Ժϵ��Ϣ ---");
                    System.out.print("������Ҫ���µ�ԺϵID (did): ");
                    String deptDid = scanner.nextLine();
                    Department deptToUpdate = departmentDAO.getDepartmentById(con, deptDid);
                    if (deptToUpdate != null) {
                        System.out.println("��ǰԺϵ����: " + deptToUpdate.getDname());
                        System.out.print("�������µ�Ժϵ���� (dname): ");
                        String newDname = scanner.nextLine();
                        deptToUpdate.setDname(newDname);
                        if (departmentDAO.updateDepartment(con, deptToUpdate)) {
                            System.out.println("Ժϵ���³ɹ�: " + deptToUpdate);
                        } else {
                            System.out.println("Ժϵ����ʧ�ܡ�");
                        }
                    } else {
                        System.out.println("δ�ҵ�IDΪ " + deptDid + " ��Ժϵ��");
                    }
                    break;
                case 2: // ����רҵ
                    System.out.println("\n--- ����רҵ��Ϣ ---");
                    System.out.print("������Ҫ���µ�רҵID (mid): ");
                    String majorMid = scanner.nextLine();
                    Major majorToUpdate = majorDAO.getMajorById(con, majorMid);
                    if (majorToUpdate != null) {
                        System.out.println("��ǰ����ԺϵID: " + (majorToUpdate.getDid() != null ? majorToUpdate.getDid() : "��"));
                        System.out.print("�������µ�����ԺϵID (did, ��Ϊ��): ");
                        String newMajorDid = scanner.nextLine();
                        majorToUpdate.setDid(newMajorDid.isEmpty() ? null : newMajorDid);

                        System.out.println("��ǰרҵ����: " + majorToUpdate.getMname());
                        System.out.print("�������µ�רҵ���� (mname): ");
                        String newMajorMname = scanner.nextLine();
                        majorToUpdate.setMname(newMajorMname);

                        if (majorDAO.updateMajor(con, majorToUpdate)) {
                            System.out.println("רҵ���³ɹ�: " + majorToUpdate);
                        } else {
                            System.out.println("רҵ����ʧ�ܡ�");
                        }
                    } else {
                        System.out.println("δ�ҵ�IDΪ " + majorMid + " ��רҵ��");
                    }
                    break;
                case 3: // ���°༶
                    System.out.println("\n--- ���°༶��Ϣ ---");
                    System.out.print("������Ҫ���µİ༶ID (cid): ");
                    String classCid = scanner.nextLine();
                    SchoolClass classToUpdate = schoolClassDAO.getClassById(con, classCid);
                    if (classToUpdate != null) {
                        System.out.println("��ǰ����רҵID: " + (classToUpdate.getMid() != null ? classToUpdate.getMid() : "��"));
                        System.out.print("�������µ�����רҵID (mid, ��Ϊ��): ");
                        String newClassMid = scanner.nextLine();
                        classToUpdate.setMid(newClassMid.isEmpty() ? null : newClassMid);

                        System.out.println("��ǰ����ԺϵID: " + (classToUpdate.getDid() != null ? classToUpdate.getDid() : "��"));
                        System.out.print("�������µ�����ԺϵID (did, ��Ϊ��): ");
                        String newClassDid = scanner.nextLine();
                        classToUpdate.setDid(newClassDid.isEmpty() ? null : newClassDid);

                        System.out.println("��ǰ�༶����: " + classToUpdate.getCname());
                        System.out.print("�������µİ༶���� (cname): ");
                        String newClassCname = scanner.nextLine();
                        classToUpdate.setCname(newClassCname);

                        System.out.println("��ǰ�༶����: " + classToUpdate.getCnumber());
                        System.out.print("�������µİ༶���� (cnumber): ");
                        int newClassCnumber = 0;
                        try {
                            newClassCnumber = scanner.nextInt();
                            scanner.nextLine();
                        } catch (InputMismatchException e) {
                            System.out.println("�༶����������Ч������ʧ�ܡ�");
                            scanner.nextLine();
                            break;
                        }
                        classToUpdate.setCnumber(newClassCnumber);

                        if (schoolClassDAO.updateClass(con, classToUpdate)) {
                            System.out.println("�༶���³ɹ�: " + classToUpdate);
                        } else {
                            System.out.println("�༶����ʧ�ܡ�");
                        }
                    } else {
                        System.out.println("δ�ҵ�IDΪ " + classCid + " �İ༶��");
                    }
                    break;
                case 4: // ����ѧ��
                    System.out.println("\n--- ����ѧ����Ϣ ---");
                    System.out.print("������Ҫ���µ�ѧ��ID (id): ");
                    String studentId = scanner.nextLine();
                    Student studentToUpdate = studentDAO.getStudentById(con, studentId);
                    if (studentToUpdate != null) {
                        System.out.println("��ǰ����: " + studentToUpdate.getName());
                        System.out.print("�������µ����� (name): ");
                        studentToUpdate.setName(scanner.nextLine());

                        System.out.println("��ǰ�Ա�: " + studentToUpdate.getSex());
                        System.out.print("�������µ��Ա� (sex, ����: ��/Ů): ");
                        studentToUpdate.setSex(scanner.nextLine());

                        System.out.println("��ǰ�༶ID: " + (studentToUpdate.getCid() != null ? studentToUpdate.getCid() : "��"));
                        System.out.print("�������µİ༶ID (cid, ��Ϊ��): ");
                        String newStudentCid = scanner.nextLine();
                        studentToUpdate.setCid(newStudentCid.isEmpty() ? null : newStudentCid);

                        System.out.println("��ǰרҵID: " + (studentToUpdate.getMid() != null ? studentToUpdate.getMid() : "��"));
                        System.out.print("�������µ�רҵID (mid, ��Ϊ��): ");
                        String newStudentMid = scanner.nextLine();
                        studentToUpdate.setMid(newStudentMid.isEmpty() ? null : newStudentMid);

                        System.out.println("��ǰԺϵID: " + (studentToUpdate.getDid() != null ? studentToUpdate.getDid() : "��"));
                        System.out.print("�������µ�ԺϵID (did, ��Ϊ��): ");
                        String newStudentDid = scanner.nextLine();
                        studentToUpdate.setDid(newStudentDid.isEmpty() ? null : newStudentDid);

                        System.out.println("��ǰ����: " + studentToUpdate.getNation());
                        System.out.print("�������µ����� (nation): ");
                        studentToUpdate.setNation(scanner.nextLine());

                        System.out.println("��ǰ����: " + studentToUpdate.getAge());
                        System.out.print("�������µ����� (age): ");
                        try {
                            studentToUpdate.setAge(scanner.nextInt());
                            scanner.nextLine();
                        } catch (InputMismatchException e) {
                            System.out.println("����������Ч������ʧ�ܡ�");
                            scanner.nextLine();
                            break;
                        }

                        System.out.println("��ǰ����: " + DATE_FORMAT.format(studentToUpdate.getBirthday()));
                        System.out.print("�������µ����� (birthday, ��ʽ:yyyy-MM-dd): ");
                        String birthdayStr = scanner.nextLine();
                        try {
                            studentToUpdate.setBirthday(DATE_FORMAT.parse(birthdayStr));
                        } catch (ParseException e) {
                            System.out.println("�������ڸ�ʽ����ȷ������ʧ�ܡ�");
                            break;
                        }

                        System.out.println("��ǰ���ڵ�: " + studentToUpdate.getLocation());
                        System.out.print("�������µ����ڵ� (location): ");
                        studentToUpdate.setLocation(scanner.nextLine());

                        System.out.println("��ǰ��ѧ����: " + DATE_FORMAT.format(studentToUpdate.getEnroll()));
                        System.out.print("�������µ���ѧ���� (enroll, ��ʽ:yyyy-MM-dd): ");
                        String enrollStr = scanner.nextLine();
                        try {
                            studentToUpdate.setEnroll(DATE_FORMAT.parse(enrollStr));
                        } catch (ParseException e) {
                            System.out.println("��ѧ���ڸ�ʽ����ȷ������ʧ�ܡ�");
                            break;
                        }

                        if (studentDAO.updateStudent(con, studentToUpdate)) {
                            System.out.println("ѧ����Ϣ���³ɹ�: " + studentToUpdate);
                        } else {
                            System.out.println("ѧ����Ϣ����ʧ�ܡ�");
                        }
                    } else {
                        System.out.println("δ�ҵ�IDΪ " + studentId + " ��ѧ����");
                    }
                    break;
                case 5: // ���¿γ�
                    System.out.println("\n--- ���¿γ���Ϣ ---");
                    System.out.print("������Ҫ���µĿγ�ID (kid): ");
                    String courseKid = scanner.nextLine();
                    Course courseToUpdate = courseDAO.getCourseById(con, courseKid);
                    if (courseToUpdate != null) {
                        System.out.println("��ǰ�γ�����: " + courseToUpdate.getKname());
                        System.out.print("�������µĿγ����� (kname): ");
                        courseToUpdate.setKname(scanner.nextLine());

                        System.out.println("��ǰѧ��: " + courseToUpdate.getKcredit());
                        System.out.print("�������µ�ѧ�� (kcredit): ");
                        try {
                            courseToUpdate.setKcredit(scanner.nextInt());
                            scanner.nextLine();
                        } catch (InputMismatchException e) {
                            System.out.println("ѧ��������Ч������ʧ�ܡ�");
                            scanner.nextLine();
                            break;
                        }

                        System.out.println("��ǰ��ʱ: " + courseToUpdate.getKperiod());
                        System.out.print("�������µĿ�ʱ (kperiod): ");
                        try {
                            courseToUpdate.setKperiod(scanner.nextInt());
                            scanner.nextLine();
                        } catch (InputMismatchException e) {
                            System.out.println("��ʱ������Ч������ʧ�ܡ�");
                            scanner.nextLine();
                            break;
                        }

                        if (courseDAO.updateCourse(con, courseToUpdate)) {
                            System.out.println("�γ���Ϣ���³ɹ�: " + courseToUpdate);
                        } else {
                            System.out.println("�γ���Ϣ����ʧ�ܡ�");
                        }
                    } else {
                        System.out.println("δ�ҵ�IDΪ " + courseKid + " �Ŀγ̡�");
                    }
                    break;
                case 6: // ���³ɼ�
                    System.out.println("\n--- ���³ɼ���Ϣ ---");
                    System.out.print("������Ҫ���µĳɼ���¼�Ŀγ�ID (kid): ");
                    String gradeKid = scanner.nextLine();
                    System.out.print("������Ҫ���µĳɼ���¼��ѧ��ID (id): ");
                    String gradeStudentId = scanner.nextLine();
                    Grade gradeToUpdate = gradeDAO.getGradeByIds(con, gradeKid, gradeStudentId);
                    if (gradeToUpdate != null) {
                        System.out.println("��ǰ�ɼ�: " + gradeToUpdate.getGgrade());
                        System.out.print("�������µĳɼ� (ggrade): ");
                        int newGgrade = 0;
                        try {
                            newGgrade = scanner.nextInt();
                            scanner.nextLine();
                        } catch (InputMismatchException e) {
                            System.out.println("�ɼ�������Ч������ʧ�ܡ�");
                            scanner.nextLine();
                            break;
                        }
                        gradeToUpdate.setGgrade(newGgrade);
                        if (gradeDAO.updateGrade(con, gradeToUpdate)) {
                            System.out.println("�ɼ����³ɹ�: " + gradeToUpdate);
                        } else {
                            System.out.println("�ɼ�����ʧ�ܡ�");
                        }
                    } else {
                        System.out.println("δ�ҵ��γ�IDΪ " + gradeKid + " ��ѧ��IDΪ " + gradeStudentId + " �ĳɼ���¼��");
                    }
                    break;
                case 7: // ���½���
                    System.out.println("\n--- ���½�����Ϣ ---");
                    System.out.print("������Ҫ���µĽ���ID (aid): ");
                    String apAid = scanner.nextLine();
                    AwardPunish apToUpdate = awardPunishDAO.getAwardPunishById(con, apAid);
                    if (apToUpdate != null) {
                        System.out.println("��ǰѧ��ID: " + (apToUpdate.getId() != null ? apToUpdate.getId() : "��"));
                        System.out.print("�������µ�ѧ��ID (id, ��Ϊ��): ");
                        String newApStudentId = scanner.nextLine();
                        apToUpdate.setId(newApStudentId.isEmpty() ? null : newApStudentId);

                        System.out.println("��ǰרҵID: " + (apToUpdate.getMid() != null ? apToUpdate.getMid() : "��"));
                        System.out.print("�������µ�רҵID (mid, ��Ϊ��): ");
                        String newApMid = scanner.nextLine();
                        apToUpdate.setMid(newApMid.isEmpty() ? null : newApMid);

                        System.out.println("��ǰԺϵID: " + (apToUpdate.getDid() != null ? apToUpdate.getDid() : "��"));
                        System.out.print("�������µ�ԺϵID (did, ��Ϊ��): ");
                        String newApDid = scanner.nextLine();
                        apToUpdate.setDid(newApDid.isEmpty() ? null : newApDid);

                        System.out.println("��ǰ��������: " + apToUpdate.getAname());
                        System.out.print("�������µĽ������� (aname, ����: ����/�ͷ�): ");
                        apToUpdate.setAname(scanner.nextLine());

                        System.out.println("��ǰ������Ŀ: " + apToUpdate.getAproject());
                        System.out.print("�������µĽ�����Ŀ (aproject): ");
                        apToUpdate.setAproject(scanner.nextLine());

                        if (awardPunishDAO.updateAwardPunish(con, apToUpdate)) {
                            System.out.println("������Ϣ���³ɹ�: " + apToUpdate);
                        } else {
                            System.out.println("������Ϣ����ʧ�ܡ�");
                        }
                    } else {
                        System.out.println("δ�ҵ�IDΪ " + apAid + " �Ľ��ͼ�¼��");
                    }
                    break;
                case 0:
                    System.out.println("\n�������˵���");
                    break;
                default:
                    System.out.println("\n��Ч��ѡ�����������롣");
                    break;
            }
            if (updateChoice != 0) {
                System.out.println("\n�� Enter ������...");
                scanner.nextLine();
            }
        } while (updateChoice != 0);
    }
}
