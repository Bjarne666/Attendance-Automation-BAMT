/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendenceautomation.BE;

import attendenceautomation.UTIL.DateConverter;
import java.util.Calendar;
import java.util.Date;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author kokus
 */
public class Attendance
{

    private Date currentDate;
    private BooleanProperty present;

    public Attendance(Date currentDate, boolean present)
    {

        this.present = new SimpleBooleanProperty();

        this.present.set(present);

        this.currentDate = currentDate;
    }

    public Date getCurrentDate()
    {
        return currentDate;
    }

    public boolean getPresent()
    {
        return present.get();
    }

    
//    public String toString()
//    {
//        return "At "+ currentDate + "your attendance were recorded as: " + present.get();
//    }
    
}
