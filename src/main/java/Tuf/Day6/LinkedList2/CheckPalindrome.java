package Tuf.Day6.LinkedList2;

import DSA2.LinkedList.Node;

import java.util.ArrayList;
import java.util.List;

public class CheckPalindrome {

    public static void main(String[] args) {
        int[] arr1 = {1, 2, 2, 1};
        Node head = Node.createLinkedList(arr1);
        System.out.print("Current Linked List is ");
        Node.printNode(head);
        System.out.println("The given list is palindrome -> " + checkPalindrome(head));
    }

    // Optimized approach with O(1) space
    public static boolean checkPalindrome(Node head) {
        if (head == null || head.next == null) return true;

        // Step 1: Find middle using slow & fast pointers
        Node slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // Step 2: Reverse the second half of the list
        Node current = slow.next; // skip the middle element for odd length
        Node prev = null;
        while (current != null) {
            Node next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }

        // Step 3: Compare first half and reversed second half
        Node firstHalf = head;
        Node secondHalf = prev;
        while (secondHalf != null) {
            if (firstHalf.getData() != secondHalf.getData()) {
                return false;
            }
            firstHalf = firstHalf.next;
            secondHalf = secondHalf.next;
        }
        return true;
    }

    // Naive Approach using extra space O(n)
    public static boolean isPalindrome(Node head) {
        List<Integer> list = new ArrayList<>();
        Node temp = head;
        while (temp != null) {
            list.add(temp.getData());
            temp = temp.next;
        }
        int i = 0, j = list.size() - 1;
        while (i < j) {
            if (!list.get(i).equals(list.get(j))) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
}