package leetcode.medium;

import java.util.HashMap;
import java.util.Map;

public class RowAndColumnPair {

    public static void main(String[] args) {
        RowAndColumnPair pair = new RowAndColumnPair();
        int[][] input = new int[][]{{3, 2, 1}, {1, 7, 6}, {2, 7, 7}};

        System.out.println(1 == pair.equalPairs(input));
    }

    public int equalPairs(int[][] grid) {
        Map<Integer, Integer> dataMap = new HashMap<>();
        int pairs = 0;

        for (int[] row : grid) {
            int hash = getRowHash(row);
            dataMap.put(hash, dataMap.getOrDefault(hash, 0) + 1);
        }

        for (int i = 0; i < grid.length; i++) {
            int hash = getColHash(grid, i);
            pairs = pairs + dataMap.getOrDefault(hash, 0);
        }

        return pairs;
    }

    private int getRowHash(int[] row) {
        int result = 0;

        for (int n : row) {
            result = result + (n << 1);
            result = result * 7;
        }

        return result;
    }

    private int getColHash(int[][] grid, int col) {
        int result = 0;

        for (int[] row : grid) {
            result = result + (row[col] << 1);
            result = result * 7;
        }

        return result;
    }

}
