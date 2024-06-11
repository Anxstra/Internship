package five;

import java.util.Comparator;
import java.util.Map;

public class PeteBaker {

    public static int cakes(Map<String, Integer> recipe, Map<String, Integer> available) {
        return recipe.entrySet().stream()
                                   .map(entry ->  available.getOrDefault(entry.getKey(), 0) / entry.getValue())
                                   .min(Comparator.comparing(Integer::intValue))
                                   .orElse(0);
    }

    public static int cakesWithoutStream(Map<String, Integer> recipe, Map<String, Integer> available) {
        int cakes = Integer.MAX_VALUE;
        for (Map.Entry<String, Integer> entry : recipe.entrySet()) {
            cakes = Math.min(cakes, available.getOrDefault(entry.getKey(), 0) / entry.getValue());
        }
        return cakes;
    }

    public static void main(String[] args) {
        Map<String, Integer> recipe = Map.of(
                "flour", 500,
                "sugar", 200,
                "eggs", 1,
                "cinnamon", 300);
        Map<String, Integer> available = Map.of(
                "flour", 1200,
                "sugar", 1200,
                "eggs", 5,
                "milk", 200);

        System.out.println(0 == cakes(recipe, available));
        System.out.println(0 == cakesWithoutStream(recipe, available));
    }
}
