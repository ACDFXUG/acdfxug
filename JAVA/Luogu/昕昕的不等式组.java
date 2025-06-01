package JAVA.Luogu;

import java.util.*;
import java.util.regex.*;

public class 昕昕的不等式组 {
    private static record Edge(
        int offset,
        boolean contains,
        boolean isLeft
    )implements Comparable<Edge>{
        public int compareTo(Edge eg){
            if(isLeft!=eg.isLeft){
                throw new IllegalArgumentException();
            }
            if(offset!=eg.offset){
                return isLeft?
                eg.offset-offset:
                offset-eg.offset;
            }else{
                if(contains&&!eg.contains){
                    return 1;
                }else if(!contains&&eg.contains){
                    return -1;
                }else{
                    return 0;
                }
            }
        }
        public String toString(){
            return isLeft?
            offset+(contains?"<=":"<"):
            (contains?"<=":"<")+offset;
        }
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        String s=sc.next();
        final Pattern eq=Pattern.compile(s+"([<=>]+)([-]?\\d+)");
        List<Edge> left=new ArrayList<>(n),
        right=new ArrayList<>(n);
        for(int i=0;i<n;i++){
            Matcher op=eq.matcher(sc.next());
            if(op.matches()){
                String ops=op.group(1);
                int offset=Integer.parseInt(op.group(2));
                switch(ops){
                    case ">="->left.add(new Edge(offset,true,true));
                    case ">"->left.add(new Edge(offset,false,true));
                    case "<="->right.add(new Edge(offset,true,false));
                    case "<"->right.add(new Edge(offset,false,false));
                    default->throw new IllegalArgumentException();
                }
            }
        }
        if(left.isEmpty()){
            right.sort(null);
            Edge eg=right.get(0);
            System.out.println(eg.contains?
            s+"<="+eg.offset:s+"<"+eg.offset);
        }else if(right.isEmpty()){
            left.sort(null);
            Edge eg=left.get(0);
            System.out.println(eg.contains?
            s+">="+eg.offset:s+">"+eg.offset);
        }else{
            left.sort(null);
            right.sort(null);
            Edge l=left.get(0),r=right.get(0);
            if(l.offset<r.offset){
                System.out.println(l+s+r);
            }else if(l.offset==r.offset){
                if(l.contains&&r.contains){
                    System.out.println(s+"="+l.offset);
                }else{
                    System.out.println("No Answer!");
                }
            }else{
                System.out.println("No Answer!");
            }
        }
        sc.close();
    }
}
