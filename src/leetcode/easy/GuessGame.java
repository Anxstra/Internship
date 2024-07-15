package leetcode.easy;

public class GuessGame {

    private final int pick;

    protected GuessGame(int pick) {
        this.pick = pick;
    }

    protected int guess(int num) {
        return Integer.compare(pick, num);
    }
}
