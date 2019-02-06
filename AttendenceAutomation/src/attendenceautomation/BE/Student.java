/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendenceautomation.BE;

/**
 *
 * @author kokus
 */
public class Student extends Person
{
   private String studentLogin;
   private String studentPassword;

    public String getStudentLogin()
    {
        return studentLogin;
    }

    public void setStudentLogin(String studentLogin)
    {
        this.studentLogin = studentLogin;
    }

    public Student(String studentLogin, String studentPassword)
    {
        this.studentLogin = studentLogin;
        this.studentPassword = studentPassword;
    }
}
