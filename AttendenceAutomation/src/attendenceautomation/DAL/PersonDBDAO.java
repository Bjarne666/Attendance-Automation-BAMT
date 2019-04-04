/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendenceautomation.DAL;

import attendenceautomation.BE.Administrator;
import attendenceautomation.BE.Attendance;
import attendenceautomation.BE.Person;
import attendenceautomation.BE.SchoolClass;
import attendenceautomation.BE.Student;
import attendenceautomation.BE.Teacher;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javafx.scene.control.Alert;

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

    public List<Student> getAllStudents()
    {
        List<Student> students = new ArrayList<>();

        try (Connection con = ds.getConnection())
        {
            Statement statement = con.createStatement();

            ResultSet rs = statement.executeQuery("SELECT firstName +' '+ lastName AS name, * FROM Person"
                    + " INNER JOIN Student ON Person.id = Student.studentID ");

            while (rs.next())
            {
                int id = rs.getInt("studentID");
//                String firstName = rs.getString("firstName");
//                String lastName = rs.getString("lastName");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String password = rs.getString("password");

                students.add(new Student(id, name, email, password));
            }

        } catch (Exception e)
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
            PreparedStatement pstmt = con.prepareStatement("SELECT firstName + ' ' + lastName AS name, * FROM Person INNER JOIN ON Person.id = Student.studentID WHERE StudentID = (?)");
            pstmt.setInt(1, id);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next())
            {
                id = rs.getInt("studentID");
//                String firstName = rs.getString("firstName");
//                String lastName = rs.getString("lastName");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String password = rs.getString("password");

                studentToGet = new Student(id, name, email, password);
            }

        } catch (Exception e)
        {
            e.printStackTrace();
        }

        return studentToGet;
    }

    public Teacher getTeacher(int id)
    {
        Teacher teacherToGet = null;

        try (Connection con = ds.getConnection())
        {
            PreparedStatement pstmt = con.prepareStatement("SELECT firstName + ' ' + lastName AS name, * FROM Person INNER JOIN ON Person.id = Teaher.teacherID WHERE teacherID = (?)");
            pstmt.setInt(1, id);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next())
            {
                id = rs.getInt("teacherID");
//                String firstName = rs.getString("firstName");
//                String lastName = rs.getString("lastName");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String password = rs.getString("password");

                teacherToGet = new Teacher(id, name, email, password);
            }

        } catch (Exception e)
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

            ResultSet rs = statement.executeQuery("SELECT firstName + ' ' + lastName AS name, * FROM Person INNER JOIN Teacher ON Person.id = Teacher.teacherID");

            while (rs.next())
            {
                int id = rs.getInt("ID");
//                String firstName = rs.getString("firstName");
//                String lastName = rs.getString("lastName");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String password = rs.getString("password");

                teachers.add(new Teacher(id, name, email, password));
            }

        } catch (Exception e)
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

                attendance.add(new Attendance(date, present));
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        return attendance;
    }

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

    public Person login(String email, String password) throws SQLServerException, SQLException
    {
        Person personToLogin = null;

        try (Connection con = ds.getConnection())
        {
            PreparedStatement pstmt = con.prepareStatement("SELECT firstName + ' ' + lastName AS name , * FROM Person"
                    + " INNER JOIN Teacher ON Teacher.TeacherID = Person.id"
                    + " WHERE email = (?) AND password = (?)");
            pstmt.setString(1, email);
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next())
            {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                email = rs.getString("email");
                password = rs.getString("password");

                personToLogin = new Teacher(id, name, email, password);

            }
        }
        try (Connection con = ds.getConnection())
        {
            PreparedStatement pstmt = con.prepareStatement("SELECT firstName + ' ' +lastName AS name, * FROM Person "
                    + " INNER JOIN Student ON Student.studentID = Person.id"
                    + " WHERE email = (?) AND password = (?)");
            pstmt.setString(1, email);
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next())
            {
                int id = rs.getInt("id");
                email = rs.getString("email");
                password = rs.getString("password");
                String name = rs.getString("name");

                personToLogin = new Student(id, name, email, password);
            }
        }
        try (Connection con = ds.getConnection())
        {
            PreparedStatement pstmt = con.prepareStatement("SELECT firstName + ' ' +lastName AS name, * FROM Person "
                    + " INNER JOIN Admin ON Admin.adminID = Person.id"
                    + " WHERE email = (?) AND password = (?)");
            pstmt.setString(1, email);
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next())
            {
                int id = rs.getInt("id");
                email = rs.getString("email");
                password = rs.getString("password");
                String name = rs.getString("name");

                personToLogin = new Administrator(id, name, email, password);
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        return personToLogin;
    }

    public List<Attendance> setUpBarChart(int id)
    {
        List<Attendance> attendance = new ArrayList<>();

        try (Connection con = ds.getConnection())
        {
            PreparedStatement pstmt = con.prepareStatement("SELECT * FROM Attendance "
                    + "INNER JOIN Student on Attendance.studentID = Student.studentID "
                    + "WHERE Student.studentID = (?) AND Attendance.isPresent = (?) ");
            pstmt.setInt(1, id);
            pstmt.setBoolean(2, false);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next())
            {
                Date date = rs.getDate("date");
                boolean isPresent = rs.getBoolean("isPresent");

                attendance.add(new Attendance(date, isPresent));
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        return attendance;
    }

   
    public List<Attendance> getStudentPresentPieChartData(int id)
    {
        List<Attendance> attendancePresent = new ArrayList<>();

        try (Connection con = ds.getConnection())
        {
            PreparedStatement pstmt = con.prepareStatement("SELECT * FROM Attendance "
                    + "INNER JOIN Student on Attendance.studentID = Student.studentID "
                    + "WHERE Student.studentID = (?) AND isPresent = (?) ");
            pstmt.setInt(1, id);
            pstmt.setBoolean(2, true);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next())
            {
                Date date = rs.getDate("date");
                boolean isPresent = rs.getBoolean("isPresent");

                attendancePresent.add(new Attendance(date, isPresent));
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        return attendancePresent;
    }

    public String getStudentClass(int id)
    {
        String nameString = new String();
        try (Connection con = ds.getConnection())
        {
            PreparedStatement pstmt = con.prepareStatement("SELECT className FROM SchoolClass"
                    + " INNER JOIN Student ON SchoolClass.classID = Student.classID"
                    + " WHERE Student.studentID = (?)");
            pstmt.setInt(1, id);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next())
            {
                nameString = rs.getString("className");
            }
        } catch (Exception e)
        {
        }
        return nameString;
    }

    public void deleteStudent(Student studentToDelete)
    {
        try (Connection con = ds.getConnection())
        {
            PreparedStatement pstmt = con.prepareStatement("DELETE FROM Person WHERE ID = (?) ");
            pstmt.setInt(1, studentToDelete.getId());
            pstmt.execute();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void deleteTeacher(Teacher teacherToDelete)
    {
        try (Connection con = ds.getConnection())
        {
            PreparedStatement pstmt = con.prepareStatement("DELETE FROM Person WHERE ID = (?) ");
            pstmt.setInt(1, teacherToDelete.getId());
            pstmt.execute();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void addStudent(Student studentToAdd, SchoolClass schoolClass)
    {
        String name = studentToAdd.getName();
        String[] splitName = name.split(" ");
        String firstName = splitName[0];
        String lastName = splitName[1];
        String email = studentToAdd.getEmail();
        String password = studentToAdd.getPassword();

        try (Connection con = ds.getConnection())
        {
            PreparedStatement pstmt = con.prepareStatement("INSERT INTO Person VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            PreparedStatement pstmt1 = con.prepareStatement("INSERT INTO Student VALUES (?, ?)");

            pstmt.setInt(1, studentToAdd.getId());

            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.setString(3, email);
            pstmt.setString(4, password);
            pstmt.execute();

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys())
            {
                if (generatedKeys.next())
                {
                    studentToAdd.setId(generatedKeys.getInt(1));
                }
            } catch (Exception e)
            {
            }
            pstmt1.setInt(1, studentToAdd.getId());
            pstmt1.setInt(2, schoolClass.getId());
            pstmt1.execute();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void addTeacher(Teacher teacherToAdd)
    {
        String name = teacherToAdd.getName();
        String[] splitName = name.split(" ");
        String firstName = splitName[0];
        String lastName = splitName[1];
        String email = teacherToAdd.getEmail();
        String password = teacherToAdd.getPassword();

        try (Connection con = ds.getConnection())
        {
            PreparedStatement pstmt = con.prepareStatement("INSERT INTO Person VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            PreparedStatement pstmt1 = con.prepareStatement("INSERT INTO Teacher VALUES (?)");
            pstmt.setInt(1, teacherToAdd.getId());

            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.setString(3, email);
            pstmt.setString(4, password);
            pstmt.execute();

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys())
            {
                if (generatedKeys.next())
                {
                    teacherToAdd.setId(generatedKeys.getInt(1));
                }

            } catch (Exception e)
            {
            }

            pstmt1.setInt(1, teacherToAdd.getId());
            pstmt1.execute();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void editPerson(String fName, String lName, String email, int id)
    {
        try (Connection con = ds.getConnection())
        {
            PreparedStatement pstmt = con.prepareStatement("UPDATE Person set firstName = (?), lastName = (?), email = (?) WHERE id = (?)");
            pstmt.setString(1, fName);
            pstmt.setString(2, lName);
            pstmt.setString(3, email);
            pstmt.setInt(4, id);

            pstmt.execute();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void moveStudentToNewClass(SchoolClass chosenClass, Student studentToMove)
    {
        try (Connection con = ds.getConnection())
        {
            PreparedStatement pstmt = con.prepareStatement("UPDATE Student SET classID = (?) WHERE studentID = (?)");
            pstmt.setInt(1, chosenClass.getId());
            pstmt.setInt(2, studentToMove.getId());
            pstmt.execute();
        } catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    public List<Attendance> getStudentPieChartAbsenceData(int id)
    {
        List<Attendance> attendanceAbsence = new ArrayList<>();
        try (Connection con = ds.getConnection())
        {
            PreparedStatement pstmt = con.prepareStatement("SELECT * FROM Attendance "
                    + "INNER JOIN Student on Attendance.studentID = Student.studentID "
                    + "WHERE Student.studentID = (?) AND isPresent = (?) ");
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
            e.printStackTrace();
        }
        
        return attendanceAbsence;
    }
}
