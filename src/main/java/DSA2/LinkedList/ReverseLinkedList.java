package DSA2.LinkedList;

/**
 * Demonstrates basic operations on a singly linked list such as
 * creation, insertion, deletion, update, reverse, and printing.
 */
public class ReverseLinkedList {

    public static void main(String[] args) {
        // Sample input array
        int[] in = new int[]{1, 2, 3, 4, 5};

        // Create a linked list from the array
        Node head = createLinkedList(in);

        // Print original linked list
        System.out.print("Current Linked List is ");
        printNode(head);

        // Reverse the linked list
        Node node = reverseList(head);

        // Print reversed linked list
        System.out.print("Reversed Linked List is ");
        printNode(node);
    }

    /**
     * Reverses the given singly linked list in-place.
     *
     * @param head - head of the original linked list
     * @return new head after reversal
     */
    public static Node reverseList(Node head) {
        Node previous = null; // Tracks the previous node
        Node current = head;  // Current node being processed

        // Traverse the list and reverse pointers
        while (current != null) {
            Node next = current.next; // Store next node
            current.next = previous;  // Reverse the link
            previous = current;       // Move previous forward
            current = next;           // Move current forward
        }

        return previous; // New head of the reversed list
    }

    /**
     * Represents a singly linked list node.
     */
    static class Node {
        private int data;
        private Node next;

        // Constructor to initialize node data
        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    /**
     * Creates a linked list from an integer array.
     *
     * @param A - input array
     * @return head node of the created linked list
     */
    public static Node createLinkedList(int[] A) {
        Node head = new Node(A[0]);   // Initialize head
        Node current = head;          // Pointer to build the list

        // Append each element to the list
        for (int i = 1; i < A.length; i++) {
            current.next = new Node(A[i]);
            current = current.next;
        }

        return head;
    }

    /**
     * Inserts a new node with the given data at the specified position.
     *
     * @param position - index to insert the new node (0-based)
     * @param head     - head of the linked list
     * @param data     - data for the new node
     * @return updated head of the linked list
     */
    public static Node insertNode(int position, Node head, int data) {
        Node node = new Node(data); // New node to insert

        // Case: Insert at the beginning
        if (position == 0) {
            node.next = head;
            return node;
        }

        Node temp = head;
        int k = 0;

        // Move to node before the insertion point
        while (k < position - 1 && temp != null) {
            k++;
            temp = temp.next;
        }

        // Position is invalid
        if (temp == null) {
            System.out.println("Position out of bounds");
            return head;
        }

        // Insert the node
        node.next = temp.next;
        temp.next = node;
        return head;
    }

    /**
     * Removes the node at the specified position.
     *
     * @param position - index of the node to remove
     * @param head     - head of the linked list
     * @return updated head of the linked list
     */
    public static Node removeNodeIndex(int position, Node head) {
        if (head == null || position < 0) {
            System.out.println("Invalid position or empty list");
            return head;
        }

        // Case: remove head node
        if (position == 0) {
            return head.next;
        }

        Node temp = head;
        int k = 0;

        // Move to node before the one to delete
        while (k < position - 1 && temp != null) {
            k++;
            temp = temp.next;
        }

        // Check for invalid position
        if (temp == null || temp.next == null) {
            System.out.println("Position out of bounds");
            return head;
        }

        // Remove the node
        temp.next = temp.next.next;
        return head;
    }

    /**
     * Updates the data of the node at the given position.
     *
     * @param position - index of the node to update
     * @param data     - new data to set
     * @param head     - head of the linked list
     * @return updated head of the list
     */
    public static Node updateNode(int position, int data, Node head) {
        if (head == null || position < 0) {
            System.out.println("Invalid position or empty list");
            return head;
        }

        Node temp = head;
        int k = 0;

        // Traverse to the desired node
        while (k < position && temp != null) {
            temp = temp.next;
            k++;
        }

        if (temp == null) {
            System.out.println("Position out of bounds");
            return head;
        }

        // Update data
        temp.data = data;
        return head;
    }

    /**
     * Prints the linked list in the format:
     * data1 -> data2 -> ... -> Null
     *
     * @param head - head of the linked list
     */
    public static void printNode(Node head) {
        if (head == null) {
            System.out.println("List is empty");
            return;
        }

        Node temp = head;

        // Traverse and print
        while (temp != null) {
            System.out.print(temp.data);
            if (temp.next != null) {
                System.out.print(" -> ");
            } else {
                System.out.print(" -> Null");
            }
            temp = temp.next;
        }
        System.out.println(); // For clean output
    }
}
