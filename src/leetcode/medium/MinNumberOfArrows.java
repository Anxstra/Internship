package leetcode.medium;

import java.util.Arrays;
import java.util.Comparator;

public class MinNumberOfArrows {

    public static void main(String[] args) {
        MinNumberOfArrows minNumberOfArrows = new MinNumberOfArrows();
        int[][] input = new int[][]{{-2147483646, -2147483645}, {2147483646, 2147483647}};
        System.out.println(2 == minNumberOfArrows.findMinArrowShots(input));
    }

    // time efficiency depends on sorting algorithm a lot
    public int findMinArrowShots(int[][] points) {
        Arrays.sort(points, Comparator.comparingInt(a -> a[1]));
        int numOfDarts = 1;
        int upper = points[0][1];
        for (int i = 1; i < points.length; i++) {
            if (points[i][0] > upper) {
                numOfDarts++;
                upper = points[i][1];
            }
        }
        return numOfDarts;
    }
}
