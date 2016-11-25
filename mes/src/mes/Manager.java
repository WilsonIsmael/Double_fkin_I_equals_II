/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mes;

import java.util.Scanner;



public class Manager
{
  
    public static void main (String[] args)
    {
        // creates protocol objects (UDP and Modbus)
        Protocol udpServer, modbusMaster;
        udpServer = new Protocol(0);
        modbusMaster = new Protocol(1);
        
        // assigns addresses to protocols
        String udpAddress = "";
        String modbusAddress = "";
        
        // assigns port values to protocols
        int udpPort = 54321;
        int modbusPort = 0;
        
        // initializes protocol parameters
        udpServer.initProtocol(udpAddress, udpPort);
        modbusMaster.initProtocol(modbusAddress, modbusPort);
        
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
        if((simulatedFactory.isReady));
        
        // initializes database
        db.initDatabase();
        
        // checks if database is ready
        if(db.isReady());
        
        
    }
    
 }