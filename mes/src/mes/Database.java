/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mes;

import java.sql.*;

/**
 *
 * @author Utilizador
 */
public class Database {
    
    private static String jdbcDriver;  
    private static String databaseURL;
    private static String databaseUser;
    private static String databasePassword;
    private boolean isReady = false;
    private Connection databaseConnection;
    private Statement databaseStatement;
    private ResultSet dataSet;
    
    /**
     * Checks if database is ready
     * @return 
     * true - database is ready
     * false - database not ready
     */
    public boolean isReady()
    {
        return isReady;
    }
    
  /**
   * Initializes database with given parameters
   * @param driver
   * @param url
   * @return
   * true - database initialized with success
   * false - database not initialized
   */
    public boolean initDatabase(String driver, String url)
    {
        if ("".equals(driver))
        {
            System.out.println("No driver.");
            return false;
        }
        else if ("".equals(url))
        {
            System.out.println("No url.");
            return false;
        }
        else
        {
            jdbcDriver = driver;  
            databaseURL = url;
            return true;
        }     
    }
    
    /**
     * Sets the database credentials 
     * @param username
     * @param password
     * @return 
     */
    public boolean setCredentials(String username, String password)
    {
         if ("".equals(username))
        {
            System.out.println("No username.");
            return false;
        }
        else if ("".equals(password))
        {
            System.out.println("No password.");
            return false;
        }
        else
        {
            databaseUser = username;
            databasePassword = password;
            return true;
        }     
    }
    
    
    /**
     * Opens a database connection
     * @return 
     */
    public boolean openConnection()
    {
      databaseConnection = null;
        
      System.out.println("Connecting to database...");
     
      try
      {
        databaseConnection = DriverManager.getConnection(getDatabaseURL(),
              getDatabaseUsername(), getDatabasePassword());
      } catch (SQLException e) 
        {

            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            return false;
        }
      
      if (databaseConnection != null) 
      {
          System.out.println("You made it, take control your database now!");
          isReady = true;
          return true;
      } 
      
      else 
      {
          System.out.println("Failed to make connection!");
          isReady = false;
          return false;
      }
    }
    
    
    /**
     * Tests a ready database connection
     * @return 
     */
    private boolean testDatabase()
    {  
        //only tests if database is ready
        if (isReady)
        {
            // if response to SQL Query is received
            if(true)
                return true;
        }
        else
        {
            System.out.println("Only tests when ready.");
            return false;
        }
        
        return true;
    }
    
    
    /**
     * 
     * @return 
     */
    public String getDatabasePassword()
    {
        return databasePassword;
    }
    
    
    /**
     * 
     * @return 
     */
    public String getDatabaseUsername()
    {
        return databaseUser;
    }
    
    
    /**
     * 
     * @return 
     */
    public String getDatabaseURL()
    {
        return databaseURL;
    }
    
    
    /**
     * 
     * @return 
     */
    public String getDatabaseDriver()
    {
        return jdbcDriver;
    }
    
    
    /**
     * 
     */
    public void registerDriver()
    {
        System.out.println("-------- PostgreSQL "
				+ "JDBC Connection Testing ------------");

        try 
        {

            Class.forName(getDatabaseDriver());

        } catch (ClassNotFoundException e) {

            System.out.println("Where is your PostgreSQL JDBC Driver? "
                                + "Include in your library path!");
            e.printStackTrace();
            return;

        }
        
        System.out.println("PostgreSQL JDBC Driver Registered!");
    }
    
    /**
     * Executes a given query
     * @param query
     * @throws SQLException 
     */
    public void executeQuery(String query) throws SQLException
    {
      System.out.println("Creating statement...");
      databaseStatement = databaseConnection.createStatement();
      ResultSet dataSet = databaseStatement.executeQuery(query);
    }
}

