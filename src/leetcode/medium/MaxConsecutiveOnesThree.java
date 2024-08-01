package leetcode.medium;

public class MaxConsecutiveOnesThree {

    public static void main(String[] args) {
        int[] input = new int[]{0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1};

        MaxConsecutiveOnesThree maxConsecutiveOnesThree = new MaxConsecutiveOnesThree();
        System.out.println(10 == maxConsecutiveOnesThree.longestOnes(input, 3));
    }

    public int longestOnes(int[] nums, int k) {
        int left = 0;
        int zeroes = k;
        int max = -1;
        for (int right = 0; right < nums.length; right++) {
            if (nums[right] == 0) {
                zeroes--;
            }
            while (zeroes < 0) {
                if (nums[left] == 0) {
                    zeroes++;
                }
                left++;
            }
            max = Math.max(max, right - left + 1);
        }

        return max;
    }
}
