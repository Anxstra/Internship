package leetcode.medium;

public class Trie {

    private final TreeNode root;

    public Trie() {
        root = new TreeNode();
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("apple");
        System.out.println(trie.search("apple"));
        System.out.println(trie.search("app"));
        System.out.println(trie.startsWith("app"));
        trie.insert("app");
        System.out.println(trie.search("app"));
    }

    public void insert(String word) {
        char[] chars = word.toCharArray();
        TreeNode cur = root;
        for (char ch : chars) {
            int index = ch - 'a';
            TreeNode[] children = cur.getChildren();
            if (children[index] == null) {
                children[index] = new TreeNode();

            }
            cur = children[index];
        }
        cur.setEnd(true);
    }

    public boolean search(String word) {
        TreeNode cur = findLastNode(word);
        if (cur == null) return false;
        return cur.isEnd();
    }

    public boolean startsWith(String prefix) {
        TreeNode cur = findLastNode(prefix);
        return cur != null;
    }

    private TreeNode findLastNode(String word) {
        char[] chars = word.toCharArray();
        TreeNode cur = root;
        for (char ch : chars) {
            TreeNode node = cur.getChildren()[ch - 'a'];
            if (node == null) {
                return null;
            }
            cur = node;
        }
        return cur;
    }

    public static class TreeNode {

        private final TreeNode[] children;

        private boolean isEnd;

        TreeNode() {
            children = new TreeNode[26];
            isEnd = false;
        }

        public TreeNode[] getChildren() {
            return children;
        }

        public boolean isEnd() {
            return isEnd;
        }

        public void setEnd(boolean end) {
            isEnd = end;
        }
    }
}
