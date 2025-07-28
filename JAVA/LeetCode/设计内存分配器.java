package JAVA.LeetCode;

import java.util.*;

public class 设计内存分配器 {
    private static record Memory(int ptr,int size)
    implements Comparable<Memory>{
        public int compareTo(Memory o){
            return ptr-o.ptr;
        }
        public boolean equals(Object mem){
            if(mem==this) return true;
            if(mem==null) return false;
            return mem instanceof Memory m
                &&ptr==m.ptr&&size==m.size;
        }
    }
    private static class Allocator{
        final int maxMemSize;
        final TreeSet<Memory> alcted;
        final Map<Integer,List<Memory>> memory;
        Allocator(int n){
            this.maxMemSize=n;
            this.alcted=new TreeSet<>();
            this.memory=new HashMap<>();
        }
        int allocate(int size, int mID) {
            if(alcted.isEmpty()){
                if(size<=maxMemSize){
                    Memory alloc=new Memory(0,size);
                    alcted.add(alloc);
                    memory.computeIfAbsent(mID,$->new ArrayList<>()).add(alloc);
                    return 0;
                }else return -1;
            }else{
                var left=alcted.first();
                if(left.ptr>=size){
                    Memory alloc=new Memory(0,size);
                    alcted.add(alloc);
                    memory.computeIfAbsent(mID,$->new ArrayList<>()).add(alloc);
                    return 0;
                }
                var it=alcted.iterator();
                var cur=it.next();
                while(it.hasNext()){
                    var next=it.next();
                    if(cur.ptr+cur.size+size<=next.ptr){
                        Memory alloc=new Memory(cur.ptr+cur.size,size);
                        alcted.add(alloc);
                        memory.computeIfAbsent(mID,$->new ArrayList<>()).add(alloc);
                        return alloc.ptr;
                    }
                    cur=next;
                }
                if(cur.ptr+cur.size+size<=maxMemSize){
                    Memory alloc=new Memory(cur.ptr+cur.size,size);
                    alcted.add(alloc);
                    memory.computeIfAbsent(mID,$->new ArrayList<>()).add(alloc);
                    return alloc.ptr;
                }
                return -1;
            }
        }
        int freeMemory(int mID) {
            if(!memory.containsKey(mID)) return 0;
            var mem=memory.get(mID);
            memory.remove(mID);
            int ans=0;
            for(var alloc:mem){
                alcted.remove(alloc);
                ans+=alloc.size;
            }
            return ans;
        }
    }
    public static void main(String[] args) {
        Allocator all=new Allocator(10);
        System.out.println(all.allocate(1,1));
        System.out.println(all.allocate(1,2));
        System.out.println(all.allocate(1,3));
        System.out.println(all.freeMemory(2));
        System.out.println(all.allocate(3,4));
        System.out.println(all.allocate(1,1));
        System.out.println(all.allocate(1,1));
        System.out.println(all.freeMemory(1));
        System.out.println(all.allocate(10,2));
        System.out.println(all.freeMemory(7));
    }
}
