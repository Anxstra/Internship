package eight;

import java.util.Arrays;

public class InvertValues {
    public static int[] invert(int[] array) {
        int[] arr = Arrays.copyOf(array, array.length);
        for (int i = 0; i < arr.length; i++) {
            arr[i] = -array[i];
        }
        return arr;
    }

    public static int[] invertByStream(int[] array) {
        return Arrays.stream(array).map(i -> -i).toArray();
    }

    public static void main(String[] args) {
        int[] res = new int[]{1, -2, 3, -4, 5};
        System.out.println(Arrays.toString(invert(res)));
        System.out.println(Arrays.toString(invertByStream(res)));
    }
}
