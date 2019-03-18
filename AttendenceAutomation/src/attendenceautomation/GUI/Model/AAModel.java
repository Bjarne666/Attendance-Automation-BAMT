/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendenceautomation.GUI.Model;

import attendenceautomation.BE.SchoolClass;
import attendenceautomation.BLL.AAManager;
import attendenceautomation.BE.Student;
import attendenceautomation.BE.Teacher;
import attendenceautomation.BLL.AttendanceInterface;
import attendenceautomation.DAL.MockDAO;
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
    

    public AAModel()
    {
       MockDAO mDAO = new MockDAO();
       aManager = new AAManager();
       studentList = FXCollections.observableArrayList(mDAO.getAllStudents());
       studentList.addAll(mDAO.getAllStudents());
       classList = FXCollections.observableArrayList(mDAO.getAllClasses());
       classList.addAll(mDAO.getAllClasses());
       teacherList = FXCollections.observableArrayList(mDAO.getAllTeachers());
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
    
}
