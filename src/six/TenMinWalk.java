package six;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TenMinWalk {

    public static boolean isValid(char[] walk) {
        if (walk.length != 10) {
            return false;
        }
        Map<Character, Integer> directionsCount = IntStream.range(0, walk.length)
                                                           .mapToObj(i -> walk[i])
                                                           .collect(Collectors.toMap(ch -> ch, ch -> 1, Integer::sum));
        return directionsCount.getOrDefault('n', 0)
                              .equals(directionsCount.getOrDefault('s', 0)) &&
                directionsCount.getOrDefault('w', 0)
                               .equals(directionsCount.getOrDefault('e', 0));
    }

    public static boolean isValidWithReduce(char[] walk) {
        if (walk.length != 10) {
            return false;
        }
        Map<Character, Integer> directionsCount = IntStream.range(0, walk.length)
                                                           .mapToObj(i -> walk[i])
                                                           .reduce(new HashMap<>(),
                                                                   (map, ch) -> {
                                                                       map.merge(ch, 1, Integer::sum);
                                                                       return map;
                                                                   },
                                                                   (map1, map2) -> {
                                                                       map2.forEach((key, value) -> map1.merge(key, value, Integer::sum));
                                                                       return map1;
                                                                   });
        return directionsCount.getOrDefault('n', 0)
                              .equals(directionsCount.getOrDefault('s', 0)) &&
                directionsCount.getOrDefault('w', 0)
                               .equals(directionsCount.getOrDefault('e', 0));
    }

    public static boolean isValidWithoutStream(char[] walk) {
        if (walk.length != 10) {
            return false;
        }
        int countWest = 0;
        int countEast = 0;
        int countNorth = 0;
        int countSouth = 0;
        for (char ch : walk) {
            switch (ch) {
                case 'n' -> countNorth++;
                case 's' -> countSouth++;
                case 'w' -> countWest++;
                case 'e' -> countEast++;
            }
        }
        return countEast == countWest && countNorth == countSouth;
    }

    public static void main(String[] args) {
        System.out.println(isValid(new char[]{'n', 's', 'n', 's', 'n', 's', 'n', 's', 'n', 's'}));
        System.out.println(isValidWithReduce(new char[]{'n', 's', 'n', 's', 'n', 's', 'n', 's', 'n', 's'}));
        System.out.println(isValidWithoutStream(new char[]{'n', 's', 'n', 's', 'n', 's', 'n', 's', 'n', 's'}));
    }

}
