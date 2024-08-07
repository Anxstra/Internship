package leetcode.medium;

import java.util.ArrayDeque;
import java.util.Queue;

public class MaxLevelSumOfBTree {

    public static void main(String[] args) {
        TreeNode nodeLL = new TreeNode(7);
        TreeNode nodeLR = new TreeNode(-8);
        TreeNode nodeL = new TreeNode(7, nodeLL, nodeLR);
        TreeNode nodeR = new TreeNode(0);
        TreeNode root = new TreeNode(1, nodeL, nodeR);

        MaxLevelSumOfBTree mst = new MaxLevelSumOfBTree();
        System.out.println(2 == mst.maxLevelSum(root));
    }

    public int maxLevelSum(TreeNode root) {

        if (root == null) {
            return 0;
        }

        Queue<TreeNode> queue = new ArrayDeque<>();
        int max = Integer.MIN_VALUE;
        int result = 1;
        int level = 1;
        queue.offer(root);
        while (!queue.isEmpty()) {
            int sum = 0;
            int size = queue.size();
            while (size > 0) {
                TreeNode node = queue.poll();
                sum = sum + node.val;
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
                size--;
            }
            if (sum > max) {
                max = sum;
                result = level;
            }
            level++;
        }

        return result;
    }

    public record TreeNode(int val, TreeNode left, TreeNode right) {

        public TreeNode(int val) {
            this(val, null, null);
        }
    }
}
