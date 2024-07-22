package leetcode.medium;

import java.util.Arrays;
import java.util.LinkedList;

public class AsteroidCollision {

    public static void main(String[] args) {
        int[] input = new int[]{5, 10, -5};
        int[] expected = new int[]{5, 10};

        AsteroidCollision a = new AsteroidCollision();
        System.out.println(Arrays.equals(expected, a.asteroidCollision(input)));
    }

    public int[] asteroidCollision(int[] asteroids) {
        LinkedList<Integer> asteroidsClash = new LinkedList<>();

        for (int current : asteroids) {

            if (current > 0 || asteroidsClash.isEmpty() || asteroidsClash.peek() < 0) {
                asteroidsClash.push(current);
                continue;
            }

            int currentAbs = Math.abs(current);

            while (!asteroidsClash.isEmpty() && asteroidsClash.peek() > 0 && asteroidsClash.peek() < currentAbs) {
                asteroidsClash.pop();
            }

            if (!asteroidsClash.isEmpty() && asteroidsClash.peek() == currentAbs) {
                asteroidsClash.pop();
            } else if (asteroidsClash.isEmpty() || asteroidsClash.peek() < 0) {
                asteroidsClash.push(current);
            }
        }

        int[] result = new int[asteroidsClash.size()];
        for (int i = result.length - 1; i >= 0; i--) {
            result[i] = asteroidsClash.pop();
        }

        return result;
    }
}
