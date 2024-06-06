package six;

public class BitCounting {
    public static int countBits(int n){
        return Integer.bitCount(n);
    }

    public static int countBitsManually(int n){
        int count = 0;
        while(n != 0){
            if((n & 1) == 1){
                count++;
            }
            n >>>= 1;
        }
        return count;
    }

    public static int countBitsWithString(int n){
        return Integer.toBinaryString(n).replace("0", "").length();
    }

    public static int countBitsWithStream(int n){
        return (int) Integer.toBinaryString(n).chars().filter(i -> i == '1').count();
    }

    public static void main(String[] args) {
        System.out.println(5 == countBits(1234));
        System.out.println(1 == countBits(4));
        System.out.println(5 == countBitsManually(1234));
        System.out.println(1 == countBitsManually(4));
        System.out.println(5 == countBitsWithString(1234));
        System.out.println(1 == countBitsWithString(4));
        System.out.println(5 == countBitsWithStream(1234));
        System.out.println(1 == countBitsWithStream(4));
    }
}
