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

    private int id;
    private final StringProperty name;
    private final StringProperty email;

    public Person(int id, String name, String email)
    {
        this.id = id;
        this.name = new SimpleStringProperty();
        this.email = new SimpleStringProperty();
        this.name.set(name);
        this.email.set(email);
    }

    public int getId()
    {
        return id;
    }

    public StringProperty getName()
    {
        return name;
    }

    public StringProperty getEmail()
    {
        return email;
    }

    public void setName(String newName)
    {
        name.set(newName);
    }
    
    public void setEmail(String newEmail)
    {
        email.set(newEmail);
    }
    
    public void setId(int newId)
    {
        this.id = id;
    }
            
}
