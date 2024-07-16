package leetcode.easy;

public class SingleNumber {

    public static void main(String[] args) {
        int[] input = new int[]{2, 3, 6, 7, 1, 5, 6, 2, 3, 1, 7};

        SingleNumber singleNumber = new SingleNumber();
        System.out.println(5 == singleNumber.singleNumber(input));
    }

    public int singleNumber(int[] nums) {

        int res = 0;
        for (int num : nums) {
            res = res ^ num;
        }

        return res;
    }

}
