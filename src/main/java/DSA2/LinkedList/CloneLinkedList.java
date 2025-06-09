package DSA2.LinkedList;

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

        // Clone the list and print
        RandomListNode node = cloneList(node1);
        printList(node);
    }

    /**
     * Clones a linked list with next and random pointers using O(1) extra space.
     */
    public static RandomListNode cloneList(RandomListNode head) {
        // Step 1: Clone each node and insert it right after the original node
        // Example: 1 -> 2 becomes 1 -> 1' -> 2 -> 2'
        RandomListNode curr = head;
        while (curr != null) {
            RandomListNode node = new RandomListNode(curr.val); // cloned node
            node.next = curr.next;
            curr.next = node;
            curr = node.next; // move to the next original node
        }

        // Step 2: Assign random pointers for the cloned nodes
        // The cloned node is always next to the original node
        curr = head;
        while (curr != null) {
            if (curr.random != null) {
                curr.next.random = curr.random.next; // copy random pointer
            }
            curr = curr.next.next; // move to the next original node
        }

        // Step 3: Separate the cloned list from the original list
        curr = head;
        RandomListNode dummy = new RandomListNode(0); // dummy head for cloned list
        RandomListNode copy = dummy;

        while (curr != null) {
            RandomListNode clone = curr.next; // cloned node
            copy.next = clone;                // link clone to new list
            copy = clone;                     // move forward in clone list

            curr.next = clone.next;           // restore original list
            curr = curr.next;                 // move forward in original list
        }

        return dummy.next; // return head of the cloned list
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
            int randomVal = (head.random != null) ? head.random.val : -1;
            System.out.println("Node " + head.val + " | Random -> " + randomVal);
            head = head.next;
        }
    }
}
