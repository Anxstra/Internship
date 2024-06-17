package five;

import java.math.BigInteger;
import java.util.stream.Stream;

public class SumFct {

    public static BigInteger perimeter(BigInteger n) {
        BigInteger sum = BigInteger.ZERO;
        BigInteger penultimate = BigInteger.ZERO;
        BigInteger previous = BigInteger.ONE;
        BigInteger intermediate;
        for (int i = 0; i <= n.intValue(); i++) {
            sum = sum.add(previous);
            intermediate = penultimate.add(previous);
            penultimate = previous;
            previous = intermediate;
        }
        return sum.multiply(BigInteger.valueOf(4));
    }

    public static void main(String[] args) {
        System.out.println(BigInteger.valueOf(80).equals(perimeter(BigInteger.valueOf(5))));
        System.out.println(BigInteger.valueOf(80).equals(perimeterWithGenerate(BigInteger.valueOf(5))));
    }

    public static BigInteger perimeterWithGenerate(BigInteger n) {
        return Stream.generate(new Generator()::next)
                     .limit(n.longValueExact() + 1)
                     .reduce(BigInteger::add)
                     .orElse(BigInteger.ZERO)
                     .multiply(BigInteger.valueOf(4));
    }

    static class Generator {

        private BigInteger penultimate = BigInteger.ONE;

        private BigInteger previous = BigInteger.ZERO;

        private BigInteger next() {
            BigInteger n = penultimate.add(previous);
            penultimate = previous;
            previous = n;
            return n;
        }
    }
}
