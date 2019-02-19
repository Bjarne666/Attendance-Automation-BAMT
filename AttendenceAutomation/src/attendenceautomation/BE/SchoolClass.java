/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendenceautomation.BE;

import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author kokus
 */
public class SchoolClass
{
    private StringProperty className;
    private List<Student> students;
    private int id;

    public SchoolClass(String className)
    {
//        this.id = id;
        this.className = new SimpleStringProperty();
        this.className.set(className);
        students = new ArrayList();
    }

    public StringProperty getClassName()
    {
        return className;
    }

    public List<Student> getStudents()
    {
        return students;
    }
    
    public void addStudent(Student studentToAdd)
    {
        students.add(studentToAdd);
    }

    public int getId()
    {
        return id;
    }
    
}