/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendenceautomation.UTIL;

import attendenceautomation.BE.Attendance;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.sql.Date;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Was going to be used to convert dates to a formatted String.
 * However, with the change to database this is not needed.
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
//                    atZone(ZoneId.systemDefault()).toLocalDate();            
            dates.add(localDate);
        }
    }
    
// @Deprecated   
// public String convertDate()
//    {
//        Date date = currentCalendar.getTime();
//        DateFormat dateFormat = new SimpleDateFormat("YYYY-mm-dd");
//        String strDate = dateFormat.format(date);
//        return strDate;
//    }   

    public ObservableList<LocalDate> getDates()
    {
        return dates;
    }
}
