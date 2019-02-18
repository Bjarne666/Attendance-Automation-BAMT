/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendenceautomation.BE;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author kokus
 */
public class Student extends Person
{
//   private final StringProperty studentLogin;

    private final StringProperty studentPassword;
    private List<Attendance> attendance;

    public Student(String name, String email, String studentPassword)
    {
        super(name, email);
//        this.studentLogin = new SimpleStringProperty();
        this.studentPassword = new SimpleStringProperty();
//        this.studentLogin.set(studentLogin);
        this.studentPassword.set(studentPassword);
        
        attendance = new ArrayList<>();
    }

//    public StringProperty getStudentLogin()
//    {
//        return studentLogin;
//    }
    public StringProperty getStudentPassword()
    {
        return studentPassword;
    }
    
    public void addAttendance(Calendar currentDate, boolean present)
    {
        attendance.add(new Attendance(currentDate, present));
    }
    
    public List<Attendance> getAttendance()
    {
        return attendance;
    }
    
}
