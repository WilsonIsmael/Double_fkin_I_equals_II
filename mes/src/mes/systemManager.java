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
    private String id;
    private PriorityQueue taskQueue;
    private int status;
    
     public static void main (String[] args) throws SQLException
     {    
           // creates protocol objects (UDP and Modbus)
        Protocol udpServer, modbusMaster;
        udpServer = new Protocol(0);
        modbusMaster = new Protocol(1);
        
        // assigns addresses to protocols
        String udpAddress = "";
        String modbusAddress = "";
        
        // assigns port values to protocols
        int udpPort = 0;
        int modbusPort = 0;
        
        // initializes protocol parameters
        udpServer.initProtocol(udpAddress, udpPort);
        System.out.println("Sucesso!!!/n");
        modbusMaster.initProtocol(modbusAddress, modbusPort);
      
         communicationManager udpManager, modbusPLCManager, modbusFactoryManager;
         udpManager = new communicationManager("", 0);
         modbusPLCManager = new communicationManager("", 0);
         modbusFactoryManager = new communicationManager("", 0);
        
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
