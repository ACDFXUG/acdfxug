package Java.LeetCode;

import java.util.*;

public class 座位预约管理系统 {
    private static class SeatManager{
        final Queue<Integer> free;
        final Set<Integer> taken;
        SeatManager(int n){
            this.free=new PriorityQueue<Integer>(n){{
                for(int i=1;i<=n;add(i++));
            }};
            this.taken=new HashSet<Integer>();
        }
        int reserve(){
            int seat=free.poll();
            taken.add(seat);
            return seat;
        }
        void unreserve(int seatNumber) {
            taken.remove(seatNumber);
            free.add(seatNumber);
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
