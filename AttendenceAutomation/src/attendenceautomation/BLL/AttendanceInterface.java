/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendenceautomation.BLL;

import attendenceautomation.BE.Student;
import attendenceautomation.BE.Teacher;

/**
 *
 * @author kokus
 */
public interface AttendanceInterface
{

    /**
     *
     * @param studentsToGet
     * @return
     */
    public Student getAllStudents(Student studentsToGet);
    
    /**
     *
     * @param classesToGet
     * @return
     */
    public Class getAllClasses(Class classesToGet);
    
    public Teacher getAllTeachers(Teacher teachersToGet);
}
