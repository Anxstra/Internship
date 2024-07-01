package leetcode.easy;

import java.util.Arrays;

public class MoveZeroes {

    public static void main(String[] args) {
        MoveZeroes moveZeroes = new MoveZeroes();
        int[] expected = new int[]{1, 3, 12, 0, 0};
        int[] output = new int[]{0, 1, 0, 3, 12};
        moveZeroes.moveZeroes(output);
        System.out.println(Arrays.equals(expected, output));
    }

    public void moveZeroes(int[] nums) {
        int pointer = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                int tmp = nums[pointer];
                nums[pointer++] = nums[i];
                nums[i] = tmp;
            }
        }
    }
}
