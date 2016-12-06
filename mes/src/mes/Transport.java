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
public class Transport {
    
    int ID;
    String type;
    
    /**
     * Constructor
     * @param transportType 
     */
    public Transport(String transportType, Factory currentFactory)
    {
        if (null == transportType)
            System.out.println("No transport type given.\n");
        else
        switch(transportType)
        {
            case "input":
                type = transportType;
                // creates transport conveyors in the curren factory
                currentFactory.addConveyors("transport", 30, currentFactory);
                break;

            case "output":
                type = transportType;
                //creates transport conveyors in the current factory
                currentFactory.addConveyors("transport", 30, currentFactory);
                break;
            
            default:
                System.out.println("Transport type not recognized.\n");
        }
    }
    
}
