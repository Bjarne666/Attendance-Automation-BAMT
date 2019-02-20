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
public abstract class Person
{

    //currently not in use
    //private int id;
    private final StringProperty name;
    private final StringProperty email;
    private StringProperty password;
    public Person(String name, String email, String password)
    {
//        this.id = id;
        this.name = new SimpleStringProperty();
        this.email = new SimpleStringProperty();
        this.name.set(name);
        this.email.set(email);
        this.password = new SimpleStringProperty();
        this.password.set(password);
    }

//    public int getId()
//    {
//        return id;
//    }
    public String getName()
    {
        return name.get();
    }

    public String getEmail()
    {
        return email.get();
    }

    public void setName(String newName)
    {
        name.set(newName);
    }

    public void setEmail(String newEmail)
    {
        email.set(newEmail);
    }
    
    public void getPassword(String password)
    {
        this.password.set(password);
    }
    
    public void setPassword(String newPassword)
    {
        password.set(newPassword);
    }
//    public void setId(int newId)
//    {
//        this.id = id;
//    }
    @Override
    public String toString()
    {
        return name + "\t\t " + email;
    }

}
