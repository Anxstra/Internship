package five;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PigLatin {

    public static String pigIt(String str) {
        Pattern pattern = Pattern.compile("(\\w+(?=\\W*+))++");
        Matcher matcher = pattern.matcher(str);
        StringBuilder sb = new StringBuilder();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).substring(1) + matcher.group(1).charAt(0) + "ay");
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    public static String pigItSimple(String str) {
        return str.replaceAll("(\\w)(\\w*)", "$2$1ay");
    }

    public static void main(String[] args) {
        System.out.println("elloHay orldway !".equals(pigIt("Hello world !")));
        System.out.println("elloHay orldway !".equals(pigItSimple("Hello world !")));
    }
}
