package Tuf.Day12.PriorityQueue;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of a Min Heap data structure.
 *
 * The Min Heap is a complete binary tree where the value of each node
 * is less than or equal to the values of its children.
 *
 * Supported operations:
 *  - initializeHeap() → create a new heap
 *  - insert(key) → insert an element while maintaining heap property
 *  - getMin() → returns the smallest element (root)
 *  - extractMin() → removes the smallest element and rebalances heap
 *  - changeKey(index, newVal) → updates a value at a given index
 *  - heapSize() → returns number of elements in heap
 *  - isEmpty() → checks if heap is empty
 *  - isValidMinHeap() → verifies the heap property
 */
public class MinHeap {

    // Internal list to represent heap as array
    public List<Integer> minHeap;

    // Initialize an empty heap
    public void initializeHeap() {
        minHeap = new ArrayList<>();
    }

    /**
     * Inserts a new key into the Min Heap.
     * Maintains the Min Heap property using "Bubble Up" approach:
     * - Add element at end
     * - Compare with parent, swap if smaller
     * - Continue until heap property is satisfied
     */
    public void insert(int key) {
        minHeap.add(key); // Step 1: Add at last position
        int index = minHeap.size() - 1;

        // Step 2: Bubble up until parent is smaller
        while (index > 0) {
            int parentIndex = (index - 1) / 2;

            // If parent > child, swap
            if (minHeap.get(parentIndex) > minHeap.get(index)) {
                swap(parentIndex, index);
                index = parentIndex;
            } else {
                break; // Heap property satisfied
            }
        }
    }

    /**
     * Swaps two elements in the heap.
     */
    public void swap(int i, int j) {
        int temp = minHeap.get(i);
        minHeap.set(i, minHeap.get(j));
        minHeap.set(j, temp);
    }

    /**
     * Changes the value at a specific index.
     * - If new value is smaller, bubble up
     * - If new value is larger, heapify down
     */
    public void changeKey(int index, int newVal) {
        if (index < 0 || index >= minHeap.size()) {
            System.out.println("Invalid index");
            return;
        }

        int oldVal = minHeap.get(index);
        minHeap.set(index, newVal);

        // If new value is smaller than old → bubble up
        if (newVal < oldVal) {
            while (index > 0) {
                int parentIndex = (index - 1) / 2;
                if (minHeap.get(parentIndex) > minHeap.get(index)) {
                    swap(parentIndex, index);
                    index = parentIndex;
                } else {
                    break;
                }
            }
        } else {
            // If new value is larger → heapify down
            heapify(index);
        }
    }

    /**
     * Removes the smallest element (root) from the heap.
     * Steps:
     * 1. Replace root with the last element
     * 2. Remove the last element
     * 3. Call heapify() to restore Min Heap property
     */
    public void extractMin() {
        if (minHeap.isEmpty()) {
            System.out.println("Heap is empty!");
            return;
        }

        int lastIndex = heapSize() - 1;
        // Move last element to root
        minHeap.set(0, minHeap.get(lastIndex));
        // Remove last element
        minHeap.remove(lastIndex);

        // Restore Min Heap property
        heapify(0);
    }

    /**
     * Checks if heap is empty.
     */
    public boolean isEmpty() {
        return minHeap.isEmpty();
    }

    /**
     * Returns the minimum element (root of heap).
     */
    public int getMin() {
        if (minHeap.isEmpty()) {
            System.out.println("Heap is empty!");
            return -1;
        }
        return minHeap.get(0);
    }

    /**
     * Returns the number of elements in the heap.
     */
    public int heapSize() {
        return minHeap.size();
    }

    /**
     * Restores Min Heap property from a given index using "Heapify Down".
     * Steps:
     * - Compare node with its left and right child
     * - Swap with the smallest if parent is greater
     * - Recursively continue until property is satisfied
     */
    public void heapify(int index) {
        int smallest = index;
        int left = (2 * index) + 1;
        int right = (2 * index) + 2;

        // Check left child
        if (left < heapSize() && minHeap.get(left) < minHeap.get(smallest)) {
            smallest = left;
        }

        // Check right child
        if (right < heapSize() && minHeap.get(right) < minHeap.get(smallest)) {
            smallest = right;
        }

        // If smallest is not parent, swap and continue heapify
        if (smallest != index) {
            swap(smallest, index);
            heapify(smallest);
        }
    }

    /**
     * Validates whether the current heap maintains the Min Heap property.
     * For every parent i:
     *   - parent <= left child
     *   - parent <= right child
     */
    public boolean isValidMinHeap() {
        for (int i = 0; i < minHeap.size() / 2; i++) {
            int parent = i;
            int left = (2 * i) + 1;
            int right = (2 * i) + 2;

            if (left < heapSize() && minHeap.get(left) < minHeap.get(parent)) {
                return false;
            }
            if (right < heapSize() && minHeap.get(right) < minHeap.get(parent)) {
                return false;
            }
        }
        return true;
    }

    // -------------------- TESTING --------------------
    public static void main(String[] args) {
        MinHeap heap = new MinHeap();
        heap.initializeHeap();

        // Insert elements
        heap.insert(3);
        heap.insert(1);
        heap.insert(4);
        heap.insert(2);
        heap.insert(0);

        System.out.println("Heap: " + heap.minHeap);
        System.out.println("Is valid: " + heap.isValidMinHeap());
        System.out.println("Min: " + heap.getMin());

        // Extract minimum
        heap.extractMin();
        System.out.println("After extractMin: " + heap.minHeap);

        // Change a key value
        heap.changeKey(2, 5);
        System.out.println("After changeKey(2,5): " + heap.minHeap);
        System.out.println("Is valid: " + heap.isValidMinHeap());
    }
}