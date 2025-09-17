package JAVA.LeetCode;

import java.util.*;

public class 设计路由器 {
    private static record Package(int src,int dst,int tm)
    implements Comparable<Package>{
        public int hashCode(){
            return Objects.hash(src,dst,tm);
        }
        public boolean equals(Object o){
            if(this==o) return true;
            if(o==null) return false;
            return o instanceof Package p
                &&src==p.src&&dst==p.dst&&tm==p.tm;
        }
        public int compareTo(Package p){
            if(tm==p.tm){
                return dst==p.dst?src-p.src:dst-p.dst;
            }else{
                return tm-p.tm;
            }
        }
    }
    private static class Router{
        final int maxLimit;
        Map<Integer,TreeSet<Package>> dstPkgs;
        LinkedHashMap<Package,Integer> allPkgs;
        Map.Entry<Package,Integer> eldestPkg=null;
        Router(int memoryLimit){
            this.maxLimit=memoryLimit;
            this.dstPkgs=new HashMap<>();
            this.allPkgs=new LinkedHashMap<>(memoryLimit){
                protected boolean removeEldestEntry(Map.Entry<Package,Integer> eldest){
                    if(size()>maxLimit){
                        eldestPkg=eldest;
                        return true;
                    }
                    return false;
                }
            };
        }
        boolean addPacket(int source, int destination, int timestamp) {
            var pkg=new Package(source,destination,timestamp);
            if(allPkgs.containsKey(pkg)){
                return false;
            }else{
                allPkgs.put(pkg,timestamp);
                if(eldestPkg!=null){
                    var p=eldestPkg.getKey();
                    dstPkgs.get(p.dst).remove(p);
                }
                dstPkgs.computeIfAbsent(destination,_->new TreeSet<>()).add(pkg);
                return true;
            }
        }
        int[] forwardPacket() {
            if(allPkgs.isEmpty()){
                return new int[0];
            }else{
                var firstPkg=allPkgs.firstEntry().getKey();
                allPkgs.remove(firstPkg);
                dstPkgs.get(firstPkg.dst).remove(firstPkg);
                return new int[]{firstPkg.src,firstPkg.dst,firstPkg.tm};
            }
        }
        int getCount(int destination, int startTime, int endTime) {
            if(allPkgs.isEmpty()){
                return 0;
            }else{
                if(dstPkgs.containsKey(destination)){
                    var pkgs=dstPkgs.get(destination);
                    return pkgs.subSet(new Package(-1,-1,startTime),true,new Package(300000,300000,endTime),true).size();
                }else{
                    return 0;
                }
            }
        }
    }
    public static void main(String[] args) {
        Router rt=new Router(3);
        System.out.println(rt.addPacket(1,4,90));
        System.out.println(rt.addPacket(2,5,90));
        System.out.println(rt.addPacket(1,4,90));
        System.out.println(rt.addPacket(3,5,95));
        System.out.println(rt.addPacket(4,5,105));
        System.out.println(Arrays.toString(rt.forwardPacket()));
        System.out.println(rt.addPacket(5,2,110));
        System.out.println(rt.getCount(5,100,110));
    }
}
