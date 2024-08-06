package leetcode.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PhoneLetterCombination {

    private static final Map<Character, String> digitToChars = new HashMap<>();

    static {
        digitToChars.put('2', "abc");
        digitToChars.put('3', "def");
        digitToChars.put('4', "ghi");
        digitToChars.put('5', "jkl");
        digitToChars.put('6', "mno");
        digitToChars.put('7', "pqrs");
        digitToChars.put('8', "tuv");
        digitToChars.put('9', "wxyz");
    }

    public static void main(String[] args) {
        PhoneLetterCombination combination = new PhoneLetterCombination();
        List<String> expected = List.of("ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf");

        System.out.println(expected.equals(combination.letterCombinations("23")));
    }

    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        char[] chars = digits.toCharArray();

        if (chars.length != 0) {
            backtrack(0, new StringBuilder(), chars, result);
        }

        return result;
    }

    private void backtrack(int pos, StringBuilder current, char[] digits, List<String> result) {
        if (pos == digits.length) {
            result.add(current.toString());
        } else {
            char[] chars = digitToChars.get(digits[pos]).toCharArray();
            for (char ch : chars) {
                backtrack(pos + 1, current.append(ch), digits, result);
                current.delete(current.length() - 1, current.length());
            }
        }
    }
}
