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
public class Teacher extends Person
{
 private String teacherLogin;
 private String teacherPassword;

    public Teacher(int id, String name, String email, String teacherLogin, String teacherPassword)
    {
        super(id, name, email);
        this.teacherLogin = teacherLogin;
        this.teacherPassword = teacherPassword;
    }

    public String getTeacherLogin()
    {
        return teacherLogin;
    }
    
    public String getTeacherPassword()
    {
        return teacherPassword;
    }
}
