package leetcode.medium;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MaxNumberOfPairs {

    public static void main(String[] args) {
        int[] input = new int[]{3, 1, 3, 4, 3};

        MaxNumberOfPairs maxNumberOfPairs = new MaxNumberOfPairs();
        System.out.println(1 == maxNumberOfPairs.maxOperationsMap(input, 6));
        System.out.println(1 == maxNumberOfPairs.maxOperationsPointer(input, 6));
    }

    //NlogN and two pointers
    public int maxOperationsPointer(int[] nums, int k) {
        Arrays.sort(nums);

        int count = 0;
        int right = nums.length - 1;
        int left = 0;
        while (left < right) {
            if (nums[left] + nums[right] == k) {
                count++;
                right--;
                left++;
            } else if (k - nums[left] > nums[right]) {
                left++;
            } else {
                right--;
            }
        }

        return count;
    }

    // N and hash map
    public int maxOperationsMap(int[] nums, int k) {
        Map<Integer, Integer> counts = new HashMap<>();

        for (int num : nums) {
            counts.put(num, counts.getOrDefault(num, 0) + 1);
        }

        int result = 0;
        for (int num : nums) {
            if (counts.containsKey(k - num)) {
                if (num != k - num) {
                    int count = Math.min(counts.get(num), counts.get(k - num));
                    result = result + count;
                    counts.remove(num);
                    counts.remove(k - num);
                } else {
                    result = result + counts.get(num) / 2;
                    counts.remove(num);
                }
            }
        }

        return result;
    }
}
