package leetcode.medium;

public class MaxTwinSum {

    public static void main(String[] args) {
        ListNode input = new ListNode(5, new ListNode(4, new ListNode(2, new ListNode(1))));

        MaxTwinSum maxTwinSum = new MaxTwinSum();
        System.out.println(6 == maxTwinSum.pairSum(input));
    }

    private static ListNode reverseList(ListNode slow) {
        ListNode prev = null;
        ListNode curr = slow;
        ListNode next;
        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    public int pairSum(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode left = head;
        ListNode right = reverseList(slow);

        int max = 0;
        while (right != null) {
            max = Math.max(max, (right.val + left.val));
            right = right.next;
            left = left.next;
        }
        return max;
    }

    public static class ListNode {

        int val;

        ListNode next;

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
