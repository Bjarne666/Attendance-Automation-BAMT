/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendenceautomation.BE;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author kokus
 */
public class Student extends Person
{
    private String absence;

    private List<Attendance> attendance;

    public Student(String name, String email, String password, String absence)
    {
        super(name, email, password);
        
        attendance = new ArrayList<>();
        
        this.absence = absence;
    }

    public void addAttendance(Calendar currentDate, boolean present)
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
    
    
    
    
}
