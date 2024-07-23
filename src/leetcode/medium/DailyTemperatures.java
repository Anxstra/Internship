package leetcode.medium;

import java.util.Arrays;
import java.util.LinkedList;

public class DailyTemperatures {

    public static void main(String[] args) {
        int[] input = new int[]{73, 74, 75, 71, 69, 72, 76, 73};
        int[] expected = new int[]{1, 1, 4, 2, 1, 1, 0, 0};

        DailyTemperatures dt = new DailyTemperatures();
        System.out.println(Arrays.equals(expected, dt.dailyTemperatures(input)));
    }

    public int[] dailyTemperatures(int[] temperatures) {
        LinkedList<Pair> stack = new LinkedList<>();

        for (int i = 0; i < temperatures.length; i++) {
            while (!stack.isEmpty() && stack.peek().temperature() < temperatures[i]) {
                Pair pair = stack.pop();
                temperatures[pair.index()] = i - pair.index();
            }
            stack.push(new Pair(temperatures[i], i));
        }

        for (Pair pair : stack) {
            temperatures[pair.index()] = 0;
        }

        return temperatures;
    }

    public record Pair(int temperature, int index) {

    }
}
