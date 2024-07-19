package leetcode.medium;

import java.util.Arrays;

public class NonOverlappingIntervals {

    public static void main(String[] args) {
        int[][] input = new int[][]{{1, 2}, {2, 3}, {3, 4}, {1, 3}};

        NonOverlappingIntervals solution = new NonOverlappingIntervals();
        System.out.println(1 == solution.eraseOverlapIntervals(input));
    }

    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, (interval1, interval2) -> interval1[1] - interval2[1]);

        int count = 0;
        int prev = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            if (prev > intervals[i][0]) {
                count++;
            } else {
                prev = intervals[i][1];
            }
        }

        return count;
    }
}
