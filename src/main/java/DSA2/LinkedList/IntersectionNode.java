package DSA2.LinkedList;

public class IntersectionNode {

    public static void main(String[] args) {

        // First List: 1 → 2 → 3 → [6 → 7 → 8]
        Node a1 = new Node(1);
        Node a2 = new Node(2);
        Node a3 = new Node(3);
        a1.next = a2;
        a2.next = a3;

        // Second List: 4 → 5 → [6 → 7 → 8]
        Node b1 = new Node(4);
        Node b2 = new Node(5);
        b1.next = b2;

        // Common part that will be shared (intersection)
        Node common = new Node(6);
        common.next = new Node(7);
        common.next.next = new Node(8);

        // Connecting the intersection part to both lists
        a3.next = common;
        b2.next = common;

        // Find intersection point
        Node ret = intersectionNode(a1, b1);

        if (ret != null) {
            System.out.println("The intersection lies at the point " + ret.data);
        } else {
            System.out.println("There is no intersection.");
        }
    }

    /**
     * Finds the intersection point of two singly linked lists.
     *
     * @param headA Head of the first list
     * @param headB Head of the second list
     * @return Node at the intersection or null if there is no intersection
     */
    public static Node intersectionNode(Node headA, Node headB) {
        Node a = headA;
        Node b = headB;

        // Traverse both lists. When either pointer reaches the end,
        // redirect it to the other list's head.
        // If there is an intersection, the pointers will eventually meet at that node.
        // If there is no intersection, both will become null at the same time.
        while (a != b) {
            a = (a == null) ? headB : a.next;
            b = (b == null) ? headA : b.next;
        }

        return a; // This could be the intersection node or null if no intersection
    }
}
