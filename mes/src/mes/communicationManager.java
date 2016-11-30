/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mes;

import java.sql.SQLException;
import java.util.*;

public class communicationManager
{
    private String id;
    private int status;
    private int port;
    private String address;
    
    public communicationManager(String protocolAddress, int protocolPort)
    {
        if ("".equals(protocolAddress))
            System.out.println("No address given.");
        else if (protocolPort == 0)
            System.out.println("No port given.");
        else
        {
            port = protocolPort;
            address = protocolAddress;
        }     
    }
    
    
    /**
     * 
     */
    public void createQueue()
    {
        taskQueue = new PriorityQueue(); 
    }
    
}
