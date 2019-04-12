/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendenceautomation.UTIL;

import attendenceautomation.BE.Attendance;
import java.time.LocalDate;
import java.util.Calendar;
import java.sql.Date;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * 
 * @author Theis
 */
public class DateConverter
{
    private List<Attendance> attendances;
    private ObservableList<LocalDate> dates;
    private Calendar currentCalendar;
    
    public DateConverter(ObservableList<Attendance> attendances)
    {
        this.dates = FXCollections.observableArrayList();
        this.attendances = attendances;
        convertToLocalDate();
    }
    
    
    
    private void convertToLocalDate()
    {
        for (Attendance attendance : attendances)
        {
            Date date = new Date(attendance.getCurrentDate().getTime());
            LocalDate localDate = date.toLocalDate();                               
            dates.add(localDate);
        }
    }
    

    public ObservableList<LocalDate> getDates()
    {
        return dates;
    }
}
