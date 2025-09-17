package JAVA.Luogu;

import java.util.*;

public class 家谱树 {
    private static class FamilyTree{
        final Map<Integer,Set<Integer>> fmlMap;
        FamilyTree(int N){
            this.fmlMap=new HashMap<>(N);
        }
        void addEdge(int prt,int son){
            fmlMap.computeIfAbsent(prt,_->new HashSet<>()).add(son);
        }
        void deleteVertex(int mem){
            fmlMap.forEach((_,sons)->sons.remove(mem));
            fmlMap.remove(mem);
        }
    }
    static int find0Indegree(FamilyTree fmlTree){ 
        for(var pair:fmlTree.fmlMap.entrySet()){
            if(pair.getValue().size()==0) return pair.getKey();
        }
        return -1;
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int N=sc.nextInt();
        FamilyTree fmlTree=new FamilyTree(N);
        for(int p=1,son;p<=N;++p){
            do{
                son=sc.nextInt();
                if(son!=0) fmlTree.addEdge(son,p);
                else break;
            }while(true);
            fmlTree.fmlMap.putIfAbsent(p,new HashSet<>());
        }
        sc.close();
        for(int i=1;i<=N;++i){
            int mem=find0Indegree(fmlTree);
            if(mem==-1){
                System.out.println("0");
                return;
            }
            System.out.printf(i==N?"%d\n":"%d ",mem);
            fmlTree.deleteVertex(mem);
        }
    }
}
