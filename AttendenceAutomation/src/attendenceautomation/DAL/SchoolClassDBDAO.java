/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendenceautomation.DAL;

import attendenceautomation.BE.SchoolClass;
import attendenceautomation.BE.Student;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bjarne Helsinghof
 */
public class SchoolClassDBDAO
{
    private final DbConnectionProvider db;
    
    /** 
     * forbindelse til database 
     * @throws java.io.IOException
     */
    public SchoolClassDBDAO() throws IOException
    {
        db = new DbConnectionProvider();    
    }
    public class SchoolClass
{
    private String className;
    private List<Student> students;
    private int id;

    public SchoolClass(String className)
    {
//        this.id = id;
        this.className = className;
        students = new ArrayList();
    }

    public String getClassName()
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
 
    @Override
    public String toString()
    {
        return className;
    }
}
 
}