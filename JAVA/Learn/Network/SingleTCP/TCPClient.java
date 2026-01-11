package Java.Learn.Network.SingleTCP;

import java.io.*;
import java.net.*;

public class TCPClient {
    public static void main(String[] args) throws Exception{
        Socket client=new Socket("127.0.0.1",8888);
        DataOutputStream dos=new DataOutputStream(client.getOutputStream());
        dos.writeUTF("hello,world");
        client.close();
    }
}
