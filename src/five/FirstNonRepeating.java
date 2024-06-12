package five;

public class FirstNonRepeating {

    public static String firstNonRepeatingLetter(String s) {
        char[] originalChars = s.toCharArray();
        s = s.toLowerCase();
        char[] loweredChars = s.toCharArray();
        for (int i = 0; i < loweredChars.length; i++) {
            if (s.indexOf(loweredChars[i]) == s.lastIndexOf(loweredChars[i])) {
                return String.valueOf(originalChars[i]);
            }
        }
        return "";
    }

    public static String firstNonRepeatingLetterWithStream(String s) {
        String loweredString = s.toLowerCase();
        return s.chars()
                .filter(ch -> {
                    ch = Character.toLowerCase(ch);
                    return loweredString.indexOf(ch) == loweredString.lastIndexOf(ch);
                })
                .mapToObj(ch -> String.valueOf((char) ch))
                .findFirst()
                .orElse("");
    }

    public static void main(String[] args) {
        System.out.println("t".equals(firstNonRepeatingLetter("streSS")));
        System.out.println("t".equals(firstNonRepeatingLetterWithStream("streSS")));
    }
}
