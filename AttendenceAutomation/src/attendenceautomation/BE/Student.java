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

    public Student(int id, String name, String email, String studentLogin, String studentPassword)
    {
        super(id, name, email);
        this.studentLogin = studentLogin;
        this.studentPassword = studentPassword;
    }
    
    
    public String getStudentLogin()
    {
        return studentLogin;
    }

    public String getStudentpassword()
    {
        return studentPassword;
    }
}
