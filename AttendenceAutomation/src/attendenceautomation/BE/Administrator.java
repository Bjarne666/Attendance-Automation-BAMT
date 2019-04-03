/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendenceautomation.BE;

/**
 *
 * @author Asvør
 */
public class Administrator extends Person
{
    
    public Administrator(int id, String name, String email, String password)
    {
        super(id, name, email, password);
        setIsAnAdmin();
    }
    
    public void setIsAnAdmin()
    {
        super.setIsAUser(3);
    }
}
