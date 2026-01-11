package Java.Learn.Network.SingleUDP;

import java.net.*;

public class UDPClient {
    public static void main(String[] args) throws Exception{
        DatagramSocket client=new DatagramSocket(7777);
        byte[] buffer="hello,world".getBytes();
        DatagramPacket dp=new DatagramPacket(
            buffer,buffer.length,InetAddress.getLocalHost(),8888
        );
        client.send(dp);
        client.close();
    }
}
