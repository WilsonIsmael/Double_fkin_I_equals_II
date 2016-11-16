/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mes;

import java.io.*;
import java.net.*;
import net.wimpi.modbus.*;
import net.wimpi.modbus.msg.*;
import net.wimpi.modbus.io.*;
import net.wimpi.modbus.net.*;
import net.wimpi.modbus.util.*;

class Protocol
{
   public static void UDPServer(String args[]) throws Exception
      {
        // creates new datagram socket (Port: 54321)  
        DatagramSocket serverSocket = new DatagramSocket(54321);
        // creates array of bytes (receiveData and sendData)
        byte[] receiveData = new byte[1024];
        //byte[] sendData = new byte[1024];
        
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
            
//            InetAddress IPAddress = receivePacket.getAddress();
//            int port = receivePacket.getPort();
//            String capitalizedSentence = sentence.toUpperCase();
//            sendData = capitalizedSentence.getBytes();
//            DatagramPacket sendPacket =
//              new DatagramPacket(sendData, sendData.length, IPAddress, port);
//              serverSocket.send(sendPacket);
        }
      }
   
  /*public class DITest {

  public static void modbusMasterTCP(String[] args) {
    try {
      ...
      ...
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }//main
  
}//class DITest
   
   
   
 */
}
