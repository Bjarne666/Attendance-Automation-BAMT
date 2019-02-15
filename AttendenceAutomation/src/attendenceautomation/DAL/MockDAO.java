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
public class MockDAO implements AADALInterface
{
    
    @Override
    public List<Student> getAllStudents()
    {
        List<Student> students = new ArrayList<>();
        
        Student s1 = new Student("Jens","jens@easv365.com","");
        Student s2 = new Student("Peter","peter@easv365.com","");
        Student s3 = new Student("Jørgen","jørgen@easv365.com","");
        Student s4 = new Student("Birger","birger@easv365.com","");
        
        students.add(s1);
        students.add(s2);
        students.add(s3);
        students.add(s4);
    return  students;
    }

    @Override
    public List<SchoolClass> getAllClasses()
    {
        List<SchoolClass> classes = new ArrayList<>();
        SchoolClass c1 = new SchoolClass("CS2018A");
        SchoolClass c2 = new SchoolClass("CS2018B");
        
        classes.add(c1);
        classes.add(c2);
        return classes;
    }

    @Override
    public List<Teacher> getAllTeachers()
    {
     List<Teacher> teachers = new ArrayList<>();
     Teacher t1 = new Teacher("Peter", "peter@easv365.com", "");
     
     teachers.add(t1);
     return teachers;
    }

    @Override
    public List<Student> getStudentsInClass(SchoolClass chosenClass)
    {
        return chosenClass.getStudents();
    }

//    @Override
//    public Student getStudent(Student chosenStudent)
//    {
//        
//    }

  
    
}
