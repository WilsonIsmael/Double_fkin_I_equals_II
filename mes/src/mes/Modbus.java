/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mes;

import java.net.InetAddress;
import javax.swing.JOptionPane;
import net.wimpi.modbus.io.ModbusTCPTransaction;
import net.wimpi.modbus.msg.*;
import net.wimpi.modbus.net.TCPMasterConnection;
import java.util.*;
import net.wimpi.modbus.util.BitVector;


/**
 *This class extends Protocol
 * @author Mário Xavier
 */
public final class Modbus {
    
    
    public String ID;
    private String address;
    private int port;
    // Modbus connection
    private TCPMasterConnection modbusConnection = null; 
    // Modbus transaction  
    private ModbusTCPTransaction modbusTransaction = null; 
    // Modbus read request
    private ReadInputDiscretesRequest modbusReadRequest = null;     
    // Modbus read response
    private ReadInputDiscretesResponse modbusReadResponse = null; 
    // Modbus write request
    private WriteMultipleCoilsResponse modbusWriteResponse = null;
    //Modbus write response
    private WriteMultipleCoilsRequest modbusWriteRequest = null;
    //the slave's address
    private InetAddress modbusAddress = null; 
    // offset where to start reading from
    private int startReadingReference = 0; 
    // number of bits to read
    private int noOfBits = 0;
    // a loop for repeating the transaction
    private int noOfTransactions = 1;       
    // offset where to start writing
    private int startWritingReference = 0;
    // Bit Vector to Write in PLC
    private BitVector bitsToWrite;
    
    
    public Modbus() {
        initModbus();
    }
    
    /**
     * 
     * @return 
     */
    public int getNoOfBits()
    {
        return noOfBits;
    }
    
    /**
     * 
     * @param bitCount
     * @return 
     */
    public boolean setNoOfBits(int bitCount)
    {
        noOfBits = bitCount;
        return true;
    }
    
    /**
     * 
     * @return 
     */
    public BitVector getBitsToWrite()
    {
        return bitsToWrite;
        
    }
    
    
    /**
     * 
     * @param vectorOfBits
     * @return 
     */
    public boolean setBitsToWrite(BitVector vectorOfBits)
    {
        bitsToWrite = vectorOfBits;
        return true;
    }
    
    

    
    /**
     * 
     * @return 
     */
    public String getModbusAddress()
    {
        return address;
    }
    
    /**
     * Gets the protocol port
     * @return  port
     */
    public int getModbusPort()
    {
        return port;
    }
    
    /**
     * Sets the protocol address
     * @return 
     *true if address is not empty
     *false if address is empty
     */
    public boolean setModbusAddress()
    {
       
         // user prompt
        String address = JOptionPane.showInputDialog("Insert address");
        
        // if given address is empty
        if ("".equals(address))
            return false;
        // if the address is not empty
        else 
            return true;
    }
    
    /**
     * Sets the protocol port
     * @return 
     * true
     * false
     */
    public boolean setModbusPort()
    {
 
        // user prompt
        String inputPort = JOptionPane.showInputDialog("Insert port");
      
        port = Integer.parseInt(inputPort);
        
        // if port is empty
        if (port == 0)
            return false;
        // if port is not empty
        else 
            return true;
    }
    
    /**
     * Gets start reading point in PLC
     * @return 
     */
    public int getStartReadingReference()
    {
        return startReadingReference;
    }
    
    
    
    /**
     * Sets start reading point in PLC
     * @param offset
     * @return 
     */
    public boolean setStartReadingReference(int offset)
    {
        startReadingReference = offset;
        return true;
    }
    
    
    
    
    /**
     * Gets the offset where to start writing in PLC
     * @return 
     */
    public int getStartWritingReference()
    {
     return startWritingReference;   
    }
    
    /**
     * Sets start writing point in PLC
     * @param offset
     * @return 
     */
    public boolean setStartWritingReference(int offset)
    {
        startWritingReference = offset;
        return true;
    }
    
    
    /**
     * 
     * @param modbusAddress
     * @return 
     */
    public boolean setModbusConnection()
    {
        try
        {
        // sets connection address
        modbusConnection = new TCPMasterConnection(
                InetAddress.getByName(address));
        
        // sets connection port
        modbusConnection.setPort(port);
        }
        catch (Exception ex) 
        {
            ex.printStackTrace();
            System.exit(1);
            return false;
        }
        
        return true;
    }
    
    /**
     * 
     * @return 
     */
    public TCPMasterConnection getModbusConnection()
    {
        return modbusConnection;
    } 
  
    
    /**
     * 
     * @param type
     * @return 
     */
    public boolean setModbusTransaction(int type)
    {
        modbusTransaction = new ModbusTCPTransaction(modbusConnection);
        
        // if type is 1 we set up a Transaction to Read Discrete Inputs
        if (type == 1)
        {
            modbusTransaction.setRequest(modbusReadRequest);
        }
        
        // if type is 2 we set up a Transaction to Write Multiple Coils
        else if (type == 2)
        {
            modbusTransaction.setRequest(modbusWriteRequest);
        }
        
        else
            return false;

        // error occured while setting modbusTransaction
        if (modbusTransaction == null)
            return false;
        // no errors
        else 
            return true;
    }
    
    /**
     * 
     * @return 
     */
    public ModbusTCPTransaction getModbusTransaction()
    {
        return modbusTransaction;
    } 
   
    /**
     * 
     * @return 
     */
    public boolean setModbusReadRequest()
    {
        modbusReadRequest = new ReadInputDiscretesRequest(
                startReadingReference, noOfBits);
        
        // if some error occured while creating modbusRequest
        if (modbusReadRequest == null)
            return false;
        else 
            return true;
    }
    
    /**
     * 
     * @return 
     */
    public ReadInputDiscretesRequest getModbusReadRequest()
    {
        return modbusReadRequest;
    }     
    
    
    /**
     * sets the Write Multiple Coils Request
     * @return 
     */
    public boolean setModbusWriteRequest()
    {
        modbusWriteRequest = new WriteMultipleCoilsRequest(startWritingReference, bitsToWrite);
        
        
        // testing if some error occurred while creating the Write Multiple Coils Request
        if (modbusWriteRequest == null)
            return false;
        else
            return true;
        
    }
    
    /**
     * returns the write multiple coils request
     * @return 
     */
    public WriteMultipleCoilsRequest getModbusWriteRequest()
    {
        return modbusWriteRequest;
    }     
    
    
    
    
    /**
     * Initializes the protocol
     * @return 
     */
    public boolean initModbus() 
    {
        // error setting the port
        if(setModbusPort() == false) {
            System.out.println("Error inserting port.\n");
            return false;
        } 
        // error setting the address
        else if (setModbusAddress() == false)
        {
            System.out.println("Error inserting address.\n");
            return false;
        }
        // no error
        else
            return true;
    }   
    
    /**
     * 
     * @return 
     */
    public boolean openConnection()
    {
        try
        {
        modbusConnection.connect();
        }
        catch (Exception ex) 
        {   
         ex.printStackTrace();
         System.exit(1);
        }
        if(modbusConnection.isConnected())
            return true;
        else
            return false;
    }
    
/**
 * 
 * @param offset
 * @param bitCount
 * @return 
 */
    public String readModbus(int offset, int bitCount)
    {
        if(setStartReadingReference(offset))
        {
            //TO DO
        }
        if(setNoOfBits(bitCount))
        {
            //TO DO
        }
        
        // if read request was created succsessfully
        if(setModbusReadRequest())
        {
            // if read transaction was read successfully
            if(setModbusTransaction(1))
            {
                try
                {
                    // executes a reading
                    modbusTransaction.execute();
                }
                catch (Exception ex) 
                {
                    ex.printStackTrace();
                    System.exit(1);
                }
                // receives a reading
                modbusReadResponse = (ReadInputDiscretesResponse) modbusTransaction.getResponse();
                return modbusReadResponse.getDiscretes().toString();
            }
            // if some error occured setting transaction;
            else
                return null;
        }
        // if some error occured setting the request;
        else
            return null;
    }   
    
    
    public String writeModbus(int offset, BitVector vectorOfBits)
    {
        if(setStartWritingReference(offset))
        {
            //TO DO
        }
        
        if (setBitsToWrite(vectorOfBits))
        {
            //TO DO
        }
        
        
        if (setModbusWriteRequest())
        {
            if (setModbusTransaction(2))
            {
                try
                {
                    // executes a reading
                    modbusTransaction.execute();
                }          
                catch(Exception ex)
                {
                    ex.printStackTrace();
                    System.exit(1);
                }
                
                modbusWriteResponse = (WriteMultipleCoilsResponse) modbusTransaction.getResponse();
                
                // Returns the response in String format
                return modbusWriteResponse.toString();
            }
            // if some error occured setting transaction;
            else
                return null;
                       
        }
        // if some error occured setting the request;
        else
            return null;

    }

}
