package Java.Learn.Network.MultiTCP;

import module java.base;

public class TCPServer {
    public static void main(String[] args) throws Exception{
        try(ServerSocket ss=new ServerSocket(8080)){
            for(;;){
                Socket client=ss.accept();
                new ServerReader(client).start();
            }
        }
    }
}
