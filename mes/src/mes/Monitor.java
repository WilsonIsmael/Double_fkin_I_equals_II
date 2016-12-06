/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mes;

/**
 *
 * @author Utilizador
 */
public class Monitor {
    
    private char[] inputData;
    private char[] outputData;
    private Modbus protocolToPLC;
    
    
    public Monitor(Modbus protocol)
    {
        protocolToPLC = protocol;
    }
    
    
    

    /**
     * returns a char array with the values of all the sensors read by the PLC
     * @return 
     */
    public char[] readSensors()
    {       
        
                
        // dataReceived contains a string with the value of all sensors, separated by a space on each byte
        String dataReceived = protocolToPLC.readModbus(0,146);
        
        
        // s is a string array in which each position has a byte
        String[] s = dataReceived.split(" ");
        
        // cycle to invert the order of each byte
        int i = 0;
        do
        {
        s[i] = new StringBuilder(s[i]).reverse().toString();
        i++;
        }while(i<19);
        
        // emptying dataReceived
        dataReceived="";
        
        // placing a string on dataReceived with the data in the intended format
        for (String abc : s)
        {
           dataReceived = dataReceived + abc;
        }
        
        // inputData contains an array in which each position has the value of the corresponding sensor on the PLC
        inputData = dataReceived.toCharArray();
        
        return inputData;
        
        
    }

    
    public void readActuators()       
        {

            
            // dataReceived contains a string with the value of all actuators, separated by a space on each byte
            String dataReceived = protocolToPLC.readModbus(160,200);
        
        
            // s is a string array in which each position has a byte
            String[] s = dataReceived.split(" ");
        
            // cycle to invert the order of each byte
            int i = 0;
            do
            {
            s[i] = new StringBuilder(s[i]).reverse().toString();
            i++;
            }while(i<25);
        
            // emptying dataReceived
            dataReceived="";
        
            // placing a string on dataReceived with the data in the intended format
            for (String abc : s)
            {
                dataReceived = dataReceived + abc;
            }
        
            // outputData contains an array in which each position has the value of the corresponding actuator on the PLC
            outputData = dataReceived.toCharArray();
        
        }
    

    
    
    
}
