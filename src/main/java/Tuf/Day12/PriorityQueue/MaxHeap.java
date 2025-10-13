package Tuf.Day12.PriorityQueue;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple implementation of a Max Heap data structure.
 * <p>
 * Max Heap properties:
 * - The value of each parent node is always greater than or equal to its children.
 * - The maximum element is always stored at the root (index 0).
 * <p>
 * Supported Operations:
 * - initializeHeap(): Initializes an empty heap
 * - insert(key): Adds a new element and maintains the heap property
 * - changeKey(index, newVal): Updates a key and rebalances the heap
 * - extractMax(): Removes the maximum element (root)
 * - getMax(): Returns the maximum element without removing it
 * - heapify(index): Re-establishes heap property from a given index downward
 * - isValidMaxHeap(): Validates if current list satisfies max-heap property
 * - isEmpty(): Checks if the heap is empty
 * - heapSize(): Returns the number of elements in the heap
 */
public class MaxHeap {

    /**
     * Internal list representing the heap
     */
    public List<Integer> maxHeap;

    /**
     * Initializes an empty heap.
     */
    public void initializeHeap() {
        maxHeap = new ArrayList<>();
    }

    /**
     * Inserts a new key into the Max Heap.
     * <p>
     * Algorithm:
     * - Add the element at the end.
     * - "Bubble up" the new element until the parent is greater or equal.
     * <p>
     * Time Complexity: O(log n)
     */
    public void insert(int key) {
        maxHeap.add(key); // Add new element at the end
        int index = maxHeap.size() - 1;

        // Move upward (bubble up) until heap property is restored
        while (index > 0) {
            int parentIndex = (index - 1) / 2;

            // Swap if parent is smaller than current
            if (maxHeap.get(parentIndex) < maxHeap.get(index)) {
                swap(parentIndex, index);
                index = parentIndex;
            } else {
                break; // Heap property satisfied
            }
        }
    }

    /**
     * Utility method to swap two elements in the heap.
     */
    public void swap(int i, int j) {
        int temp = maxHeap.get(i);
        maxHeap.set(i, maxHeap.get(j));
        maxHeap.set(j, temp);
    }

    /**
     * Changes the key (value) at a specific index and rebalances the heap.
     * <p>
     * If new value is greater → bubble up.
     * If new value is smaller → push down using heapify.
     * <p>
     * Time Complexity: O(log n)
     */
    public void changeKey(int index, int newVal) {
        if (index < 0 || index >= maxHeap.size()) {
            System.out.println("Invalid index");
            return;
        }

        int oldVal = maxHeap.get(index);
        maxHeap.set(index, newVal);

        if (newVal > oldVal) {
            // If new value is greater, bubble up
            while (index > 0) {
                int parentIndex = (index - 1) / 2;
                if (maxHeap.get(parentIndex) < maxHeap.get(index)) {
                    swap(parentIndex, index);
                    index = parentIndex;
                } else {
                    break;
                }
            }
        } else {
            // If smaller, restore heap downward
            heapify(index);
        }
    }

    /**
     * Removes the maximum element (root) from the heap.
     * <p>
     * Algorithm:
     * - Replace root with last element.
     * - Remove the last element.
     * - Call heapify() on the root to restore heap property.
     * <p>
     * Time Complexity: O(log n)
     */
    public void extractMax() {
        if (maxHeap.isEmpty()) {
            System.out.println("Heap is empty!");
            return;
        }

        int lastIndex = maxHeap.size() - 1;

        // Replace root with last element
        maxHeap.set(0, maxHeap.get(lastIndex));

        // Remove last element
        maxHeap.remove(lastIndex);

        // Restore heap property from root down
        heapify(0);
    }

    /**
     * Returns the maximum (root) element from the heap.
     * Does not remove it.
     * <p>
     * Time Complexity: O(1)
     */
    public int getMax() {
        if (maxHeap.isEmpty()) {
            System.out.println("Heap is empty!");
            return -1;
        }
        return maxHeap.get(0);
    }

    /**
     * Restores the Max Heap property starting from the given index.
     * <p>
     * Algorithm:
     * - Compare node with left and right children.
     * - If either child is greater, swap with the largest child.
     * - Recursively call heapify() for the swapped child.
     * <p>
     * Time Complexity: O(log n)
     */
    public void heapify(int index) {
        int largest = index;
        int left = (2 * index) + 1;
        int right = (2 * index) + 2;

        // Check if left child is greater than parent
        if (left < maxHeap.size() && maxHeap.get(largest) < maxHeap.get(left)) {
            largest = left;
        }

        // Check if right child is greater than current largest
        if (right < maxHeap.size() && maxHeap.get(largest) < maxHeap.get(right)) {
            largest = right;
        }

        // If largest is not parent, swap and continue heapify
        if (largest != index) {
            swap(largest, index);
            heapify(largest);
        }
    }

    /**
     * Validates whether the heap satisfies the Max Heap property.
     * <p>
     * Returns true if every parent >= children.
     * Useful for debugging and testing.
     */
    public boolean isValidMaxHeap() {
        for (int i = 0; i < maxHeap.size(); i++) {
            int index = i;
            int left = (2 * index) + 1;
            int right = (2 * index) + 2;

            if (left < maxHeap.size() && maxHeap.get(index) < maxHeap.get(left)) {
                return false;
            }
            if (right < maxHeap.size() && maxHeap.get(index) < maxHeap.get(right)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if the heap is empty.
     */
    public boolean isEmpty() {
        return maxHeap.isEmpty();
    }

    /**
     * Returns the current number of elements in the heap.
     */
    public int heapSize() {
        return maxHeap.size();
    }

    public static void main(String[] args) {
        MaxHeap heap = new MaxHeap();
        heap.initializeHeap();

        // 🔹 Insert elements
        heap.insert(3);
        heap.insert(10);
        heap.insert(5);
        heap.insert(6);
        heap.insert(2);
        heap.insert(8);
        heap.insert(1);

        System.out.println("Initial Heap: " + heap.maxHeap);
        System.out.println("Is valid max heap? " + heap.isValidMaxHeap());
        System.out.println("Current Max: " + heap.getMax());

        // 🔹 Extract max
        heap.extractMax();
        System.out.println("\nAfter extractMax:");
        System.out.println("Heap: " + heap.maxHeap);
        System.out.println("New Max: " + heap.getMax());
        System.out.println("Is valid: " + heap.isValidMaxHeap());

        // 🔹 Change key (increase)
        heap.changeKey(2, 12); // increase value at index 2
        System.out.println("\nAfter changeKey(2, 12):");
        System.out.println("Heap: " + heap.maxHeap);
        System.out.println("Max: " + heap.getMax());
        System.out.println("Is valid: " + heap.isValidMaxHeap());

        // 🔹 Change key (decrease)
        heap.changeKey(1, 4); // decrease value at index 1
        System.out.println("\nAfter changeKey(1, 4):");
        System.out.println("Heap: " + heap.maxHeap);
        System.out.println("Is valid: " + heap.isValidMaxHeap());

        // 🔹 Size and emptiness check
        System.out.println("\nHeap size: " + heap.heapSize());
        System.out.println("Is heap empty? " + heap.isEmpty());

        // 🔹 Extract all to empty the heap
        while (!heap.isEmpty()) {
            System.out.println("Extracting max: " + heap.getMax());
            heap.extractMax();
            System.out.println("Heap now: " + heap.maxHeap);
        }

        System.out.println("\nFinal check -> Is heap empty? " + heap.isEmpty());
    }

}