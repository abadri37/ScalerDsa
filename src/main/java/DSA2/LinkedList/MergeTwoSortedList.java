package DSA2.LinkedList;

import static DSA2.LinkedList.Node.createLinkedList;
import static DSA2.LinkedList.Node.printNode;

/**
 * Merges two sorted linked lists into a single sorted linked list.
 */
public class MergeTwoSortedList {

    public static void main(String[] args) {
        int[] list1 = {1, 4, 6, 8};      // Sorted array 1
        int[] list2 = {2, 3, 5, 9, 10};  // Sorted array 2

        // Create linked lists from the arrays
        Node node1 = createLinkedList(list1);
        Node node2 = createLinkedList(list2);

        // Merge the two sorted linked lists
        Node result = mergeList(node1, node2);

        // Print the merged list
        System.out.print("Merged Linked List is ");
        printNode(result);
    }

    /**
     * Merges two sorted linked lists into a single sorted linked list.
     * This method reuses the existing nodes for efficient memory use.
     *
     * @param node1 - head of the first sorted linked list
     * @param node2 - head of the second sorted linked list
     * @return head of the merged sorted linked list
     */
    public static Node mergeList(Node node1, Node node2) {
        // Create a dummy node to simplify the merge logic
        Node head = new Node(0); // dummy node, actual result starts from head.next
        Node temp = head;        // temp is used to build the merged list

        // Traverse both lists and attach the smaller node to the result
        while (node1 != null && node2 != null) {
            if (node1.data < node2.data) {
                temp.next = node1;  // attach node1
                node1 = node1.next; // move node1 ahead
            } else {
                temp.next = node2;  // attach node2
                node2 = node2.next; // move node2 ahead
            }
            temp = temp.next; // move the temp pointer
        }

        // Attach remaining nodes (only one of these will execute)
        if (node1 != null) {
            temp.next = node1;
        }
        if (node2 != null) {
            temp.next = node2;
        }

        // Return the merged list, skipping the dummy node
        return head.next;
    }
}
