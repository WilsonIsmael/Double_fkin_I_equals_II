package mes;

import java.io.*;
import java.net.*;

class Protocol
{
    public int id;
    public int port;
    
    public Protocol(int id, int port)
    {
        id = 1;
        port = 54321;
    }
    
    public static void UDPServer() throws Exception
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
}