/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendenceautomation.DAL;

import attendenceautomation.BE.Attendance;
import attendenceautomation.BE.Student;
import attendenceautomation.BE.Teacher;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Theis
 */
public class PersonDBDAO
{
    
    DbConnectionProvider ds;

    public PersonDBDAO() throws IOException
    {
        ds = new DbConnectionProvider();
    }
    
    public List<Student> getAllStudents ()
    {
        List<Student> students = new ArrayList<>();
        
        try (Connection con = ds.getConnection())
        {
            Statement statement = con.createStatement();
            
            ResultSet rs = statement.executeQuery("SELECT* FROM Student");
            
            while (rs.next())
                    {
                        int id = rs.getInt("studentID");
                        String firstName = rs.getString("firstName");
                        String lastName = rs.getString("lastName");
                        String email = rs.getString("email");
                        String password = rs.getString("password");
                        
                        students.add(new Student(id, firstName, lastName, email, password));
                    }
            
        } 
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return students;
    }
    
    public Student getStudent(int id)
    {
        Student studentToGet = null;
        
        try (Connection con = ds.getConnection())
        {
            PreparedStatement pstmt = con.prepareStatement("SELECT * FROM Student WHERE StudentID = (?)");
            pstmt.setInt(1, id);
            
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next())
                    {
                        id = rs.getInt("studentID");
                        String firstName = rs.getString("firstName");
                        String lastName = rs.getString("lastName");
                        String email = rs.getString("email");
                        String password = rs.getString("password");
                        
                        studentToGet = new Student(id, firstName, lastName, email, password);
                    }
            
        } 
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        return studentToGet;
    }
    
    public Teacher getTeacher (int id)
    {
        Teacher teacherToGet = null;
        
        try (Connection con = ds.getConnection())
        {
            PreparedStatement pstmt = con.prepareStatement("SELECT * FROM Teacher WHERE teacherID = (?)");
            pstmt.setInt(1, id);
            
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next())
                    {
                        id = rs.getInt("teacherID");
                        String firstName = rs.getString("firstName");
                        String lastName = rs.getString("lastName");
                        String email = rs.getString("email");
                        String password = rs.getString("password");
                        
                        teacherToGet = new Teacher(id, firstName, email, password);
                    }
            
        } 
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        return teacherToGet;
    }
    
    public List<Teacher> getAllTeachers()
    {
        List<Teacher> teachers = new ArrayList<>();
        
        try (Connection con = ds.getConnection())
        {
            
            Statement statement = con.createStatement();
            
            ResultSet rs = statement.executeQuery("SELECT * FROM Teacher");
            
            while (rs.next())
                    {
                        int id = rs.getInt("studentID");
                        String firstName = rs.getString("firstName");
                        String lastName = rs.getString("lastName");
                        String email = rs.getString("email");
                        String password = rs.getString("password");
                        
                        teachers.add(new Teacher(id, firstName, email, password));
                    }
            
            
        } 
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        return teachers;
    }
    
    public List<Attendance> getAttendance(Student student)
    {
        List<Attendance> attendance = new ArrayList<>();
        
        try (Connection con = ds.getConnection())
        {
            PreparedStatement pstmt = con.prepareStatement("SELECT date, isPresent FROM Attendance WHERE StudentID = (?)");
            pstmt.setInt(1, student.getId());
            
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next())
            {
                Date date = rs.getDate("date");
                boolean present = rs.getBoolean("isPresent");
                
                attendance.add(new Attendance (date, present));  
            }
        } 
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        return attendance;
    }
}
