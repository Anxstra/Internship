package leetcode.easy;

public class GuessHigherOrLower extends GuessGame {

    protected GuessHigherOrLower(int pick) {
        super(pick);
    }

    public static void main(String[] args) {
        GuessHigherOrLower g1 = new GuessHigherOrLower(6);
        System.out.println(6 == g1.guessNumber(10));

        GuessHigherOrLower g2 = new GuessHigherOrLower(1);
        System.out.println(1 == g2.guessNumber(1));
    }

    public int guessNumber(int n) {
        int low = 1;
        int high = n;
        while (low <= high) {
            int mid = (low + high) >>> 1;
            int res = guess(mid);
            if (res == 1) {
                low = mid + 1;
            } else if (res == -1) {
                high = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
}
