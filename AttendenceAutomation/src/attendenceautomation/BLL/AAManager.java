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
import attendenceautomation.DAL.AADALInterface;
import attendenceautomation.DAL.AttendanceFacade;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author kokus
 */
public class AAManager implements AttendanceInterface
{
    AADALInterface aDAO;

    public AAManager(AADALInterface dalInterface) throws IOException
    {
     aDAO = new AttendanceFacade();
//     aDAO = new MockDAO();
    }

    @Override
    public List<Student> getAllStudents()
    {
        return aDAO.getAllStudents();
    }

    @Override
    public List<SchoolClass> getAllClasses()
    {
        return aDAO.getAllClasses();
    }

    @Override
    public List<Teacher> getAllTeachers()
    {
        return aDAO.getAllTeachers();
    }

    @Override
    public List<Student> getStudentsInClass(SchoolClass chosenClass)
    {
        return aDAO.getStudentsInClass(chosenClass);
    }

    @Override
    public Student getStudent(Student studentToGet)
    {
        return aDAO.getStudent(studentToGet);
    }

    @Override
    public Teacher getTeacher(Teacher teacherToGet)
    {
        return aDAO.getTeacher(teacherToGet);
    }

    @Override
    public List<Attendance> getAttendance(Student student)
    {
        return aDAO.getAttendance(student);
    }

    @Override
    public Person login(String email, String password) throws SQLServerException, SQLException
    {
        return aDAO.login(email, password);
    }

    @Override
    public List<Attendance> setUpBarChart(int id)
    {
       return aDAO.setUpBarChart(id);
    }

    @Override
    public List<Attendance> getStudentPieChartData(int id)
    {
        return aDAO.getStudentPieChartData(id);
    }

    @Override
    public String getStudentClass(int id)
    {
        return aDAO.getStudentClass(id);
    }
    
@Override
    public void setAttendance(Attendance attendance, int id)
    {
        aDAO.setAttendance(attendance, id);
    }

    @Override
    public List<Attendance> getStudentPresentPieChartData(int id)
    {
        return aDAO.getStudentPresentPieChartData(id);
    }
    @Override
    public void editAttendance(Attendance attenToEdit, int id)
    {
        aDAO.editAttendance(attenToEdit, id);
    }
}