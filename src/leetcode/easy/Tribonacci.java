package leetcode.easy;

public class Tribonacci {

    public static void main(String[] args) {
        Tribonacci tribonacci = new Tribonacci();
        System.out.println(4 == tribonacci.tribonacci(4));
        System.out.println(1389537 == tribonacci.tribonacci(25));
    }

    // timeComplexity O(n), spaceComplexity O(1)
    public int tribonacci(int n) {
        int[] val = new int[]{0, 1, 1};
        if (n < 3) {
            return val[n];
        }

        for (int i = 3; i <= n; i++) {
            int newVal = val[0] + val[1] + val[2];
            val[0] = val[1];
            val[1] = val[2];
            val[2] = newVal;
        }

        return val[2];
    }
}
