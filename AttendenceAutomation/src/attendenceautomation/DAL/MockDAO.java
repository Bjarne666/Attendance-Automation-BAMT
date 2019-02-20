/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendenceautomation.DAL;

import attendenceautomation.BE.SchoolClass;
import attendenceautomation.BE.Student;
import attendenceautomation.BE.Teacher;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kokus
 */
public class MockDAO implements AADALInterface {

    //create school classes
    SchoolClass c1 = new SchoolClass("CS2018A");
    SchoolClass c2 = new SchoolClass("CS2018B");

    //create students
    Student s1 = new Student("Jens", "jens@easv365.com", "");
    Student s2 = new Student("Peter", "peter@easv365.com", "");
    Student s3 = new Student("Jørgen", "jørgen@easv365.com", "");
    Student s4 = new Student("Birger", "birger@easv365.com", "");

    //create teacher
    Teacher t1 = new Teacher("Peter", "peter@easv365.com", "");

    public MockDAO() {
    c1.addStudent(s1);
    c1.addStudent(s2);
    c2.addStudent(s3);
    c2.addStudent(s4);
    

    }
    
    
    //adds the mockStudents to a list and returns it
    @Override
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();

        students.add(s1);
        students.add(s2);
        students.add(s3);
        students.add(s4);
        return students;
    }
    
    //adds the students to a class and then adds the classes to a list to return
    @Override
    public List<SchoolClass> getAllClasses() {
        List<SchoolClass> classes = new ArrayList<>();
        
        classes.add(c1);
        classes.add(c2);
        return classes;
    }
    
    //adds classes to the teacher and then adds the teacher to a list to return
    @Override
    public List<Teacher> getAllTeachers() {
        List<Teacher> teachers = new ArrayList<>();
       
        teachers.add(t1);
        return teachers;
    }
    
    // returns all students in a class
    @Override
    public List<Student> getStudentsInClass(SchoolClass chosenClass) {
        return chosenClass.getStudents();
    }

}
//    @Override
//    public Student getStudent(Student chosenStudent)
//    {
//        
//    }

