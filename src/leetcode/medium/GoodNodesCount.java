package leetcode.medium;

public class GoodNodesCount {

    public static void main(String[] args) {
        TreeNode nodeLL = new TreeNode(3);
        TreeNode nodeL = new TreeNode(1, nodeLL, null);
        TreeNode nodeRR = new TreeNode(5);
        TreeNode nodeRL = new TreeNode(1);
        TreeNode nodeR = new TreeNode(4, nodeRL, nodeRR);
        TreeNode root = new TreeNode(3, nodeL, nodeR);

        GoodNodesCount goodNodesCount = new GoodNodesCount();
        System.out.println(4 == goodNodesCount.goodNodes(root));
    }

    private int dfs(TreeNode node, int maxSoFar) {
        if (node == null) {
            return 0;
        }

        int count = 0;
        if (node.val >= maxSoFar) {
            count = 1;
            maxSoFar = node.val;
        }

        count = count + dfs(node.left, maxSoFar);
        count = count + dfs(node.right, maxSoFar);

        return count;
    }

    public int goodNodes(TreeNode root) {
        return dfs(root, root.val);
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
