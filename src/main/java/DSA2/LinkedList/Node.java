package DSA2.LinkedList;

public class Node {

    int data;
    Node next;

    public Node(int data) {
        this.data = data;
        this.next = null;
    }

    /**
     * Creates a linked list from an array of integers.
     *
     * @param A Array input
     * @return Head of the linked list
     */
    public static Node createLinkedList(int[] A) {
        Node head = new Node(A[0]);
        Node current = head;

        for (int i = 1; i < A.length; i++) {
            current.next = new Node(A[i]);
            current = current.next;
        }

        return head;
    }

    /**
     * Inserts a node with given data at a given index.
     *
     * @param position Index to insert node
     * @param head     Head of the list
     * @param data     Value to insert
     * @return Head of the updated list
     */
    public static Node insertNode(int position, Node head, int data) {
        Node node = new Node(data);

        if (position == 0) {
            node.next = head;
            return node;
        }

        Node temp = head;
        int k = 0;

        while (k < position - 1 && temp != null) {
            k++;
            temp = temp.next;
        }

        if (temp == null) {
            System.out.println("Position out of bounds");
            return head;
        }

        node.next = temp.next;
        temp.next = node;
        return head;
    }

    /**
     * Removes node at a given index.
     *
     * @param position Index to remove node
     * @param head     Head of the list
     * @return Updated head
     */
    public static Node removeNodeIndex(int position, Node head) {
        if (head == null || position < 0) {
            System.out.println("Invalid position or empty list");
            return head;
        }

        if (position == 0) {
            return head.next;
        }

        Node temp = head;
        int k = 0;

        while (k < position - 1 && temp != null) {
            k++;
            temp = temp.next;
        }

        if (temp == null || temp.next == null) {
            System.out.println("Position out of bounds");
            return head;
        }

        temp.next = temp.next.next;
        return head;
    }

    /**
     * Updates data of the node at a given index.
     *
     * @param position Index of node
     * @param data     New value
     * @param head     Head of the list
     * @return Updated head
     */
    public static Node updateNode(int position, int data, Node head) {
        if (head == null || position < 0) {
            System.out.println("Invalid position or empty list");
            return head;
        }

        Node temp = head;
        int k = 0;

        while (k < position && temp != null) {
            temp = temp.next;
            k++;
        }

        if (temp == null) {
            System.out.println("Position out of bounds");
            return head;
        }

        temp.data = data;
        return head;
    }

    /**
     * Prints the linked list in a readable format.
     *
     * @param head Head of the list
     */
    public static void printNode(Node head) {
        if (head == null) {
            System.out.println("List is empty");
            return;
        }

        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data);
            if (temp.next != null) {
                System.out.print(" -> ");
            } else {
                System.out.print(" -> Null");
            }
            temp = temp.next;
        }
        System.out.println();
    }

    /**
     * Creates a cycle in the linked list at the given position.
     * For example, position 4 will link the last node to the node at index 4.
     *
     * @param head     Head of the list
     * @param position Zero-based index where the cycle should begin
     */
    public static void createCycle(Node head, int position) {
        if (head == null || position < 0) return;

        Node cycleStart = null;
        Node temp = head;
        int index = 0;

        while (temp.next != null) {
            if (index == position) {
                cycleStart = temp;
            }
            temp = temp.next;
            index++;
        }

        if (cycleStart != null) {
            temp.next = cycleStart;  // Create the cycle
        }
    }

}
