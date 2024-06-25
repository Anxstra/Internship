package four;

import java.util.Arrays;

public class NextBiggerNumber {

    public static long nextBiggerNumber(long n) {
        char[] s = String.valueOf(n).toCharArray();
        for (int i = s.length - 2; i >= 0; i--) {
            for (int j = s.length - 1; j > i; j--) {
                if (s[i] < s[j]) {
                    char tmp = s[i];
                    s[i] = s[j];
                    s[j] = tmp;
                    Arrays.sort(s, i + 1, s.length);
                    return Long.parseLong(String.valueOf(s));
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(414L == nextBiggerNumber(144L));
        System.out.println(1234567908L == nextBiggerNumber(1234567890L));
    }
}
