package six;

public class Persist {

    public static int persistence(long n) {
        int result = 0;
        while (n > 9) {
            int multiplication = 1;
            while (n != 0) {
                multiplication *= (int) (n % 10);
                n /= 10;
            }
            result++;
            n = multiplication;
        }
        return result;
    }

    public static int persistenceWithStream(long n) {
        if (n < 10) {
            return 0;
        }
        int multiplication = Long.toString(n)
                                 .chars()
                                 .reduce(1, (subtotal, next) -> subtotal * (next - '0'));
        return persistenceWithStream(multiplication) + 1;
    }

    public static int persistenceRecursively(long n) {
        if (n < 10) {
            return 0;
        }
        int multiplication = 1;
        while (n != 0) {
            multiplication *= (int) (n % 10);
            n /= 10;
        }
        return persistenceRecursively(multiplication) + 1;
    }

    public static void main(String[] args) {
        System.out.println(4 == persistence(999));
        System.out.println(4 == persistenceRecursively(999));
        System.out.println(4 == persistenceWithStream(999));
    }
}
