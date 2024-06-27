package leetcode.easy;

import java.util.LinkedList;
import java.util.List;

public class KidsWithGreatestNumber {

    public static void main(String[] args) {
        KidsWithGreatestNumber k = new KidsWithGreatestNumber();

        System.out.println(List.of(true, false, false, false, false)
                               .equals(k.kidsWithCandies(new int[]{4, 2, 1, 1, 2}, 1)));
    }

    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        int max = candies[0];
        List<Boolean> result = new LinkedList<>();
        for (int candy : candies) {
            max = Math.max(candy, max);
        }
        for (int candy : candies) {
            result.add(candy + extraCandies >= max);
        }
        return result;
    }
}
