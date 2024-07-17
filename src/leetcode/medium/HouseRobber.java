package leetcode.medium;

public class HouseRobber {

    public static void main(String[] args) {
        int[] input = new int[]{2, 1, 1, 2};

        HouseRobber houseRobber = new HouseRobber();
        System.out.println(4 == houseRobber.rob(input));
    }

    public int rob(int[] nums) {

        int rob1 = 0;
        int rob2 = 0;
        for (int num : nums) {

            int tmp = Math.max(rob1 + num, rob2);
            rob1 = rob2;
            rob2 = tmp;
        }

        return rob2;
    }
}
