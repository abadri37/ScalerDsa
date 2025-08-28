package Tuf.Day6.LinkedList2;

import DSA2.LinkedList.Node;

import static DSA2.LinkedList.Node.createLinkedList;
import static DSA2.LinkedList.Node.printNode;

/**
 * Problem: Reverse Nodes in k-Group
 *
 * Given a singly linked list, reverse the nodes of the list k at a time and return its modified list.
 * k is a positive integer and is less than or equal to the length of the linked list.
 * If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.
 *
 * Example:
 * Input: 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8 -> 9 -> 10, k = 2
 * Output: 2 -> 1 -> 4 -> 3 -> 6 -> 5 -> 8 -> 7 -> 10 -> 9
 */
public class ReverseNodesKGroup {
    public static void main(String[] args) {
        int[] in = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        // Create linked list from input array
        Node head = createLinkedList(in);
        System.out.print("Current Linked List is: ");
        printNode(head);

        // Reverse nodes in groups of k (iterative approach)
        Node node = reverseKNodes(head, 2);
        System.out.print("Reversed Linked List (iterative k-group) is: ");
        printNode(node);

        // Reverse nodes in groups of k (optimised recursive approach)
        Node node2 = reverseKNodeGroup(createLinkedList(in), 2);
        System.out.print("Reversed Linked List (recursive k-group) is: ");
        printNode(node2);
    }

    /**
     * Iteratively reverses nodes in the linked list in groups of size k.
     * Uses helper method reverseFromToIndex for each group.
     */
    public static Node reverseKNodes(Node head, int k) {
        if (head == null || k <= 1) {
            System.out.println("Invalid k or empty list");
            return head;
        }

        // Calculate size of the linked list
        int fromIndex = 0;
        int toIndex = k - 1;
        int size = 0;
        Node temp = head;
        while (temp != null) {
            temp = temp.next;
            size++;
        }

        // Reverse each k-group
        while (toIndex < size) {
            head = reverseFromToIndex(head, fromIndex, toIndex);
            fromIndex += k;
            toIndex += k;
        }
        return head;
    }

    /**
     * Optimised recursive approach to reverse nodes in k-groups.
     */
    public static Node reverseKNodeGroup(Node head, int k) {
        if (head == null || k == 0) return head;

        // Check if there are at least k nodes to reverse
        int count = 0;
        Node current = head;
        while (count < k && current != null) {
            count++;
            current = current.next;
        }

        // Reverse first k nodes and recurse for the remaining
        if (count == k) {
            Node reversedHead = reverse(head, k);
            head.next = reverseKNodeGroup(current, k);
            return reversedHead;
        }

        // Less than k nodes remaining; do not reverse
        return head;
    }

    /**
     * Reverse exactly k nodes starting from head and return the new head.
     */
    public static Node reverse(Node head, int k) {
        Node current = head;
        Node prev = null;
        while (k > 0 && current != null) {
            Node next = current.next;
            current.next = prev;
            prev = current;
            current = next;
            k--;
        }
        return prev;
    }

    /**
     * Reverses nodes from 'fromIndex' to 'toIndex' (0-based) in the linked list.
     * Returns the head of the modified list.
     */
    public static Node reverseFromToIndex(Node head, int fromIndex, int toIndex) {
        if (head == null || fromIndex == toIndex) return head;

        // Dummy node simplifies edge cases (e.g., reversing from head)
        Node dummy = new Node(0);
        dummy.next = head;

        // Step 1: Traverse to node just before 'fromIndex'
        Node temp = dummy;
        int i = 0;
        while (i < fromIndex && temp != null) {
            temp = temp.next;
            i++;
        }

        Node beforeReverse = temp;      // Node before the segment to reverse
        Node reverseStart = temp.next;  // First node in the segment to reverse

        // Step 2: Reverse nodes between fromIndex and toIndex
        int count = 0;
        Node current = reverseStart;
        Node prev = null;
        while (current != null && count < (toIndex - fromIndex + 1)) {
            Node next = current.next;
            current.next = prev;
            prev = current;
            current = next;
            count++;
        }

        // Step 3: Connect reversed segment back to the list
        beforeReverse.next = prev;
        reverseStart.next = current;

        return dummy.next;
    }
}