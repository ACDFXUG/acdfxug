package Java.Learn.Network.SingleTCP;

import java.net.*;
import java.io.*;

public class TCPServer {
    public static void main(String[] args) throws Exception{
        ServerSocket ss=new ServerSocket(8888);
        Socket server=ss.accept();
        DataInputStream dis=new DataInputStream(server.getInputStream());
        System.out.println(dis.readUTF());
        System.out.println(server.getInetAddress());
        System.out.println(server.getPort());
        dis.close();
        server.close();
        ss.close();
    }
}
