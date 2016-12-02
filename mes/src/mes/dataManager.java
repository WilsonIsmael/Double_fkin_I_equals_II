/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mes;

/**
 *
 * @author i004
 */
public class dataManager 
{
    private String id;
    private int status;
    
      
        // creates a database object
        Database db = new Database();
        
        if(db.initDatabase("org.postgresql.Driver", 
                "jdbc:postgresql://dbm.fe.up.pt/sinf16g67"))
        {
            if(db.setCredentials("sinf16g67","manueljoaofraga"))
            {
                if(db.openConnection())
                {
                    db.executeQuery("CREATE TABLE mes.TEST();");
                }
            }
        }
        
           // closes the connection
        if(db.closeConnection());  
}
