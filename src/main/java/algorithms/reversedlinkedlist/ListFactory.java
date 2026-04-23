package algorithms.reversedlinkedlist;

public class ListFactory {

    // Bygger en liste af et antal int-værdier
    public static Node buildList(int... values) {
        if (values.length == 0) return null;
        Node head = new Node(values[0]);
        Node current = head;
        for (int i = 1; i < values.length; i++) {
            current.next = new Node(values[i]);
            current = current.next;
        }
        return head;
    }

// values = 1, 5, 7, 12, 17

    //

    public static Node reverseList(int... values) {
        if (values.length == 0) return null;

        Node tail = new Node(values[values.length - 1]);
        Node current = tail;
        for (int i = values.length - 1; i > 0; i--) {
            current.next = new Node(values[i - 1]);
            current = current.next;
        }
        return tail;
    }


    public static Node reverseLists(Node head) {
        Node prev = null;
        Node current = head;
        Node next = null;

        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        return prev;
    }
}
