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
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
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
   
    private Person user;


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

       

    }
    public void editAttendance(Attendance attenToEdit, int id)
    {
        aManager.editAttendance(attenToEdit, id);
    }
    public String getStudentClass(int id)
    {
        return aManager.getStudentClass(id);
    }
    public ObservableList<Student> getAllStudents()
    {
        return FXCollections.observableArrayList(aManager.getAllStudents());
    }

    public ObservableList<SchoolClass> getAllClasses()
    {
        return FXCollections.observableArrayList(aManager.getAllClasses());
    }

    public ObservableList<Teacher> getAllTeachers()
    {
        return FXCollections.observableArrayList(aManager.getAllTeachers());
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
    
    public ObservableList<Attendance> getAbsenceSumById (int id)
    {       
//        ObservableList absences = FXCollections.observableArrayList(aManager.setUpBarChart(id));
//        int absencesum = 0;
//        
//        for (int i = 0; i < absences.size(); i++)
//        {
//            absencesum ++;
//        }
//        return absencesum;
        
        return FXCollections.observableArrayList(aManager.setUpBarChart(id));
        
    }
    
    public int getStudentPieChartData(int id)
    {
        ObservableList isNotPresentData = FXCollections.observableArrayList(aManager.getStudentPieChartData(id));
        
        
        int isAbsenceAvg = 0;
        
        for (int i = 0; i < isNotPresentData.size(); i++)
        {
            isAbsenceAvg++;
        }
        
        return isAbsenceAvg;
    }
    
    public int getStudentPresentPieChartData(int id)
    {
        ObservableList isPresentData = FXCollections.observableArrayList(aManager.getStudentPresentPieChartData(id));
        
        int isPresentAvg = 0;
        
        for (int i = 0; i < isPresentData.size(); i++)
        {
            isPresentAvg++;
        }
        
        return isPresentAvg;
    }
    
    public void setAttendance(Attendance attendance, int id)
        {
            aManager.setAttendance(attendance, id);
        }
    
    public void deleteStudent (Student studentToDelete)
    {
        aManager.deleteStudent(studentToDelete);
        this.studentList.remove(studentToDelete);
    }
    
    public void deleteTeacher (Teacher teacherToDelete)
    {
        aManager.deleteTeacher(teacherToDelete);
        this.teacherList.remove(teacherToDelete);
    }
    
    public void deleteClass (SchoolClass classToDelete)
    {
        aManager.deleteClass(classToDelete);
        this.classList.remove(classToDelete);
    }
    
    public void addStudent (Student studentToAdd, SchoolClass schoolClass)
    {
        aManager.addStudent(studentToAdd, schoolClass);
        studentList.add(studentToAdd);
    }
    
    public void editSchoolClassName (SchoolClass classToEdit)
    {
        aManager.editSchoolClassName(classToEdit);
    }
    
    public void addClass (SchoolClass classToAdd)
    {
        aManager.addClass(classToAdd);
    }
    
    public void addTeacher (Teacher teacherToAdd)
    {
        aManager.addTeacher(teacherToAdd);
    }

}
