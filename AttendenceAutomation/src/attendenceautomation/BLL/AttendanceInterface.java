/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendenceautomation.BLL;

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
public interface AttendanceInterface
{

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
    public Student getStudent (Student studentToGet);
    
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
    public Teacher getTeacher (Teacher teacherToGet);

    /**
     *
     * @param chosenClass
     * @return
     */
    public List<Student> getStudentsInClass (SchoolClass chosenClass);
   
    /**
     * 
     * @param student
     * @return 
     */
    public List<Attendance> getAttendance (Student student);
    
    /**
     *
     * @param email
     * @param password
     * @return
     * @throws com.microsoft.sqlserver.jdbc.SQLServerException
     */
    public Person login(String email, String password) throws SQLServerException, SQLException;
    
    /**
     * 
     * @param studentAbsence
     * @param student
     * @return 
     */
    public List<Attendance> setUpBarChart (Student student);
}
