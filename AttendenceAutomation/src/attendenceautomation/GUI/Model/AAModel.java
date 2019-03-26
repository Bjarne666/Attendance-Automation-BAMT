/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendenceautomation.GUI.Model;

import attendenceautomation.BE.Attendance;
import attendenceautomation.BE.Person;
import attendenceautomation.BE.SchoolClass;
import attendenceautomation.BLL.AAManager;
import attendenceautomation.BE.Student;
import attendenceautomation.BE.Teacher;
import attendenceautomation.BLL.AttendanceInterface;
import attendenceautomation.DAL.AttendanceFacade;
import attendenceautomation.DAL.MockDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author kokus
 */
public class AAModel
{
    private final AttendanceInterface aManager;
    private ObservableList<Student> studentList;
    private ObservableList<SchoolClass> classList;
    private ObservableList<Teacher> teacherList;
    private ObservableList<Attendance> attendanceList;
    private Person user;
    private Student student;

    public AAModel() throws IOException
    {
//       MockDAO mDAO = new MockDAO();
       AttendanceFacade aFacade = new AttendanceFacade();
       aManager = new AAManager(aFacade);
       
       studentList = FXCollections.observableArrayList(aManager.getAllStudents());
       studentList.addAll(aManager.getAllStudents());
       classList = FXCollections.observableArrayList(aManager.getAllClasses());
       classList.addAll(aManager.getAllClasses());
       teacherList = FXCollections.observableArrayList(aManager.getAllTeachers());
//       attendanceList = FXCollections.observableArrayList(aManager.getAbsenceSumById(student));
       

    }
    public ObservableList<Student> getAllStudents()
    {
        return FXCollections.observableArrayList(aManager.getAllStudents());
    }

    public ObservableList<SchoolClass> getAllClasses()
    {
        return FXCollections.observableArrayList(aManager.getAllClasses());
    }

    public List<Teacher> getAllTeachers()
    {
        return aManager.getAllTeachers();
    }

    public ObservableList<Student> getStudentsInClass(SchoolClass chosenClass)
    {
        return FXCollections.observableArrayList(aManager.getStudentsInClass(chosenClass));
    }
    
    public Student getStudent(Student studentToGet)
    {
        return aManager.getStudent(studentToGet);
    }
    
    public Person login(String email, String password) throws SQLException
    {
        user = aManager.login(email, password);
        return user;
    }
    
    public List<Attendance> getAttendance(Student student)
    {
        return aManager.getAttendance(student);
    }
    
    public Person getUser()
    {
        return user;
    }
    
    public int getAbsenceSumById (int id)
    {
        ObservableList absences = FXCollections.observableArrayList(aManager.setUpBarChart(id));
        int absencesum = 0;
        
        for (Object absence : absences)
        {
            absencesum++;
        }
        
        return absencesum;
        
    }
    
    public ObservableList<Attendance> getStudentPieChartData(int id)
    {
        return FXCollections.observableArrayList(aManager.getStudentPieChartData(id));
        
    }
}
