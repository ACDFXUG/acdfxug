package Java.Learn.Network.MultiUDP;

import java.net.*;
import java.util.Scanner;

public class UDPClient {
    public static void main(String[] args) throws Exception{
        try(DatagramSocket client=new DatagramSocket(7777);
            Scanner sc=new Scanner(System.in)){
            for(;;){
                byte[] buffer=sc.nextLine().getBytes();
                DatagramPacket dp=new DatagramPacket(
                    buffer,buffer.length,InetAddress.getLocalHost(),8080
                );
                client.send(dp);
            }
        }
    }
}
