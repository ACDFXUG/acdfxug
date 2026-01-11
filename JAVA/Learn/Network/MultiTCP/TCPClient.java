package Java.Learn.Network.MultiTCP;

import module java.base;

public class TCPClient {
    public static void main(String[] args) throws Exception{
        try(Socket client=new Socket("127.0.0.1",8080);
            Scanner sc=new Scanner(System.in)){
            DataOutputStream dos=new DataOutputStream(client.getOutputStream());
            WRITE:for(;;){
                String line=sc.nextLine();
                switch(line){
                    case "exit"->{
                        break WRITE;
                    }default->{
                        dos.writeUTF(line);
                        dos.flush();
                    }
                }
            }
        }
    }
}
