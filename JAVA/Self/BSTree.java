package Java.Self;

import java.util.*;

public class BSTree<T extends Comparable<T>> 
implements Comparable<BSTree<T>>,Iterable<T>{
    T val;
    BSTree<T> left,right;
    public BSTree(){
        this(null);   
    }
    public BSTree(T val){
        this.val=val;
    }
    public BSTree(T val,BSTree<T> left,BSTree<T> right){
        this.val=val;
        this.left=left;
        this.right=right;
    }
    public boolean insert(T value){
        if(this.val==null){
            this.val=value;
            return true;
        }else{
            if(value.compareTo(this.val)<0){
                if(left==null){
                    left=new BSTree<T>(value);
                    return true;
                }else{
                    return left.insert(value);
                }
            }else if(value.compareTo(this.val)>0){
                if(right==null){
                    right=new BSTree<T>(value);
                    return true;
                }else{
                    return right.insert(value);
                }
            }else{
                return false;
            }
        }
    }
    private static<T extends Comparable<T>>
    BSTree<T> findMin(BSTree<T> root){
        while(root.left!=null){
            root=root.left;
        }
        return root;
    }
    private static<T extends Comparable<T>>
    BSTree<T> remove(BSTree<T> root,T value){
        if(root==null) return null;
        int cmp=value.compareTo(root.val);
        if(cmp<0){
            root.left=remove(root.left,value);
        }else if(cmp>0){
            root.right=remove(root.right,value);
        }else{
            if(root.left==null){
                return root.right;
            }else if(root.right==null){
                return root.left;
            }
            root.val=findMin(root.right).val;
            root.right=remove(root.right,root.val);
        }
        return root;
    }
    public boolean remove(T value){
        remove(this,value);
        return true;
    }
    public boolean contains(T value){
        var root=this;
        while(root!=null){
            int cmp=value.compareTo(root.val);
            if(cmp==0) return true;
            else if(cmp>0) root=root.right;
            else root=root.left;
        }
        return false;
    }
    public static<T extends Comparable<T>> BSTree<T> build(T[] vals){
        var root=new BSTree<T>();
        for(var val:vals) root.insert(val);
        return root;
    }
    public static<T extends Comparable<T>> BSTree<T> build(Collection<T> vals){
        var root=new BSTree<T>();
        vals.forEach(root::insert);
        return root;
    }
    public List<T> preOrdered(){
        if(val==null) return new ArrayList<>();
        List<T> pre=new ArrayList<>();
        pre.add(val);
        if(left!=null) pre.addAll(left.preOrdered());
        if(right!=null) pre.addAll(right.preOrdered());
        return pre;
    }
    public List<T> inOrdered(){
        if(val==null) return new ArrayList<>();
        List<T> in=new ArrayList<>();
        if(left!=null) in.addAll(left.inOrdered());
        in.add(val);
        if(right!=null) in.addAll(right.inOrdered());
        return in;
    }
    public List<T> postOrdered(){
        if(val==null) return new ArrayList<>();
        List<T> post=new ArrayList<>();
        if(left!=null) post.addAll(left.postOrdered());
        if(right!=null) post.addAll(right.postOrdered());
        post.add(val);
        return post;
    }
    public String toString(){
        return inOrdered().toString();
    }
    public T getValue(){
        return val;
    }
    public BSTree<T> getLeft(){
        return left;
    }
    public BSTree<T> getRight(){
        return right;
    }
    public Iterator<T> iterator(){
        return inOrdered().iterator();
    }
    public int compareTo(BSTree<T> o) {
        var l1=inOrdered();
        var l2=o.inOrdered();
        int minL=Math.min(l1.size(),l2.size());
        for(int i=0;i<minL;i++){
            int cmp=l1.get(i).compareTo(l2.get(i));
            if(cmp!=0) return cmp;
        }
        return l1.size()-l2.size();
    }
    public static void main(String[] args) {
        var tree=BSTree.build(new Integer[]{5,3,7,1,4,6,8});
        System.out.println(tree);
        tree.remove(7);
        System.out.println(tree);
        tree.forEach(System.out::println);
    }
}
