package leetcode.medium;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class ContainerWithWater {

    public static void main(String[] args) {
        int[] input = new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7};

        ContainerWithWater cw = new ContainerWithWater();
        System.out.println(49 == cw.maxArea(input));
    }

    public int maxArea(int[] height) {
        int area = 0;
        int l = 0;
        int r = height.length - 1;

        while (l < r) {
            int minHeight = min(height[l], height[r]);
            area = max(area, minHeight * (r - l));
            while (l < r && height[l] <= minHeight) {
                l++;
            }
            while (l < r && height[r] <= minHeight) {
                r--;
            }
        }

        return area;
    }
}
