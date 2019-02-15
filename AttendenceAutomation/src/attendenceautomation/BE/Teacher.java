/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendenceautomation.BE;

import java.util.ArrayList;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author kokus
 */
public class Teacher extends Person
{
// private final StringProperty teacherLogin;
 private final StringProperty teacherPassword;
 private ArrayList<SchoolClass> schoolClass;

    public Teacher(String name, String email, String teacherPassword)
    {
        super(name, email);
        
//        this.teacherLogin = new SimpleStringProperty();
        this.teacherPassword = new SimpleStringProperty();
//        this.teacherLogin.set(teacherLogin);
        this.teacherPassword.set(teacherPassword);
    }

    public ArrayList<SchoolClass> getSchoolClass()
    {
        return schoolClass;
    }

//    public StringProperty getTeacherLogin()
//    {
//        return teacherLogin;
//    }

    public StringProperty getTeacherPassword()
    {
        return teacherPassword;
    }
    
    public void setTeacherPassword(String newPassword)
    {
        teacherPassword.set(newPassword);
    }
//    public void setTeacherLogin(String newLogin)
//    {
//        teacherLogin.set(newLogin);
//    }
    public void addClass(SchoolClass newClass)
    {
        schoolClass.add(newClass);
    }
    
   

   
}
