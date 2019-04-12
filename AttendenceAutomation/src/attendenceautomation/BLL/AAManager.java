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
    public void editAttendance(int id, Attendance... attenToEdit)
    {
        aDAO.editAttendance(id, attenToEdit);
    }

    @Override
    public void deleteStudent(Student studentToDelete)
    {
        aDAO.deleteStudent(studentToDelete);
    }

    @Override
    public void deleteTeacher(Teacher teacherToDelete)
    {
        aDAO.deleteTeacher(teacherToDelete);
    }

    @Override
    public void deleteClass(SchoolClass classToDelete)
    {
        aDAO.deleteClass(classToDelete);
    }

    @Override
    public void addStudent (Student studentToAdd, SchoolClass schoolClass)
    {
        aDAO.addStudent(studentToAdd, schoolClass);
    }

    @Override
    public void editSchoolClassName(String className, int id)
    {
        aDAO.editSchoolClassName(className, id);
    }

    @Override
    public void addClass(SchoolClass classToAdd)
    {
        aDAO.addClass(classToAdd);
    }

    @Override
    public void addTeacher(Teacher teacherToAdd)
    {
        aDAO.addTeacher(teacherToAdd);
    }

    @Override
    public void editPerson(String fName, String lName, String email, int id)
    {
        aDAO.editPerson(fName, lName, email, id);
    }

    @Override
    public void moveStudentToNewClass(SchoolClass chosenClass, Student studentToMove)
    {
        aDAO.moveStudentToNewClass(chosenClass, studentToMove);
    }

    @Override
    public double calculateTotalAbsence(int id)
    {
       return aDAO.calculateTotalAbsence(id);
    }
    
    /**
     * takes a list of class absence, counts and returns it
     * @param id
     * @return 
     */
    @Override
    public int getTotalClassPresence(int id)
    {
        
        List<Attendance> tempList = aDAO.getTotalClassPresence(id);
        return countPresence(tempList);
       
    }
    
    public int countPresence(List<Attendance> attList)
    {
        int classPresence = 0;
        for (Attendance attendance : attList)
        {
            classPresence++;
        }
        return classPresence;
    }
    
    
    /**
     * takes a list of class absence, counts and returns it
     * @param id
     * @return 
     */
    @Override
    public int getTotalClassAbsence(int id)
    {
        List<Attendance> tempList = aDAO.getTotalClassAbsence(id);
        int classAbsence = 0;
        for (Attendance attendance : tempList)
        {
            classAbsence++;
        }
        return classAbsence;
    }

    
    /**
     * Takes a list of dates with presence, counts and returns it
     * @param id
     * @return 
     */
    @Override
    public int getStudentPresentPieChartData(int id)
    {
        List<Attendance> temp = aDAO.getStudentPresentPieChartData(id);
        int presence = 0;
        for (Attendance attendance : temp)
        {
            presence++;
        }
        return presence;
    }
    
    
    /**
     * Takes a list of dates with absences, counts and returns it
     * @param id
     * @return 
     */
    @Override
    public int getStudentPieChartAbsenceData(int id)
    {
        List<Attendance> tempList = aDAO.getStudentPieChartAbsenceData(id);
        int absence = 0;
        
        for (Attendance attendance : tempList)
        {
        absence++;    
        }
        return absence;
    }

}