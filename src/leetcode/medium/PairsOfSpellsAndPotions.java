package leetcode.medium;

import java.util.Arrays;

public class PairsOfSpellsAndPotions {

    public static void main(String[] args) {
        PairsOfSpellsAndPotions p = new PairsOfSpellsAndPotions();
        int[] spells = new int[]{5, 1, 3};
        int[] potions = new int[]{1, 2, 3, 4, 5};
        int[] expected = new int[]{4, 0, 3};

        System.out.println(Arrays.equals(expected, p.successfulPairs(spells, potions, 7L)));
    }

    public int[] successfulPairs(int[] spells, int[] potions, long success) {
        Arrays.sort(potions);
        int[] result = new int[spells.length];

        for (int i = 0; i < spells.length; i++) {
            long minStrength = (success + spells[i] - 1) / spells[i];
            int position = binarySearch(potions, minStrength);
            result[i] = potions.length - position;
        }

        return result;
    }

    int binarySearch(int[] potions, long minStrength) {
        int left = 0;
        int right = potions.length;

        while (left < right) {
            int middle = (left + right) >>> 1;

            if (potions[middle] < minStrength) {
                left = middle + 1;
            } else {
                right = middle;
            }
        }

        return right;
    }
}
