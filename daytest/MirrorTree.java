package Test1.Test326;
/*
镜像二叉树
 */

class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;

    public TreeNode(int val) {
        this.val = val;

    }
}
public class MirrorTree {
    public void Mirror(TreeNode root) {
            TreeNode temp=null;
            if(root!=null){
                temp=root.left;
                root.right=root.left;
                root.left=temp;
            }
            if(root.left!=null){
                Mirror(root.left);
            }

    }
    public static void main(String[] args) {
        MirrorTree mirrorTree=new MirrorTree();
        TreeNode treeNode=new TreeNode(1);
        treeNode.val=2;
        treeNode.val=3;
        treeNode.val=6;
        treeNode.val=7;
        mirrorTree.Mirror(treeNode);
    }
}
