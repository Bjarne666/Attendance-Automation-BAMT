/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendenceautomation.DAL;

import attendenceautomation.BE.Attendance;
import attendenceautomation.BE.SchoolClass;
import attendenceautomation.BE.Student;
import attendenceautomation.BE.Teacher;
import java.io.IOException;
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Teacher> getAllTeachers()
    {
        return pDbDao.getAllTeachers();
    }

    @Override
    public List<Student> getStudentsInClass(SchoolClass chosenClass)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    
}
