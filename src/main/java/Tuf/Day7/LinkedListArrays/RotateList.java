package Tuf.Day7.LinkedListArrays;

import DSA2.LinkedList.Node;

public class RotateList {

    /*
     * 61. Rotate List (LeetCode Medium)
     *
     * Question:
     * Given the head of a linked list, rotate the list to the right by k places.
     *
     * Example:
     * Input:  1 -> 2 -> 3 -> 4 -> 5, k = 2
     * Output: 4 -> 5 -> 1 -> 2 -> 3
     *
     * Idea/Formula:
     * 1. Find the length of the list (size).
     * 2. Make the list circular (last node points back to head).
     * 3. Find the new head position using formula:
     *        stepsToNewHead = size - (k % size)
     * 4. Traverse to the new head, break the cycle, and return the rotated list.
     */

    public static void main(String[] args) {

        // Create an array with 20 values: 1 to 20
        int[] values = new int[20];
        for (int i = 0; i < 20; i++) {
            values[i] = i + 1;
        }

        // Create a linked list from the values
        Node head = Node.createLinkedList(values);

        System.out.print("Current Linked List is ");
        Node.printNode(head);

        // Rotate by k = 4
        Node node1 = rotate(head, 4);

        System.out.print("Current Linked List after rotating is ");
        Node.printNode(node1);
    }

    public static Node rotate(Node head, int k) {
        if (head == null || head.next == null || k == 0) {
            return head; // No rotation needed
        }

        // Step 1: Find the size of the linked list
        int size = 1;
        Node temp = head;
        while (temp.next != null) {
            size++;
            temp = temp.next;
        }

        // Step 2: Make it a circular linked list
        temp.next = head;

        // Step 3: Find the effective rotation (k could be larger than size)
        k = k % size;
        int stepsToNewHead = size - k;

        // Step 4: Traverse to the new head
        Node newTail = head;
        for (int i = 1; i < stepsToNewHead; i++) {
            newTail = newTail.next;
        }

        // Step 5: Break the cycle and set the new head
        Node newHead = newTail.next;
        newTail.next = null;

        return newHead;
    }
}