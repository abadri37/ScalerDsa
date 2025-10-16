package Tuf.Day13.StacksQueues1;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Implementation of a Stack using a single Queue.
 *
 * Key Concept:
 * A stack is LIFO (Last In First Out),
 * whereas a queue is FIFO (First In First Out).
 *
 * To simulate stack behavior, every time we push a new element,
 * we rotate the existing queue so that the newly added element
 * always comes to the front (top of the stack).
 */
public class StackUsingQueue {

    // The main data structure to hold elements
    Queue<Integer> queue;

    public StackUsingQueue() {
        queue = new LinkedList<>();
    }

    /**
     * Push an element onto the stack.
     *
     * Step-by-step:
     * 1️⃣ Add the new element 'x' to the queue.
     * 2️⃣ Rotate the queue (move all previous elements behind 'x')
     *     so that 'x' becomes the front element.
     *
     * Example:
     *   push(10) → [10]
     *   push(20) → add(20), rotate → [20, 10]
     *   push(30) → add(30), rotate → [30, 20, 10]
     *
     * Top of stack (front of queue) → 30
     */
    public void push(int x) {
        int size = queue.size();
        queue.add(x); // Step 1: Add new element
        for (int i = 0; i < size; i++) {
            queue.add(queue.poll()); // Step 2: Rotate older elements to the back
        }
    }

    /**
     * Removes and returns the top element of the stack.
     *
     * Since we rotated the queue in push(),
     * the top element is always at the front of the queue.
     *
     * Returns -1 if stack is empty.
     *
     * Example:
     *   Stack = [30, 20, 10]
     *   pop() → 30 → Queue becomes [20, 10]
     */
    public int pop() {
        return queue.isEmpty() ? -1 : queue.poll();
    }

    /**
     * Returns the top element of the stack without removing it.
     *
     * Returns -1 if stack is empty.
     *
     * Example:
     *   Stack = [30, 20, 10]
     *   top() → 30 (stack remains unchanged)
     */
    public int top() {
        return queue.isEmpty() ? -1 : queue.peek();
    }

    /**
     * Checks whether the stack is empty.
     *
     * Returns true if stack has no elements, false otherwise.
     */
    public boolean empty() {
        return queue.isEmpty();
    }

    /**
     * Main method for testing the stack implementation.
     */
    public static void main(String[] args) {
        StackUsingQueue stack = new StackUsingQueue();

        // 🧪 Test 1: Push elements
        stack.push(10);
        stack.push(20);
        stack.push(30);
        System.out.println("Top after pushes: " + stack.top()); // Expected: 30

        // 🧪 Test 2: Pop elements
        System.out.println("Popped: " + stack.pop()); // Expected: 30
        System.out.println("Top now: " + stack.top()); // Expected: 20

        // 🧪 Test 3: Push another element
        stack.push(40);
        System.out.println("Top after pushing 40: " + stack.top()); // Expected: 40

        // 🧪 Test 4: Pop remaining elements
        System.out.println("Popped: " + stack.pop()); // Expected: 40
        System.out.println("Popped: " + stack.pop()); // Expected: 20
        System.out.println("Popped: " + stack.pop()); // Expected: 10

        // 🧪 Test 5: Empty stack check
        System.out.println("Is empty: " + stack.empty()); // Expected: true

        // 🧪 Test 6: Pop from empty stack
        System.out.println("Pop from empty: " + stack.pop()); // Expected: -1
    }
}