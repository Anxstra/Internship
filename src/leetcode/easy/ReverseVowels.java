package leetcode.easy;

public class ReverseVowels {

    public static boolean isVowel(char ch) {
        return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u' ||
                ch == 'A' || ch == 'E' || ch == 'I' || ch == 'O' || ch == 'U';
    }

    public static void main(String[] args) {
        ReverseVowels reverseVowels = new ReverseVowels();
        System.out.println("holle".equals(reverseVowels.reverseVowels("hello")));
        System.out.println("leotcede".equals(reverseVowels.reverseVowels("leetcode")));
    }

    public String reverseVowels(String s) {
        char[] chars = s.toCharArray();
        int start = 0;
        int end = chars.length - 1;
        while (start < end) {
            if (!isVowel(chars[start])) {
                start++;
            }
            if (!isVowel(chars[end])) {
                end--;
            }
            if (isVowel(chars[start]) && isVowel(chars[end])) {
                char tmp = chars[start];
                chars[start] = chars[end];
                chars[end] = tmp;
                start++;
                end--;
            }
        }

        return String.valueOf(chars);
    }
}
