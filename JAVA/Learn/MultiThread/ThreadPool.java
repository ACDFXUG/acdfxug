package Java.Learn.MultiThread;

import java.util.concurrent.*;

public class ThreadPool {
    public static void main(String[] args) throws Exception{
        ExecutorService es=new ThreadPoolExecutor(
            3,5,10,TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(3)
        );
        es.submit(new SubIntCallable(10)).get();
        es.submit(new SubIntCallable(100)).get();
        es.submit(new SubIntCallable(1000)).get();

        es.close();
    }
}
