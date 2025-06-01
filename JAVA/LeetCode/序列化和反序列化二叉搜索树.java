package JAVA.LeetCode;

public class 序列化和反序列化二叉搜索树 {
    @SuppressWarnings("unused")
    private static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val){
            this.val = val;
        }
        TreeNode(int val,TreeNode left,TreeNode right){
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    private static class Codec{
        static String serialize(TreeNode root) {
            if(root==null) return "[]";
            else{
                StringBuilder sb=new StringBuilder();
                sb.append("[");
                sb.append(root.val);
                if(root.left!=null||root.right!=null){
                    sb.append(",");
                    sb.append(serialize(root.left));
                    sb.append(",");
                    sb.append(serialize(root.right));
                    sb.append("]");
                    return sb.toString();
                }else{
                    sb.append("]");
                    return sb.toString();
                }
            }
        }
        static TreeNode deserialize(String data) {
            if(data.equals("[]")) return null;
            else{
                String[] str=data.substring(1,data.length()-1).split(",");
                TreeNode root=new TreeNode(Integer.parseInt(str[0]));
                if(str.length>1){
                    root.left=deserialize(data.substring(data.indexOf("[")+1,data.lastIndexOf(",")));
                    root.right=deserialize(data.substring(data.lastIndexOf(",")+1,data.lastIndexOf("]")));
                }
                return root;
            }
        }
    }
    public static void main(String[] args) {
        TreeNode root=new TreeNode(1);
        root.left=new TreeNode(2);
        root.right=new TreeNode(3);
        System.out.println(Codec.serialize(root));
        System.out.println(Codec.deserialize(Codec.serialize(root)));
    }
}
