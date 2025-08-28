package Tuf.Day5.LinkedList1;

import DSA2.LinkedList.Node;

import static DSA2.LinkedList.Node.createLinkedList;
import static DSA2.LinkedList.Node.printNode;

public class RemoveNthNodeEndOfList {

    public static void main(String[] args) {
        int[] in = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int n = 5;

        Node head = createLinkedList(in);
        System.out.print("Current Linked List is ");
        printNode(head);

        Node temp = removeNode(createLinkedList(in), n);
        System.out.print("Resultant Linked List is ");
        printNode(temp);
    }

    public static Node removeNode(Node head, int n) {
        Node temp = head;
        int size = 0;

        // Step 1: Find the total size of the linked list
        while (temp != null) {
            size++;
            temp = temp.next;
        }

        // Step 2: If the node to remove is the head
        if (n == size) {
            return head.next;
        }

        // Step 3: Traverse to the node just before the target (size - n - 1 steps)
        temp = head;
        int count = 0;
        while (temp != null && count < size - n - 1) {
            temp = temp.next;
            count++;
        }

        // Step 4: Skip the nth node from the end
        if (temp != null && temp.next != null) {
            temp.next = temp.next.next;
        }

        return head;
    }
}