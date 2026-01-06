package Java.Learn.MultiThread.GrabRedEnvelopes;

import java.util.*;

public class RedEnvelope {
    private final int cash;
    private static final List<RedEnvelope> envelopes;
    static{
        envelopes=new ArrayList<>(200);
        Random amount=new Random();
        for(int i=0;i<160;++i){
            envelopes.add(new RedEnvelope(amount.nextInt(30)+1));
        }
        for(int i=160;i<200;++i){
            envelopes.add(new RedEnvelope(amount.nextInt(70)+31));
        }
        Collections.shuffle(envelopes);
    }
    public RedEnvelope(int cash) {
        this.cash=cash;
    }
    public int getCash() {
        return cash;
    }
    public synchronized static RedEnvelope getRedEnvelope() {
        if(envelopes.size()>0){
            return envelopes.removeLast();
        }
        return null;
    }
}
