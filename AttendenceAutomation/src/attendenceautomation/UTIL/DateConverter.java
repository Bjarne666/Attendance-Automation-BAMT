/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendenceautomation.UTIL;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author kokus
 */
public class DateConverter
{

    private Calendar currentDate;

    public DateConverter(Calendar currentDate)
    {
        this.currentDate = currentDate;
    }
    
 public String convertDate()
    {
        Date date = currentDate.getTime();
        DateFormat dateFormat = new SimpleDateFormat("YYYY-mm-dd");
        String strDate = dateFormat.format(date);
        return strDate;
    }   
}
