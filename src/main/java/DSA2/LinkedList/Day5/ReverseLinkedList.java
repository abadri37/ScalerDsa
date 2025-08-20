package DSA2.LinkedList.Day5;

import DSA2.LinkedList.Node;

import static DSA2.LinkedList.Node.createLinkedList;
import static DSA2.LinkedList.Node.printNode;

public class ReverseLinkedList {

    public static void main(String[] args) {
        int[] in = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        Node head = createLinkedList(in);
        System.out.print("Current Linked List is ");
        printNode(head);

        // Reverse entire linked list
        Node node = reverseLinkedList(head);
        System.out.print("Reversed Linked List is ");
        printNode(node);

        // Reverse list from index 2 till the end
        Node node2 = reverseFromIndex(createLinkedList(in), 2);
        System.out.print("Reversed Linked List from Index 2 is ");
        printNode(node2);

        // Reverse list from index 2 to index 5
        Node node3 = reverseFromIndex(createLinkedList(in), 2, 5);
        System.out.print("Reversed Linked List from index 2 to index 5 is ");
        printNode(node3);

        // Reverse first 4 nodes only
        Node node4 = reverseFirstKnode(createLinkedList(in), 4);
        System.out.print("Reversed First K nodes in Linked List is ");
        printNode(node4);
    }

    /**
     * Reverse the entire linked list
     * @param head head of the linked list
     * @return new head of reversed list
     */
    public static Node reverseLinkedList(Node head) {
        Node previous = null, current = head;
        while (current != null) {
            Node next = current.next;  // store next node
            current.next = previous;   // reverse current node's pointer
            previous = current;        // move 'previous' one step forward
            current = next;            // move 'current' one step forward
        }
        return previous; // 'previous' will be the new head
    }

    /**
     * Reverse the linked list from given index till the end
     * Example: [1,2,3,4,5], position=2 → [1,2,5,4,3]
     */
    public static Node reverseFromIndex(Node head, int position) {
        if (head == null || position < 0) return head;
        if (position == 0) return reverseLinkedList(head);

        // Traverse to node before the given position
        Node temp = head;
        int index = 0;
        while (temp != null && index < position - 1) {
            temp = temp.next;
            index++;
        }

        // If position is out of bounds
        if (temp == null || temp.next == null) return head;

        Node beforeReverse = temp;      // node before reversal starts
        Node reverseStart = temp.next;  // first node to be reversed

        // Standard reverse logic
        Node previous = null, current = reverseStart;
        while (current != null) {
            Node next = current.next;
            current.next = previous;
            previous = current;
            current = next;
        }

        // Connect beforeReverse to new head of reversed part
        beforeReverse.next = previous;
        // Connect old head of reversed part to null (as it’s now last node)
        reverseStart.next = null;

        return head;
    }

    /**
     * Reverse first K nodes only
     * Example: [1,2,3,4,5], K=3 → [3,2,1,4,5]
     */
    public static Node reverseFirstKnode(Node head, int k) {
        Node previous = null, current = head;
        int count = k;

        // Reverse first K nodes
        while (current != null && count > 0) {
            Node next = current.next;
            current.next = previous;
            previous = current;
            current = next;
            count--;
        }

        // Connect last node of reversed part (old head) to remaining list
        if (head != null) head.next = current;

        return previous; // 'previous' is the new head
    }

    /**
     * Reverse the linked list between two given indices [fromIndex, toIndex]
     * Example: [1,2,3,4,5,6], from=2, to=4 → [1,2,5,4,3,6]
     */
    public static Node reverseFromIndex(Node head, int fromIndex, int toIndex) {
        if (head == null || fromIndex < 0 || toIndex < 0 || fromIndex > toIndex) {
            System.out.println("Invalid position or empty list");
            return head;
        }

        // Special case: reverse from head
        if (fromIndex == 0) {
            return reverseFirstKnode(head, toIndex + 1);
        }

        // Traverse to node before 'fromIndex'
        int position = 0;
        Node temp = head;
        while (temp != null && position < fromIndex - 1) {
            temp = temp.next;
            position++;
        }

        // If starting point is invalid
        if (temp == null || temp.next == null) {
            System.out.println("Position out of bounds or nothing to reverse");
            return head;
        }

        Node beforeReverse = temp;     // Node before reversal starts
        Node reverseStart = temp.next; // First node to reverse

        Node previous = null, current = reverseStart;
        int count = 0;

        // Reverse only within the [fromIndex, toIndex] range
        while (current != null && count <= (toIndex - fromIndex)) {
            Node next = current.next;
            current.next = previous;
            previous = current;
            current = next;
            count++;
        }

        // Connect beforeReverse to new head of reversed section
        beforeReverse.next = previous;
        // Connect tail of reversed section to remaining list
        reverseStart.next = current;

        return head;
    }
}