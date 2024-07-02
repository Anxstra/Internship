package leetcode.medium;

public class ReverseWords {

    public String reverseWords(String s) {
        StringBuilder sb = new StringBuilder();
        int start = s.length() - 1;
        while (start >= 0) {
            while (start >= 0 && s.charAt(start) == ' ') {
                start--;
            }
            if (start < 0)
                break;
            int end = start;
            while (start >= 0 && s.charAt(start) != ' ') {
                start--;
            }
            if (!sb.isEmpty())
                sb.append(' ');
            sb.append(s, start + 1, end + 1);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        ReverseWords rw = new ReverseWords();
        System.out.println("example good a".equals(rw.reverseWords(" a good   example ")));
    }

}
