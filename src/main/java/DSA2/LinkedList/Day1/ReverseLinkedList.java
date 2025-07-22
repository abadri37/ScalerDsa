package DSA2.LinkedList.Day1;

import DSA2.LinkedList.Node;

import static DSA2.LinkedList.Node.createLinkedList;
import static DSA2.LinkedList.Node.printNode;

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
        while (current != null) {
            Node nextNode = current.next;
            current.next = previous;
            previous = current;
            current = nextNode;
        }
        return previous;
    }
}
