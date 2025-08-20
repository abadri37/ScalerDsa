package DSA2.LinkedList.Day5;

import DSA2.LinkedList.Node;

import static DSA2.LinkedList.Node.createLinkedList;

public class MiddleOfLinkedList {
    public static void main(String[] args) {
        // Sample input array to create the linked list
        int[] in = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};

        // Create a linked list from the array
        Node head = createLinkedList(in);

        // Find the middle element of the linked list
        Node middle = middleElement(head);

        // Print the middle element's data
        System.out.print("The Middle element in Linked List is " + middle.getData());
    }

    /**
     * Function to find the middle node of a linked list
     * Uses the "slow and fast pointer" (tortoise and hare) technique:
     * - Slow pointer moves one step at a time
     * - Fast pointer moves two steps at a time
     *
     * When the fast pointer reaches the end of the list,
     * the slow pointer will be at the middle node.
     *
     * @param head Head node of the linked list
     * @return Middle node of the linked list
     */
    public static Node middleElement(Node head) {
        // Initialize two pointers at the head
        Node slow = head;
        Node fast = head;

        // Move fast by 2 steps and slow by 1 step
        // Loop continues until fast reaches the end
        while (slow != null && fast != null && fast.next != null) {
            slow = slow.next;           // Move slow pointer one step
            fast = fast.next.next;      // Move fast pointer two steps
        }

        // When loop ends, slow will point to the middle node
        return slow;
    }
}