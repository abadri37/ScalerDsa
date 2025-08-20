package DSA2.LinkedList.Day5;

import DSA2.LinkedList.Node;

import static DSA2.LinkedList.Node.createLinkedList;
import static DSA2.LinkedList.Node.printNode;

public class AddTwoNumbersLinkedList {
    public static void main(String[] args) {

        // Example input: numbers are stored in reverse order
        int[] list1 = {2, 4, 3};   // Represents 342
        int[] list2 = {5, 6, 4};   // Represents 465

        Node node1 = createLinkedList(list1);
        Node node2 = createLinkedList(list2);

        Node res = addNumbers(node1, node2);

        System.out.print("Resultant Linked List is ");
        printNode(res);  // Expected: 7 -> 0 -> 8 (807)
    }

    // Function to add two numbers represented as linked lists (reverse order)
    public static Node addNumbers(Node node1, Node node2) {
        Node head = new Node(0);   // Dummy node to simplify result building
        Node current = head;
        int carry = 0;

        // Traverse both lists until both are null and no carry remains
        while (node1 != null || node2 != null || carry != 0) {
            int sum = carry;

            if (node1 != null) {
                sum += node1.getData();
                node1 = node1.next;
            }
            if (node2 != null) {
                sum += node2.getData();
                node2 = node2.next;
            }

            carry = sum / 10;                     // Update carry
            current.next = new Node(sum % 10);    // Create new node for current digit
            current = current.next;
        }

        return head.next;  // Skip dummy node and return actual result head
    }
}