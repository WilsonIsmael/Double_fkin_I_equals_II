/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mes;

import java.util.*;

public class Manager
{
    
        
    private PriorityQueue<String> taskQueue;
    private int type;
    
    
    /**
     * Construct
     * 
     * @param managerType 
     * "Communication" - creates a communication manager
     * "Data" - creates a data manager
     * "Task" - creates a task manager
     */
    public Manager(String managerType)
    {
         if (managerType == "Communication")
             type = 0;
         else if (managerType == "Data")
             type = 1;
         else if (managerType == "Task")
             type = 2;
         else
             System.out.println("Manager type is not recognized");
    }

    /**
     * 
     * @param args 
     * args[0] - UDP port
     * args[1] - UDP address
     * args[2] - Modbus port
     * args[3] - Modbus address
     *
     */
    public static void main (String[] args)
    {
        Manager communicationManager, dataManager, taskManager;
        
        // creates manager objects
        communicationManager = new Manager("Communication");
        dataManager = new Manager("Data");
        taskManager = new Manager("Task");
        
        // creates protocol objects (UDP and Modbus)
        Protocol udpServer, modbusMaster;
        udpServer = new Protocol("UDP");
        modbusMaster = new Protocol("Modbus");
        
        // assigns addresses to protocols
        String udpAddress = args[1];
        String modbusAddress = args[3];
        
        // assigns port values to protocols
        int udpPort = Integer.parseInt(args[0]);
        int modbusPort = Integer.parseInt(args[2]);
        
        // initializes protocol parameters
        udpServer.initProtocol(udpAddress, udpPort);
        modbusMaster.initProtocol(modbusAddress, modbusPort);
        
        //starts protocol
        udpServer.startProtocol();
        modbusMaster.startProtocol();
        
        // creates priorityQueue
        communicationManager.createQueue();
        
        // creates a factory object
        Factory simulatedFactory = new Factory();
        
        // creates a database object
        Database db = new Database();
        
        // run protocols
        udpServer.startProtocol();
        modbusMaster.startProtocol();
        
        // initializes factory 
        simulatedFactory.initFactory();
        
        // checks if factory is ready
        if((simulatedFactory.isReady()));
        
        // initializes database
        db.initDatabase();
        
        // checks if database is ready
        if(db.isReady());
        
        
    }
    
    /**
     * 
     */
    public void createQueue()
    {
        taskQueue = new PriorityQueue(); 
    }
    
 }