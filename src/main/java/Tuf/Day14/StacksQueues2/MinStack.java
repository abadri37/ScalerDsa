/**
 * ✅ Problem: Min Stack (LeetCode #155)
 * <p>
 * 🔹 Question:
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time (O(1)).
 * <p>
 * Implement the MinStack class:
 * <p>
 * - MinStack() initializes the stack object.
 * - void push(int val) pushes the element val onto the stack.
 * - void pop() removes the element on the top of the stack.
 * - int top() gets the top element of the stack.
 * - int getMin() retrieves the minimum element in the stack.
 * <p>
 * ⚙️ Constraints:
 * - All operations must run in O(1) time.
 * - At most 3 * 10^4 operations will be called.
 * <p>
 * Example:
 * Input:
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * System.out.println(minStack.getMin()); // Output: -3
 * minStack.pop();
 * System.out.println(minStack.top());    // Output: 0
 * System.out.println(minStack.getMin()); // Output: -2
 * <p>
 * 💡 Approach:
 * Use two stacks:
 * 1️⃣ Main stack → to store all values.
 * 2️⃣ Min stack → to keep track of the minimum values.
 * <p>
 * Each time you push a new value, check if it's smaller than or equal to the current minimum.
 * If yes, push it into the min stack as well.
 * <p>
 * When you pop, check if the popped element is the same as the top of the min stack.
 * If yes, pop from the min stack too (since the minimum is changing).
 */

package Tuf.Day14.StacksQueues2;

import java.util.Stack;

public class MinStack {

    // 🧱 Main stack to store all the values
    Stack<Integer> stack;

    // 🧩 Auxiliary stack to store the minimum values
    Stack<Integer> minStack;

    /**
     * Constructor initializes two empty stacks.
     */
    public MinStack() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }

    /**
     * Pushes an element onto the main stack.
     * Also pushes it onto the minStack if it is smaller than or equal
     * to the current minimum (top of minStack).
     *
     * @param val the value to be pushed
     */
    public void push(int val) {
        // Push to the main stack
        stack.push(val);

        // If minStack is empty or val is smaller than the current minimum,
        // then push val to the minStack.
        if (minStack.isEmpty() || val <= minStack.peek()) {
            minStack.push(val);
        }
    }

    /**
     * Removes the element on top of the stack.
     * If the popped element is the current minimum (i.e., equal to top of minStack),
     * remove it from the minStack as well.
     */
    public void pop() {
        if (!stack.isEmpty()) {
            int topElement = stack.pop();

            // Only pop from minStack if the top element was the minimum
            if (!minStack.isEmpty() && topElement == minStack.peek()) {
                minStack.pop();
            }
        }
    }

    /**
     * Returns the top element of the stack.
     * If the stack is empty, returns -1.
     *
     * @return top element or -1 if stack is empty
     */
    public int top() {
        if (stack.isEmpty()) {
            return -1;
        }
        return stack.peek();
    }

    /**
     * Retrieves the minimum element from the stack in O(1) time.
     * If the stack is empty, returns -1.
     *
     * @return current minimum element or -1 if empty
     */
    public int getMin() {
        if (minStack.isEmpty()) {
            return -1;
        }
        return minStack.peek();
    }

    /**
     * 🧪 Main method for testing the MinStack implementation.
     */
    public static void main(String[] args) {
        MinStack minStack = new MinStack();

        System.out.println("▶️ Pushing elements: -2, 0, -3");
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);

        System.out.println("Current Min: " + minStack.getMin()); // Expected: -3

        System.out.println("🗑 Popping top element...");
        minStack.pop();  // Removes -3

        System.out.println("Top Element: " + minStack.top());    // Expected: 0
        System.out.println("Current Min: " + minStack.getMin()); // Expected: -2

        System.out.println("▶️ Pushing new element: -1");
        minStack.push(-1);

        System.out.println("Top Element: " + minStack.top());    // Expected: -1
        System.out.println("Current Min: " + minStack.getMin()); // Expected: -2

        System.out.println("🗑 Popping all elements...");
        minStack.pop(); // pops -1
        minStack.pop(); // pops 0
        minStack.pop(); // pops -2

        System.out.println("After all pops → Top: " + minStack.top() + ", Min: " + minStack.getMin());
        // Expected: Top = -1 (empty case handled), Min = -1
    }
}