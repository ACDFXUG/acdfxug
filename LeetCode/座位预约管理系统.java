package LeetCode;



public class 座位预约管理系统 {
    private static class SeatManager{
        // Map<Integer,Boolean> isFree;
        boolean[] isFull;
        SeatManager(int n){
            // this.isFree=new LinkedHashMap<Integer,Boolean>();
            // for(int i=1;i<=n;i++){
            //     isFree.put(i,true);
            // }
            this.isFull=new boolean[n];
        }
        int reserve(){
            // int index=isFree.entrySet().stream()
            // .filter(Map.Entry::getValue)
            // .findFirst()
            // .get()
            // .getKey();
            // isFree.put(index,false);
            // return index;
            for(int i=0;i<isFull.length;i++){
                if(!isFull[i]){
                    isFull[i]=true;
                    return i+1;
                }
            }
            return 0;
        }
        void unreserve(int seatNumber){
            // isFree.put(seatNumber,true);
            isFull[seatNumber-1]=false;
        }
    }
    public static void main(String[] args) {
        SeatManager sm=new SeatManager(5);
        System.out.println(sm.reserve());
        System.out.println(sm.reserve());
        sm.unreserve(2);
        System.out.println(sm.reserve());
        System.out.println(sm.reserve());
        System.out.println(sm.reserve());
        System.out.println(sm.reserve());
        sm.unreserve(5);
    }
}
