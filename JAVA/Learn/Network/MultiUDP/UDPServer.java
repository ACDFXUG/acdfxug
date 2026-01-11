package Java.Learn.Network.MultiUDP;

import java.net.*;

public class UDPServer {
    public static void main(String[] args) throws Exception{
        try(DatagramSocket server=new DatagramSocket(8080)){
            byte[] buffer=new byte[1024*64];
            DatagramPacket dp=new DatagramPacket(buffer,buffer.length);
            for(;;){
                server.receive(dp);
                String message=new String(dp.getData(),0,dp.getLength());
                System.out.println(message);
                System.out.println("---------------------------------");
            }
        }
    }
}
