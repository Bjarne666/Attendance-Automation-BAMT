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
     * @param studentToGet
     * @return
     */
    public Student getStudent (Student studentToGet);
    
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
     * @param teacherToGet
     * @return 
     */
    public Teacher getTeacher (Teacher teacherToGet);
    
    /**
     *
     * @param chosenClass
     * @return
     */
    public List<Student> getStudentsInClass (SchoolClass chosenClass);
   
    /**
     * 
     * @param student
     * @return 
     */
    public List<Attendance> getAttendance (Student student);
}
