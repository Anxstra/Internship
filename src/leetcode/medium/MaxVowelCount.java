package leetcode.medium;

public class MaxVowelCount {

    public static void main(String[] args) {
        MaxVowelCount mv = new MaxVowelCount();

        System.out.println(2 == mv.maxVowels("aeiou", 2));
        System.out.println(3 == mv.maxVowels("abciiidef", 3));
        System.out.println(2 == mv.maxVowels("leetcode", 3));
    }

    private boolean isVowel(char ch) {
        return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u';
    }

    public int maxVowels(String s, int k) {
        int strLen = s.length();
        char[] chars = s.toCharArray();

        int count = 0;
        for (int i = 0; i < k; i++) {
            if (isVowel(chars[i])) {
                count++;
            }
        }

        int maxCount = count;
        for (int i = k; i < strLen; i++) {
            if (isVowel(chars[i])) {
                count++;
            }
            if (isVowel(chars[i - k])) {
                count--;
            }
            maxCount = Math.max(maxCount, count);
        }

        return maxCount;
    }

}
