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

    public Teacher(String teacherLogin, String teacherPassword)
    {
        this.teacherLogin = teacherLogin;
        this.teacherPassword = teacherPassword;
    }

    public String getTeacherLogin()
    {
        return teacherLogin;
    }
}
