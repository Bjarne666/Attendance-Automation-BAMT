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
import java.util.List;
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
       aManager = new AAManager();
    }
    public List<Student> getAllStudents()
    {
        return aManager.getAllStudents();
    }

    public List<SchoolClass> getAllClasses()
    {
        return aManager.getAllClasses();
    }

    public List<Teacher> getAllTeachers()
    {
        return aManager.getAllTeachers();
    }

    public List<Student> getStudentsInClass(SchoolClass chosenClass)
    {
        return aManager.getStudentsInClass(chosenClass);
    }
    
}
