package Tuf.Day13.StacksQueues1;

import java.util.ArrayList;
import java.util.List;

public class StackUsingArray {

    // ✅ Internal list to store stack elements
    private List<Integer> stack;

    /**
     * Constructor
     * 💭 Why initialize with ArrayList?
     * → ArrayList allows O(1) insertion/removal from the end,
     *   which is perfect for stack operations (LIFO order).
     */
    public StackUsingArray() {
        stack = new ArrayList<>();
    }

    /**
     * Push an element onto the stack.
     * 💭 What happens internally?
     * → Adds the element to the end of the list (top of the stack).
     *
     * Example:
     * stack = []
     * push(10) → [10]
     * push(20) → [10, 20]
     */
    public void push(int x) {
        stack.add(x);
    }

    /**
     * Pop the top element from the stack and return it.
     * 💭 What happens if the stack is empty?
     * → Throw an exception to prevent invalid access.
     *
     * Example:
     * stack = [10, 20, 30]
     * pop() → returns 30; stack becomes [10, 20]
     */
    public int pop() {
        if (stack.isEmpty()) {
            throw new IllegalArgumentException("Stack is empty");
        }
        int value = stack.get(stack.size() - 1);
        stack.remove(stack.size() - 1);
        return value;
    }

    /**
     * Get the top element of the stack without removing it.
     * 💭 How is it different from pop()?
     * → top() only *reads* the last element but does not remove it.
     *
     * Example:
     * stack = [10, 20, 30]
     * top() → returns 30; stack remains [10, 20, 30]
     */
    public int top() {
        if (stack.isEmpty()) {
            throw new IllegalArgumentException("Stack is empty");
        }
        return stack.get(stack.size() - 1);
    }

    /**
     * Check if the stack is empty.
     * 💭 When does this return true?
     * → Only when no elements are present.
     */
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    /**
     * ✅ Optional utility: display current stack contents.
     * (Helps visualize the stack state during testing.)
     */
    public void printStack() {
        System.out.println("Stack (bottom → top): " + stack);
    }

    // --------------------- 🔹 TESTING SECTION 🔹 ---------------------
    public static void main(String[] args) {
        StackUsingArray stack = new StackUsingArray();

        System.out.println("---- Stack Operations Demo ----");

        // 💭 Test 1: Push operations
        System.out.println("\nPushing elements: 10, 20, 30");
        stack.push(10);
        stack.push(20);
        stack.push(30);
        stack.printStack();  // Expected: [10, 20, 30]

        // 💭 Test 2: Top element check
        System.out.println("\nTop element: " + stack.top()); // Expected: 30

        // 💭 Test 3: Pop operation
        System.out.println("\nPopping element...");
        int popped = stack.pop();
        System.out.println("Popped value: " + popped); // Expected: 30
        stack.printStack(); // Expected: [10, 20]

        // 💭 Test 4: Push again
        System.out.println("\nPushing element: 40");
        stack.push(40);
        stack.printStack(); // Expected: [10, 20, 40]

        // 💭 Test 5: Check if stack is empty
        System.out.println("\nIs stack empty? " + stack.isEmpty()); // Expected: false

        // 💭 Test 6: Pop remaining elements
        System.out.println("\nPopping all elements:");
        while (!stack.isEmpty()) {
            System.out.println("Popped: " + stack.pop());
        }

        // 💭 Test 7: Check empty stack behavior
        System.out.println("\nIs stack empty now? " + stack.isEmpty()); // Expected: true

        // 💭 Test 8: Edge case — pop on empty stack
        System.out.println("\nAttempting pop on empty stack...");
        try {
            stack.pop();
        } catch (IllegalArgumentException e) {
            System.out.println("Caught Exception: " + e.getMessage()); // Expected
        }
    }
}