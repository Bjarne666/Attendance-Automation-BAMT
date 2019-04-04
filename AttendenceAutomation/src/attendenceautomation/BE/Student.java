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

    private List<Attendance> attendance;
    private String absence;
    public Student(int id, String name, String email, String password)
    {
        super(id, name, email, password);
        attendance = new ArrayList<>();
        setIsAStudent();
        absence = new String();
    }

    public void setIsAStudent()
    {
        super.setIsAUser(1);
    }

    public void addAttendance(Date currentDate, boolean present)
    {
        attendance.add(new Attendance(currentDate, present));
    }
    
    public List<Attendance> getAttendance()
    {
        return attendance;
    }


    public String getAbsence()
    {
        return absence;
    }

    public void setAbsence(String absence)
    {
        this.absence = absence;
    }
    
    
    
    
}
