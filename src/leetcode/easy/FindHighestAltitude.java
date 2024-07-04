package leetcode.easy;

import static java.lang.Math.max;

public class FindHighestAltitude {

    public int largestAltitude(int[] gain) {
        int altitude = 0;
        int sum = 0;

        for (int height : gain) {
            sum += height;
            altitude = max(altitude, sum);
        }

        return altitude;
    }

    public static void main(String[] args) {
        int[] input = {-5,1,5,0,-7};

        FindHighestAltitude f = new FindHighestAltitude();
        System.out.println(1 == f.largestAltitude(input));
    }

}
