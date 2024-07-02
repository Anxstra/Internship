package leetcode.easy;

public class IsSubsequence {

    public static void main(String[] args) {
        IsSubsequence is = new IsSubsequence();
        System.out.println(is.isSubsequence("abc", "ahbgdc"));
    }

    public boolean isSubsequence(String subsequence, String sequence) {
        if (subsequence.isEmpty()) {
            return true;
        }
        int seqLen = sequence.length();
        int subseqLen = subsequence.length();

        char[] seqChars = sequence.toCharArray();
        char[] subseqChars = subsequence.toCharArray();

        int j = 0;
        for (int i = 0; i < seqLen; i++) {
            if (subseqChars[j] == seqChars[i]) {
                j++;
                if (j == subseqLen) {
                    return true;
                }
            }
        }

        return false;
    }
}
