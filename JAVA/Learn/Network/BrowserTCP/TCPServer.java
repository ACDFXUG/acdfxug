package Java.Learn.Network.BrowserTCP;

import module java.base;

public class TCPServer {
    public static void main(String[] args) throws Exception{
        try(ServerSocket ss=new ServerSocket(8080);
            ExecutorService es=new ThreadPoolExecutor(
                3,10,10,TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(100))){
            for(;;){
                Socket client=ss.accept();
                Runnable tcp=new ServerReaderThread(client);
                es.execute(tcp);
            }
        }
    }
}
