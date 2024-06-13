package five;

import java.math.BigInteger;

public class StringIncrementer {

    public static String incrementString(String str) {
        if (str.isEmpty()) return "1";
        int i = str.length();
        while (i > 0 && Character.isDigit(str.charAt(i - 1))) {
            i--;
        }
        String lettersPart = str.substring(0, i);
        String numberPart = str.substring(i);
        if (numberPart.isEmpty()) {
            return lettersPart + "1";
        }
        int numberLength = numberPart.length();
        BigInteger incrementedNumber = new BigInteger(numberPart).add(BigInteger.ONE);
        String incrementedNumberStr = String.format("%0" + numberLength + "d", incrementedNumber);
        return lettersPart + incrementedNumberStr;
    }

    public static void main(String[] args) {
        System.out.println("foobar01000".equals(incrementString("foobar00999")));
    }
}
