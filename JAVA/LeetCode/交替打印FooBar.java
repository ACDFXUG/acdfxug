package JAVA.LeetCode;

public class 交替打印FooBar {
    @SuppressWarnings("unused")
    private static class FooBar{
        int n;
        boolean isFoo;
        final Object lock=new Object();
        FooBar(int n){
            this.n=n;
            this.isFoo=true;
        }
        void foo(Runnable printFoo) throws InterruptedException {
        
            for (int i = 0; i < n; i++) {
                synchronized(lock){
                    while(!isFoo){
                        lock.wait();
                    }
                    // printFoo.run() outputs "foo". Do not change or remove this line.
                    printFoo.run();
                    isFoo=false;
                    lock.notifyAll();
                }
            }
            
            
        }
        void bar(Runnable printBar) throws InterruptedException {
        
            for (int i = 0; i < n; i++) {
                synchronized(lock){
                    while(isFoo){
                        lock.wait();
                    }   
                    // printBar.run() outputs "bar". Do not change or remove this line.
                    printBar.run();
                    isFoo=true;
                    lock.notifyAll();
                }
            }
            
            
        }
    }
    public static void main(String[] args) {
        
    }
}
