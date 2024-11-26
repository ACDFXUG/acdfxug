package LeetCode;

import java.util.*;

public class 设计位集 {
    private static class BitSet{
        byte[] bits,flipBits;
        Set<Integer> zeroIndex,oneIndex;
        BitSet(int size){
            this.bits=new byte[size];
            this.flipBits=new byte[size];
            for(int i=0;i<size;i++){
                flipBits[i]=1;
            }
            this.zeroIndex=new HashSet<Integer>(size){{
                for(int i=0;i<size;add(i++));
            }};
            this.oneIndex=new HashSet<Integer>(size);
        }
        void fix(int idx) {
            bits[idx]=1;
            flipBits[idx]=0;
            zeroIndex.remove(idx);
            oneIndex.add(idx);
        }
        void unfix(int idx) {
            bits[idx]=0;
            flipBits[idx]=1;
            zeroIndex.add(idx);
            oneIndex.remove(idx);
        }
        void flip() {
            var t=bits;
            bits=flipBits;
            flipBits=t;
            var tmp=zeroIndex;
            zeroIndex=oneIndex;
            oneIndex=tmp;
        }
        boolean all() {
            return zeroIndex.isEmpty();
        }
        boolean one() {
            return oneIndex.size()>0;
        }
        int count() {
            return oneIndex.size();
        }
        public String toString() {
            StringBuilder ans=new StringBuilder(bits.length);
            for(byte b:bits){
                ans.append(b==0?'0':'1');
            }
            return ans.toString();
        }
    }
    public static void main(String[] args) {
        BitSet bs=new BitSet(5);
        bs.fix(3);
        bs.fix(1);
        bs.flip();
        System.out.println(bs.all());
        bs.unfix(0);
        bs.flip();
        System.out.println(bs.one());
        bs.unfix(0);
        System.out.println(bs.count());
        System.out.println(bs);
    }
}
