package Java.Learn.Network.ChatSystem.Client;

import java.io.*;
import java.net.*;

public class ClientReaderThread extends Thread{
    private Socket client;
    private DataInputStream dis;
    private ChatUI chatUI;
    public ClientReaderThread(ChatUI chatUI,Socket client){
        this.chatUI=chatUI;
        this.client=client;
    }
    public void run(){
        try{
            dis=new DataInputStream(client.getInputStream());
            for(;;){
                int type=dis.readInt();
                switch(type){
                    case Constant.UPDATE_ONLINE_USERS->{
                        updateClientOnlineUsers();
                    }case Constant.UPDATE_GROUP_MESSAGE->{
                        String message=dis.readUTF();
                        updateMessageToAllUsers(message);
                    }default->{
                        System.out.println("Invalid message type");
                    }
                }
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    private void updateClientOnlineUsers() throws IOException{
        int count=dis.readInt();
        String[] users=new String[count];
        for(int i=0;i<count;++i){
            users[i]=dis.readUTF();
        }
        chatUI.updateOnlineUsers(users);
    }
    private void updateMessageToAllUsers(String message){
        chatUI.appendMessage(message);
    }
}
