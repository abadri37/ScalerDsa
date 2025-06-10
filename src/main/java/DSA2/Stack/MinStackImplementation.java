package DSA2.Stack;

import java.util.Stack;

public class MinStackImplementation {

    public static void main(String[] args) {
        MinStackImplementation stack = new MinStackImplementation();

        stack.push(5);
        stack.push(3);
        stack.push(7);
        stack.push(3);

        System.out.println("Minimum: " + stack.getMin()); // Output: 3
        System.out.println("Popped: " + stack.pop());     // Output: 3
        System.out.println("Minimum: " + stack.getMin()); // Output: 3
        System.out.println("Popped: " + stack.pop());     // Output: 7
        System.out.println("Minimum: " + stack.getMin()); // Output: 3
        stack.pop();                                      // Popped 3
        System.out.println("Minimum: " + stack.getMin()); // Output: 5
    }

    public Stack<Integer> mainStack;
    public Stack<Integer> minStack;

    // Constructor initializes both stacks
    public MinStackImplementation() {
        this.mainStack = new Stack<>();
        this.minStack = new Stack<>();
    }

    /**
     * Pushes an element onto the stack and updates the minStack accordingly.
     */
    public void push(int data) {
        mainStack.push(data);

        // Push to minStack if it’s empty or the new element is <= current min
        if (minStack.isEmpty() || data <= minStack.peek()) {
            minStack.push(data);
        }
    }

    /**
     * Pops the top element from the main stack and updates the min stack if needed.
     *
     * @return The popped element.
     */
    public int pop() {
        int popped = mainStack.pop();

        // Pop from minStack if the popped element was the current minimum
        if (!minStack.isEmpty() && minStack.peek() == popped) {
            minStack.pop();
        }
        return popped;
    }

    /**
     * Returns the top element of the main stack without removing it.
     */
    public int peek() {
        return mainStack.peek();
    }

    /**
     * Returns the current minimum element in the stack. O(1) complexity
     */
    public int getMin() {
        return minStack.peek();
    }
}
