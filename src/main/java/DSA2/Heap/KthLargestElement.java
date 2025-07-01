package DSA2.Heap;

import java.util.PriorityQueue;

public class KthLargestElement {
    public static void main(String[] args) {
        int[] nums = {3, 2, 1, 5, 6, 4};
        int k = 2; // We want the 2nd largest element, expected output: 5

        // Print the result of kth largest element
        System.out.println("The " + k + "th Largest element in the given array is " + largestElement(nums, k));
    }

    /**
     * Finds the kth largest element in an unsorted array using a Min-Heap (PriorityQueue).
     *
     * @param nums The input array of integers
     * @param k    The kth largest position to find
     * @return     The kth largest element in the array
     */
    public static int largestElement(int[] nums, int k) {
        // Min-heap to store the top k largest elements
        PriorityQueue<Integer> heap = new PriorityQueue<>();

        // Iterate through each element in the array
        for (int i = 0; i < nums.length; i++) {
            if (heap.size() < k) {
                // If the heap size is less than k, just add the element
                heap.add(nums[i]);
            } else {
                // Check if the current number is larger than the smallest in heap (top of min-heap)
                int peek = heap.peek();
                if (peek < nums[i]) {
                    heap.poll();       // Remove the smallest element
                    heap.add(nums[i]); // Insert the current larger element
                }
                // Else, skip as current element is not among the k largest so far
            }
        }

        // The root of the min-heap is the kth largest element
        return heap.peek();
    }
}
