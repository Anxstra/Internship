package six;

import java.util.Arrays;

public class FindOutlier {

    static int find(int[] integers) {
        int  mod = Math.abs(integers[0] % 2) + Math.abs(integers[1] % 2) + Math.abs(integers[2] % 2) > 1  ? 0 : 1;
        for (int num : integers) {
            if (Math.abs(num % 2) == mod) {
                return num;
            }
        }
        return 0;
    }

    static int findWithStream(int[] integers) {
        int sum = Arrays.stream(integers).limit(3).map(i -> Math.abs(i % 2)).sum();
        int mod = sum > 1 ? 0 : 1;
        return Arrays.stream(integers).filter(i -> Math.abs(i % 2) == mod).findFirst().getAsInt();
    }

    public static void main(String[] args) {
        System.out.println(3 == find(new int[] {2, 6, 8, -10, 3}));
        System.out.println(206847684 == find(new int[] {206847684, 1056521, 7, 17, 1901, 21104421, 7, 1, 35521, 1, 7781}));
        System.out.println(3 == findWithStream(new int[] {2, 6, 8, -10, 3}));
        System.out.println(206847684 == findWithStream(new int[] {206847684, 1056521, 7, 17, 1901, 21104421, 7, 1, 35521, 1, 7781}));
    }
}
