package leetcode.medium;

import java.util.Objects;

public class OddEvenLinkedList {

    public static void main(String[] args) {
        ListNode input = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        ListNode expected = new ListNode(1, new ListNode(3, new ListNode(5, new ListNode(2, new ListNode(4)))));

        OddEvenLinkedList solution = new OddEvenLinkedList();
        System.out.println(expected.equals(solution.oddEvenList(input)));
    }

    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode oddTail = head;
        ListNode evenHead = head.next;
        ListNode evenTail = head.next;
        while (evenTail != null && evenTail.next != null) {
            oddTail.next = oddTail.next.next;
            evenTail.next = evenTail.next.next;
            oddTail = oddTail.next;
            evenTail = evenTail.next;
        }

        oddTail.next = evenHead;
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
        public final boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof ListNode listNode)) return false;

            return val == listNode.val && Objects.equals(next, listNode.next);
        }

        @Override
        public int hashCode() {
            int result = val;
            result = 31 * result + Objects.hashCode(next);
            return result;
        }
    }
}
