/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendenceautomation.BE;

import java.util.ArrayList;

/**
 *
 * @author kokus
 */
public class Teacher extends Person
{
 private ArrayList<SchoolClass> schoolClass;

    public Teacher(int id, String name, String email, String password)
    {
        super(id, name, email, password);
        
    }

    public ArrayList<SchoolClass> getSchoolClass()
    {
        return schoolClass;
    }

    public void addClass(SchoolClass newClass)
    {
        schoolClass.add(newClass);
    }
    
   

   
}
