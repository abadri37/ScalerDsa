package Tuf.Day5.LinkedList1;

import DSA2.LinkedList.Node;

import static DSA2.LinkedList.Node.createLinkedList;
import static DSA2.LinkedList.Node.printNode;

public class MergeTwoSortedLinkedList {

    public static void main(String[] args) {
        int[] list1 = {1, 4, 6, 8};
        int[] list2 = {2, 3, 5, 9, 10};

        Node node1 = createLinkedList(list1);
        Node node2 = createLinkedList(list2);

        Node temp = mergeList(node1, node2);

        System.out.print("Merged Linked List is ");
        printNode(temp);
    }

    public static Node mergeList(Node node1, Node node2) {
        Node head = new Node(0);   // dummy node
        Node temp = head;

        // merge nodes in sorted order
        while (node1 != null && node2 != null) {
            if (node1.getData() < node2.getData()) {
                temp.next = node1;
                node1 = node1.next;
            } else {
                temp.next = node2;
                node2 = node2.next;
            }
            temp = temp.next;
        }

        // attach remaining nodes
        if (node1 != null) temp.next = node1;
        if (node2 != null) temp.next = node2;

        return head.next; // skip dummy
    }
}