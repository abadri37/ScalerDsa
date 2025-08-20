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

        // Reverse linked list starting from index 2
        Node indexReverse = reverseFromIndex(createLinkedList(in), 2);

        System.out.print("Reversed Linked List from Index 2 is ");
        printNode(indexReverse); // print reversed list
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

    /**
     * Reverses the linked list starting from a given index.
     *
     * Example: List = [1 → 2 → 3 → 4 → 5], position = 2
     * Result  = [1 → 2 → 5 → 4 → 3]
     *
     * @param head     head of the linked list
     * @param position 0-based index from where reversal should start
     * @return modified linked list head
     */
    public static Node reverseFromIndex(Node head, int position) {
        // Edge case: empty list or invalid position
        if (head == null || position < 0) {
            System.out.println("Invalid position or empty list");
            return head;
        }

        // If position == 0 → reverse the whole list
        if (position == 0) {
            System.out.println("Reversing complete List");
            return reverseLinkedList(head);
        }

        // Step 1: Traverse to the node just before the given position
        Node temp = head;
        int index = 0;
        while (temp != null && index < position - 1) {
            temp = temp.next;
            index++;
        }

        // Step 2: If position is out of range → return unchanged list
        if (temp == null || temp.next == null) {
            System.out.println("Invalid position or empty list");
            return head;
        }

        // 'beforeReverse' is the last node before the reversal starts
        Node beforeReverse = temp;

        // 'reverseStart' is the first node of the part to be reversed
        Node reverseStart = temp.next;

        // Step 3: Reverse the sub-list starting from reverseStart till end
        Node previous = null;
        Node current = reverseStart;

        while (current != null) {
            Node next = current.next;  // Save next node
            current.next = previous;   // Reverse pointer
            previous = current;        // Move 'previous' forward
            current = next;            // Move 'current' forward
        }

        // Step 4: Connect 'beforeReverse' to the new head of reversed part
        beforeReverse.next = previous;

        // Step 5: Connect the tail of reversed part (old 'reverseStart')
        // to null (or can be adjusted if you want to stop earlier)
        reverseStart.next = null;

        return head;
    }
}