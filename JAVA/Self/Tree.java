package Java.Self;

import java.util.*;

public class Tree {
    @SuppressWarnings("unused")
    private static class TreeNode<T>{
        T data;
        TreeNode<T> LEFT,RIGHT;
        TreeNode(T data){
            this.data=data;
            this.LEFT=null;
            this.RIGHT=null;
        }
        TreeNode(TreeNode<T> root){
            this.data=root.data;
            this.LEFT=root.LEFT;
            this.RIGHT=root.RIGHT;
        }
        TreeNode<T> addLeft(T data){
            LEFT=new TreeNode<T>(data);
            return this;
        }
        TreeNode<T> addLeft(TreeNode<T> left){
            LEFT=new TreeNode<T>(left);
            return this;
        }
        TreeNode<T> addRight(T data){
            RIGHT=new TreeNode<T>(data);
            return this;
        }
        TreeNode<T> addRight(TreeNode<T> right){
            RIGHT=new TreeNode<T>(right);
            return this;
        }
        TreeNode<T> getLeft(){
            return LEFT;
        }
        TreeNode<T> getRight(){
            return RIGHT;
        }
        T getData(){
            return data;
        }
        static<T> List<T> preOrdered(TreeNode<T> root){
            final List<T> preOrder=new ArrayList<>();
            if(root==null) return preOrder;
            else{
                preOrder.add(root.data);
                preOrder.addAll(preOrdered(root.LEFT));
                preOrder.addAll(preOrdered(root.RIGHT));
            }
            return preOrder;
        }
        List<T> preOrdered(){
            return preOrdered(this);
        }
        static<T> List<T> inOrdered(TreeNode<T> root){
            final List<T> inOrder=new ArrayList<>();
            if(root==null) return inOrder;
            else{
                inOrder.addAll(inOrdered(root.LEFT));
                inOrder.add(root.data);
                inOrder.addAll(inOrdered(root.RIGHT));
            }
            return inOrder;
        }
        List<T> inOrdered(){
            return inOrdered(this);
        }
        static<T> List<T> postOrdered(TreeNode<T> root){
            final List<T> postOrder=new ArrayList<>();
            if(root==null) return postOrder;
            else{
                postOrder.addAll(postOrdered(root.LEFT));
                postOrder.addAll(postOrdered(root.RIGHT));
                postOrder.add(root.data);
            }
            return postOrder;
        }
        List<T> postOrdered(){
            return postOrdered(this);
        }
        @SuppressWarnings("unchecked")
        public boolean equals(Object tree){
            if(this==tree){
                return true;
            }else if(!(tree instanceof TreeNode)){
                return false;
            }else{
                TreeNode<T> other=(TreeNode<T>)tree;
                if(data.equals(other.data)){
                    if(LEFT==null&&other.LEFT==null){
                        if(RIGHT==null&&other.RIGHT==null){
                            return true;
                        }else if(RIGHT!=null&&other.RIGHT!=null){
                            return RIGHT.equals(other.RIGHT);
                        }else{
                            return false;
                        }
                    }else if(LEFT!=null&&other.LEFT!=null){
                        if(RIGHT!=null&&other.RIGHT!=null){
                            return LEFT.equals(other.LEFT)&&
                            RIGHT.equals(other.RIGHT);
                        }else if(RIGHT==null&&other.RIGHT==null){
                            return LEFT.equals(other.LEFT);
                        }else{
                            return false;
                        }
                    }else{
                        return false;
                    }
                }else{
                    return false;
                }
            }
        }
        public int hashCode(){
            int hash=17;
            hash=31*hash+data.hashCode();
            hash=31*hash+(LEFT==null?0:LEFT.hashCode());
            hash=31*hash+(RIGHT==null?0:RIGHT.hashCode());
            return hash;
        }
        public String toString(){
            return preOrdered().toString();
        }
        public List<T> DFS(){
            List<T> ans=new ArrayList<>();
            Deque<TreeNode<T>> dfs=new ArrayDeque<>();
            dfs.push(this);
            while(!dfs.isEmpty()){
                var node=dfs.pop();
                ans.add(node.data);
                if(node.RIGHT!=null) dfs.push(node.RIGHT);
                if(node.LEFT!=null) dfs.push(node.LEFT);
            }
            return ans;
        }
        public List<T> BFS(){
            List<T> ans=new ArrayList<>();
            Deque<TreeNode<T>> bfs=new ArrayDeque<>();
            bfs.add(this);
            while(!bfs.isEmpty()){
                var node=bfs.poll();
                ans.add(node.data);
                if(node.LEFT!=null) bfs.add(node.LEFT);
                if(node.RIGHT!=null) bfs.add(node.RIGHT);
            }
            return ans;
        }
    }
    public static void main(String[] args) {
        TreeNode<Integer> root1=new TreeNode<Integer>(1);
        TreeNode<Integer> root2=new TreeNode<Integer>(1);
        root1.addLeft(2).addRight(3);
        root1.LEFT.addLeft(4).addRight(5);
        root2.addLeft(root1);
        root2.addRight(root1);
        System.out.println(root1.getLeft());
        System.out.println(root2.getRight().getData());
        System.out.println(TreeNode.preOrdered(root1));
        System.out.println(TreeNode.inOrdered(root1));
        System.out.println(root1.postOrdered());
    }
}
