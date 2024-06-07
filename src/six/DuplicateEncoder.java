package six;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class DuplicateEncoder {

    static String encode(String word) {
        String lowerCase = word.toLowerCase();
        Map<String, Integer> charOccurrenceCount = Arrays.stream(lowerCase.split(""))
                                                         .collect(Collectors.toMap(str -> str, str -> 1, Integer::sum));
        return Arrays.stream(lowerCase.split(""))
                     .map(str -> charOccurrenceCount.get(str) > 1 ? ")" : "(")
                     .collect(Collectors.joining());
    }

    static String encodeWithoutStream(String word) {
        String lowerCase = word.toLowerCase();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < lowerCase.length(); i++) {
            char c = lowerCase.charAt(i);
            result.append(lowerCase.lastIndexOf(c) == lowerCase.indexOf(c) ? "(" : ")");
        }
        return result.toString();
    }

    public static void main(String[] args) {
        System.out.println(")()())()(()()(".equals(encode("Prespecialized")));
        System.out.println(")()())()(()()(".equals(encodeWithoutStream("Prespecialized")));
    }
}
