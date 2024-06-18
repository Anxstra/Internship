package five;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LoopInspector {

    public static void main(String[] args) {
        Node node1 = new Node();
        Node node2 = new Node();
        Node node3 = new Node();
        Node node4 = new Node();

        node1.setNext(node2);
        node2.setNext(node3);
        node3.setNext(node4);
        node4.setNext(node2);

        LoopInspector inspector = new LoopInspector();
        System.out.println(3 == inspector.loopSize(node1));
        System.out.println(3 == inspector.loopSizeFloydAlgorithm(node1));
    }

    public int loopSize(Node node) {
        List<Node> nodes = new ArrayList<>();
        nodes.add(node);
        for (int i = 0; Objects.nonNull(node.getNext()); i++) {
            int index = nodes.indexOf(node.getNext());
            if (index != -1) {
                return i - index + 1;
            }
            node = node.getNext();
            nodes.add(node);
        }
        return 0;
    }

    public int loopSizeFloydAlgorithm(Node node) {
        Node a = node;
        Node b = node.getNext();
        while (a != b) {
            a = a.getNext();
            b = b.getNext().getNext();
        }

        int result = 0;

        do {
            a = a.getNext();
            result++;
        } while (a != b);

        return result;
    }

    static class Node {

        private Node next;

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }
}

