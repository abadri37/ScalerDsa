package DSA2.LinkedList.Day7;

/**
 * 💡 Problem: Clone a Linked List with Random Pointer
 *
 * Given a linked list where each node contains two pointers:
 *  - next: points to the next node in the list
 *  - random: points to any node in the list (or null)
 *
 * Task:
 * Create a deep copy of this list.
 * The cloned list should consist of exactly the same data and random links
 * as the original list, but with completely new nodes.
 *
 * Example:
 * Input list:
 *   1 -> 2 -> 3 -> 4
 * Random pointers:
 *   1.random -> 3
 *   2.random -> 1
 *   3.random -> 3 (itself)
 *   4.random -> 2
 *
 * Expected cloned list:
 *   (1' -> 2' -> 3' -> 4')
 * With random pointers corresponding to the same pattern:
 *   1'.random -> 3'
 *   2'.random -> 1'
 *   3'.random -> 3'
 *   4'.random -> 2'
 *
 * Constraints:
 * - The number of nodes in the list is in the range [0, 1000].
 * - -10^4 <= Node.val <= 10^4
 */
public class CloneLinkedList {

    public static void main(String[] args) {
        // Step 1: Create nodes
        RandomListNode node1 = new RandomListNode(1);
        RandomListNode node2 = new RandomListNode(2);
        RandomListNode node3 = new RandomListNode(3);
        RandomListNode node4 = new RandomListNode(4);

        // Step 2: Link them linearly: 1 -> 2 -> 3 -> 4
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;

        // Step 3: Assign random pointers
        node1.random = node3; // 1 -> 3
        node2.random = node1; // 2 -> 1
        node3.random = node3; // 3 -> 3 (self)
        node4.random = node2; // 4 -> 2

        // Step 4: Clone the list
        RandomListNode cloneHead = cloneList(node1);

        // Step 5: Print cloned list
        System.out.println("Cloned Linked List:");
        printList(cloneHead);
    }

    public static RandomListNode cloneList(RandomListNode head) {
        if (head == null) return null;

        RandomListNode current = head;

        // 1️⃣ Insert cloned nodes between original nodes
        while (current != null) {
            RandomListNode temp = new RandomListNode(current.val);
            temp.next = current.next;
            current.next = temp;
            current = temp.next;
        }

        // 2️⃣ Assign random pointers to the cloned nodes
        current = head;
        while (current != null) {
            if (current.random != null) {
                current.next.random = current.random.next;
            }
            current = current.next.next;
        }

        // 3️⃣ Separate original list and cloned list
        current = head;
        RandomListNode cloneHead = current.next; // head of cloned list
        while (current != null) {
            RandomListNode clone = current.next;
            current.next = clone.next; // restore original list
            if (clone.next != null) {
                clone.next = clone.next.next; // link clone nodes
            }
            current = current.next;
        }

        return cloneHead;
    }

    /**
     * Node structure with random pointer.
     */
    static class RandomListNode {
        int val;
        RandomListNode next, random;

        public RandomListNode(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    /**
     * Prints the linked list showing node values and their corresponding random pointer values.
     */
    public static void printList(RandomListNode head) {
        while (head != null) {
            String randomVal = (head.random != null) ? String.valueOf(head.random.val) : "null";
            System.out.println("Node " + head.val + " | Random -> " + randomVal);
            head = head.next;
        }
    }
}