package DSA2.LinkedList.Day5;

import DSA2.LinkedList.Node;

/**
 * Problem:
 * Given only access to a node (not the head) in a singly linked list,
 * delete the given node.
 *
 * Note:
 * - The given node is guaranteed not to be the last node.
 * - "Deleting" a node means its value should not appear in the list anymore,
 *   and the number of nodes should decrease by one.
 *
 * Example:
 * Input: head = [4, 5, 1, 9], node = 5
 * Output: [4, 1, 9]
 */
public class DeleteNodeFromLinkedList {
    public static void main(String[] args) {
        // Example: [4 -> 5 -> 1 -> 9]
        int[] arr = {4, 5, 1, 9};
        Node head = Node.createLinkedList(arr);

        System.out.print("Original List: ");
        Node.printNode(head);

        // Delete node with value = 5 (second node)
        Node nodeToDelete = head.next;
        deleteNode(nodeToDelete);

        System.out.print("After Deletion: ");
        Node.printNode(head);   // Expected: [4 -> 1 -> 9]
    }

    /**
     * Deletes the given node from the linked list by:
     * 1. Copying the value from the next node into the current node.
     * 2. Skipping over the next node.
     */
    public static void deleteNode(Node node) {
        node.setData(node.next.getData());
        node.next = node.next.next;
    }
}