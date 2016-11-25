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
public class Factory {
    
    private boolean isReady = false;
    
  
    public boolean isReady()
    {
        return isReady;
    }

    public void initFactory()
    {
        // updates the status
        isReady = true;
    } 
}

