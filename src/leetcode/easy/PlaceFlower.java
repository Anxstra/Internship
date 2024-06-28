package leetcode.easy;

public class PlaceFlower {

    public static void main(String[] args) {
        PlaceFlower p = new PlaceFlower();
        System.out.println(true == p.canPlaceFlowers(new int[]{1, 0, 0, 0, 1}, 1));
        System.out.println(false == p.canPlaceFlowers(new int[]{1, 0, 0, 0, 1}, 2));
    }

    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int length = flowerbed.length;
        for (int i = 0; i < length; i += 2) {
            if (i + 1 < length && flowerbed[i + 1] == 1) {
                i++;
            } else if (flowerbed[i] == 0) {
                n--;
            }
        }
        return n <= 0;
    }
}
