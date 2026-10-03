package Java.LeetCode;

import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

public class 打印零与奇偶数 {
    static final IntConsumer printNumber=System.out::print;
    static void P(Semaphore s) throws InterruptedException{
        s.acquire();
    }
    static void V(Semaphore s) throws InterruptedException{
        s.release();
    }
    static class PrintThread extends Thread{
        ZeroEvenOdd zeo;
        final int TURN,N;
        PrintThread(int n,int TURN,ZeroEvenOdd zeo){
            super();
            this.N=n;
            this.TURN=TURN;
            this.zeo=zeo;
        }
        public void run(){
            try{
                switch(TURN){
                    case 0->zeo.zero(printNumber);
                    case 1->zeo.odd(printNumber);
                    case 2->zeo.even(printNumber);
                }
            }catch(InterruptedException _){}
        }
    }

    static class ZeroEvenOdd {
        private int n;
        private final Semaphore zero,odd,even;
        public ZeroEvenOdd(int n) {
            this.n = n;
            this.zero=new Semaphore(1);
            this.odd=new Semaphore(0);
            this.even=new Semaphore(0);
        }

        // printNumber.accept(x) outputs "x", where x is an integer.
        public void zero(IntConsumer printNumber) throws InterruptedException {
            for(int i=1;i<=n;++i){
                P(zero);
                printNumber.accept(0);
                if((i&1)==1) V(odd);
                else V(even);
            }
        }

        public void even(IntConsumer printNumber) throws InterruptedException {
            for(int i=2;i<=n;i+=2){
                P(even);
                printNumber.accept(i);
                V(zero);
            }
        }

        public void odd(IntConsumer printNumber) throws InterruptedException {
            for(int i=1;i<=n;i+=2){
                P(odd);
                printNumber.accept(i);
                V(zero);
            }
        }
    }
    public static void main(String[] args) {
        ZeroEvenOdd zeo=new ZeroEvenOdd(100);
        PrintThread pt0=new PrintThread(5,0,zeo);
        PrintThread pt1=new PrintThread(5,1,zeo);
        PrintThread pt2=new PrintThread(5,2,zeo);
        pt0.start();
        pt1.start();
        pt2.start();
    }
}
