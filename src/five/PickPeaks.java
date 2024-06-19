package five;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class PickPeaks {

    private static final int[][] array = {{1, 2, 3, 6, 4, 1, 2, 3, 2, 1},
            {3, 2, 3, 6, 4, 1, 2, 3, 2, 1, 2, 3},
            {3, 2, 3, 6, 4, 1, 2, 3, 2, 1, 2, 2, 2, 1},
            {2, 1, 3, 1, 2, 2, 2, 2, 1},
            {2, 1, 3, 1, 2, 2, 2, 2},
            {2, 1, 3, 2, 2, 2, 2, 5, 6},
            {2, 1, 3, 2, 2, 2, 2, 1},
            {1, 2, 5, 4, 3, 2, 3, 6, 4, 1, 2, 3, 3, 4, 5, 3, 2, 1, 2, 3, 5, 5, 4, 3},
            {},
            {1, 1, 1, 1}};

    private static final int[][] posS = {{3, 7},
            {3, 7},
            {3, 7, 10},
            {2, 4},
            {2},
            {2},
            {2},
            {2, 7, 14, 20},
            {},
            {}};

    private static final int[][] peaksS = {{6, 3},
            {6, 3},
            {6, 3, 2},
            {3, 2},
            {3},
            {3},
            {3},
            {5, 6, 5, 5},
            {},
            {}};

    public static Map<String, List<Integer>> getPeaks(int[] arr) {
        Map<String, List<Integer>> map = new HashMap<>();
        map.put("peaks", new ArrayList<>());
        map.put("pos", new ArrayList<>());

        if (Objects.nonNull(arr) && arr.length == 0) {
            return map;
        }

        int posMax = 0;
        boolean matchAsc = false;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1] < arr[i]) {
                posMax = i;
                matchAsc = true;
            }
            if (matchAsc && arr[i - 1] > arr[i]) {
                matchAsc = false;
                map.get("pos").add(posMax);
                map.get("peaks").add(arr[i - 1]);
            }
        }
        return map;
    }

    public static void main(String[] args) {
        for (int i = 0; i < array.length; i++) {
            Map<String, List<Integer>> expected = new HashMap<>();
            expected.put("peaks", Arrays.stream(peaksS[i])
                                        .boxed()
                                        .toList());
            expected.put("pos", Arrays.stream(posS[i])
                                      .boxed()
                                      .toList());
            System.out.println(getPeaks(array[i]).equals(expected));
        }
    }
}
