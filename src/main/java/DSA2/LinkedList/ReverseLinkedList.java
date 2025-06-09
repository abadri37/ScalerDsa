package DSA2.LinkedList;

import static DSA2.LinkedList.Node.createLinkedList;
import static DSA2.LinkedList.Node.printNode;

/**
 * Demonstrates basic operations on a singly linked list such as
 * creation, insertion, deletion, update, reverse, and printing.
 */
public class ReverseLinkedList {

    public static void main(String[] args) {
        // Sample input array
        int[] in = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        // Create a linked list from the array
        Node head = createLinkedList(in);

        System.out.print("Current Linked List is ");
        printNode(head);

        // Reverse entire linked list
        Node node = reverseList(head);
        System.out.print("Reversed Linked List is ");
        printNode(node);

        // Reverse linked list from index 2 till end
        Node node2 = reverseFromIndex(createLinkedList(in), 2);
        System.out.print("Reversed Linked List from index 2 is ");
        printNode(node2);

        // Reverse linked list from index 2 to 5
        Node node3 = reverseFromIndex(createLinkedList(in), 2, 5);
        System.out.print("Reversed Linked List from index 2 to index 5 is ");
        printNode(node3);

        // Reverse first 4 nodes
        Node node4 = reverseFirstKnode(createLinkedList(in), 4);
        System.out.print("Reversed First K node in Linked List is ");
        printNode(node4);
    }

    /**
     * Reverses the linked list from a given position till the end.
     *
     * @param head     Head of the list
     * @param position Index from where to start reversing
     * @return Updated head of the list
     */
    public static Node reverseFromIndex(Node head, int position) {
        if (head == null || position < 0) {
            System.out.println("Invalid position or empty list");
            return head;
        }
        if (position == 0) {
            return reverseList(head);
        }

        // Move to (position-1)th node
        int k = 0;
        Node temp = head;
        while (k < position - 1 && temp != null) {
            k++;
            temp = temp.next;
        }

        if (temp == null || temp.next == null) {
            System.out.println("Position out of bounds or nothing to reverse");
            return head;
        }

        Node nodeBeforeReverse = temp;
        Node reverseStart = temp.next;

        // Reverse the sublist from position to end
        Node previous = null;
        Node current = reverseStart;
        while (current != null) {
            Node next = current.next;
            current.next = previous;
            previous = current;
            current = next;
        }

        // Connect node before reversal to new start
        nodeBeforeReverse.next = previous;
        return head;
    }

    /**
     * Reverses the first K nodes of the linked list.
     *
     * @param head     Head of the list
     * @param position Number of nodes to reverse
     * @return New head after reversal
     */
    public static Node reverseFirstKnode(Node head, int position) {
        int k = position;
        Node current = head;
        Node previous = null;
        Node nextNode = null;

        // Standard iterative reversal loop
        while (current != null && k > 0) {
            nextNode = current.next;
            current.next = previous;
            previous = current;
            current = nextNode;
            k--;
        }

        // Connect original head (now last of reversed) to rest of list
        if (head != null) {
            head.next = current;
        }

        return previous;
    }

    /**
     * Reverses nodes from 'fromIndex' to 'toIndex'.
     *
     * @param head      Head of the list
     * @param fromIndex Starting index of the sublist to reverse
     * @param toIndex   Ending index of the sublist to reverse
     * @return Updated head
     */
    public static Node reverseFromIndex(Node head, int fromIndex, int toIndex) {
        if (head == null || fromIndex < 0 || toIndex < 0 || fromIndex > toIndex) {
            System.out.println("Invalid position or empty list");
            return head;
        }

        // Special case: reverse from beginning
        if (fromIndex == 0) {
            int k = toIndex;
            Node current = head;
            Node previous = null;
            Node nextNode = null;

            while (current != null && k >= 0) {
                nextNode = current.next;
                current.next = previous;
                previous = current;
                current = nextNode;
                k--;
            }

            if (head != null) {
                head.next = current;
            }

            return previous;
        }

        // Traverse to (fromIndex-1)th node
        int k = 0;
        Node temp = head;
        while (k < fromIndex - 1 && temp != null) {
            k++;
            temp = temp.next;
        }

        if (temp == null || temp.next == null) {
            System.out.println("Position out of bounds or nothing to reverse");
            return head;
        }

        Node beforeReverse = temp;
        Node reverseStart = temp.next;

        Node previous = null;
        Node current = reverseStart;
        int count = 0;

        // Reverse the sublist
        while (current != null && count <= (toIndex - fromIndex)) {
            Node nextNode = current.next;
            current.next = previous;
            previous = current;
            current = nextNode;
            count++;
        }

        // Connect reversed sublist to remaining list
        beforeReverse.next = previous;
        reverseStart.next = current;

        return head;
    }

    /**
     * Reverses the entire linked list.
     *
     * @param head Head of the original linked list
     * @return New head after reversal
     */
    public static Node reverseList(Node head) {
        Node previous = null;
        Node current = head;

        // Iterate and reverse links
        while (current != null) {
            Node next = current.next;
            current.next = previous;
            previous = current;
            current = next;
        }

        return previous;
    }
}
