/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendenceautomation.DAL;

import attendenceautomation.BE.Attendance;
import attendenceautomation.BE.Person;
import attendenceautomation.BE.SchoolClass;
import attendenceautomation.BE.Student;
import attendenceautomation.BE.Teacher;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kokus
 */
public class MockDAO implements AADALInterface {

    //create school classes
//    SchoolClass c1 = new SchoolClass("CS2018A");
//    SchoolClass c2 = new SchoolClass("CS2018B");

    //create students
//    Student s1 = new Student("Jens", "jens@easv365.com", "", "25%");
//    Student s2 = new Student("birger", "birger@easv365.com", "birger365", "10%"); //current student login
//    Student s3 = new Student("Jørgen", "jørgen@easv365.com", "", "0%");
//    Student s4 = new Student("Per", "per@easv365.com", "", "30%");
//
//    //create teacher
//    Teacher t1 = new Teacher("Peter", "peter@easv365.com", "peter365"); //current teacher login

    public MockDAO() {
//    c1.addStudent(s1);
//    c1.addStudent(s2);
//    c2.addStudent(s3);
//    c2.addStudent(s4);
    

    }
    
    
    //adds the mockStudents to a list and returns it
    @Override
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();

//        students.add(s1);
//        students.add(s2);
//        students.add(s3);
//        students.add(s4);
        return students;
    }
    
    //adds the students to a class and then adds the classes to a list to return
    @Override
    public List<SchoolClass> getAllClasses() {
        List<SchoolClass> classes = new ArrayList<>();
        
//        classes.add(c1);
//        classes.add(c2);
        return classes;
    }
    
    //adds classes to the teacher and then adds the teacher to a list to return
    @Override
    public List<Teacher> getAllTeachers() {
        List<Teacher> teachers = new ArrayList<>();
       
//        teachers.add(t1);
        return teachers;
    }
    
    // returns all students in a class
    @Override
    public List<Student> getStudentsInClass(SchoolClass chosenClass) {
        return chosenClass.getStudents();
    }

    @Override
    public Student getStudent(Student studentToGet)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Teacher getTeacher(Teacher teacherToGet)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Attendance> getAttendance(Student student)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Person login(String email, String password) throws SQLServerException, SQLException
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Attendance> setUpBarChart(int id)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Attendance> getStudentPieChartAbsenceData(int id)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getStudentClass(int id)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

@Override
    public void setAttendance(Attendance attendance, int id)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Attendance> getStudentPresentPieChartData(int id)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools
    }
    public void editAttendance(Attendance attenToEdit, int id)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteStudent(Student studentToDelete)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteTeacher(Teacher teacherToDelete)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteClass(SchoolClass classToDelete)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

//    @Override
//    public void addStudent(Student studentToAdd)
//    {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

    @Override
    public void editSchoolClassName(String className, int id)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addClass(SchoolClass classToAdd)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addStudent(Student studentToAdd, SchoolClass schoolClass)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addTeacher(Teacher teacherToAdd)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void editPerson(String fName, String lName, String email, int id)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void moveStudentToNewClass(SchoolClass chosenClass, Student studentToMove)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void editAttendance(int id, Attendance... attenToEdit)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public double calculateTotalAbsence(int id)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Attendance> getTotalClassPresence(int id)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Attendance> getTotalClassAbsence(int id)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}
