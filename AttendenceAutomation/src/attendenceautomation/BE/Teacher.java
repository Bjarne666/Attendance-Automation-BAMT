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
 private final StringProperty teacherLogin;
 private final StringProperty teacherPassword;
 private ArrayList<Class> schoolClass;

    public Teacher(int id, String name, String email, String teacherLogin, String teacherPassword)
    {
        super(id, name, email);
        
        this.teacherLogin = new SimpleStringProperty();
        this.teacherPassword = new SimpleStringProperty();
        this.teacherLogin.set(teacherLogin);
        this.teacherPassword.set(teacherPassword);
    }

    public ArrayList<Class> getSchoolClass()
    {
        return schoolClass;
    }

    public StringProperty getTeacherLogin()
    {
        return teacherLogin;
    }

    public StringProperty getTeacherPassword()
    {
        return teacherPassword;
    }
    
    public void setTeacherPassword(String newPassword)
    {
        teacherPassword.set(newPassword);
    }
    public void setTeacherLogin(String newLogin)
    {
        teacherPassword.set(newLogin);
    }
    public void addClass(Class newClass)
    {
        schoolClass.add(newClass);
    }
    
   

   
}
