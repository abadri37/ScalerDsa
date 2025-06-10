package DSA2.Stack;

import java.util.ArrayList;
import java.util.List;

/**
 * Custom implementation of a Stack using an ArrayList.
 * Follows LIFO (Last-In-First-Out) principle.
 */
public class StackImplementation {


    public static void main(String[] args) {
        StackImplementation stack = new StackImplementation();

        // Pushing elements into the stack
        stack.push(10);
        stack.push(20);
        stack.push(30);
        System.out.println("Pushed: 10, 20, 30");

        // Peek the top element
        System.out.println("Top element (peek): " + stack.peek()); // Expected: 30

        // Check size
        System.out.println("Current size: " + stack.size()); // Expected: 3

        // Pop elements
        stack.pop(); // Removes 30
        System.out.println("Popped top element.");

        // Peek again
        System.out.println("Top element after pop: " + stack.peek()); // Expected: 20

        // Check if stack is empty
        System.out.println("Is stack empty? " + stack.isEmpty()); // Expected: false

        // Pop remaining elements
        stack.pop(); // Removes 20
        stack.pop(); // Removes 10
        System.out.println("Popped remaining elements.");

        // Try popping from an empty stack
        stack.pop(); // Should print Stack Underflow

        // Final check
        System.out.println("Is stack empty now? " + stack.isEmpty()); // Expected: true
    }

    // Internal list to store stack elements
    private List<Integer> stackList;

    // Constructor to initialize the stack
    public StackImplementation() {
        stackList = new ArrayList<>();
    }

    /**
     * Pushes an element onto the top of the stack.
     *
     * @param data The integer to push
     */
    public void push(int data) {
        stackList.add(data);
    }

    /**
     * Returns the top element of the stack without removing it.
     *
     * @return Top element if stack is not empty, otherwise -1
     */
    public int peek() {
        if (!stackList.isEmpty()) {
            return stackList.get(stackList.size() - 1);
        }
        return -1; // Stack is empty
    }

    /**
     * Checks whether the stack is empty.
     *
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return stackList.isEmpty();
    }

    /**
     * Returns the number of elements in the stack.
     *
     * @return Current size of the stack
     */
    public int size() {
        return stackList.size();
    }

    /**
     * Removes the top element from the stack.
     * Prints an error message if the stack is empty.
     */
    public void pop() {
        if (!stackList.isEmpty()) {
            stackList.remove(stackList.size() - 1);
        } else {
            System.out.println("Stack Underflow: Cannot pop from an empty stack.");
        }
    }
}
