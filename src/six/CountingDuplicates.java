package six;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CountingDuplicates {

    public static int duplicateCount(String text) {
        String loweredText = text.toLowerCase();
        return (int) text.chars()
                         .filter(ch -> loweredText.indexOf(ch) != loweredText.lastIndexOf(ch))
                         .distinct()
                         .count();
    }

    public static int duplicateCountWithFrequency(String text) {
        List<String> chars = Arrays.asList(text.toLowerCase()
                                               .split(""));
        return (int) chars.stream()
                          .filter(ch -> Collections.frequency(chars, ch) > 1)
                          .distinct()
                          .count();
    }

    public static int duplicateCountWithHashSet(String text) {
        Set<Character> duplicates = new HashSet<>();
        Set<Character> chars = new HashSet<>();
        text.toLowerCase()
            .chars()
            .forEach(c -> {
                if (chars.contains((char) c)) {
                    duplicates.add((char) c);
                } else {
                    chars.add((char) c);
                }
            });
        return duplicates.size();
    }

    public static int duplicateCountWithoutStream(String text) {
        text = text.toLowerCase();
        int count = 0;
        while (!text.isEmpty()) {
            String firstChar = text.substring(0, 1);
            text = text.substring(1);
            if (text.contains(firstChar)) {
                count++;
                text = text.replace(firstChar, "");
            }
        }
        return count;
    }

    public static void main(String[] args) {
        String testThousandA = new String(new char[1000]).replace('\0', 'a');
        String testHundredB = new String(new char[100]).replace('\0', 'b');
        String testTenC = new String(new char[10]).replace('\0', 'c');
        String test1CapitalA = new String(new char[1]).replace('\0', 'A');
        String test1d = new String(new char[1]).replace('\0', 'd');
        String test = test1d + test1CapitalA + testTenC +
                testHundredB + testThousandA;

        System.out.println(3 == duplicateCount(test));
        System.out.println(3 == duplicateCountWithFrequency(test));
        System.out.println(3 == duplicateCountWithHashSet(test));
        System.out.println(3 == duplicateCountWithoutStream(test));
    }

}
