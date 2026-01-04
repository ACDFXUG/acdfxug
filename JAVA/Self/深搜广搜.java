package Java.Self;

import java.util.*;

public class 深搜广搜 {
    @SuppressWarnings("unused")
    private static class Graph<T>{
        final Map<T,Set<T>> adj;// 邻接表
        Graph(){
            this.adj=new HashMap<>();
        }
        Graph(Map<T,Set<T>> adj){
            this.adj=adj;
        }
        void addEdge(T src,T dst){
            adj.computeIfAbsent(src,_->new HashSet<>()).add(dst);
            adj.computeIfAbsent(dst,_->new HashSet<>()).add(src);
        }
        /**
         * 深度优先搜索
         * @param src 源点
         * @return 访问路径
         */
        String DFS(T src){
            StringJoiner path=new StringJoiner("=>");
            Set<T> visited=new HashSet<>();
            Deque<T> dfs=new ArrayDeque<>();
            dfs.push(src);
            visited.add(src);
            while(!dfs.isEmpty()){
                T node=dfs.pop();
                path.add(node.toString());
                adj.getOrDefault(node,new LinkedHashSet<>()).forEach(n->{
                    if(!visited.contains(n)){
                        visited.add(n);
                        dfs.push(n);
                    }
                });
            }
            return path.toString();
        }
        /**
         * 广度优先搜索
         * @param src 源点
         * @return 访问路径
         */
        String BFS(T src){
            StringJoiner path=new StringJoiner("=>");
            Set<T> visited=new HashSet<>();
            Queue<T> bfs=new ArrayDeque<>();
            bfs.add(src);
            visited.add(src);
            while(!bfs.isEmpty()){
                T node=bfs.poll();
                path.add(node.toString());
                adj.getOrDefault(node,new LinkedHashSet<>()).forEach(n->{
                    if(!visited.contains(n)){
                        visited.add(n);
                        bfs.add(n);
                    }
                });
            }
            return path.toString();
        }
        public String toString(){
            StringBuilder sb=new StringBuilder();
            adj.forEach((K,V)->{
                sb.append("{").append(K).append(": ")
                    .append(V).append("}\n");
            });
            return sb.toString();

        }
    }
    public static void main(String[] args) {
        Graph<Integer> graph=new Graph<>();
        graph.addEdge(1,2);
        graph.addEdge(1,3);
        graph.addEdge(1,4);
        graph.addEdge(2,3);
        graph.addEdge(2,4);
        graph.addEdge(3,4);
        System.out.println(graph.DFS(1));
        System.out.println(graph.BFS(2));
        System.out.println(graph);
    }
}
