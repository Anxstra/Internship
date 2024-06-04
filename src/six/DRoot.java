package six;

public class DRoot {

    public static int digitalRoot(int n) {
        while(n > 9) {
            int currentNumber = n;
            int sum = 0;
            while(currentNumber != 0) {
                sum += currentNumber % 10;
                currentNumber /= 10;
            }
            n = sum;
        }
        return n;
    }

    public static int digitalRootSimplified(int n) {
        while(n > 9) {
            n = n % 10 + n / 10;
        }
        return n;
    }

    public static void main(String[] args) {
        System.out.println(9 == digitalRoot(198));
        System.out.println(9 == digitalRootSimplified(198));
    }
}
