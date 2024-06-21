package five;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SumOfK {

    public static Integer chooseBestSum(int t, int k, List<Integer> ls) {
        if (ls.size() < k) return null;

        int[] distances = ls.stream().mapToInt(Integer::intValue).toArray();
        List<List<Integer>> allCombinations = new ArrayList<>();
        combination(distances, k, 0, new ArrayList<>(), allCombinations);

        Integer bestSum = null;
        for (List<Integer> combo : allCombinations) {
            int sum = combo.stream().mapToInt(Integer::intValue).sum();
            if (sum <= t && (bestSum == null || sum > bestSum)) {
                bestSum = sum;
            }
        }

        return bestSum;
    }

    public static void combination(int[] distances, int k, int start, List<Integer> current, List<List<Integer>> result) {
        if (current.size() == k) {
            result.add(new ArrayList<>(current));
            return;
        }
        for (int i = start; i < distances.length; i++) {
            current.add(distances[i]);
            combination(distances, k, i + 1, current, result);
            current.remove(current.size() - 1);
        }
    }

    public static Integer chooseBestSumUpdated(int t, int k, List<Integer> ls) {
        int result = -1;
        for (int i = 0; i < ls.size(); i++) {
            if (ls.get(i) <= t) {
                if (k == 1) {
                    result = Math.max(result, ls.get(i));
                } else {
                    Integer temp = chooseBestSum(t - ls.get(i), k - 1, ls.subList(i + 1, ls.size()));
                    if (temp != null) {
                        result = Math.max(result, ls.get(i) + temp);
                    }
                }
            }
        }
        if (result < 0) {
            return null;
        }
        return result;
    }

    public static void main(String[] args) {
        List<Integer> ts = new ArrayList<>(List.of(50, 55, 56, 57, 58));
        Integer res1 = chooseBestSum(163, 3, ts);
        Integer res2 = chooseBestSumUpdated(163, 3, ts);
        System.out.println(Objects.nonNull(res1) && res1.equals(163));
        System.out.println(Objects.nonNull(res2) && res2.equals(163));
    }
}
