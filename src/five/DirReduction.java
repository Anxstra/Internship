package five;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class DirReduction {

    public static String[] dirReduc(String[] arr) {
        Deque<String> result = Arrays.stream(arr)
                                     .reduce(new ArrayDeque<>(), (subtotal, next) -> {
                                         if (subtotal.isEmpty()) {
                                             subtotal.add(next);
                                             return subtotal;
                                         }
                                         if (verifyDirection(subtotal.peekLast(), next)) {
                                             subtotal.pollLast();
                                         } else {
                                             subtotal.add(next);
                                         }
                                         return subtotal;
                                     }, (deque1, deque2) -> {
                                         deque1.addAll(deque2);
                                         return deque1;
                                     });
        return result.toArray(String[]::new);
    }

    private static boolean verifyDirection(String previous, String next) {
        return previous.equals("NORTH") && next.equals("SOUTH") ||
                previous.equals("SOUTH") && next.equals("NORTH") ||
                previous.equals("WEST") && next.equals("EAST") ||
                previous.equals("EAST") && next.equals("WEST");
    }

    public static String[] dirReducWithoutStream(String[] arr) {
        Deque<String> result = new ArrayDeque<>();
        for (String str : arr) {
            if (result.isEmpty()) {
                result.add(str);
                continue;
            }
            if (verifyDirection(result.peekLast(), str)) {
                result.pollLast();
            } else {
                result.add(str);
            }
        }
        return result.toArray(String[]::new);
    }

    public static void main(String[] args) {
        System.out.println(Arrays.equals(new String[]{"WEST"},
                dirReduc(new String[]{"NORTH", "SOUTH", "SOUTH", "EAST", "WEST", "NORTH", "WEST"})));
        System.out.println(Arrays.equals(new String[]{"WEST"},
                dirReducWithoutStream(new String[]{"NORTH", "SOUTH", "SOUTH", "EAST", "WEST", "NORTH", "WEST"})));
    }
}
