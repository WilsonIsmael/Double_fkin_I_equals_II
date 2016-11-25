

package mes;

import java.io.*;
import java.net.*;
import net.wimpi.modbus.*;
import net.wimpi.modbus.msg.*;
import net.wimpi.modbus.io.*;
import net.wimpi.modbus.net.*;
import net.wimpi.modbus.util.*;



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
    public Protocol(int protocolType)       
    {
        // UDP
        if(protocolType == 0)
            type = 0;
        
        // Modbus
        else
            type = 1;
    }
    
    /**
     * 
     * @param protocolAddress
     * @param protocolPort 
     */
    public void initProtocol(String protocolAddress, int protocolPort) 
    {
        // UDP
        if (type == 0)
        {
            port = protocolPort;
            address = protocolAddress;
        }
        
        // Modbus
        else
        {
            port = protocolPort;
            address = protocolAddress;
        }
            
            
    }
    
    /**
     * 
     * @param protocolType 
     */
    public void startProtocol()
    {
        if(type == 0)
        {
            try
            {
                udpServer();
            }
            catch(Exception ex)
            {
                //TO DO
            }
        }
        else
            modbusMasterTCP();
    }
    
    public static void udpServer() throws Exception
      {
        // creates new datagram socket (Port: 54321)  
        DatagramSocket serverSocket = new DatagramSocket(54321);
        
        // creates array of bytes (receiveData and sendData)
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
   
 

    public static void modbusMasterTCP() 
    {
    try 
        {
        /* The important instances of the classes mentioned before */
        TCPMasterConnection con = null; //the connection
        
        ModbusTCPTransaction trans = null; //the transaction
        
        ReadInputDiscretesRequest req = null; //the request
        
        ReadInputDiscretesResponse res = null; //the response

        /* Variables for storing the parameters */
        InetAddress addr = null;            //the slave's address
        int port = Modbus.DEFAULT_PORT;
        int ref = 0;                        //the reference; offset where to start reading from
        int count = 0;                      //the number of DI's to read
        int repeat = 1;                      //a loop for repeating the transaction
        
        //1. Setup the parameters
        if (args.length < 3) 
            {
            System.exit(1);
            } 
        else 
            {// Pode não ser necessário.
            try 
                {
                String astr = args[0];
                int idx = astr.indexOf(':');
                if(idx > 0)
                    {
                    port = Integer.parseInt(astr.substring(idx+1));
                    astr = astr.substring(0,idx);
                    }
                addr = InetAddress.getByName(astr);
                ref = Integer.decode(args[1]).intValue();
                count = Integer.decode(args[2]).intValue();
                if (args.length == 4) 
                    {
                    repeat = Integer.parseInt(args[3]);
                    }
                }
            catch (Exception ex) 
                {
                ex.printStackTrace();
                System.exit(1);
                }
            }
        
        //2. Open the connection
        con = new TCPMasterConnection(addr);
        con.setPort(port);
        con.connect();

        //3. Prepare the request
        req = new ReadInputDiscretesRequest(ref, count);

        //4. Prepare the transaction
        trans = new ModbusTCPTransaction(con);
        trans.setRequest(req);
        
    
        //5. Execute the transaction repeat times
        int k = 0;
        do {
            trans.execute();
            res = (ReadInputDiscretesResponse) trans.getResponse();
            System.out.println("Digital Inputs Status=" + res.getDiscretes().toString());
            k++;
            }  while (k < repeat);

        //6. Close the connection
            con.close();
        
        }
        catch (Exception ex) 
            {   
            ex.printStackTrace();
            System.exit(1);
            }
    
    }
   
    
    
    
}
