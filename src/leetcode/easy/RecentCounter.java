package leetcode.easy;

import java.util.LinkedList;
import java.util.Queue;

class RecentCounter {

    private final Queue<Integer> calls;

    public RecentCounter() {
        calls = new LinkedList<>();
    }

    public static void main(String[] args) {
        RecentCounter rc = new RecentCounter();

        System.out.println(1 == rc.ping(1));
        System.out.println(2 == rc.ping(100));
        System.out.println(3 == rc.ping(3001));
        System.out.println(3 == rc.ping(3002));
    }

    public int ping(int t) {
        calls.add(t);
        while (calls.element() < t - 3000) {
            calls.remove();
        }

        return calls.size();
    }
}
