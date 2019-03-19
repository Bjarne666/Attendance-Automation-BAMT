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
     * @throws java.io.IOException
     */
    public SchoolClassDBDAO() throws IOException
    {
        ds = new DbConnectionProvider();    
    }
    public List <Student> getAllStudentsInClass() throws SQLException
            
    {
        List <Student> students = new ArrayList<>();
        
        try (Connection con = ds.getConnection())
        {
            Statement statement = con.createStatement();
            
            ResultSet rs = statement.executeQuery("SELECT* FROM Student");
            
            while (rs.next());
            {
                int id = rs.getInt("studentID");
                String className = rs.getString("className");
                int classID = rs.getInt("classID");
                
                SchoolClass.add(new Schoolclass(id,className,classID));
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return students;

}
    public List <SchoolClass> getAllClasses() throws  SQLException
            
    { 
        List <SchoolClass> SchoolClasses = new ArrayList<>();
        
        try (Connection con = ds.getConnection())
        {
            Statement statement = con.createStatement();
            
            ResultSet rs = statement.executeQuery("SELECT* FROM SchoolClass");
            
            while (rs.next());
            {
                int id = rs.getInt("studentID");
                int classID = rs.getInt("classID");
                String className = rs.getString("className");
                int teacherid = rs.getInt("teacherID");
                
                SchoolClass.add(new SchoolClass(id,className,classID,teacherid));
                
            }
    }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    return SchoolClasses;
}
    
 public SchoolClass getSchoolClass (int id)


{
SchoolClass schoolclassToGet = null; 
try (Connection con = ds.getConnection())
{
PreparedStatement pstmt = con.prepareStatement("SELECT* FROM SchoolClass WHERE classID = (?)");
pstmt.setInt(1, id);

ResultSet rs = pstmt.executeQuery();

while (rs.next());
{
int classID = rs.getInt("classID");

schoolclassToGet = new SchoolClass(classID);
}
}
catch (Exception e)
{
e.printStackTrace();
}
return schoolclassToGet;
}
}

