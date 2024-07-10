package leetcode.medium;

import java.util.Objects;

public class DeleteMiddleNode {

    public static void main(String[] args) {
        ListNode input1 = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        ListNode input2 = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4))));
        ListNode expected1 = new ListNode(1, new ListNode(2, new ListNode(4, new ListNode(5))));
        ListNode expected2 = new ListNode(1, new ListNode(2, new ListNode(4)));

        DeleteMiddleNode solution = new DeleteMiddleNode();
        System.out.println(expected1.equals(solution.deleteMiddle(input1)));
        System.out.println(expected2.equals(solution.deleteMiddle(input2)));
    }

    public ListNode deleteMiddle(ListNode head) {
        if (head.next == null) {
            return null;
        }

        ListNode slowHead = head;
        ListNode fastHead = head.next.next;
        while (fastHead != null && fastHead.next != null) {
            slowHead = slowHead.next;
            fastHead = fastHead.next.next;
        }
        slowHead.next = slowHead.next.next;

        return head;
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

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            ListNode listNode = (ListNode) o;
            return val == listNode.val && Objects.equals(next, listNode.next);
        }

        @Override
        public int hashCode() {
            int result = val;
            result = 31 * result + Objects.hashCode(next);
            return result;
        }

        @Override
        public String toString() {
            return "ListNode{" +
                    "val=" + val +
                    ", next=" + next +
                    '}';
        }
    }
}
