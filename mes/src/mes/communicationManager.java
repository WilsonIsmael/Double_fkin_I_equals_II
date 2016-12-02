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
    private String ID;
    private int status = 0;
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
     * Get communication manager ID
     * @return ID
     */
    public String getID()
    {
        return ID;
    }
    
    /**
     * get communication manager status
     * @return status
     */
    public int getStatus()
    {
        return status;
    }
    
    /**
     * Get protocol port
     * @return port
     */
    public int getPort()
    {
        return port;
    }
    
    /**
     * Get protocol address
     * @return address
     */
    public String getAddress()
    {
        return address;
    }
    
    /**
     * Creates a protocol
     * @param protocolType
     * "UDP" - creates a UDP protocol
     * "Modbus" - creates a Modbus protocol
     * @return boolean
     * true - the protocol was created
     * false - the protocol was not created
     */
    public Protocol createProtocol(String protocolType)
    {
        // checks if protocolType is empty
        if ("".equals(protocolType))
        {
            System.out.println("No protocol defined./n");
            return null;
        }
        // creates an UDP protocol
        else if ("UDP".equals(protocolType))
        {
            Protocol udpProtocol = new Protocol(protocolType);
            return udpProtocol;
        }
        // creates a Modbus protocol
        else if ("Modbus".equals("protocolType"))
        {
            Protocol modbusProtocol = new Protocol("Modbus");
            return modbusProtocol;
        }
        // the given protocol is neither Modbus nor UDP 
        else
        {
            System.out.println("Protocol not recognized./n");
            return null;
        }
    }
    
    
    /**
     * Initializes protocol
     * @param protocolToInit
     * @return boolean
     * true - the protocol was initizlized
     * false - the protocol was not initialized
     */
    public boolean initProtocol(Protocol protocolToInit) 
    {
        // checks if the protocol was not given
        if (protocolToInit == null)
        {
            System.out.println("Missing protocol to initialize./n");
            return false;
        }
        // if the protocol was given
        else
        {
            // error setting the port
            if(protocolToInit.setProtocolPort() == false)
            {
                System.out.println("Error inserting port./n");
                return false;
            }
            // error setting the address
            else if (protocolToInit.setProtocolAddress() == false)
            {
                System.out.println("Error inserting address./n");
                return false;
            }
            // no error
            else
                return true;
        }
    }
    
    
    
}
