

package mes;

import java.io.*;
import java.net.*;
import net.wimpi.modbus.*;
import net.wimpi.modbus.msg.*;
import net.wimpi.modbus.io.*;
import net.wimpi.modbus.net.*;
import net.wimpi.modbus.util.*;
import java.util.*;


public class Protocol
{
    private int type;
    private String address = new String();
    private int port;
    
   /**
    * Constructor of a protocol object
    * @param type: protocol type:
    * 0 - UDP
    * 1 - Modbus
    */
    public Protocol(String protocolType)       
    {
        // UDP
        if(protocolType == "UDP")
            type = 0;
        
        // Modbus
        else
            type = 1;
    }
    
    
    /**
     * 
     * @return type
     */
    public int getProtocolType()
    {
        return type;
    }
    
    /**
     * 
     * @return address 
     */
    public String getProtocolAddress()
    {
        return address;
    }
    
    /**
     * 
     * @return  port
     */
    public int getProtocolPort()
    {
        return port;
    }
    
    /**
     * 
     * @return 
     *true if address is not empty
     *false if address is empty
     */
    public boolean setProtocolAddress()
    {
        Scanner userInput = new Scanner(System.in);
        System.out.println("Please insert the address:");
        address = userInput.next();
        
        if ("".equals(address))
            return false;
        else 
            return true;
    }
    
    
    /**
     * 
     * @return 
     */
    public boolean setProtocolPort()
    {
        Scanner userInput = new Scanner(System.in);
        System.out.println("Please insert the port:");
        port = userInput.nextInt();
        
        if (port == 0)
            return false;
        else 
            return true;
    }
    
    
    /**
     * 
     * @param protocolAddress
     * @param protocolPort 
     */
    public void initProtocol(String protocolAddress, int protocolPort) 
    {
        port = protocolPort;
        address = protocolAddress;
        
        // UDP
        if (type == 0);
        
        // Modbus
        else;       
    }
    
    
    /**
     * 
     * @param protocolType 
     */
    public void startProtocol()
    {   
        // starts UDP protocol
        if(type == 0)
        {
            try
            {
                // creates new datagram socket (Port: 54321)  
                DatagramSocket serverSocket = new DatagramSocket(port);
                // creates array of bytes (receiveData)
                byte[] receiveData = new byte[1024]; 
       
                while(true)
                {
            
                    // creates new packet to receive data
                    DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                    // receives a packet from port 54321
                    serverSocket.receive(receivePacket);
                    // retrieves the sentence from the packet
                    String sentence = new String( receivePacket.getData());
                    // prints the sentence
                    System.out.println("RECEIVED: " + sentence);
                }
            }
            catch(Exception ex)
            {
                //TO DO
            }
        }
        // starts Modbus protocol
        else
        {
            try 
            {
                /* The important instances of the classes mentioned before */
                
                //Modbus connection
                TCPMasterConnection modbusConnection = null; 
                //Modbus transaction  
                ModbusTCPTransaction modbusTransaction = null; 
                //Modbus request
                ReadInputDiscretesRequest modbusRequest = null;     
                //Modbus response
                ReadInputDiscretesResponse modbusResponse = null; 

                /* Variables for storing the parameters */
                //the slave's address
                InetAddress modbusAddress = null;            
                int port = Modbus.DEFAULT_PORT;
                // offset where to start reading from
                int startReadingReference = 0; 
                // number of inputs to read
                int noOfInputs = 0;
                // a loop for repeating the transaction
                int noOfTransactions = 1;                      
        
                //1. Setup the parameters
                try 
                {
                    String modbusAddressParameter = "";
                    int index = modbusAddressParameter.indexOf(':');
                    if(index > 0)
                    {
                        port = Integer.parseInt(modbusAddressParameter.substring(index + 1));
                        modbusAddressParameter = modbusAddressParameter.substring(0, index);
                    }
                    modbusAddress = InetAddress.getByName(modbusAddressParameter);
                    startReadingReference = 0;
                    noOfInputs = 0;
                    if (true) 
                    {
                        noOfTransactions = 0;
                    }
                }
                catch (Exception ex) 
                {
                    ex.printStackTrace();
                    System.exit(1);
                }
        
                //2. Open the connection
                modbusConnection = new TCPMasterConnection(modbusAddress);
                modbusConnection.setPort(port);
                modbusConnection.connect();

                //3. Prepare the request
                modbusRequest = new ReadInputDiscretesRequest(startReadingReference, noOfInputs);

                //4. Prepare the transaction
                modbusTransaction = new ModbusTCPTransaction(modbusConnection);
                modbusTransaction.setRequest(modbusRequest);

                //5. Execute the transaction repeat times
                int k = 0;
                do 
                {

                    modbusTransaction.execute();
                    modbusResponse = (ReadInputDiscretesResponse) modbusTransaction.getResponse();
                    System.out.println("Digital Inputs Status = " + modbusResponse.getDiscretes().toString());
                    k++;
                }  while (k < noOfTransactions);

                //6. Close the connection
                modbusConnection.close();

            }
            catch (Exception ex) 
            {   
             ex.printStackTrace();
             System.exit(1);
            }

        }
    }
}    
