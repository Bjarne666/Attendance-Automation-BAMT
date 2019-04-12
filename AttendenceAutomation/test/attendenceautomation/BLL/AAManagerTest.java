/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendenceautomation.BLL;

import attendenceautomation.BE.Attendance;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.junit.AfterClass;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Theis
 */
public class AAManagerTest
{
    
    public AAManagerTest()
    {
    }
    
    @BeforeClass
    public static void setUpClass()
    {
    }
    
    @AfterClass
    public static void tearDownClass()
    {
    }

 
    /**
     * Test of countPresence method, of class AAManager.
     */
    @Test
    public void testCountNoPresence() throws IOException
    {
      List<Attendance> attendances = new ArrayList<>();
      AAManager aaManager = new AAManager(null);
      int count = aaManager.countPresence(attendances);
        assertEquals(0, count);
    }
    /**
     * Test of countPresence method, of class AAManager.
     */
    @Test
    public void testCountPresence() throws IOException
    {
      List<Attendance> attendances = new ArrayList<>();
      Calendar cal = Calendar.getInstance();
      Date currentDate = cal.getTime();
      
      attendances.add(new Attendance(currentDate, true));
      attendances.add(new Attendance(currentDate, true));
      attendances.add(new Attendance(currentDate, true));
      attendances.add(new Attendance(currentDate, true));
      attendances.add(new Attendance(currentDate, true));
      AAManager aaManager = new AAManager(null);
      int count = aaManager.countPresence(attendances);
        assertEquals(5, count);
    }
    
}
