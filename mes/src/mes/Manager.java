/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mes;

import java.sql.SQLException;
import java.util.Scanner;



public class Manager
{
  
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
        modbusMaster.initProtocol(modbusAddress, modbusPort);
        
        // creates a factory object
        Factory simulatedFactory = new Factory();
        
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
        
        // run protocols
        udpServer.startProtocol();
        modbusMaster.startProtocol();
        
        // initializes factory 
        simulatedFactory.initFactory();
        
        // checks if factory is ready
        //if((simulatedFactory.isReady));
       
    }
    
 }
