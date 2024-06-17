package five;

public class NumberOfZeros {

    public static int zeros(int n) {
        int count = 0;
        for (int i = 5; n / i >= 1; i *= 5) {
            count += n / i;
        }
        return count;
    }

    public static int zerosRecursive(int n) {
        return  n < 5 ? 0 : n / 5 + zeros(n / 5);
    }

    public static void main(String[] args) {
        System.out.println(2 == zeros(12));
        System.out.println(2 == zerosRecursive(12));

    }

}
