package leetcode.easy;

public class FindPivotIndex {

    public static void main(String[] args) {
        int[] input = new int[]{1, 7, 3, 6, 5, 6};

        FindPivotIndex pivotIndex = new FindPivotIndex();
        System.out.println(3 == pivotIndex.pivotIndex(input));
    }

    public int pivotIndex(int[] nums) {
        int sumLeft = 0;
        int sumRight = 0;

        for (int num : nums) {
            sumRight += num;
        }

        for (int i = 0; i < nums.length; i++) {
            sumRight -= nums[i];
            if (sumLeft == sumRight) {
                return i;
            }
            sumLeft += nums[i];
        }

        return -1;
    }
}
