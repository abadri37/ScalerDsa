package DSA2.LinkedList;

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

        // If a cycle exists, print where it starts
        hasCyclePoints(head);
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

    /**
     * Prints the starting point of the cycle in the linked list, if one exists.
     *
     * @param head The head of the linked list
     */
    public static void hasCyclePoints(Node head) {
        Node slow = head;
        Node fast = head;

        // Detect cycle using two pointers
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            // If a cycle is detected
            if (slow == fast) {
                Node entry = head;

                // Move both pointers one step at a time to find the cycle start
                while (entry != slow) {
                    entry = entry.next;
                    slow = slow.next;
                }

                // 'entry' now points to the start of the cycle
                System.out.println("The Cycle starts at node with value: " + entry.data);
                break;
            }
        }
    }
}