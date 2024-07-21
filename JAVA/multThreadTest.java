package JAVA;

class UserThread extends Thread{
    int sleepTime;
    public UserThread(String id){
        super(id);
        sleepTime=(int)(Math.random()*1000);
        System.out.printf("Thread:%s,sleep:%dms\n",getName(),sleepTime);
    }

    public void run(){
        try{
            Thread.sleep(sleepTime);
        }catch(InterruptedException e){
            System.err.printf("error:%s\n",e.toString());
        }
        System.out.printf("the running thread is:%s\n",getName());
    }
}

public class multThreadTest {
    public static void main(String[] args) {
        UserThread 
        t1=new UserThread("1"),
        t2=new UserThread("2"),
        t3=new UserThread("3"),
        t4=new UserThread("4");
        t1.start();
        t2.start();
        t3.start();
        t4.start();

    }
}
