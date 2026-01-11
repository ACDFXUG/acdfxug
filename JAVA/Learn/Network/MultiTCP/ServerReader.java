package Java.Learn.Network.MultiTCP;

import java.net.*;
import java.io.*;

public class ServerReader extends Thread{
    private final Socket client;
    public ServerReader(Socket client){
        this.client=client;
    }
    public void run(){ 
        try{
            DataInputStream dis=new DataInputStream(client.getInputStream());
            for(;;){
                System.out.println(Thread.currentThread().getName()+":"+dis.readUTF());
                System.out.println("-----------------------");
            }
        }catch(IOException e){
            System.out.println(Thread.currentThread().getName()+" closed");
            Thread.currentThread().interrupt();
        }
    }
}
