package leetcode.medium;

public class StringCompression {

    public static void main(String[] args) {
        StringCompression s = new StringCompression();
        char[] expected = new char[]{'a', '2', 'b', '2', 'c', '3'};

        char[] chars = new char[]{'a', 'a', 'b', 'b', 'c', 'c', 'c'};
        int result = s.compress(chars);
        System.out.println(6 == result);
        boolean flag = true;
        for (int i = 0; i < result; i++) {
            if (chars[i] != expected[i]) {
                flag = false;
                break;
            }
        }
        System.out.println(flag);
    }

    public int compress(char[] chars) {

        if (chars.length == 1) return 1;

        StringBuilder sol = new StringBuilder();

        int charIndex = 0;
        int charCount = 0;

        for (int i = 0; i < chars.length; i++) {
            if (chars[charIndex] == chars[i]) {
                charCount++;
            } else {
                sol.append(chars[charIndex]);
                if (charCount > 1) {
                    sol.append(charCount);
                }

                charIndex = i;
                charCount = 1;
            }
        }

        if (charCount > 0) {
            sol.append(chars[charIndex]);
            if (charCount != 1) {
                sol.append(charCount);
            }
        }

        for (int i = 0; i < sol.length(); i++) {
            chars[i] = sol.charAt(i);
        }

        return sol.length();
    }
}
