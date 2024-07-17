package leetcode.medium;

import java.util.ArrayList;
import java.util.List;

public class BTreeRightSide {

    public static void main(String[] args) {

        TreeNode input = new TreeNode(1,
                new TreeNode(2, null, new TreeNode(5)),
                new TreeNode(3, null, new TreeNode(4)));

        List<Integer> expected = List.of(1, 3, 4);

        BTreeRightSide bt = new BTreeRightSide();
        System.out.println(expected.equals(bt.rightSideView(input)));
    }

    public List<Integer> rightSideView(TreeNode root) {

        List<Integer> result = new ArrayList<>();
        helper(root, result, 0);

        return result;
    }

    private void helper(TreeNode root, List<Integer> seen, int depth) {

        if (root == null) {
            return;
        }

        if (depth == seen.size()) {
            seen.add(root.val);
        }

        helper(root.right, seen, depth + 1);
        helper(root.left, seen, depth + 1);
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
