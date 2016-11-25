/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mes;

/**
 *
 * @author Utilizador
 */
public class Database {
    
    private boolean isReady = false;
    
    /**
     * 
     * @return 
     */
    public boolean isReady()
    {
        return isReady;
    }
    
    /**
     * 
     */
    public void initDatabase()
    {
        
        if (testDatabase())
            isReady = true;
        else 
        {
            isReady = false;
            System.out.println("Database communication error. (initDatabase)");
        }
    }
    
    private boolean testDatabase()
    {
        
        // if response to SQL Query is received
        if(true)
            return true;
      
        else 
            return false;
    }
    
}
