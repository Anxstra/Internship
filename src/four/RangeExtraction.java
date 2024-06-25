package four;

import java.util.StringJoiner;

public class RangeExtraction {

    public static String rangeExtraction(int[] arr) {
        StringJoiner sj = new StringJoiner(",");
        int length = 1;
        for (int i = 1; i < arr.length; i++) {
            if (Math.abs(arr[i] - arr[i - 1]) == 1) {
                length++;
            } else {
                appendSeq(arr, length, sj, i);
                length = 1;
            }
        }
        appendSeq(arr, length, sj, arr.length);
        return sj.toString();
    }

    private static void appendSeq(int[] values, int seqLength, StringJoiner sj, int currentPos) {
        if (seqLength > 2) {
            sj.add(values[currentPos - seqLength] + "-" + values[currentPos - 1]);
        } else {
            for (int j = seqLength; j > 0; j--) {
                sj.add(String.valueOf(values[currentPos -j]));
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("-10--8,-6,-3-1,3-5,7-11,14,15,17-20".equals(
                rangeExtraction(new int[] {-10, -9, -8, -6, -3, -2, -1, 0, 1, 3, 4, 5, 7, 8, 9, 10, 11, 14, 15, 17, 18, 19, 20})));
    }
}
