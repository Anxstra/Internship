package leetcode.medium;

import java.util.HashMap;
import java.util.Map;

public class PathSumThree {

    public static void main(String[] args) {
        TreeNode nodeLLL = new TreeNode(3);
        TreeNode nodeLLR = new TreeNode(-2);
        TreeNode nodeLL = new TreeNode(3, nodeLLL, nodeLLR);

        TreeNode nodeLRR = new TreeNode(1);
        TreeNode nodeLR = new TreeNode(2, null, nodeLRR);

        TreeNode nodeL = new TreeNode(5, nodeLL, nodeLR);

        TreeNode nodeRR = new TreeNode(11);
        TreeNode nodeR = new TreeNode(-3, null, nodeRR);

        TreeNode root = new TreeNode(10, nodeL, nodeR);

        PathSumThree path = new PathSumThree();
        System.out.println(3 == path.pathSum(root, 8));
    }

    public int pathSum(TreeNode root, int targetSum) {
        Map<Long, Integer> prefixSumCount = new HashMap<>();
        prefixSumCount.put(0L, 1);
        return dfs(root, 0L, targetSum, prefixSumCount);
    }

    private int dfs(TreeNode node, long currentSum, int targetSum, Map<Long, Integer> prefixSumCount) {
        if (node == null) return 0;

        currentSum += node.val;
        int count = prefixSumCount.getOrDefault(currentSum - targetSum, 0);

        prefixSumCount.put(currentSum, prefixSumCount.getOrDefault(currentSum, 0) + 1);

        count += dfs(node.left, currentSum, targetSum, prefixSumCount);
        count += dfs(node.right, currentSum, targetSum, prefixSumCount);

        prefixSumCount.put(currentSum, prefixSumCount.get(currentSum) - 1);

        return count;
    }

    public record TreeNode(int val, TreeNode left, TreeNode right) {

        public TreeNode(int val) {
            this(val, null, null);
        }
    }
}
