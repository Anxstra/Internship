package five;

import java.util.Arrays;
import java.util.stream.Collectors;

public class WeightSort {

    public static String orderWeight(String strng) {
        return Arrays.stream(strng.split(" "))
                     .sorted((s1, s2) -> {
                         if (getWeight(s1) != getWeight(s2)) {
                             return getWeight(s1) - getWeight(s2);
                         } else {
                             return s1.compareTo(s2);
                         }
                     })
                     .collect(Collectors.joining(" "));
    }

    private static int getWeight(String strng) {
        int result = 0;
        for (int i = 0; i < strng.length(); i++) {
            result = result + (strng.charAt(i) - '0');
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println("2000 103 123 4444 99".equals(orderWeight("103 123 4444 99 2000")));
        System.out.println("11 11 2000 10003 22 123 1234000 44444444 9999".equals(
                orderWeight("2000 10003 1234000 44444444 9999 11 11 22 123")));
    }
}
