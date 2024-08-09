package leetcode.medium;

import java.util.PriorityQueue;
import java.util.Queue;

public class SmallestInfiniteSet {

    private final Queue<Integer> queue;
    private int current;

    public SmallestInfiniteSet() {
        this.queue = new PriorityQueue<>();
        this.current = 1;
    }

    public int popSmallest() {
        if (!queue.isEmpty()) {
            return queue.poll();
        }

        return current++;
    }

    public void addBack(int num) {
        if (current > num && !queue.contains(num)) {
            queue.offer(num);
        }
    }

    public static void main(String[] args) {
        SmallestInfiniteSet s = new SmallestInfiniteSet();

        s.addBack(2);
        System.out.println(1 == s.popSmallest());
        System.out.println(2 == s.popSmallest());
        System.out.println(3 == s.popSmallest());
        s.addBack(1);
        System.out.println(1 == s.popSmallest());
        System.out.println(4 == s.popSmallest());
        System.out.println(5 == s.popSmallest());
    }
}
