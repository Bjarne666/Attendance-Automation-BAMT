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
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Theis
 */
public class AttendanceFacade implements AADALInterface
{
    SchoolClassDBDAO sCDbDao;
    PersonDBDAO pDbDao;
    public AttendanceFacade() throws IOException
    {
        sCDbDao = new SchoolClassDBDAO();
        pDbDao = new PersonDBDAO();
    }

    @Override
    public List<Student> getAllStudents()
    {
       return pDbDao.getAllStudents();
    }

    @Override
    public Student getStudent(Student studentToGet)
    {
        return pDbDao.getStudent(studentToGet.getId());
    }

    @Override
    public List<SchoolClass> getAllClasses()
    {
        return sCDbDao.getAllClasses();
    }

    @Override
    public List<Teacher> getAllTeachers()
    {
        return pDbDao.getAllTeachers();
    }

    @Override
    public List<Student> getStudentsInClass(SchoolClass chosenClass) 
    {
       return sCDbDao.getAllStudentsInClass(chosenClass);
    }

    @Override
    public Teacher getTeacher(Teacher teacherToGet)
    {
        return pDbDao.getTeacher(teacherToGet.getId());
    }

    @Override
    public List<Attendance> getAttendance(Student student)
    {
        return pDbDao.getAttendance(student);
    }

    @Override
    public Person login(String email, String password) throws SQLServerException, SQLException
    {
        return pDbDao.login(email, password);
    }
    
}
