package leetcode.easy;

public class MergeStringAlt {

    public String mergeAlternately(String word1, String word2) {
        int length = Math.min(word1.length(), word2.length());
        StringBuilder res = new StringBuilder();

        for(int i = 0; i < length; i++) {
            res.append(word1.charAt(i));
            res.append(word2.charAt(i));
        }

        if (length == word1.length()) {
            res.append(word2.substring(length));
        } else {
            res.append(word1.substring(length));
        }

        return res.toString();
    }

    public static void main(String[] args) {
        MergeStringAlt ms = new MergeStringAlt();

        System.out.println("apbqcr".equals(ms.mergeAlternately("abc", "pqr")));
        System.out.println("apbqrs".equals(ms.mergeAlternately("ab", "pqrs")));
        System.out.println("apbqcd".equals(ms.mergeAlternately("abcd", "pq")));
    }
}
