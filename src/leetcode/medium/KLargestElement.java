package leetcode.medium;

import java.util.Arrays;

public class KLargestElement {

    public static void main(String[] args) {
        int[] input = new int[]{3, 2, 1, 5, 6, 4};
        KLargestElement k = new KLargestElement();

        System.out.println(5 == k.findKthLargest(input, 2));
    }

    public int findKthLargest(int[] nums, int k) {

        Arrays.sort(nums);

        return nums[nums.length - k];
    }
}
