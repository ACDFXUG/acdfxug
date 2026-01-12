package Java.Learn.Network.ChatSystem.Server;

import java.net.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.*;

public class ServerReaderThread extends Thread{
    private final Socket client;
    public ServerReaderThread(Socket client){
        this.client=client;
    }
    public void run(){ 
        try{
            DataInputStream dis=new DataInputStream(client.getInputStream());
            for(;;){
                int msgType=dis.readInt();
                switch(msgType){
                    case Constant.LOGIN->{
                        String nickname=dis.readUTF();
                        ServerMain.onlineClients.put(client,nickname);
                        updateClientOnlineUsers();
                    }case Constant.GROUP->{
                        String message=dis.readUTF();
                        updateMessageToAllUsers(message);
                    }case Constant.PRIVATE->{

                    }default->{
                        System.out.println("Invalid message type");
                    }
                }
            }
        }catch(IOException e){
            System.out.println(client.getInetAddress()+" disconnected");
            ServerMain.onlineClients.remove(client);
            updateClientOnlineUsers();
        }
    }
    private void updateClientOnlineUsers(){
        var names=ServerMain.onlineClients.values();
        ServerMain.onlineClients.forEach((socket,_)->{
            try{
                DataOutputStream dos=new DataOutputStream(socket.getOutputStream());
                dos.writeInt(Constant.UPDATE_ONLINE_USERS);
                dos.writeInt(names.size());
                for(var name:names) dos.writeUTF(name);
                dos.flush();
            }catch(IOException e){
                e.printStackTrace();
            }
        });
    }
    private void updateMessageToAllUsers(String message){
        StringBuilder sb=new StringBuilder();
        String name=ServerMain.onlineClients.get(client);
        var now=LocalDateTime.now();
        var dtf=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss EEE");
        String msg=sb.append(name).append(" ").append(dtf.format(now))
            .append("\r\n").append(message).append("\r\n").toString();
        ServerMain.onlineClients.forEach((socket,_)->{
            try{
                DataOutputStream dos=new DataOutputStream(socket.getOutputStream());
                dos.writeInt(Constant.UPDATE_GROUP_MESSAGE);
                dos.writeUTF(msg);
                dos.flush();
            }catch(IOException e){
                e.printStackTrace();
            }
        });
    }
}
