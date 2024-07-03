package leetcode.easy;

public class MaxAvgSubarrayFirst {

    public static void main(String[] args) {
        int[] inputFirst = new int[]{1, 12, -5, -6, 50, 3};
        int[] inputSecond = new int[]{5};

        MaxAvgSubarrayFirst m = new MaxAvgSubarrayFirst();
        System.out.println(Double.compare(12.75, m.findMaxAverage(inputFirst, 4)) == 0);
        System.out.println(Double.compare(5, m.findMaxAverage(inputSecond, 1)) == 0);
    }

    public double findMaxAverage(int[] nums, int k) {
        int sum = 0;

        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }
        int max = sum;

        for (int i = k; i < nums.length; i++) {
            sum = sum + nums[i] - nums[i - k];
            max = Math.max(max, sum);
        }

        return (double) max / k;
    }
}
