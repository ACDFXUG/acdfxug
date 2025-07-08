package JAVA.LeetCode;

public class 省份数量 {
    static void dfs(int city,int[][] isConnected,boolean[] visited){
        visited[city]=true;
        for(int i=0;i<isConnected.length;++i){
            if(isConnected[city][i]==1&&!visited[i]){
                dfs(i,isConnected,visited);
            }
        }
    }
    static int findCircleNum(int[][] isConnected) {
        int com=0;
        boolean[] visited=new boolean[isConnected.length];
        for(int i=0;i<isConnected.length;++i){
            if(!visited[i]){
                dfs(i,isConnected,visited);
                ++com;
            }
        }
        return com;
    }
    public static void main(String[] args) {
        int[][] isConnected=new int[][]{{1,1,0},{1,1,0},{0,0,1}};
        System.out.println(findCircleNum(isConnected));
    }
}
