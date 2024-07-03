package leetcode.medium;

import java.util.Arrays;

public class ProductOfArray {

    public static void main(String[] args) {
        int[] expected = new int[]{24, 12, 8, 6};
        int[] input = new int[]{1, 2, 3, 4};

        ProductOfArray productOfArray = new ProductOfArray();
        System.out.println(Arrays.equals(expected, productOfArray.productExceptSelf(input)));
    }

    public int[] productExceptSelf(int[] nums) {
        int[] result = new int[nums.length];

        int multiplication = 1;
        for (int i = 0; i < result.length; i++) {
            result[i] = multiplication;
            multiplication *= nums[i];
        }


        multiplication = 1;
        for (int i = result.length - 1; i >= 0; i--) {
            result[i] *= multiplication;
            multiplication *= nums[i];
        }

        return result;
    }
}
