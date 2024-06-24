package four;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Snail {

    public static int[] snail(int[][] array) {
        List<Integer> result = new ArrayList<>();

        int left = 0;
        int right = array[0].length;
        int top = 0;
        int bottom = array.length;

        while (left < right && top < bottom) {

            for (int i = 0; left + i < right; i++) {
                result.add(array[top][left + i]);
            }
            top++;

            for (int i = 0; top + i < bottom; i++) {
                result.add(array[top + i][right - 1]);
            }
            right--;

            if (top > bottom || left > right) {
                break;
            }

            for (int i = 0; right - 1 -  i >= left; i++) {
                result.add(array[bottom - 1][right - 1 - i]);
            }
            bottom--;

            for (int i = 0; bottom - 1 - i >= top; i++) {
                result.add(array[bottom - 1 - i][left]);
            }
            left++;
        }

        return result.stream().mapToInt(i -> i).toArray();
    }

    public static void main(String[] args) {
        System.out.println(Arrays.equals(new int[]{1, 2, 3, 6, 9, 8, 7, 4, 5},
                snail(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}})));
    }
}
