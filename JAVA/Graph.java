package JAVA;

import java.util.*;

public class Graph<T> {
    private Map<T,Set<T>> adj;
    private int edge;
    private boolean isDirected;
    /**
     * 默认为无向图
     */
    public Graph(){
        this(false);
    }
    /**
     * 指定是否为有向图
     * @param isDirected
     */
    public Graph(boolean isDirected){
        this.adj=new HashMap<>();
        this.edge=0;
        this.isDirected=isDirected;
    }
    public void addEdge(T src,T dst){
        adj.computeIfAbsent(src,$->new LinkedHashSet<>()).add(dst);
        if(!isDirected){
            adj.computeIfAbsent(dst,$->new LinkedHashSet<>()).add(src);
        }
        ++edge;
    }
    public Set<T> getAdjacencySet(T v){
        return adj.getOrDefault(v,new LinkedHashSet<>());
    }
    public int getVertexs(){
        return adj.size();
    }
    public int getEdges(){
        return edge;
    }
    public Map<T,Set<T>> getAdjacencyList(){
        return adj;
    }
    /**
     * 广度优先搜索
     * @param src
     * @return path
     */
    public String BFS(T src){
        StringJoiner path=new StringJoiner("=>");
        Queue<T> bfs=new ArrayDeque<>();
        Set<T> visited=new HashSet<>();
        bfs.add(src);
        while(!bfs.isEmpty()){
            T node=bfs.poll();
            if(!visited.contains(node)){
                visited.add(node);
                path.add(node.toString());
                adj.getOrDefault(node,new LinkedHashSet<>()).forEach(bfs::add);
            }
        }
        return path.toString();
    }
    /**
     * 深度优先搜索
     * @param src
     * @return path
     */
    public String DFS(T src){
        StringJoiner path=new StringJoiner("=>");
        Deque<T> dfs=new ArrayDeque<>();
        Set<T> visited=new HashSet<>();
        dfs.push(src);
        while(!dfs.isEmpty()){
            T node=dfs.pop();
            if(!visited.contains(node)){
                visited.add(node);
                path.add(node.toString());
                adj.getOrDefault(node,new LinkedHashSet<>()).forEach(dfs::push);
            }
        }
        return path.toString();
    }
    public String toString(){
        StringBuilder sb=new StringBuilder();
        adj.forEach((K,V)->{
            sb.append("{").append(K).append("=>")
                .append(V).append("}\n");
        });
        return sb.toString();
    }
    public boolean equals(Object g){
        if(this==g) return true;
        if(g==null) return false;
        return g instanceof Graph<?> graph
            &&graph.isDirected==isDirected
            &&graph.adj.equals(adj);
    }
    public int hashCode(){
        return adj.hashCode();
    }
    public int[][] getAdjacencyMatrix(){
        int n=adj.size();
        int[][] matrix=new int[n][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                matrix[i][j]=adj.getOrDefault(i,new LinkedHashSet<>()).contains(j)?1:0;
            }
        }
        return matrix;
    }
    /**
     * @return the isDirected
     */
    public boolean isDirected(){
        return isDirected;
    }
    public boolean contains(T vertex){
        return adj.containsKey(vertex);
    }
    public boolean containsEdge(T src,T dst){
        return adj.getOrDefault(src,new LinkedHashSet<>()).contains(dst);
    }
    public void removeEdge(T src,T dst){
        adj.getOrDefault(src,new LinkedHashSet<>()).remove(dst);
    }
    public void removeVertex(T vertex){
        adj.remove(vertex);
        adj.forEach((K,V)->V.remove(vertex));
    }
    public static void main(String[] args) {
        Graph<Integer> graph=new Graph<>(true);
        graph.addEdge(1,2);
        graph.addEdge(1,3);
        graph.addEdge(1,4);
        graph.addEdge(2,3);
        graph.addEdge(2,4);
        graph.addEdge(3,4);
        System.out.println(graph);
        System.out.println(graph.DFS(2));
        System.out.println(graph.BFS(1));
    }
}
