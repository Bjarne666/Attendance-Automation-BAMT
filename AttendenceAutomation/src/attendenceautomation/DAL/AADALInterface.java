/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendenceautomation.DAL;

import attendenceautomation.BE.Student;
import attendenceautomation.BE.Teacher;
import java.util.List;

/**
 *
 * @author kokus
 */
public interface AADALInterface
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
    public List<Class> getAllClasses();
    
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
    public List<Student> getStudentsInClass (Class chosenClass);
   
}
