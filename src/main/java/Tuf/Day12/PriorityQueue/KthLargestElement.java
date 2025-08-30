package Tuf.Day12.PriorityQueue;

import java.util.PriorityQueue;

public class KthLargestElement {
    public static void main(String[] args) {
        int[] nums = new int[]{3, 2, 1, 5, 6, 4};
        int k = 2;
        System.out.println("The Kth Largest Element is " + solve(nums, k));
    }

    public static int solve(int[] nums, int k) {
        // If k is larger than the number of elements, return -1 (invalid case)
        if (k > nums.length) {
            return -1;
        }

        // Create a min-heap (default PriorityQueue in Java is a min-heap)
        // This heap will store at most 'k' largest elements
        PriorityQueue<Integer> heap = new PriorityQueue<>();

        // Iterate through all numbers
        for (int i = 0; i < nums.length; i++) {
            // Add the current element into the heap
            heap.add(nums[i]);

            // If heap size exceeds k, remove the smallest element
            // This ensures that the heap always contains only the largest k elements
            if (heap.size() > k) {
                heap.poll();  // removes the smallest element
            }
        }

        // After processing all elements:
        // -> The heap contains the k largest elements
        // -> The smallest among them (root of the min-heap) is the kth largest element
        return !heap.isEmpty() ? heap.peek() : -1;
    }
}