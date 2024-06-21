package five;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class LastDigitOfBig {

    private static final Map<Integer, Integer[]> lastDigits = new HashMap<>();

    static {
        lastDigits.put(0, new Integer[]{0});
        lastDigits.put(1, new Integer[]{1});
        lastDigits.put(5, new Integer[]{5});
        lastDigits.put(6, new Integer[]{6});

        lastDigits.put(2, new Integer[]{2, 4, 8, 6});
        lastDigits.put(3, new Integer[]{3, 9, 7, 1});
        lastDigits.put(4, new Integer[]{4, 6});
        lastDigits.put(7, new Integer[]{7, 9, 3, 1});
        lastDigits.put(8, new Integer[]{8, 4, 2, 6});
        lastDigits.put(9, new Integer[]{9, 1});
    }

    public static int lastDigit(BigInteger n1, BigInteger n2) {
        if (n2.equals(BigInteger.ZERO)) {
            return 1;
        }

        Integer lastDigit = n1.mod(BigInteger.TEN).intValue();
        Integer[] possibleLastDigits = lastDigits.get(lastDigit);
        if (possibleLastDigits.length == 1) {
            return possibleLastDigits[0];
        } else {
            return possibleLastDigits[n2.subtract(BigInteger.ONE)
                                        .mod(BigInteger.valueOf(possibleLastDigits.length))
                                        .intValue()];
        }
    }

    public static int lastDigitCheat(BigInteger n1, BigInteger n2) {
        return n1.modPow(n2, BigInteger.TEN).intValue();
    }

    public static void main(String[] args) {
        System.out.println(8 == lastDigit(BigInteger.valueOf(2L), BigInteger.valueOf(7L)));
        System.out.println(9 == lastDigit(BigInteger.valueOf(9L), BigInteger.valueOf(7L)));
        System.out.println(8 == lastDigitCheat(BigInteger.valueOf(2L), BigInteger.valueOf(7L)));
        System.out.println(9 == lastDigitCheat(BigInteger.valueOf(9L), BigInteger.valueOf(7L)));
    }

}
