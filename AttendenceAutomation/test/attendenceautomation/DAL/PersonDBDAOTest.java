/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendenceautomation.DAL;

import attendenceautomation.BE.Attendance;
import attendenceautomation.BE.SchoolClass;
import attendenceautomation.BE.Student;
import attendenceautomation.BE.Teacher;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Theis
 */
public class PersonDBDAOTest
{
    private PersonDBDAO db;
    public PersonDBDAOTest() throws IOException
    {
     db = new PersonDBDAO();
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
     * Test of getAllStudents method, of class PersonDBDAO.
     */
    @Test
    public void testGetAllStudents() throws IOException
    {   
        List<Student> list = new ArrayList<>();
        list = db.getAllStudents();
        assertTrue(list.size() > 0);
    
    }

    /**
     * Test of getAllTeachers method, of class PersonDBDAO.
     */
    @Test
    public void testGetAllTeachers() throws Exception
    {
        List<Teacher> list = new ArrayList<>();
        list = db.getAllTeachers();
        assertTrue(list.size() > 0);       
        
    }

    /**
     * Test of getStudentClass method, of class PersonDBDAO.
     */
    @Test
    public void testGetStudentClass()
    {
        Student birger = new Student(1, "Birger Jensen", "birger@easv365.dk", "Birger365");
        assertTrue(!db.getStudentClass(birger.getId()).isEmpty());
    }
 
}
