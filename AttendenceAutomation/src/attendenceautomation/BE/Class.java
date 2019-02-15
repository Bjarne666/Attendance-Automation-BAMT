/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendenceautomation.BE;

import java.util.ArrayList;
import javafx.beans.property.StringProperty;

/**
 *
 * @author kokus
 */
public class Class
{
    private StringProperty className;
    private ArrayList<Student> students;
    private int id;

    public Class(int id, StringProperty className, ArrayList<Student> students)
    {
        this.id = id;
        this.className = className;
        students = new ArrayList();
    }

    public StringProperty getClassName()
    {
        return className;
    }

    public ArrayList<Student> getStudents()
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
