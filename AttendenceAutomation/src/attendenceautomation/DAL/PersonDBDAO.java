/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendenceautomation.DAL;

import java.io.IOException;

/**
 *
 * @author Theis
 */
public class PersonDBDAO
{
    
    DbConnectionProvider ds;

    public PersonDBDAO() throws IOException
    {
        ds = new DbConnectionProvider();
    }
}
