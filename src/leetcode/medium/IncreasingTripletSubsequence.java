package leetcode.medium;

public class IncreasingTripletSubsequence {

    public static void main(String[] args) {
        int[] input = new int[]{1, 1, -2, 6};

        IncreasingTripletSubsequence tripletSubsequence = new IncreasingTripletSubsequence();
        System.out.println(tripletSubsequence.increasingTriplet(input));
    }

    public boolean increasingTriplet(int[] nums) {
        if (nums.length < 3) {
            return false;
        }

        int first = Integer.MAX_VALUE;
        int second = Integer.MAX_VALUE;
        for (int num : nums) {
            if (num > second) {
                return true;
            } else if (num <= first) {
                first = num;
            } else {
                second = num;
            }
        }

        return false;
    }
}
