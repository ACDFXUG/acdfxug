package LeetCode;

import java.util.*;

public class 快照数组 {
    private static class SnapshotArray{
        int snapId;
        ArrayList<HashMap<Integer,Integer>> snapshots;
        SnapshotArray(int length){
            this.snapId=0;
            this.snapshots=new ArrayList<>(length){{
                for(int i=0;i<length;i++) add(null);
            }};
        }
        void set(int index, int val) {
            if(snapshots.get(index)==null){
                snapshots.set(index,new HashMap<>());
            }
            snapshots.get(index).put(snapId,val);
        }
        int snap() {
            return snapId++;
        }
        int get(int index, int snap_id) {
            var snap=snapshots.get(index);
            if(snap!=null){
                int id=snap_id;
                for(;id>=0;id--){
                    if(snap.containsKey(id)){
                        return snap.get(id);
                    }
                }
            }
            return 0;
        }
    }
    public static void main(String[] args) {
        SnapshotArray ssa=new SnapshotArray(2);
        System.out.println(ssa.snap());
        System.out.println(ssa.get(1,0));
        System.out.println(ssa.get(0,0));
        ssa.set(1,2);
        System.out.println(ssa.get(1,0));
        ssa.set(0,20);
        System.out.println(ssa.get(0,0));
        ssa.set(0,7);
    }
}
