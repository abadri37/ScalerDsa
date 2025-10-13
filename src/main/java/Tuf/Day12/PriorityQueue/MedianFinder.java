package Tuf.Day12.PriorityQueue;

import java.util.Collections;
import java.util.PriorityQueue;

public class MedianFinder {

    // Min-heap stores the larger half of numbers
    PriorityQueue<Integer> minHeap;

    // Max-heap stores the smaller half of numbers (in reverse order)
    PriorityQueue<Integer> maxHeap;

    public MedianFinder() {
        minHeap = new PriorityQueue<>();
        maxHeap = new PriorityQueue<>(Collections.reverseOrder());
    }

    /**
     * Adds a number into the data structure.
     * <p>
     * Steps:
     * 1. If maxHeap is empty, add number there.
     * 2. Otherwise, compare with maxHeap.peek() (largest number in smaller half)
     * - If num > maxHeap.peek(), it belongs to minHeap (larger half)
     * - Else, it belongs to maxHeap (smaller half)
     * 3. Rebalance heaps if size difference > 1:
     * - Move top element from larger heap to smaller heap
     */
    public void addNum(int num) {
        if (maxHeap.isEmpty()) {
            maxHeap.add(num);
            return;
        }

        if (maxHeap.peek() < num) {
            minHeap.add(num);
        } else {
            maxHeap.add(num);
        }

        // Rebalance if size difference > 1
        if (Math.abs(minHeap.size() - maxHeap.size()) > 1) {
            if (maxHeap.size() > minHeap.size()) {
                minHeap.add(maxHeap.poll());
            } else {
                maxHeap.add(minHeap.poll());
            }
        }
    }

    /**
     * Returns the median of current numbers
     * <p>
     * Cases:
     * 1. Total number of elements is odd -> median is top of larger heap
     * 2. Total number of elements is even -> median is average of both heap tops
     */
    public double findMedian() {
        int totalSize = minHeap.size() + maxHeap.size();
        if (totalSize % 2 == 1) {
            return maxHeap.size() > minHeap.size() ? (double) maxHeap.peek() : (double) minHeap.peek();
        } else {
            return (maxHeap.peek() + minHeap.peek()) / 2.0;
        }
    }

    /**
     * Main method for testing
     */
    public static void main(String[] args) {
        MedianFinder mf = new MedianFinder();

        // Example input array
        int[] nums = {5, 3, 8, 9, 2};

        System.out.println("Adding numbers and finding median step by step:");
        for (int num : nums) {
            mf.addNum(num);
            System.out.println("Added " + num + " -> Current Median: " + mf.findMedian());
        }

        // Additional test
        System.out.println("\nFinal Median after all insertions: " + mf.findMedian());
    }
}

/**
 * Example Input & Output:
 * <p>
 * Input sequence: 5, 3, 8, 9, 2
 * Step by step:
 * 1. Add 5 -> Median = 5
 * 2. Add 3 -> Median = (5+3)/2 = 4.0
 * 3. Add 8 -> Median = 5
 * 4. Add 9 -> Median = (5+8)/2 = 6.5
 * 5. Add 2 -> Median = 5
 * <p>
 * Explanation:
 * - maxHeap contains the smaller half (in reverse order)
 * - minHeap contains the larger half
 * - Median is calculated based on heap sizes and top elements
 */
