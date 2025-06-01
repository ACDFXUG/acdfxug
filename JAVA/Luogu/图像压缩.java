package JAVA.Luogu;

import java.util.*;
import static java.lang.Integer.*;

public class 图像压缩 {
    static final String HEX_RADIX="0123456789ABCDEF";
    private static final class GrayScale
    implements Comparable<GrayScale>{
        final int GSValue;
        GrayScale(String gs){
            this.GSValue=parseInt(gs,16);
        }
        public int compareTo(GrayScale gs){
            return GSValue-gs.GSValue;
        }
        public int hashCode(){
            return GSValue;
        }
        public boolean equals(Object gs){
            if(this==gs){
                return true;
            }
            if(gs==null||!(gs instanceof GrayScale)){
                return false;
            }
            return GSValue==((GrayScale)gs).GSValue;
        }
        public String toString(){
            return String.format("%02X",GSValue);
        }
    }
    static GrayScale toClosest(GrayScale gs,List<GrayScale> hex)
    throws NoSuchElementException{
        int minDis=0x7fffffff;
        for(GrayScale GS:hex){
            minDis=Math.min(minDis,Math.abs(GS.GSValue-gs.GSValue));
        }
        for(int i=0,l=hex.size();i<l;i++){
            GrayScale GS=hex.get(i);
            if(minDis==Math.abs(GS.GSValue-gs.GSValue)){
                return GS;
            }
        }
        throw new NoSuchElementException("not found!");
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        String[] gs=new String[n];
        for(int i=0;i<n;gs[i++]=sc.next());
        Map<GrayScale,Integer> GSCount=new HashMap<>();
        for(int i=0,l=gs[0].length();i<n;i++){
            for(int j=0;j<l;j+=2){
                String gsi=gs[i].substring(j,j+2);
                GrayScale GS=new GrayScale(gsi);
                GSCount.put(GS,GSCount.getOrDefault(GS,0)+1);
            }
        }
        List<GrayScale> hexGS=new ArrayList<>(16);
        GSCount.entrySet().stream()
        .sorted((E1,E2)->{
            int cnt1=E1.getValue(),cnt2=E2.getValue();
            return cnt1==cnt2?
            E1.getKey().compareTo(E2.getKey()):cnt2-cnt1;
        }).limit(16).map(Map.Entry::getKey).forEach(GS->{
            hexGS.add(GS);System.out.print(GS);
        });
        System.out.println();
        Map<GrayScale,Integer> GSIndex=new HashMap<>();
        for(int i=0,l=hexGS.size();i<l;i++){
            GSIndex.put(hexGS.get(i),i);
        }
        for(int i=0,l=gs[0].length();i<n;i++){
            StringBuilder hex=new StringBuilder();
            for(int j=0;j<l;j+=2){
                String gsi=gs[i].substring(j,j+2);
                GrayScale toHEX=toClosest(new GrayScale(gsi),hexGS);
                hex.append(HEX_RADIX.charAt(GSIndex.get(toHEX)));
            }
            System.out.println(hex);
        }
        sc.close();
    }
}
