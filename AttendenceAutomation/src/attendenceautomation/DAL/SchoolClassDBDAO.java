/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendenceautomation.DAL;

import attendenceautomation.BE.SchoolClass;
import attendenceautomation.BE.Student;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;

/**
 *
 * @author Bjarne Helsinghof
 */
public class SchoolClassDBDAO
{

    DbConnectionProvider ds;

    /**
     * forbindelse til database
     *
     * @throws java.io.IOException
     */
    public SchoolClassDBDAO() throws IOException
    {
        ds = new DbConnectionProvider();
    }

    public List<Student> getAllStudentsInClass(SchoolClass classToGet)

    {
        List<Student> students = new ArrayList<>();

        try (Connection con = ds.getConnection())
        {
            PreparedStatement pstmt = con.prepareStatement("SELECT firstName + ' ' + lastName AS name, * FROM Person "
                    + "INNER JOIN Student ON Person.id = studentID "
                    + "WHERE classID = (?)");           
            pstmt.setInt(1, classToGet.getId());

            ResultSet rs = pstmt.executeQuery();

            while (rs.next())
            {
                String name = rs.getString("name");
                String email = rs.getString("email");
                String password = rs.getString("password");
                int id = rs.getInt("id");
                students.add(new Student(id, name, email, password));
            }
        }
        catch (Exception e)
        {
        }
        return students;

    }

    public List<SchoolClass> getAllClasses()

    {
        List<SchoolClass> schoolClasses = new ArrayList<>();

        try (Connection con = ds.getConnection())
        {
            Statement statement = con.createStatement();

            ResultSet rs = statement.executeQuery("SELECT * FROM SchoolClass");

            while(rs.next())
            {
                String className = rs.getString("className");
                int classID = rs.getInt("classID");
                schoolClasses.add(new SchoolClass(classID, className));

            }
        } 
        catch (Exception e)
        {
        }
        return schoolClasses;
    }

    public SchoolClass getSchoolClass(int id)

    {
        SchoolClass schoolclassToGet = null;
        try (Connection con = ds.getConnection())
        {
            PreparedStatement pstmt = con.prepareStatement("SELECT * FROM SchoolClass WHERE classID = (?)");
            pstmt.setInt(1, id);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next())
            {
                String className = rs.getString("className");
                int classID = rs.getInt("classID");

                schoolclassToGet = new SchoolClass(classID, className);
            }
        } 
        catch (Exception e)
        {
        }
        return schoolclassToGet;
    }
    
    public void deleteClass (SchoolClass classToDelete)
    {
        try (Connection con = ds.getConnection())
        {
            PreparedStatement pstmt = con.prepareStatement("DELETE FROM SchoolClass WHERE classID = (?) ");
            pstmt.setInt(1, classToDelete.getId());
            pstmt.execute();
        } 
        catch (Exception e)
        {
        }
    }
    
    public void addClass (SchoolClass classToAdd)
    {
        try (Connection con = ds.getConnection())
        {
            PreparedStatement pstmt = con.prepareStatement("INSERT INTO SchoolClass VALUES (?)");
            pstmt.setString(1, classToAdd.getClassName());
            pstmt.execute();
        } 
        catch (Exception e)
        {
        }
    }
    
    public void editSchoolClassName (String className, int id)
    {
        try (Connection con = ds.getConnection())
        {
            PreparedStatement pstmt = con.prepareStatement("UPDATE SchoolClass SET className = (?) WHERE classID = (?)");
            pstmt.setString(1, className);
            pstmt.setInt(2, id);
            
            pstmt.execute();
            
        } 
        catch (Exception e)
        {
        }
    }
}
