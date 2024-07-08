package leetcode.easy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArraysDiff {

    public static void main(String[] args) {
        int[] input1 = {1, 2, 3, 3};
        int[] input2 = {1, 1, 2, 2};

        List<List<Integer>> expected = List.of(List.of(3), List.of());

        ArraysDiff arraysDiff = new ArraysDiff();
        System.out.println(expected.equals(arraysDiff.findDifference(input1, input2)));
    }

    public List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
        Map<Integer, Integer> numsMap = new HashMap<>();
        for (int num : nums1) {
            numsMap.put(num, 1);
        }

        List<Integer> res2 = new ArrayList<>();
        for (int num : nums2) {
            if (!numsMap.containsKey(num)) {
                res2.add(num);
            }
            numsMap.put(num, 2);
        }

        List<Integer> res1 = new ArrayList<>();
        for (int num : nums1) {
            if (numsMap.get(num) == 1) {
                numsMap.put(num, -1);
                res1.add(num);
            }
        }

        return List.of(res1, res2);
    }
}
