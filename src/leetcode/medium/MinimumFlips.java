package leetcode.medium;

public class MinimumFlips {

    public static void main(String[] args) {
        MinimumFlips mf = new MinimumFlips();
        System.out.println(3 == mf.minFlips(2, 6, 5));
    }

    public int minFlips(int a, int b, int c) {
        int flips = 0;

        for (int i = 0; i < 32; i++) {
            if ((c >> i & 1) == 1) {
                if ((a >> i & 1) != 1 && (b >> i & 1) != 1) {
                    flips++;
                }
            } else {
                if ((a >> i & 1) == 1) {
                    flips++;
                }
                if ((b >> i & 1) == 1) {
                    flips++;
                }
            }
        }

        return flips;
    }
}
