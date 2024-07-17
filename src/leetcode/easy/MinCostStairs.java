package leetcode.easy;

public class MinCostStairs {

    public static void main(String[] args) {
        int[] input = new int[]{10, 15, 30};

        MinCostStairs minCostStairs = new MinCostStairs();
        System.out.println(15 == minCostStairs.minCostClimbingStairs(input));
    }

    public int minCostClimbingStairs(int[] cost) {

        for (int i = cost.length - 3; i >= 0; i--) {
            cost[i] = cost[i] + Math.min(cost[i + 1], cost[i + 2]);
        }

        return Math.min(cost[0], cost[1]);
    }
}
