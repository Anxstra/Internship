package five;

import java.util.Arrays;

public class MaxSubarraySum {

    public static int sequence(int[] arr) {
        int maxSum = 0;
        for (int i = 0; i < arr.length; i++) {
            int sum = 0;
            for (int j = i; j < arr.length; j++) {
                sum += arr[j];
                if (maxSum < sum) {
                    maxSum = sum;
                }
            }
        }
        return maxSum;
    }

    public static int sequenceKadaneAlgorithm(int[] arr) {
        int maxSum = 0;
        int currentSum = 0;
        for (int number : arr) {
            currentSum = Math.max(number, currentSum + number);
            maxSum = Math.max(maxSum, currentSum);
        }
        return maxSum;
    }

    public static int sequenceWithStream(int[] arr) {
        int[] currentSum = new int[]{0};
        return Arrays.stream(arr)
                     .map(i -> {
                         currentSum[0] = Math.max(i, currentSum[0] + i);
                         return Math.max(0, currentSum[0]);
                     })
                     .max()
                     .orElse(0);
    }

    public static void main(String[] args) {
        System.out.println(6 == sequence(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
        System.out.println(6 == sequenceKadaneAlgorithm(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
        System.out.println(6 == sequenceWithStream(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
    }
}
