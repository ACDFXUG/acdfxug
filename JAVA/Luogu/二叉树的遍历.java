package Java.Luogu;

// import java.util.*;

// public class 二叉树的遍历 {
//     private static class TreeNode{
//         int val;
//         TreeNode left,right;
//         TreeNode(int val){
//             this(val,null,null);
//         }
//         TreeNode(int val,TreeNode left,TreeNode right){
//             this.val=val;
//             this.left=left;
//             this.right=right;
//         }
//     }
//     static List<Integer> preOrder(TreeNode root){
//         List<Integer> preOrder=new ArrayList<>();
//         if(root==null) return new ArrayList<>();
//         preOrder.add(root.val);
//         preOrder.addAll(preOrder(root.left));
//         preOrder.addAll(preOrder(root.right));
//         return preOrder;
//     }
//     static List<Integer> inOrder(TreeNode root){
//         List<Integer> inOrder=new ArrayList<>();
//         if(root==null) return new ArrayList<>();
//         inOrder.addAll(inOrder(root.left));
//         inOrder.add(root.val);
//         inOrder.addAll(inOrder(root.right));
//         return inOrder;
//     }
//     static List<Integer> postOrder(TreeNode root){
//         List<Integer> postOrder=new ArrayList<>();
//         if(root==null) return new ArrayList<>();
//         postOrder.addAll(postOrder(root.left));
//         postOrder.addAll(postOrder(root.right));
//         postOrder.add(root.val);
//         return postOrder;
//     }
//     static void println(List<Integer> list){
//         for(int i=0;i<list.size();++i){
//             System.out.printf(i==list.size()-1?"%d\n":"%d ",list.get(i));
//         }
//     }
//     public static void main(String[] args) {
//         Scanner sc=new Scanner(System.in);
//         int n=sc.nextInt();
//         Map<Integer,TreeNode> tree=new HashMap<>(n);
//         TreeNode root=new TreeNode(1);
//         tree.put(1,root);
//         for(int l=1,left,right;l<=n;++l){
//             left=sc.nextInt();
//             right=sc.nextInt();
//             TreeNode tn;
//             if(tree.containsKey(l)) tn=tree.get(l);
//             else{
//                 tn=new TreeNode(l);
//                 tree.put(l,tn);
//             }
//             if(left!=0){
//                 if(tree.containsKey(left)) tn.left=tree.get(left);
//                 else{
//                     tn.left=new TreeNode(left);
//                     tree.put(left,tn.left);
//                 }
//             }
//             if(right!=0){
//                 if(tree.containsKey(right)) tn.right=tree.get(right);
//                 else{
//                     tn.right=new TreeNode(right);
//                     tree.put(right,tn.right);
//                 }
//             }
//         }
//         println(preOrder(root));
//         println(inOrder(root));
//         println(postOrder(root));
//         sc.close();
//     }
// }
import java.util.*;

public class 二叉树的遍历 {
    private static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    // 前序遍历（非递归）
    static List<Integer> preOrder(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node == null) continue;
            res.add(node.val);
            stack.push(node.right); // 右孩子先入栈，左后处理
            stack.push(node.left);
        }

        return res;
    }

    // 中序遍历（非递归）
    static List<Integer> inOrder(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;

        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;

        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            res.add(curr.val);
            curr = curr.right;
        }

        return res;
    }

    // 后序遍历（非递归，双栈法）
    static List<Integer> postOrder(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;

        Stack<TreeNode> s1 = new Stack<>();
        Stack<TreeNode> s2 = new Stack<>();
        s1.push(root);

        while (!s1.isEmpty()) {
            TreeNode node = s1.pop();
            s2.push(node);
            if (node.left != null) s1.push(node.left);
            if (node.right != null) s1.push(node.right);
        }

        while (!s2.isEmpty()) {
            res.add(s2.pop().val);
        }

        return res;
    }

    static void println(List<Integer> list) {
        for (int i = 0; i < list.size(); ++i) {
            System.out.printf(i == list.size() - 1 ? "%d\n" : "%d ", list.get(i));
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Map<Integer, TreeNode> tree = new HashMap<>(n);
        TreeNode root = new TreeNode(1);
        tree.put(1, root);

        for (int l = 1; l <= n; ++l) {
            int left = sc.nextInt();
            int right = sc.nextInt();

            TreeNode tn = tree.getOrDefault(l, new TreeNode(l));
            if (left != 0) {
                TreeNode ln = tree.getOrDefault(left, new TreeNode(left));
                tn.left = ln;
                tree.putIfAbsent(left, ln);
            }
            if (right != 0) {
                TreeNode rn = tree.getOrDefault(right, new TreeNode(right));
                tn.right = rn;
                tree.putIfAbsent(right, rn);
            }
            tree.putIfAbsent(l, tn);
        }

        println(preOrder(root));
        println(inOrder(root));
        println(postOrder(root));
        sc.close();
    }
}