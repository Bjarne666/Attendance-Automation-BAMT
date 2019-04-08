/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendenceautomation.DAL;

import attendenceautomation.BE.Attendance;
import attendenceautomation.BE.Student;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javafx.scene.control.Alert;

/**
 *
 * @author Theis
 */
public class AttendanceDBDAO
{
   DbConnectionProvider ds;

    public AttendanceDBDAO() throws IOException
    {
        ds = new DbConnectionProvider();
    }
   
    /**
     * Gets all tuples from the database where the studentID matches the input
     * @param student
     * @return 
     */
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

                attendance.add(new Attendance(date, present));
            }
        } catch (Exception e)
        {
        }

        return attendance;
    }
    
    /**
     * This is a helper method for editAttendance that checks whether there is already
     * data in the database for the choosen date and student
     * @param id
     * @param sqlDate
     * @param con
     * @return 
     */
    public boolean checkIfExistingEntry(int id, java.sql.Date sqlDate, Connection con)
    {
        try
        {
            PreparedStatement pstmt = con.prepareStatement("SELECT * FROM Attendance WHERE date = (?) AND studentID = (?)");
            pstmt.setDate(1, sqlDate);
            pstmt.setInt(2, id);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next())
            {
                return true;

            }
        } catch (Exception e)
        {
        }
        return false;
    }
    
    /**
     * Inserts a the date and state to the datebase if one does not already exists 
     * and updates if it already exists
     * @param id
     * @param attenToEdit 
     */
    public void editAttendance(int id, Attendance... attenToEdit)
    {
        try (Connection con = ds.getConnection())
        {
            PreparedStatement pstmt1 = con.prepareStatement("INSERT INTO Attendance VALUES (?,?,?)");
            PreparedStatement pstmt2 = con.prepareStatement("UPDATE Attendance SET isPresent = (?) WHERE date = (?) AND studentID = (?) ");
            con.setAutoCommit(false);
            for (Attendance attendance : attenToEdit)
            {
                java.sql.Date sqlDate = new java.sql.Date(attendance.getCurrentDate().getTime());

                //Insert statement in case the chosen entry does not exist
                pstmt1.setInt(1, id);
                pstmt1.setDate(2, sqlDate);
                pstmt1.setBoolean(3, attendance.getPresent());

                //Update statement in case the chosen entry does exist
                pstmt2.setBoolean(1, attendance.getPresent());
                pstmt2.setDate(2, sqlDate);
                pstmt2.setInt(3, id);

                //executes search statement
                if (!checkIfExistingEntry(id, sqlDate, con))
                {
                    pstmt1.execute();    //executes insert statement
                }
                if (checkIfExistingEntry(id, sqlDate, con))
                {
                    pstmt2.execute(); //executes update statement
                }
            }

            con.commit();
        } catch (Exception e)
        {
            Alert error = new Alert(Alert.AlertType.ERROR, "Something went wrong in the database");
            error.showAndWait();
        }
    }
    
    /**
     * Sets the attendance for the current day, for the given student
     * @param attendance
     * @param id 
     */
    public void setAttendance(Attendance attendance, int id)
    {
        java.sql.Date sqlDate = new java.sql.Date(attendance.getCurrentDate().getTime());
        try (Connection con = ds.getConnection())
        {
            //removes chosen values if they already exist to avoid duplicates
            PreparedStatement pstmt1 = con.prepareStatement("DELETE FROM Attendance WHERE date = (?) AND studentID = (?)");
            pstmt1.setDate(1, sqlDate);
            pstmt1.setInt(2, id);
            pstmt1.execute();

            PreparedStatement pstmt = con.prepareStatement("INSERT INTO Attendance VALUES (?,?,?)");
            pstmt.setInt(1, id);
            pstmt.setDate(2, sqlDate);
            pstmt.setBoolean(3, attendance.getPresent());

            pstmt.execute();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    /**
     * Gets the total absence of the students in the given School Class
     * @param id
     * @return 
     */
    public List<Attendance> getTotalClassAbsence(int id)
    {
        List<Attendance> attendanceAbsence = new ArrayList<>();

        try (Connection con = ds.getConnection())
        {
            PreparedStatement pstmt = con.prepareStatement("SELECT isPresent, date FROM Attendance "
                    + "INNER JOIN Student ON Attendance.studentID = Student.studentID "
                    + "INNER JOIN SchoolClass ON SchoolClass.classID = Student.classID "
                    + "WHERE SchoolClass.classID = (?) AND isPresent = (?)");
            pstmt.setInt(1, id);
            pstmt.setBoolean(2, false);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next())
            {
                Date date = rs.getDate("date");
                boolean isNotPresent = rs.getBoolean("isPresent");

                attendanceAbsence.add(new Attendance(date, isNotPresent));
            }
        } catch (Exception e)
        {
        }

        return attendanceAbsence;
    }
    
    /**
     * Gets the total presence from all students of the given School Class
     * @param id
     * @return 
     */
    public List<Attendance> getTotalClassPresence(int id)
    {
        List<Attendance> attendancePresence = new ArrayList<>();

        try (Connection con = ds.getConnection())
        {
            PreparedStatement pstmt = con.prepareStatement("SELECT isPresent, date FROM Attendance "
                    + "INNER JOIN Student ON Attendance.studentID = Student.studentID "
                    + "INNER JOIN SchoolClass ON SchoolClass.classID = Student.classID "
                    + "WHERE SchoolClass.classID = (?) AND isPresent = (?)");
            pstmt.setInt(1, id);
            pstmt.setBoolean(2, true);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next())
            {
                Date date = rs.getDate("date");
                boolean isNotPresent = rs.getBoolean("isPresent");

                attendancePresence.add(new Attendance(date, isNotPresent));
            }
        } catch (Exception e)
        {
        }

        return attendancePresence;
    }
    
    /**
     * Calculates the total percentage of absence the chosen student has
     *
     * @param id the chosen student's id
     * @return
     */
    public double calculateTotalAbsence(int id)
    {
        double absence = 0;
        double total = 0;
        try (Connection con = ds.getConnection())
        {
            PreparedStatement pstmt = con.prepareStatement("SELECT COUNT(isPresent) AS total FROM Attendance WHERE studentID = (?)");
            PreparedStatement pstmt2 = con.prepareStatement("SELECT isPresent FROM Attendance WHERE studentID = (?) AND isPresent = (?)");
            pstmt.setInt(1, id);

            pstmt2.setInt(1, id);
            pstmt2.setBoolean(2, false);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next())
            {
                total = rs.getInt("total");
            }

            ResultSet rs2 = pstmt2.executeQuery();
            while (rs2.next())
            {
                absence++;
            }

        } catch (Exception e)
        {
        }
        return (absence / total) * 100;
    }
}
