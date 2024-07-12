package leetcode.easy;

import java.util.Objects;

public class BTS {

    public static void main(String[] args) {
        TreeNode nodeLL = new TreeNode(1);
        TreeNode nodeLR = new TreeNode(3);
        TreeNode nodeL = new TreeNode(2, nodeLL, nodeLR);
        TreeNode nodeR = new TreeNode(7);
        TreeNode root = new TreeNode(4, nodeL, nodeR);

        TreeNode expected = new TreeNode(2, new TreeNode(1), new TreeNode(3));

        BTS bts = new BTS();
        System.out.println(expected.equals(bts.searchBST(root, 2)));
    }

    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null || root.val == val) {
            return root;
        }

        if (root.val > val) {
            return searchBST(root.left, val);
        } else {
            return searchBST(root.right, val);
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

        @Override
        public final boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof TreeNode treeNode)) return false;

            return val == treeNode.val && Objects.equals(left, treeNode.left) && Objects.equals(right, treeNode.right);
        }

        @Override
        public int hashCode() {
            int result = val;
            result = 31 * result + Objects.hashCode(left);
            result = 31 * result + Objects.hashCode(right);
            return result;
        }
    }
}
