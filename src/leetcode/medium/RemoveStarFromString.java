package leetcode.medium;

import java.util.ArrayDeque;
import java.util.Deque;

public class RemoveStarFromString {

    public static void main(String[] args) {
        RemoveStarFromString removeStarFromString = new RemoveStarFromString();

        System.out.println("lecoe".equals(removeStarFromString.removeStars("leet**cod*e")));
        System.out.println("lecoe".equals(removeStarFromString.removeStarsWithoutStack("leet**cod*e")));
    }

    public String removeStars(String s) {
        Deque<Character> charStack = new ArrayDeque<>();
        char[] chars = s.toCharArray();
        for (char ch : chars) {
            if (ch == '*') {
                charStack.pollLast();
            } else {
                charStack.offer(ch);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (Character ch : charStack) {
            sb.append(ch);
        }

        return sb.toString();
    }

    public String removeStarsWithoutStack(String s) {
        char[] chars = s.toCharArray();
        int shift = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '*') {
                shift = shift + 2;
            } else {
                chars[i - shift] = chars[i];
            }
        }

        return String.valueOf(chars, 0, chars.length - shift);
    }

}
