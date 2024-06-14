package five;

import java.util.Map;
import java.util.stream.Collectors;

public class Scramblies {

    public static boolean scramble(String str1, String str2) {
        Map<Character, Long> charCount = str1.chars()
                                             .mapToObj(c -> (char) c)
                                             .collect(Collectors.groupingBy(ch -> ch, Collectors.counting()));

        for (char ch : str2.toCharArray()) {
            long count = charCount.getOrDefault(ch, 0L);
            if (count == 0) {
                return false;
            }
            charCount.put(ch, count - 1);
        }

        return true;
    }

    public static boolean scrambleOptimal(String str1, String str2) {
        int[] frequency = new int[26];
        for (char ch : str1.toCharArray()) {
            frequency[ch - 'a']++;
        }
        for (char ch : str2.toCharArray()) {
            if (frequency[ch - 'a'] == 0) {
                return false;
            }
            frequency[ch - 'a']--;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(scramble("aabbcamaomsccdd", "commas"));
        System.out.println(scrambleOptimal("aabbcamaomsccdd", "commas"));
    }
}
