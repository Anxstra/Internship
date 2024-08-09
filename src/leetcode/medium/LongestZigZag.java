package leetcode.medium;

public class LongestZigZag {

    int ans;

    public static void main(String[] args) {
        TreeNode nodeR = new TreeNode(1);
        TreeNode nodeLRLR = new TreeNode(1);
        TreeNode nodeLRL = new TreeNode(1, null, nodeLRLR);
        TreeNode nodeLRR = new TreeNode(1);
        TreeNode nodeLR = new TreeNode(1, nodeLRL, nodeLRR);
        TreeNode nodeL = new TreeNode(1, null, nodeLR);
        TreeNode root = new TreeNode(1, nodeL, nodeR);

        LongestZigZag lz = new LongestZigZag();
        System.out.println(4 == lz.longestZigZag(root));
    }

    void childZigZagLength(TreeNode root, boolean goLeft, int length) {
        if (root == null) {
            return;
        }

        ans = Math.max(ans, length);

        if (goLeft) {
            childZigZagLength(root.left, false, length + 1);
            childZigZagLength(root.right, true, 1);
        } else {
            childZigZagLength(root.right, true, length + 1);
            childZigZagLength(root.left, false, 1);
        }
    }

    public int longestZigZag(TreeNode root) {
        ans = 0;
        childZigZagLength(root.left, false, 1);
        childZigZagLength(root.right, true, 1);
        return ans;
    }

    public record TreeNode(int val, TreeNode left, TreeNode right) {

        public TreeNode(int val) {
            this(val, null, null);
        }
    }
}
