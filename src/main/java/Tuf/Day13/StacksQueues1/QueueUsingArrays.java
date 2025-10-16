package Tuf.Day13.StacksQueues1;

import java.util.ArrayList;
import java.util.List;

public class QueueUsingArrays {

    // Internal list to store queue elements
    private List<Integer> queue;

    /**
     * Constructor
     * 💭 Why use an ArrayList?
     * → Simple structure to simulate a queue (FIFO: First In First Out).
     */
    public QueueUsingArrays() {
        queue = new ArrayList<>();
    }

    /**
     * Push (enqueue) an element into the queue.
     * 💭 Where should new elements go in a queue?
     * → Always at the *end* (tail) of the list.
     *
     * Example:
     * queue = []
     * push(10) → [10]
     * push(20) → [10, 20]
     */
    public void push(int x) {
        queue.add(x);
    }

    /**
     * Pop (dequeue) the front element from the queue and return it.
     * 💭 Why remove from the front (index 0)?
     * → Because queue follows FIFO order.
     *
     * Example:
     * queue = [10, 20, 30]
     * pop() → returns 10; queue becomes [20, 30]
     */
    public int pop() {
        if (queue.isEmpty()) {
            throw new IllegalArgumentException("Queue is empty");
        }
        int value = queue.get(0);
        queue.remove(0); // Remove first element (O(n) shift)
        return value;
    }

    /**
     * Peek (get the front element) without removing it.
     * 💭 What is peek used for?
     * → To check which element is next to be dequeued.
     *
     * Example:
     * queue = [10, 20, 30]
     * peek() → returns 10; queue remains [10, 20, 30]
     */
    public int peek() {
        if (queue.isEmpty()) {
            throw new IllegalArgumentException("Queue is empty");
        }
        return queue.get(0);
    }

    /**
     * Check if the queue is empty.
     * 💭 Why important?
     * → Prevents invalid operations like pop() on an empty queue.
     */
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    /**
     * Utility method for debugging:
     * Prints the queue contents from front → rear.
     */
    public void printQueue() {
        System.out.println("Queue (front → rear): " + queue);
    }

    // --------------------- 🔹 TESTING SECTION 🔹 ---------------------
    public static void main(String[] args) {
        QueueUsingArrays queue = new QueueUsingArrays();

        System.out.println("---- Queue Operations Demo ----");

        // 💭 Test 1: Push operations
        System.out.println("\nPushing elements: 10, 20, 30");
        queue.push(10);
        queue.push(20);
        queue.push(30);
        queue.printQueue(); // Expected: [10, 20, 30]

        // 💭 Test 2: Peek front element
        System.out.println("\nPeek front element: " + queue.peek()); // Expected: 10

        // 💭 Test 3: Pop operation
        System.out.println("\nPopping front element...");
        int popped = queue.pop();
        System.out.println("Popped value: " + popped); // Expected: 10
        queue.printQueue(); // Expected: [20, 30]

        // 💭 Test 4: Push again
        System.out.println("\nPushing element: 40");
        queue.push(40);
        queue.printQueue(); // Expected: [20, 30, 40]

        // 💭 Test 5: Check if queue is empty
        System.out.println("\nIs queue empty? " + queue.isEmpty()); // Expected: false

        // 💭 Test 6: Pop all elements
        System.out.println("\nPopping all elements:");
        while (!queue.isEmpty()) {
            System.out.println("Popped: " + queue.pop());
        }

        // 💭 Test 7: Check empty queue behavior
        System.out.println("\nIs queue empty now? " + queue.isEmpty()); // Expected: true

        // 💭 Test 8: Edge case — pop on empty queue
        System.out.println("\nAttempting pop on empty queue...");
        try {
            queue.pop();
        } catch (IllegalArgumentException e) {
            System.out.println("Caught Exception: " + e.getMessage()); // Expected: "Queue is empty"
        }
    }
}