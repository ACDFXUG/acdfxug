package LeetCode;

public class 根据二叉树创建字符串 {
    @SuppressWarnings("unused")
    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(){}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    static String tree2str(TreeNode root) {
        if(root==null) return "";
        else{
            StringBuilder sb=new StringBuilder();
            sb.append(root.val);
            if(root.left!=null||root.right!=null){
                sb.append("(");
                sb.append(tree2str(root.left));
                sb.append(")");
                if(root.right!=null){
                    sb.append("(");
                    sb.append(tree2str(root.right));
                    sb.append(")");
                }
            }
            return sb.toString();
        }
    }
    public static void main(String[] args) {
        TreeNode root=new TreeNode(1);
        root.left=new TreeNode(2);
        root.right=new TreeNode(3);
        root.left.left=new TreeNode(4);
        System.out.println(tree2str(root));
    }
}
