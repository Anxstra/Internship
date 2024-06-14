package five;

public class Greed {

    public static int greedy(int[] dice) {
        int result = 0;
        int[] diceCount = new int[7];
        int[] triplets = new int[]{0, 1000, 200, 300, 400, 500, 600};

        for (int val : dice) {
            diceCount[val]++;
        }
        for (int i = 0; i < diceCount.length; i++) {
            if (diceCount[i] >= 3) {
                result += triplets[i];
                diceCount[i] -= 3;
            }
            if (i == 1) {
                result += 100 * diceCount[1];
            }
            if (i == 5) {
                result += 50 * diceCount[5];
            }
        }

        return result;
    }

    public static int greedyEfficient(int[] dice) {
        int[] diceCount = new int[7];

        for (int val : dice) {
            diceCount[val]++;
        }

        return diceCount[1] / 3 * 1000 + diceCount[1] % 3 * 100 + diceCount[2] / 3 * 200 + diceCount[3] / 3 * 300
                + diceCount[4] / 3 * 400 + diceCount[5] / 3 * 500 + diceCount[5] % 3 * 50 + diceCount[6] / 3 * 600;
    }

    public static void main(String[] args) {
        System.out.println(1100 == greedy(new int[]{1,1,1,3,1}));
        System.out.println(1100 == greedyEfficient(new int[]{1,1,1,3,1}));
    }
}
