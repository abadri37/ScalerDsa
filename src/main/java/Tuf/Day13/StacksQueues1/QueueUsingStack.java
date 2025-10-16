package Tuf.Day13.StacksQueues1;

import java.util.Stack;

/**
 * QueueUsingStack — Implements a Queue (FIFO) using two Stacks (LIFO).
 *
 * Core idea:
 * ➤ stack1 is used for input (enqueue)
 * ➤ stack2 is used for output (dequeue)
 *
 * The logic ensures that the oldest element always gets popped first,
 * maintaining true Queue behavior.
 */
public class QueueUsingStack {

    Stack<Integer> stack1; // used for pushing new elements
    Stack<Integer> stack2; // used for popping elements in correct order

    public QueueUsingStack() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }

    /**
     * Adds (enqueues) an element to the queue.
     *
     * Q: Why push to stack1?
     * A: Because stack1 acts as the "input" side of the queue — we store
     *    elements in LIFO order temporarily until they are transferred to stack2.
     *
     * Example:
     *   push(10), push(20), push(30)
     *   stack1 = [10, 20, 30], stack2 = []
     */
    public void push(int x) {
        stack1.push(x);
    }

    /**
     * Removes (dequeues) the front element from the queue.
     *
     * Q: Why move elements from stack1 to stack2?
     * A: Because we want to reverse the order. The oldest element that was
     *    first pushed into stack1 will end up on top of stack2 after the transfer.
     *
     * Steps:
     *   1️⃣ If stack2 is empty, move all elements from stack1 to stack2.
     *   2️⃣ Pop from stack2 (this gives the front of the queue).
     *
     * Example:
     *   stack1 = [10, 20, 30], stack2 = []
     *   pop() → move all to stack2 → stack2 = [30, 20, 10]
     *   pop() → returns 10
     *
     * Returns: front element (the one inserted earliest)
     */
    public int pop() {
        // Step 1: Move all elements only if stack2 is empty
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }

        // Step 2: Pop from stack2
        return stack2.pop();
    }

    /**
     * Returns the front element (without removing it).
     *
     * Q: Why peek from stack2?
     * A: Because stack2 contains the elements in reversed order,
     *    meaning the front of the queue is always at the top of stack2.
     *
     * Example:
     *   stack1 = [10, 20, 30], stack2 = [30, 20, 10]
     *   peek() → 10
     *
     * If stack2 is empty, we first move all elements from stack1 to stack2.
     */
    public int peek() {
        if (!stack2.isEmpty()) {
            return stack2.peek();
        } else {
            // Move elements to restore order
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        return stack2.peek();
    }

    /**
     * Checks whether the queue is empty.
     *
     * Q: Why check both stacks?
     * A: Because elements can be temporarily in either stack1 or stack2.
     *    Only if both are empty, the queue has no elements.
     *
     * Returns: true if queue is empty, false otherwise.
     */
    public boolean empty() {
        return stack1.isEmpty() && stack2.isEmpty();
    }

    /**
     * Main method for testing QueueUsingStack behavior.
     */
    public static void main(String[] args) {
        QueueUsingStack queue = new QueueUsingStack();

        // 🧪 Test 1: Push elements
        queue.push(10);
        queue.push(20);
        queue.push(30);
        System.out.println("Peek front: " + queue.peek()); // Expected: 10

        // 🧪 Test 2: Pop one element
        System.out.println("Popped: " + queue.pop()); // Expected: 10
        System.out.println("Peek front after pop: " + queue.peek()); // Expected: 20

        // 🧪 Test 3: Add more elements
        queue.push(40);
        queue.push(50);
        System.out.println("Peek front: " + queue.peek()); // Expected: 20

        // 🧪 Test 4: Pop remaining elements
        System.out.println("Popped: " + queue.pop()); // Expected: 20
        System.out.println("Popped: " + queue.pop()); // Expected: 30
        System.out.println("Popped: " + queue.pop()); // Expected: 40
        System.out.println("Popped: " + queue.pop()); // Expected: 50

        // 🧪 Test 5: Check if empty
        System.out.println("Is empty: " + queue.empty()); // Expected: true

        // 🧪 Test 6: Try popping from empty queue (will throw EmptyStackException)
        // Uncomment to see:
        // System.out.println(queue.pop());
    }
}