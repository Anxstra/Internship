package five;

public class PrimeDecomp {

    public static String factors(int n) {
        StringBuilder result = new StringBuilder();
        for (int factor = 2; factor * factor <= n; factor++) {
            int count = 0;
            while (n % factor == 0) {
                n /= factor;
                count++;
            }
            if (count > 0) {
                result.append("(")
                      .append(factor)
                      .append(count > 1 ? "**" + count : "")
                      .append(")");
            }
        }
        if (n > 1) {
            result.append("(")
                  .append(n)
                  .append(")");
        }
        return result.toString();
    }

    public static void main(String[] args) {
        System.out.println(factors(86240).equals("(2**5)(5)(7**2)(11)"));
    }
}
