package six;

import java.util.Arrays;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class SpinWords {

    public String spinWords(String sentence) {
        return Arrays.stream(sentence.split(" "))
                .map(i -> i.length() > 4 ? new StringBuilder(i).reverse().toString() : i)
                .collect(Collectors.joining(" "));
    }

    public String spinWordsWithoutStream(String sentence) {
        String[] words = sentence.split(" ");
        for (int i = 0; i < words.length; i++) {
            if(words[i].length() > 4) {
                words[i] = new StringBuilder(words[i]).reverse().toString();
            }
        }
        StringJoiner joiner = new StringJoiner(" ");
        for (String word : words) {
            joiner.add(word);
        }
        return joiner.toString();
    }

    public static void main(String[] args) {
        SpinWords spinWords = new SpinWords();
        System.out.println("Hey wollef sroirraw".equals(spinWords.spinWords("Hey fellow warriors")));
        System.out.println("Hey wollef sroirraw".equals(spinWords.spinWordsWithoutStream("Hey fellow warriors")));
    }
}
