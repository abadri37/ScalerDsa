package DSA2.LinkedList.Day6;

import java.util.PriorityQueue;

public class FlattenLinkedList {

    public static void main(String[] args) {

        // ✅ Build sample input (multi-level doubly linked list)
        Node head = createHead();

        // ✅ Run the flattening logic
        Node node1 = flatten(head);

        // ✅ Print the final flattened list (expected sorted order: 1–12)
        System.out.println("\nFlattened list:");
        printList(node1);
    }

    /**
     * Flattens a multilevel doubly linked list into a sorted singly linked list.
     * Approach:
     *   1. Traverse entire structure (including child lists) and collect values into a Min-Heap
     *   2. Pop elements from Min-Heap one by one to build a new sorted linked list
     */
    public static Node flatten(Node head) {
        Node temp = head;
        PriorityQueue<Integer> heap = new PriorityQueue<>();

        // Collect all node values (DFS traversal)
        addElements(temp, heap);

        // Build new sorted list
        Node dummy = new Node(0);  // dummy head for easy return
        Node ret = dummy;
        while (!heap.isEmpty()) {
            Node clone = new Node(heap.poll());
            ret.next = clone;
            ret = ret.next;
        }
        return dummy.next;
    }

    /**
     * Recursively traverse list and push values into min-heap
     */
    public static void addElements(Node head, PriorityQueue<Integer> heap) {
        Node temp = head;
        while (temp != null) {
            if (temp.child != null) {
                // DFS into child before moving to next
                addElements(temp.child, heap);
            }
            heap.add(temp.val);
            temp = temp.next;
        }
    }

    /**
     * Helper to print a list
     * Prints both node value and its child (if exists) for debugging
     */
    public static void printList(Node head) {
        while (head != null) {
            String childVal = (head.child != null) ? String.valueOf(head.child.val) : "null";
            System.out.println("Node " + head.val + " | Child -> " + childVal);
            head = head.next;
        }
    }

    /**
     * 🔹 Creates the following multilevel list as input:
     *
     * 1 - 2 - 3 - 4 - 5 - 6
     *         |
     *         7 - 8 - 9 - 10
     *             |
     *             11 - 12
     *
     * Flattened output should be:
     * 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8 -> 9 -> 10 -> 11 -> 12
     */
    public static Node createHead() {
        // Level 0: 1-2-3-4-5-6
        Node head = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);

        head.next = node2; node2.prev = head;
        node2.next = node3; node3.prev = node2;
        node3.next = node4; node4.prev = node3;
        node4.next = node5; node5.prev = node4;
        node5.next = node6; node6.prev = node5;

        // Level 1 child of 3: 7-8-9-10
        Node node7 = new Node(7);
        Node node8 = new Node(8);
        Node node9 = new Node(9);
        Node node10 = new Node(10);

        node7.next = node8; node8.prev = node7;
        node8.next = node9; node9.prev = node8;
        node9.next = node10; node10.prev = node9;

        node3.child = node7; // attach child

        // Level 2 child of 8: 11-12
        Node node11 = new Node(11);
        Node node12 = new Node(12);
        node11.next = node12; node12.prev = node11;

        node8.child = node11; // attach child

        // Print original structure for debugging
        System.out.println("Original multilevel list:");
        printList(head);

        return head;
    }

    // Node definition (doubly linked + child pointer)
    static class Node {
        int val;
        Node prev;
        Node next;
        Node child;

        Node(int val) {
            this.val = val;
        }
    }
}