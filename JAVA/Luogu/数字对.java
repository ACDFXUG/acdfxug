package Java.Luogu;

// import java.util.*;

// public class 数字对 {
//     private static class State{
//         int x,y,step;
//         State(int x,int y,int step){
//             this.x=x;
//             this.y=y;
//             this.step=step;
//         }
//         public int hashCode(){
//             return Objects.hash(x,y);
//         }
//         public boolean equals(Object s){
//             if(this==s) return true;
//             if(s==null) return false;
//             return s instanceof State st
//                 &&x==st.x&&y==st.y;
//         }
//     }
//     static int BFS(int n,int a,int b){
//         Queue<State> bfs=new LinkedList<>();
//         Set<State> visited=new HashSet<>();
//         State start=new State(a,b,0);
//         visited.add(start);
//         bfs.add(start);
//         while(!bfs.isEmpty()){
//             var curState=bfs.poll();
//             if(curState.x==n||curState.y==n){
//                 return curState.step;
//             }
//             var nxtS1=new State(curState.x+curState.y,curState.y,curState.step+1);
//             if(nxtS1.x==n||nxtS1.x==n) return nxtS1.step;
//             if(!visited.contains(nxtS1)){
//                 visited.add(nxtS1);
//                 bfs.add(nxtS1);
//             }
//             var nxtS2=new State(curState.x,curState.x+curState.y,curState.step+1);
//             if(nxtS2.x==n||nxtS2.x==n) return nxtS2.step;
//             if(!visited.contains(nxtS2)){
//                 visited.add(nxtS2);
//                 bfs.add(nxtS2);
//             }
//         }
//         return -1;
//     }
//     public static void main(String[] args) {
//         Scanner sc=new Scanner(System.in);
//         int n=sc.nextInt();
//         System.out.println(BFS(n,1,1));
//         sc.close();
//     }
// }

import java.util.*;

public class 数字对 {
    // 计算从(a,b)逆向回(1,1)的步数（辗转相除法）
    private static int calc(int a, int b) {
        if (b == 1) return a - 1; // 当b=1时，需(a-1)步
        if (b == 0) return Integer.MAX_VALUE; // 不可达
        return a / b + calc(b, a % b);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.close();

        if (n == 1) {
            System.out.println(0);
            return;
        }

        int minSteps = Integer.MAX_VALUE;
        // 枚举所有可能的k（1 ≤ k < n）
        for (int k = 1; k < n; k++) {
            if (gcd(n, k) != 1) continue; // 跳过非互质的k
            int steps = calc(n, k);
            if (steps < minSteps) minSteps = steps;
        }
        System.out.println(minSteps);
    }

    // 求最大公约数（优化为迭代避免递归栈溢出）
    private static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}