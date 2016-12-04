/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mes;

import java.sql.SQLException;
import java.util.*;
import javax.swing.JOptionPane;
import net.wimpi.modbus.util.BitVector;

/**
 * Classe para gerir o arranque e funcionamento do sistema
 * @author Mário Xavier
 */
public class systemManager 
{    
    private String ID;
    private PriorityQueue taskQueue;
    private int status;
    
     public static void main (String[] args) throws SQLException
     {     
        
        Modbus protocolToPLC;
        UDP protocolToERP;
        // creates UDP protocol object
        //protocolToERP = new Protocol();
        // error creating protocol to ERP
        //if (protocolToERP == null)
        {
            //TO DO
        }
        
        // creates Modbus protocol object
        protocolToPLC = new Modbus();
        // error creating protocol to PLC
        if (protocolToPLC == null)
        {
            //TO DO
        }
        
        if (protocolToPLC.setModbusConnection())
            System.out.println("Modbus connection on.\n");
        else
            System.out.println("Modbus connection failed.\n");

        
        protocolToPLC.openConnection();
        
        
        // creating a bit vector of size 8
        BitVector b = new BitVector(8);
        
        // setting all bits to 1
        int i=0;
        do
        {
            b.setBit(i,true);
            i++;
        }while(i<8);
        
        // prints the bit vector
        System.out.println(b.toString());
        
        // prints the result of the function writeModbus (Write Multiple Coils) 
        System.out.println(protocolToPLC.writeModbus(0,b));
        
        

        
        
       // creates a database object
       Database db = new Database();
      
       // if database is initialized
       if(db.initDatabase("org.postgresql.Driver", 
                "jdbc:postgresql://dbm.fe.up.pt/sinf16g67"))
       {
          // if credentials are right
          if(db.setCredentials("sinf16g67","manueljoaofraga"))
           {
               // if a databased connection is opened
               if(db.openConnection())
               {
                   // executes a query
                   db.executeQuery("CREATE TABLE mes.TEST_THREE();");
              }
          }
       }
         
        // creates a factory object
        Factory simulatedFactory = new Factory();

        // initializes factory 
        simulatedFactory.initFactory();
        
        // checks if factory is ready
        //if((simulatedFactory.isReady));
  
        
        
        
        
        
        
        
        
        
        
 }
   
}
