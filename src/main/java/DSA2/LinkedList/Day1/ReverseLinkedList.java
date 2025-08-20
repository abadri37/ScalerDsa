package DSA2.LinkedList.Day1;

import DSA2.LinkedList.Node;

import static DSA2.LinkedList.Node.createLinkedList;
import static DSA2.LinkedList.Node.printNode;

public class ReverseLinkedList {

    public static void main(String[] args) {
        // Sample input array to build a linked list
        int[] in = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        // Create a linked list from the array [1 → 2 → 3 … → 10]
        Node head = createLinkedList(in);

        System.out.print("Current Linked List is ");
        printNode(head); // print original list

        // Reverse the entire linked list
        Node node = reverseLinkedList(head);

        System.out.print("Reversed Linked List is ");
        printNode(node); // print reversed list
    }

    /**
     * Reverses a singly linked list.
     *
     * @param head head of the original linked list
     * @return new head of the reversed linked list
     */
    public static Node reverseLinkedList(Node head) {
        // 'previous' will track the already reversed portion (initially empty → null)
        Node previous = null;

        // 'current' will iterate over the original list
        Node current = head;

        // Traverse the list until we reach the end
        while (current != null) {
            // Step 1: Save the next node (to not lose the rest of the list)
            Node next = current.next;

            // Step 2: Reverse the current node's pointer
            current.next = previous;

            // Step 3: Move 'previous' one step forward
            previous = current;

            // Step 4: Move 'current' one step forward
            current = next;
        }

        // At the end, 'previous' points to the new head of the reversed list
        return previous;
    }
}