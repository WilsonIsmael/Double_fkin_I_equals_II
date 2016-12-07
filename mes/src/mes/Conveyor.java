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
public class Conveyor {
    
    public int ID;
    public String type;
    private String status;
    
    
    /**
     * Constructor
     */
    public Conveyor(String conveyorType)
    {
      if (null == conveyorType)
          System.out.println("No conveyor type given.\n");
      else
          switch(conveyorType)
          {
              case "cell":
                  type = conveyorType;
                  break;
                  
              case "transport":
                  type = conveyorType;
                  break;
                  
              default:
                System.out.println("Cell type not recognized.\n");
          } 
    }
    
    /**
     * Updates the status of the conveyor
     * @param conveyorStatus
     * @return 
     */
    public boolean updateStatus(String conveyorStatus)
    {
        // if no conveyor status was given
        if (null == conveyorStatus)
        {
            System.out.println("No conveyor status was defined.\n");
            return false;
        }
        // if a conveyor status was given
        else
        {
            status = conveyorStatus;
            return true;
        }
    }
    
    /**
     * Gets conveyor status
     * @return 
     */
    public String getStatus()
    {
        return status;
    }
    
    /**
     * Sets a conveyor type
     * @param conveyorType
     * @return 
     */
    public boolean setType(String conveyorType)
    {
        // if no conveyorType was given
        if (null == conveyorType)
        {
            System.out.println("No conveyor type was given.\n");
            return false;
        }
        // if a conveyor type was given
        else
        {
            type = conveyorType;
            return true;
        }
    }
    
    /**
     * Gets the conveyor type
     * @return 
     */
    public String getType()
    {
        return type;   
    }
}