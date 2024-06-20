package five;

import java.util.Arrays;

public class CountIPAddresses {

    public static long ipsBetween(String start, String end) {
        int[] startValues = Arrays.stream(start.split("\\."))
                                  .mapToInt(Integer::parseInt)
                                  .toArray();
        int[] endValues = Arrays.stream(end.split("\\."))
                                .mapToInt(Integer::parseInt)
                                .toArray();

        long result = 0;
        for (int i = 0; i < startValues.length; i++) {
            int degree = 4 - i - 1;
            result += (long) ((endValues[i] - startValues[i]) * Math.pow(256, degree));
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(50 == ipsBetween( "10.0.0.0", "10.0.0.50" ));
        System.out.println(246 == ipsBetween( "20.0.0.10", "20.0.1.0" ));
        System.out.println((1L << 32L) - 1L == ipsBetween( "0.0.0.0", "255.255.255.255" ));
    }
}
