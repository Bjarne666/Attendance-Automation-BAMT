/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendenceautomation.BLL;

import attendenceautomation.BE.SchoolClass;
import attendenceautomation.BE.Student;
import attendenceautomation.BE.Teacher;
import java.util.List;

/**
 *
 * @author kokus
 */
public interface AttendanceInterface
{

   /**
     *
     * @return
     */
    public List<Student> getAllStudents();
    
    /**
     *
     * @return
     */
    public List<SchoolClass> getAllClasses();
    
    /**
     *
     * @return
     */
    public List<Teacher> getAllTeachers();

    /**
     *
     * @param chosenClass
     * @return
     */
    public List<Student> getStudentsInClass (SchoolClass chosenClass);
   
    /**
     *
     * @param chosenStudent
     * @return
     */
//    public Student getStudent(Student chosenStudent);
}
