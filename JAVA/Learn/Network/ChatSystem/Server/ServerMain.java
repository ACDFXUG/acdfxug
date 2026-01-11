package Java.Learn.Network.ChatSystem.Server;

import java.io.*;
import java.net.*;
import java.util.*;

public class ServerMain {
    protected static final Map<Socket,String> onlineClients=new HashMap<>();
    public static void main(String[] args) {
        System.out.println("Server started");
        try(ServerSocket ss=new ServerSocket(Constant.PORT)){
            for(;;){
                var client=ss.accept();
                var reader=new ServerReaderThread(client);
                reader.start();
            }
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
}
