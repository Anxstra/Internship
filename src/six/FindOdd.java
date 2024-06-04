package six;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

//Precondition: there will always be only one integer that appears an odd number of times.
public class FindOdd {

    public static int findIt(int[] a) {
        Map<Integer, Integer> countOfOccurrences = new HashMap<>();
        for(int number : a) {
            countOfOccurrences.put(number, countOfOccurrences.getOrDefault(number, 0) + 1);
        }
        for(Map.Entry<Integer, Integer> entry : countOfOccurrences.entrySet()) {
            if(entry.getValue() % 2 != 0) {
                return entry.getKey();
            }
        }
        return 0;
    }

    public static int findItWithStream(int[] a) {
        return Arrays.stream(a).boxed()
                    .collect(Collectors.toMap(key -> key, key -> 1, Integer::sum)).entrySet().stream()
                    .filter(entry -> entry.getValue() % 2 != 0)
                    .map(Map.Entry::getKey)
                    .findFirst().orElse(0);

    }

    public static int findItWithXor(int[] a) {
        int result = 0;
        for(int number : a) {
            result ^= number;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(5 == findIt(new int[]{20,1,-1,2,-2,3,3,5,5,1,2,4,20,4,-1,-2,5}));
        System.out.println(5 == findItWithStream(new int[]{20,1,-1,2,-2,3,3,5,5,1,2,4,20,4,-1,-2,5}));
        System.out.println(5 == findItWithXor(new int[]{20,1,-1,2,-2,3,3,5,5,1,2,4,20,4,-1,-2,5}));
    }
}
