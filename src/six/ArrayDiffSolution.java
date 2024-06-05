package six;

import java.util.*;
import java.util.stream.Collectors;

public class ArrayDiffSolution {

    public static int[] arrayDiff(int[] a, int[] b) {
        Set<Integer> subtrahend = Arrays.stream(b).boxed().collect(Collectors.toSet());
        return Arrays.stream(a).filter(i -> !subtrahend.contains(i)).toArray();
    }

    public static int[] arrayDiffWithStream(int[] a, int[] b) {
        List<Integer> subtrahend = Arrays.stream(b).boxed().toList();
        List<Integer> minuend = Arrays.stream(a).boxed().collect(Collectors.toList());
        minuend.removeAll(subtrahend);
        return minuend.stream().mapToInt(i -> i).toArray();
    }

    public static int[] arrayDiffWithoutStream(int[] a, int[] b) {
        List<Integer> minuend  = new LinkedList<>();
        for (int number : a) {
            boolean isMatched = false;
            for (int sample : b) {
                if (sample == number) {
                    isMatched = true;
                    break;
                }
            }
            if (!isMatched) {
                minuend.add(number);
            }
        }
        int[] result = new int[minuend.size()];
        for(int i = 0; i < result.length; i++) {
            result[i] = minuend.get(i);
        }
        return result;
    }



    public static void main(String[] args) {
        System.out.println(Arrays.equals(arrayDiff(new int[] {1, 2, 2, 2, 3}, new int[] {2}), new int[]{1, 3}));
        System.out.println(Arrays.equals(arrayDiffWithStream(new int[] {1, 2, 2, 2, 3}, new int[] {2}), new int[]{1, 3}));
        System.out.println(Arrays.equals(arrayDiffWithoutStream(new int[] {1, 2, 2, 2, 3}, new int[] {2}), new int[]{1, 3}));
    }
}
