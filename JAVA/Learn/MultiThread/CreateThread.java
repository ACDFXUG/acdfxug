package Java.Learn.MultiThread;

import java.util.concurrent.*;

public class CreateThread {
    public static void main(String[] args)
    throws InterruptedException,ExecutionException {
        SubThread st=new SubThread();
        st.start();
        st.join();

        SubRunnable sr=new SubRunnable();
        var tsr=new Thread(sr);
        tsr.start();
        tsr.join();

        SubCallable sc=new SubCallable(100);
        var ft=new FutureTask<>(sc);
        var tsc=new Thread(ft);
        tsc.start();
        System.out.println(ft.get());
    }
}

//通过继承Thread类创建线程
class SubThread extends Thread{
    public void run(){
        System.out.println("SubThread is running");
    }
}

//通过实现Runnable接口创建线程
class SubRunnable implements Runnable{
    public void run(){
        System.out.println("SubRunnable is running");
    }
}

class SubCallable implements Callable<Integer>{
    private int n;
    SubCallable(int n){
        this.n=n;
    }
    public Integer call(){
        int sum=0;
        for(int i=1;i<=n;sum+=i++);
        return sum;
    }
}