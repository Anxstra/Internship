package leetcode.easy;

public class MaxDepthOfBTree {

    public static void main(String[] args) {
        TreeNode nodeRL = new TreeNode(15);
        TreeNode nodeRR = new TreeNode(7);
        TreeNode nodeL = new TreeNode(9);
        TreeNode nodeR = new TreeNode(20, nodeRL, nodeRR);
        TreeNode root = new TreeNode(3, nodeL, nodeR);

        MaxDepthOfBTree maxDepthOfBTree = new MaxDepthOfBTree();
        System.out.println(3 == maxDepthOfBTree.maxDepth(root));
    }

    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int depthLeft = maxDepth(root.left);
        int depthRight = maxDepth(root.right);

        return Math.max(depthLeft, depthRight) + 1;
    }

    public static class TreeNode {

        int val;

        TreeNode left;

        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

}
