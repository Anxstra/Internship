package leetcode.easy;

import java.util.Arrays;

public class CountingBits {

    public static void main(String[] args) {
        int[] expected = new int[]{0, 1, 1, 2, 1, 2};

        CountingBits cb = new CountingBits();
        System.out.println(Arrays.equals(expected, cb.countBits(5)));
        System.out.println(Arrays.equals(expected, cb.countBitsUpdated(5)));
    }

    public int[] countBits(int n) {
        int[] res = new int[n + 1];

        for (int i = 0; i <= n; i++) {
            int val = i;
            int ones = 0;
            while (val > 0) {
                ones += val & 1;
                val >>= 1;
            }
            res[i] = ones;
        }

        return res;
    }

    public int[] countBitsUpdated(int n) {
        int[] ans = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            ans[i] = ans[i >> 1] + (i & 1);
        }
        return ans;
    }

}
