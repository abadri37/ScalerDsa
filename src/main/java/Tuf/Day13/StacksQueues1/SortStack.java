package Tuf.Day13.StacksQueues1;

import java.util.Stack;

/**
 * SortStack — Sorts a given stack in ascending order using only another stack.
 *
 * Constraints:
 *  - You may only use standard stack operations: push, pop, peek, and isEmpty.
 *  - No recursion, no extra data structures except one additional stack.
 *
 * Approach:
 *  Use a temporary stack (tempStack) to hold elements in sorted order.
 *  The main stack (st) will be used to temporarily hold elements during insertion.
 *
 *  Time Complexity: O(n²)
 *  Space Complexity: O(n) — due to the extra temporary stack.
 */
public class SortStack {

    public void sortStack(Stack<Integer> st) {

        // Temporary stack used to store sorted elements
        Stack<Integer> tempStack = new Stack<>();

        /**
         * Step 1: Traverse each element from the input stack 'st'
         * and insert it into the correct position in 'tempStack'.
         */
        while (!st.isEmpty()) {

            // Pop the top element from the original stack
            int peek = st.pop();

            // Q: Why check if tempStack is empty?
            // A: If it's empty, we can directly push the first element.
            if (tempStack.isEmpty()) {
                tempStack.push(peek);
            } else {
                /**
                 * Step 2: Compare current element with tempStack's top.
                 * If the current element is smaller, push it directly.
                 * Else, move elements from tempStack back to st until
                 * the correct sorted position is found.
                 *
                 * Q: Why move back to 'st'?
                 * A: Because we need to "make space" for the correct position
                 *    of the current element inside tempStack.
                 */
                if (tempStack.peek() > peek) {
                    tempStack.push(peek);
                } else {
                    // Move larger elements back to original stack
                    while (!tempStack.isEmpty() && peek > tempStack.peek()) {
                        st.push(tempStack.pop());
                    }
                    tempStack.push(peek);
                }
            }
        }

        /**
         * Step 3: Move all sorted elements back from tempStack → st
         * Now 'st' will contain all elements in ascending order.
         */
        while (!tempStack.isEmpty()) {
            st.push(tempStack.pop());
        }
    }

    /**
     * Main method to test the SortStack logic with different inputs.
     */
    public static void main(String[] args) {

        SortStack sorter = new SortStack();

        // 🧪 Test Case 1: Random order
        Stack<Integer> st1 = new Stack<>();
        st1.push(3);
        st1.push(1);
        st1.push(4);
        st1.push(2);
        st1.push(5);
        System.out.println("Original Stack: " + st1);
        sorter.sortStack(st1);
        System.out.println("Sorted Stack (Ascending): " + st1);
        // Expected Output: [1, 2, 3, 4, 5]

        // 🧪 Test Case 2: Already sorted stack
        Stack<Integer> st2 = new Stack<>();
        st2.push(1);
        st2.push(2);
        st2.push(3);
        System.out.println("\nOriginal Stack: " + st2);
        sorter.sortStack(st2);
        System.out.println("Sorted Stack (Ascending): " + st2);
        // Expected Output: [1, 2, 3]

        // 🧪 Test Case 3: Reverse order
        Stack<Integer> st3 = new Stack<>();
        st3.push(5);
        st3.push(4);
        st3.push(3);
        st3.push(2);
        st3.push(1);
        System.out.println("\nOriginal Stack: " + st3);
        sorter.sortStack(st3);
        System.out.println("Sorted Stack (Ascending): " + st3);
        // Expected Output: [1, 2, 3, 4, 5]

        // 🧪 Test Case 4: Stack with duplicates
        Stack<Integer> st4 = new Stack<>();
        st4.push(4);
        st4.push(2);
        st4.push(2);
        st4.push(5);
        st4.push(1);
        System.out.println("\nOriginal Stack: " + st4);
        sorter.sortStack(st4);
        System.out.println("Sorted Stack (Ascending): " + st4);
        // Expected Output: [1, 2, 2, 4, 5]
    }
}