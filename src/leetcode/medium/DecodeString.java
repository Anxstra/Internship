package leetcode.medium;

import java.util.ArrayDeque;
import java.util.Deque;

public class DecodeString {

    public static void main(String[] args) {
        DecodeString ds = new DecodeString();
        System.out.println("abcabccdcdcdef".equals(ds.decodeString("2[abc]3[cd]ef")));
    }

    public String decodeString(String s) {
        Deque<Integer> intStack = new ArrayDeque<>();
        Deque<StringBuilder> stringStack = new ArrayDeque<>();
        StringBuilder currentString = new StringBuilder();
        int k = 0;

        for (char c : s.toCharArray()) {
            switch (c) {
                case '[' -> {
                    intStack.push(k);
                    stringStack.push(currentString);
                    currentString = new StringBuilder();
                    k = 0;
                }
                case ']' -> {
                    StringBuilder decodedString = stringStack.pop();
                    decodedString.append(String.valueOf(currentString).repeat(Math.max(0, intStack.pop())));
                    currentString = decodedString;
                }
                default -> {
                    if (Character.isDigit(c)) {
                        k = k * 10 + (c - '0');
                    } else {
                        currentString.append(c);
                    }
                }
            }
        }
        return currentString.toString();
    }
}
