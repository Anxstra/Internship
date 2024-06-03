package eight;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Sorter {
    public static List<String> sort(List<String> textbooks) {
        textbooks.sort(Comparator.comparing(String::toLowerCase));
        return textbooks;
    }

    public static void main(String[] args) {
        ArrayList<String> expected  = new ArrayList<>(Arrays.asList("Algebra", "english", "Geometry", "History"));
        ArrayList<String> textbooks = new ArrayList<>(Arrays.asList("Algebra", "History", "Geometry", "english"));
        System.out.println(expected.equals(sort(textbooks)));
    }
}
