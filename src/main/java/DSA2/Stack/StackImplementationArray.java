package DSA2.Stack;

public class StackImplementationArray {

    public static void main(String[] args) {
        StackImplementationArray stack = new StackImplementationArray(5);

        stack.push(10);
        stack.push(20);
        stack.push(30);
        System.out.println("Top: " + stack.peek()); // Output: 30

        System.out.println("Size: " + stack.size()); // Output: 3

        System.out.println("Popped: " + stack.pop()); // Output: 30
        System.out.println("New Top: " + stack.peek()); // Output: 20
    }

    // Array to store stack elements
    public int[] stackList;

    // Pointer to the top of the stack
    int top = -1;

    // Maximum size of the stack
    int capacity;

    // Constructor to initialize the stack with a fixed size
    public StackImplementationArray(int size) {
        stackList = new int[size]; // Allocate array
        this.capacity = size;      // Set the capacity
    }

    /**
     * Pushes an element onto the top of the stack.
     * Throws RuntimeException if the stack is full.
     */
    public void push(int data) {
        if (isFull()) {
            throw new RuntimeException("Stack is full");
        }
        top++;                    // Move top pointer
        stackList[top] = data;    // Add data to the new top position
    }

    /**
     * Returns the top element of the stack without removing it.
     * Throws RuntimeException if the stack is empty.
     */
    public int peek() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }
        return stackList[top];   // Return top element
    }

    /**
     * Checks if the stack is empty.
     * Returns true if top is -1.
     */
    public boolean isEmpty() {
        return top == -1;
    }

    /**
     * Returns the number of elements currently in the stack.
     */
    public int size() {
        return top + 1;
    }

    /**
     * Removes and returns the top element of the stack.
     * Throws RuntimeException if the stack is empty.
     */
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }
        return stackList[top--]; // Return top and then decrease top pointer
    }

    /**
     * Checks if the stack is full.
     * Returns true if top has reached capacity - 1.
     */
    public boolean isFull() {
        return top == capacity - 1;
    }
}
