package leetcode.easy;

import java.util.ArrayList;
import java.util.List;

public class LeafSimilarTrees {

    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(1, new TreeNode(2), new TreeNode(3));
        TreeNode root2 = new TreeNode(1, new TreeNode(3), new TreeNode(2));

        LeafSimilarTrees leafSimilarTrees = new LeafSimilarTrees();
        System.out.println(false == leafSimilarTrees.leafSimilar(root1, root2));
    }

    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> leftLeaves = new ArrayList<>();
        List<Integer> rightLeaves = new ArrayList<>();

        collectLeaves(root1, leftLeaves);
        collectLeaves(root2, rightLeaves);

        return leftLeaves.equals(rightLeaves);
    }

    private void collectLeaves(TreeNode root, List<Integer> leaves) {
        if (root == null) {
            return;
        }

        if (root.left == null && root.right == null) {
            leaves.add(root.val);
        } else {
            collectLeaves(root.left, leaves);
            collectLeaves(root.right, leaves);
        }
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
