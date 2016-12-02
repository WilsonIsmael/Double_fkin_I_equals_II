/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mes;

import java.sql.SQLException;
import java.util.*;

/**
 *
 * @author i004
 */
public class systemManager 
{    
    private String ID;
    private PriorityQueue taskQueue;
    private int status = 0;
    
     public static void main (String[] args) throws SQLException
     {    
        communicationManager udpManager, modbusPLCManager, modbusFactoryManager;
        // created object to communicate with ERP
        udpManager = new communicationManager("", 0);
        // created object to communicate with PLC
        modbusPLCManager = new communicationManager("", 0);
        // created object to communicate with Factory
        modbusFactoryManager = new communicationManager("", 0);
         
        // creates protocol objects - UDP and Modbus
        Protocol udpProtocolToERP, modbusProtocolToPLC, modbusProtocolToFactory;
        
        udpProtocolToERP = udpManager.createProtocol("UDP");
        // error creating protocol to ERP
        if (udpProtocolToERP == null)
        {
            //TO DO
        }
        
        modbusProtocolToPLC = modbusPLCManager.createProtocol("Modbus");
        // error creating protocol to PLC
        if (modbusProtocolToPLC == null)
        {
            //TO DO
        }
        
        modbusProtocolToFactory = modbusFactoryManager.createProtocol("Modbus");
        // error creating protocol to Factory
        if (modbusProtocolToFactory == null)
        {
            //TO DO
        }
        
        
        // initializes protocol parameters
        if(udpManager.initProtocol(udpProtocolToERP) == false)
        {
            //TO DO
        }
        if(modbusPLCManager.initProtocol(modbusProtocolToPLC) == false)
        {
            //TO DO
        }
        if(modbusFactoryManager.initProtocol(modbusProtocolToFactory))
        {
            //TO DO
        }
      
        
        // run protocols
        udpServer.startProtocol();
        modbusMaster.startProtocol();
         
  
        // creates a factory object
        Factory simulatedFactory = new Factory();

        
        // initializes factory 
        simulatedFactory.initFactory();
        
        // checks if factory is ready
        //if((simulatedFactory.isReady));
  
 }
   
}
