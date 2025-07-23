package DSA2.LinkedList.Day2;

import DSA2.LinkedList.Node;

public class CycleDetection {

    public static void main(String[] args) {

        int[] values = new int[20]; // Create an array with 20 values: 1 to 20
        for (int i = 0; i < 20; i++) {
            values[i] = i + 1;
        }

        // Create a linked list from the values
        Node head = Node.createLinkedList(values);

        // Create a cycle in the linked list by connecting the last node to the node at index 5
        Node.createCycle(head, 5);

        // Detect if a cycle exists
        System.out.println("The Cycle exists in the LinkedList is " + hasCycle(head));

    }

    /**
     * Detects whether a cycle exists in the linked list using Floyd's Tortoise and Hare algorithm.
     *
     * @param head The head of the linked list
     * @return true if a cycle exists, false otherwise
     */
    public static boolean hasCycle(Node head) {
        Node slow = head;
        Node fast = head;

        // Move slow by 1 and fast by 2 steps and check if they ever meet
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            // Cycle detected
            if (slow == fast) {
                return true;
            }
        }

        // No cycle
        return false;
    }

}
