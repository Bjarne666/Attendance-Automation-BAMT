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
    
    public void editAttendance(int id, Attendance... attenToEdit)
    {
        aManager.editAttendance(id, attenToEdit);
    }
    public String getStudentClass(int id)
    {
        return aManager.getStudentClass(id);
    }
    public ObservableList<Student> getAllStudents()
    {
        List<Student> tempStudentList = aManager.getAllStudents();
        studentList.clear();
        studentList.addAll(tempStudentList);
        
        return studentList;
    }

    public ObservableList<SchoolClass> getAllClasses()
    {
        List<SchoolClass> tempClassList = aManager.getAllClasses();
        classList.clear();
        classList.addAll(tempClassList);
        
        return classList;
    }

    public ObservableList<Teacher> getAllTeachers()
    {
        List<Teacher> tempTeacherList = aManager.getAllTeachers();
        teacherList.clear();
        teacherList.addAll(tempTeacherList);
        
        return teacherList;
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
    
    public void editSchoolClassName (String className, int id)
    {
        aManager.editSchoolClassName(className, id);
    }
    
    public void addClass (SchoolClass classToAdd)
    {
        aManager.addClass(classToAdd);
        classList.add(classToAdd);
    }
    
    public void addTeacher (Teacher teacherToAdd)
    {
        aManager.addTeacher(teacherToAdd);
        teacherList.add(teacherToAdd);
    }

    public void editPerson(String fName, String lName, String email, int id)
    {
        aManager.editPerson(fName, lName, email, id);
    }
    
    public void moveStudentToNewClass(SchoolClass chosenClass, Student studentToMove)
    {
        aManager.moveStudentToNewClass(chosenClass, studentToMove);
    }
    
    public int getStudentPieChartAbsenceData(int id)
    {
        ObservableList isNotPresentData = FXCollections.observableArrayList(aManager.getStudentPieChartData(id));
        
        
        int isAbsenceAvg = 0;
        
        for (int i = 0; i < isNotPresentData.size(); i++)
        {
            isAbsenceAvg++;
        }
        
        return isAbsenceAvg;
    }
}
