package Java.Learn.Network.BrowserTCP;

import java.net.*;
import java.nio.charset.StandardCharsets;
import java.io.*;

public class ServerReaderThread extends Thread{
    private final Socket client;
    public ServerReaderThread(Socket client){
        this.client=client;
    }
    public void run(){ 
        try(PrintStream ps=new PrintStream(client.getOutputStream())){
            BufferedReader reader = new BufferedReader(
                new InputStreamReader(client.getInputStream(), StandardCharsets.UTF_8)
            );
        String requestLine = reader.readLine();
        System.out.println("Request: " + requestLine + " | Thread: " + Thread.currentThread().getName());
            ps.println("HTTP/1.1 200 OK");
            ps.println("Content-Type:text/html;charset=utf-8");
            ps.println();
            ps.println("<html><head><title>BrowserTCPTest</title></head>");
            ps.println("<body>");
            ps.println("<h1>"+Thread.currentThread().getName()+"</h1>");
            ps.println("<img src='https://avatars.githubusercontent.com/u/170488392?s=400&u=7ae5a7f69db6b13ad184df513ac3a268b129555c&v=4'>");
            ps.println("</body></html>");
            ps.close();
            client.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
