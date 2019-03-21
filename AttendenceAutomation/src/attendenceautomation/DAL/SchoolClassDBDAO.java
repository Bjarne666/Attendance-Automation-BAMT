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
                    + "INNER JOIN Student a ON Person.id = a.studentID "
                    + "INNER JOIN Student b ON b.classID = (?)");           // aware that this might not work
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

            while(rs.next())
            {
                String className = rs.getString("className");
                int classID = rs.getInt("classID");
                schoolClasses.add(new SchoolClass(classID, className));

            }
        } catch (Exception e)
        {
            e.printStackTrace();
//            Alert warning = new Alert(Alert.AlertType.ERROR, "There's no classes in the database");
//            warning.showAndWait();
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
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return schoolclassToGet;
    }
}
