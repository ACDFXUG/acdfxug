package Java.Learn.MultiThread;

public class ThreadSafety {
    public static void main(String[] args) {
        Account acnt=new Account(100000);
        var xm=new DrawThread("XM",acnt);
        var xh=new DrawThread("XH",acnt);
        xm.start();
        xh.start();
    }
}

class Account{
    double cash;

    /**
     * @return the cash
     */
    public double getCash() {
        return cash;
    }

    /**
     * @param cash the cash to set
     */
    public void setCash(double cash) {
        this.cash = cash;
    }

    /**
     * 
     */
    public Account() {
    }

    /**
     * @param cash
     */
    public Account(double cash) {
        this.cash = cash;
    }
    
    void drawCash(double cash){
        if(this.cash>=cash){
            this.cash-=cash;
            System.out.println(Thread.currentThread().getName()+"取钱成功，余额为："+this.cash);
        }else{
            System.out.println(Thread.currentThread().getName()+"取钱失败，余额不足！");
        }
    }
}

class DrawThread extends Thread{
    Account acnt;
    DrawThread(String name,Account acnt){
        super(name);
        this.acnt=acnt;
    }
    public void run(){ 
        acnt.drawCash(100000);
    }
}
