package DSA2.LinkedList.Day1;

import DSA2.LinkedList.Node;

import static DSA2.LinkedList.Node.createLinkedList;
import static DSA2.LinkedList.Node.printNode;

public class ReverseLinkedList {

    public static void main(String[] args) {
        int[] in = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        Node head = createLinkedList(in);
        System.out.print("Current Linked List is ");
        printNode(head);

        Node node = reverseLinkedList(head);
        System.out.print("Reversed Linked List is ");
        printNode(node);

        Node indexReverse = reverseFromIndex(createLinkedList(in), 2);
        System.out.print("Reversed Linked List from Index 2 is ");
        printNode(indexReverse);

        Node node4 = reverseFirstKnode(createLinkedList(in), 4);
        System.out.print("Reversed First K node in Linked List is ");
        printNode(node4);
    }

    // Reverse entire linked list
    public static Node reverseLinkedList(Node head) {
        Node previous = null, current = head;
        while (current != null) {
            Node next = current.next;
            current.next = previous;
            previous = current;
            current = next;
        }
        return previous;
    }

    // Reverse linked list from given index till end
    public static Node reverseFromIndex(Node head, int position) {
        if (head == null || position < 0) return head;
        if (position == 0) return reverseLinkedList(head);

        Node temp = head;
        int index = 0;
        while (temp != null && index < position - 1) {
            temp = temp.next;
            index++;
        }
        if (temp == null || temp.next == null) return head;

        Node beforeReverse = temp;
        Node reverseStart = temp.next;

        Node previous = null, current = reverseStart;
        while (current != null) {
            Node next = current.next;
            current.next = previous;
            previous = current;
            current = next;
        }

        beforeReverse.next = previous;
        reverseStart.next = null;
        return head;
    }

    // Reverse first K nodes only
    public static Node reverseFirstKnode(Node head, int position) {
        Node previous = null, current = head;
        int k = position;
        while (current != null && k > 0) {
            Node next = current.next;
            current.next = previous;
            previous = current;
            current = next;
            k--;
        }
        if (head != null) head.next = current;
        return previous;
    }
}