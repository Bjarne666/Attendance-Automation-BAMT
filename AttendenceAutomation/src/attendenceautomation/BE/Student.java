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
public class Student extends Person
{
   private final StringProperty studentLogin;
   private final StringProperty studentPassword;

    public Student(int id, String name, String email, String studentLogin, String studentPassword)
    {
        super(id, name, email);
        this.studentLogin = new SimpleStringProperty();
        this.studentPassword = new SimpleStringProperty();
        this.studentLogin.set(studentLogin);
        this.studentPassword.set(studentPassword);
    }

    public StringProperty getStudentLogin()
    {
        return studentLogin;
    }

    public StringProperty getStudentPassword()
    {
        return studentPassword;
    }
    
    
   
}
