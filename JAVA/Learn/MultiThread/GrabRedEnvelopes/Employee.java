package Java.Learn.MultiThread.GrabRedEnvelopes;

import java.util.concurrent.Callable;

public class Employee implements Callable<Integer> {
    private final int id;
    private int totalCash;
    public Employee(int id){
        this.id=id;
        this.totalCash=0;
    }
    public int getId(){
        return id;
    }
    public Integer call() throws RuntimeException{
        while(true){
            var redEnvelope=RedEnvelope.getRedEnvelope();
            if(redEnvelope==null){
                break;
            }
            totalCash+=redEnvelope.getCash();
        }
        return totalCash;
    }
}
