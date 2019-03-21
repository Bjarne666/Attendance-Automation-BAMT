/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendenceautomation.BE;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author kokus
 */
public class Student extends Person
{       
// as we currently have no way to calculate absence this is only impeding our
// efforts to make a functioning login.
//    private String absence;

    private List<Attendance> attendance;
    
    public Student(int id, String name, String email, String password)
    {
        super(id, name, email, password);
        attendance = new ArrayList<>();
        setIsAStudent();
//        this.absence = absence;
    }

    public void setIsAStudent()
    {
        super.setIsAStudent(true);
    }

    public void addAttendance(Date currentDate, boolean present)
    {
        attendance.add(new Attendance(currentDate, present));
    }
    
    public List<Attendance> getAttendance()
    {
        return attendance;
    }

//    public String getAbsence()
//    {
//        return absence;
//    }
    
    
    
    
}
