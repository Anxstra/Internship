package five;

import java.util.Arrays;

public class ProdFib {

    public static long[] productFib(long prod) {
        long penultimate = 0L;
        long last = 1L;
        while (penultimate * last < prod) {
            long newLast = last + penultimate;
            penultimate = last;
            last = newLast;
        }
        if (penultimate * last == prod) {
            return new long[]{penultimate, last, 1L};
        } else {
            return new long[]{penultimate, last, 0L};
        }
    }

    public static void main(String[] args) {
        System.out.println(Arrays.equals(new long[] {55, 89, 1}, productFib(4895)));
        System.out.println(Arrays.equals(new long[] {89, 144, 0}, productFib(5895)));

    }
}
