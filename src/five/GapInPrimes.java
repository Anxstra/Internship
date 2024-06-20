package five;

import java.util.Arrays;

public class GapInPrimes {

    public static long[] gap(int g, long m, long n) {
        long lastPrime = Long.MIN_VALUE;
        while (m <= n) {
            if (isPrime(m)) {
                if (m - lastPrime == g) {
                    return new long[]{lastPrime, m};
                }
                lastPrime = m;
            }
            m++;
        }
        return null;
    }

    private static boolean isPrime(long n) {
        if (n < 2) return false;
        if (n <= 3) return true;
        double sqrt = Math.sqrt(n);
        for (int i = 2; i <= sqrt; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.equals(new long[]{337, 347}, gap(10, 300, 400)));
    }
}
