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
            PreparedStatement pstmt = con.prepareStatement("SELECT * FROM Student WHERE classID = (?)");
            pstmt.setInt(1, classToGet.getId());

            ResultSet rs = pstmt.executeQuery();

            while (rs.next());
            {
                String className = rs.getString("className");

                students.add(new Student(0, className, className, className, className));
            }
        } catch (Exception e)
        {
            e.printStackTrace();
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

            while (rs.next());
            {
                String className = rs.getString("className");

                schoolClasses.add(new SchoolClass(className));

            }
        } catch (Exception e)
        {
            e.printStackTrace();
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

            while (rs.next());
            {
                String className = rs.getString("className");

                schoolclassToGet = new SchoolClass(className);
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return schoolclassToGet;
    }
}
