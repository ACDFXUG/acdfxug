package Java.Learn.Network.SingleUDP;

import java.net.*;

public class UDPServer {
    public static void main(String[] args)throws Exception{
        DatagramSocket server=new DatagramSocket(8888);
        byte[] buffer=new byte[1024*64];
        DatagramPacket dp=new DatagramPacket(buffer,buffer.length);
        server.receive(dp);
        String message=new String(dp.getData(),0,dp.getLength());
        System.out.println(message);
        server.close();
    }
}
