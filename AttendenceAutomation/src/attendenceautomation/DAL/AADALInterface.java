/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendenceautomation.DAL;

import attendenceautomation.BE.Attendance;
import attendenceautomation.BE.Person;
import attendenceautomation.BE.SchoolClass;
import attendenceautomation.BE.Student;
import attendenceautomation.BE.Teacher;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author kokus
 */
public interface AADALInterface
{
    /**
     *
     * @param attenToEdit
     * @param id
     */
    public void editAttendance(int id, Attendance... attenToEdit);
    
    /**
     *
     * @return
     */
    public List<Student> getAllStudents();

    /**
     *
     * @param studentToGet
     * @return
     */
    public Student getStudent(Student studentToGet);

    /**
     *
     * @return
     */
    public List<SchoolClass> getAllClasses();

    /**
     *
     * @return
     */
    public List<Teacher> getAllTeachers();

    /**
     *
     * @param teacherToGet
     * @return
     */
    public Teacher getTeacher(Teacher teacherToGet);

    /**
     *
     * @param chosenClass
     * @return
     */
    public List<Student> getStudentsInClass(SchoolClass chosenClass);

    /**
     *
     * @param id
     * @return
     */
    public double calculateTotalAbsence(int id);
    
    /**
     *
     * @param student
     * @return
     */
    public List<Attendance> getAttendance(Student student);

    /**
     *
     * @param email
     * @param password
     * @return
     * @throws SQLServerException
     * @throws SQLException
     */
    public Person login(String email, String password) throws SQLServerException, SQLException;
    
    /**
     * 
     * @param id
     * @return 
     */
    public List<Attendance> setUpBarChart (int id);
    
    /**
     * 
     * @param id
     * @return 
     */
    public List<Attendance> getStudentPieChartAbsenceData(int id);
    
    /**
     * 
     * @param id
     * @return 
     */
    public List<Attendance> getStudentPresentPieChartData(int id);
    
    /**
     *
     * @param id
     * @return
     */
    public String getStudentClass(int id);
    
    /** 
     * 
     * @param attendance
     * @param id 
     */
    
    public void setAttendance(Attendance attendance, int id);
    
    /**
     * 
     * @param studentToDelete 
     */
    public void deleteStudent (Student studentToDelete);
    
    /**
     * 
     * @param teacherToDelete 
     */
    public void deleteTeacher (Teacher teacherToDelete);
    
    /**
     * 
     * @param classToDelete 
     */
    public void deleteClass (SchoolClass classToDelete);
    
    /**
     * 
     * @param studentToAdd 
     * @param schoolClass 
     */
    public void addStudent (Student studentToAdd, SchoolClass schoolClass);
    
    /**
     * 
     * @param className
     * @param id
     */
    public void editSchoolClassName (String className, int id);
    
    /**
     * 
     * @param classToAdd 
     */
    public void addClass (SchoolClass classToAdd);
    
    /**
     * 
     * @param teacherToAdd 
     */
    public void addTeacher (Teacher teacherToAdd);

    /**
     * 
     * @param id
     * @param fName
     * @param lName
     * @param email 
     */
    public void editPerson(String fName, String lName, String email, int id);
    
    /**
     * 
     * @param chosenClass
     * @param studentToMove 
     */
    public void moveStudentToNewClass(SchoolClass chosenClass, Student studentToMove);
    
    /**
     * 
     * @param id
     * @return 
     */
    public List<Attendance> getTotalClassPresence(int id);
    
    /**
     * 
     * @param id
     * @return 
     */
    public  List<Attendance> getTotalClassAbsence(int id);
}

