/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendenceautomation.BLL;

import attendenceautomation.BE.SchoolClass;
import attendenceautomation.BE.Student;
import attendenceautomation.BE.Teacher;
import attendenceautomation.DAL.AADALInterface;
import attendenceautomation.DAL.MockDAO;
import java.util.List;

/**
 *
 * @author kokus
 */
public class AAManager implements AttendanceInterface
{
    AADALInterface aDAO;

    public AAManager()
    {
     aDAO = new MockDAO();
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
}
