/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendenceautomation.BE;

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

    public Teacher(int id, String name, String email, String teacherLogin, String teacherPassword)
    {
        super(id, name, email);
        
        this.teacherLogin = new SimpleStringProperty();
        this.teacherPassword = new SimpleStringProperty();
        this.teacherLogin.set(teacherLogin);
        this.teacherPassword.set(teacherPassword);
    }

    public StringProperty getTeacherLogin()
    {
        return teacherLogin;
    }

    public StringProperty getTeacherPassword()
    {
        return teacherPassword;
    }

   
}
