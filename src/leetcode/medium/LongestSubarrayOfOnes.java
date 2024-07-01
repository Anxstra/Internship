package leetcode.medium;

public class LongestSubarrayOfOnes {

    public static void main(String[] args) {
        LongestSubarrayOfOnes longestSubarray = new LongestSubarrayOfOnes();
        int[] input = new int[]{0, 1, 1, 1, 0, 1, 1, 0, 1};
        System.out.println(5 == longestSubarray.longestSubarray(input));
        System.out.println(5 == longestSubarray.longestSubarraySlidingWindow(input));
    }

    public int longestSubarray(int[] nums) {
        int onesBefore = 0;
        int onesAfter = 0;
        int res = 0;
        int totalOnes;

        boolean foundZero = false;
        for (int num : nums) {
            if (num == 1) {
                onesAfter++;
            } else {
                totalOnes = onesBefore + onesAfter;
                res = Math.max(res, totalOnes);
                onesBefore = onesAfter;
                onesAfter = 0;
                foundZero = true;
            }
        }
        totalOnes = onesBefore + onesAfter;
        res = Math.max(res, totalOnes);

        return foundZero ? res : nums.length - 1;
    }

    public int longestSubarraySlidingWindow(int[] nums) {
        int zeroCount = 1;
        int l = 0;
        int r;
        for (r = 0; r < nums.length; r++) {
            zeroCount -= nums[r] == 0 ? 1 : 0;
            if (zeroCount < 0) {
                zeroCount += nums[l] == 0 ? 1 : 0;
                l++;
            }
        }
        return r - 1 - l;
    }
}
