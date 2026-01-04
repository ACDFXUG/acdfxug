package Java.Self;

import java.util.*;

public class WGraph<T> {
    private Map<T,Map<T,Integer>> adjWeight;
    private boolean isDirected;
    private int edge;
    private Set<T> vertex;
    WGraph(){
        this(false);
    }
    WGraph(boolean isDirected){
        this.adjWeight=new HashMap<>();
        this.vertex=new HashSet<>();
        this.isDirected=isDirected;
        this.edge=0;
    }
    public void addEdge(T src,T dst,int w){
        adjWeight.computeIfAbsent(src,_->new LinkedHashMap<>()).put(dst,w);
        if(!isDirected){
            adjWeight.computeIfAbsent(dst,_->new LinkedHashMap<>()).put(src,w);
        }
        ++edge;
        vertex.add(src);
        vertex.add(dst);
    }
    public Map<T,Integer> getAdjacencyMap(T v){
        return adjWeight.get(v);
    }
    public int getVertices(){
        return vertex.size();
    }
    public int getEdges(){
        return edge;
    }
    public Map<T,Map<T,Integer>> getAdjacencyMap(){
        return adjWeight;
    }
    public boolean contains(T vertex){
        return this.vertex.contains(vertex);
    }
    public boolean containsEdge(T src,T dst){
        return adjWeight.getOrDefault(src,new LinkedHashMap<>()).containsKey(dst);
    }
    public void removeEdge(T src,T dst){ 
        adjWeight.getOrDefault(src,new LinkedHashMap<>()).remove(dst);
        if(!isDirected){
            adjWeight.getOrDefault(dst,new LinkedHashMap<>()).remove(src);
        }
    }
    public void removeVertex(T vertex){
        adjWeight.remove(vertex);
        adjWeight.forEach((_,V)->V.remove(vertex));
    }
    public boolean isDirected(){
        return isDirected;
    }
    public int[][] getAdjacencyMatrix(){
        int n=adjWeight.size();
        int[][] matrix=new int[n][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                int w=adjWeight.getOrDefault(i,new LinkedHashMap<>()).getOrDefault(j,0);
                matrix[i][j]=w;
            }
        }
        return matrix;
    }
    public int getWeight(T src,T dst){
        return adjWeight.getOrDefault(src,new LinkedHashMap<>()).getOrDefault(dst,0);
    }
    public void setWeight(T src,T dst,int newW){
        adjWeight.getOrDefault(src,new LinkedHashMap<>()).put(dst,newW);
        if(!isDirected){
            adjWeight.getOrDefault(dst,new LinkedHashMap<>()).put(src,newW);
        }
    }
    public int hashCode(){
        return adjWeight.hashCode();
    }
    public boolean equals(Object o){
        if(this==o) return true;
        if(o==null) return false;
        return o instanceof WGraph<?> graph
            &&graph.isDirected==isDirected
            &&graph.adjWeight.equals(adjWeight);
    }
    public String toString(){
        StringBuilder sb=new StringBuilder();
        adjWeight.forEach((K,V)->{
            sb.append("{").append(K).append("=>")
                .append(V).append("}\n");
        });
        return sb.toString();
    }
    public String BFS(T src){
        StringBuilder path=new StringBuilder();
        Queue<T> bfs=new ArrayDeque<>();
        Set<T> visited=new HashSet<>();
        bfs.add(src);
        T last=null;
        while(!bfs.isEmpty()){
            T node=bfs.poll();
            if(!visited.contains(node)){
                visited.add(node);
                if(bfs.isEmpty()&&path.isEmpty()){
                    path.append(node);
                }else{
                    int w=adjWeight.getOrDefault(last,new LinkedHashMap<>()).getOrDefault(node,0);
                    if(w>0)
                    path.append("|-(").append(w).append(")->|").append(node);
                }
                if(adjWeight.containsKey(node))
                    adjWeight.get(node).keySet().forEach(bfs::add);
            }
            last=node;
        }
        return path.toString();
    }
    public String DFS(T src){
        StringBuilder path=new StringBuilder();
        Deque<T> dfs=new ArrayDeque<>();
        Set<T> visited=new HashSet<>();
        dfs.push(src);
        T last=null;
        while(!dfs.isEmpty()){ 
            T node=dfs.pop();
            if(!visited.contains(node)){
                visited.add(node);
                if(dfs.isEmpty()&&path.isEmpty()){
                    path.append(node);
                }else{
                    int w=adjWeight.getOrDefault(last,new LinkedHashMap<>()).getOrDefault(node,0);
                    if(w>0)
                    path.append("|-(").append(w).append(")->|").append(node);
                }
                if(adjWeight.containsKey(node))
                    adjWeight.get(node).keySet().forEach(dfs::push);
            }
            last=node;
        }
        return path.toString();
    }
    public List<String> dijkstra(T src){
        Map<T,Integer> dist=new HashMap<>();// 最短距离
        Map<T,T> prev=new HashMap<>();// 前驱节点
        Set<T> visited=new HashSet<>();// 已经访问过的节点
        PriorityQueue<T> pq=new PriorityQueue<>((a,b)->dist.get(a)-dist.get(b));
        pq.add(src);
        while(!pq.isEmpty()){
            T node=pq.poll();
            if(!visited.contains(node)){
                visited.add(node);
                adjWeight.getOrDefault(node,new LinkedHashMap<>()).forEach((K,V)->{
                    if(!visited.contains(K)){
                        if(!dist.containsKey(K)){
                            dist.put(K,V);
                            prev.put(K,node);
                            pq.add(K);
                        }else{
                            if(dist.get(node)+V<dist.get(K)){
                                dist.put(K,dist.get(node)+V);
                                prev.put(K,node);
                                pq.add(K); // 重新加入优先队列以确保最小堆性质
                            }
                        }
                    }
                });
            }
        }
        List<String> ans=new ArrayList<>(prev.size());
        prev.forEach((to,from)->ans.add(from+"->"+to));
        return ans;
    }
    public static void main(String[] args) {
        WGraph<Integer> graph=new WGraph<>(true);
        graph.addEdge(1,2,1);
        graph.addEdge(1,3,2);
        graph.addEdge(1,4,3);
        graph.addEdge(2,3,4);
        graph.addEdge(2,4,5);
        graph.addEdge(3,4,6);
        System.out.println(graph);
        System.out.println(graph.DFS(2));
        System.out.println(graph.BFS(1));
        System.out.println(graph.dijkstra(1));
    }
}
