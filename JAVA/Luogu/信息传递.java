package Java.Luogu;

import java.util.*;

public class 信息传递 {
    private static class StudentNode{
        final int birthday;
        Set<Integer> birthKnew;
        StudentNode(int birth){
            this.birthday=birth;
            this.birthKnew=new HashSet<>();
            birthKnew.add(birth);
        }
        public int hashCode(){
            return birthday;
        }
        public boolean equals(Object obj){
            if(obj==this) return true;
            if(obj==null) return false;
            return obj instanceof StudentNode s
                &&birthday==s.birthday;
        }
        public String toString() {
            return "StudentNode [birthday=" + birthday + ", birthKnew=" + birthKnew + "]";
        }
        
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        Map<Integer,StudentNode> students=new HashMap<>();
        Map<StudentNode,StudentNode> broadcast=new HashMap<>();
        Map<StudentNode,Set<Integer>> toSent=new HashMap<>();
        for(int i=1;i<=n;++i){
            int x=sc.nextInt();
            var stu1=students.computeIfAbsent(i,StudentNode::new);
            var stu2=students.computeIfAbsent(x,StudentNode::new);
            broadcast.put(stu1,stu2);
        }
        OUT:for(int round=1;;++round){
            toSent.clear();
            broadcast.forEach((from,_)->{ 
                toSent.put(from,new HashSet<>(from.birthKnew));
            });
            for(var entry:broadcast.entrySet()){
                var from=entry.getKey();
                var to=entry.getValue(); 
                if(toSent.get(from).contains(to.birthday)){
                    System.out.println(round);
                    break OUT;
                }
                to.birthKnew.addAll(toSent.get(from));
            }
        }
        sc.close();
    }
}
