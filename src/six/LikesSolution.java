package six;

public class LikesSolution {

    public static String whoLikesIt(String... names) {
        return switch (names.length) {
            case 0 -> "no one likes this";
            case 1 -> String.format("%s likes this", names[0]);
            case 2 -> String.format("%s and %s like this", names[0], names[1]);
            case 3 -> String.format("%s, %s and %s like this", names[0], names[1], names[2]);
            default -> String.format("%s, %s and %d others like this", names[0], names[1], names.length - 2);
        };
    }

    public static void main(String[] args) {
        System.out.println("no one likes this".equals(whoLikesIt()));
        System.out.println("Peter likes this".equals(whoLikesIt("Peter")));
        System.out.println("Jacob and Alex like this".equals(whoLikesIt("Jacob", "Alex")));
        System.out.println("Max, John and Mark like this".equals(whoLikesIt("Max", "John", "Mark")));
        System.out.println("Alex, Jacob and 2 others like this".equals(whoLikesIt("Alex", "Jacob", "Mark", "Max")));
    }
}
