package leetcode.easy;

import java.util.Objects;

public class ReverseLinkedList {

    public static void main(String[] args) {
        ListNode input1 = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        ListNode input2 = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        ListNode expected = new ListNode(5, new ListNode(4, new ListNode(3, new ListNode(2, new ListNode(1)))));

        ReverseLinkedList reverseLinkedList = new ReverseLinkedList();
        System.out.println(expected.equals(reverseLinkedList.reverseList(input1)));
        System.out.println(expected.equals(reverseLinkedList.reverseListRecursive(input2)));
    }

    public ListNode reverseList(ListNode head) {
        if (Objects.isNull(head)) {
            return null;
        }

        ListNode current = head;
        ListNode prev = null;
        while (Objects.nonNull(current)) {
            ListNode next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        return prev;
    }

    public ListNode reverseListRecursive(ListNode head) {
        if (Objects.isNull(head)) {
            return null;
        }

        ListNode newHead = head;
        if (Objects.nonNull(head.next)) {
            newHead = reverseListRecursive(head.next);
            head.next.next = head;
        }
        head.next = null;

        return newHead;
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