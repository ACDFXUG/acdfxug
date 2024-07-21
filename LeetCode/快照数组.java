package LeetCode;

import java.util.*;

public class 快照数组 {
    private static class SnapshotArray{
        int snap[],snapID;
        Map<Integer,int[]> SnapShot;
        SnapshotArray(int length){
            this.snap=new int[length];
            this.snapID=0;
            this.SnapShot=new HashMap<Integer,int[]>();
        }
        void set(int index,int val){
            snap[index]=val;
        }
        int snap(){
            SnapShot.put(snapID++,snap.clone());
            return snapID-1;
        }
        int get(int index,int snap_id){
            return SnapShot.get(snap_id)[index];
        }
    }
    public static void main(String[] args) {
        SnapshotArray ssa=new SnapshotArray(3);
        ssa.set(0,5);
        System.out.println(ssa.snap());
        ssa.set(0,6);
        System.out.println(ssa.get(0,0));
    }
}
