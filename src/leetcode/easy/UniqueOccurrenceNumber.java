package leetcode.easy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class UniqueOccurrenceNumber {

    public static void main(String[] args) {
        int[] input1 = new int[]{1, 2, 2, 1, 1, 3};
        int[] input2 = new int[]{26, 2, 16, 16, 5, 5, 26, 2, 5, 20, 20, 5, 2, 20, 2, 2, 20, 2, 16, 20, 16, 17, 16, 2, 16, 20, 26, 16};

        UniqueOccurrenceNumber uniqueOccurrenceNumber = new UniqueOccurrenceNumber();
        System.out.println(true == uniqueOccurrenceNumber.uniqueOccurrences(input1));
        System.out.println(true == uniqueOccurrenceNumber.uniqueOccurrencesWithoutMap(input1));
        System.out.println(false == uniqueOccurrenceNumber.uniqueOccurrencesWithoutMap(input2));
        System.out.println(false == uniqueOccurrenceNumber.uniqueOccurrencesWithoutMap(input2));
    }

    public boolean uniqueOccurrences(int[] arr) {
        Map<Integer, Integer> occurrences = new HashMap<>();

        for (int num : arr) {
            occurrences.put(num, occurrences.getOrDefault(num, 0) + 1);
        }

        Set<Integer> uniqueOccurrences = new HashSet<>();

        for (Map.Entry<Integer, Integer> num : occurrences.entrySet()) {
            if (!uniqueOccurrences.add(num.getValue())) {
                return false;
            }
        }

        return true;
    }

    public boolean uniqueOccurrencesWithoutMap(int[] arr) {
        Arrays.sort(arr);

        Set<Integer> uniqueOccurrences = new HashSet<>();
        int current = arr[0];
        int currentCount = 0;
        for (int j : arr) {
            if (current == j) {
                currentCount++;
            } else {
                if (!uniqueOccurrences.add(currentCount)) {
                    return false;
                }
                current = j;
                currentCount = 1;
            }
        }

        return uniqueOccurrences.add(currentCount);
    }

}
