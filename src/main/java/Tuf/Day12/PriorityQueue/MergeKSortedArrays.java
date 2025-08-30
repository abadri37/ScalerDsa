package Tuf.Day12.PriorityQueue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class MergeKSortedArrays {
    public static void main(String[] args) {
        // Example input: multiple sorted arrays
        int[][] nums = {
                {1, 4, 5},
                {1, 3, 4},
                {2, 6},
                {0, 9, 10, 11},
                {7, 8}
        };

        // Print the merged sorted result
        System.out.println("The Merged Sorted Array is " + Arrays.toString(solve(nums)));
    }

    public static int[] solve(int[][] nums) {
        // List to store the final sorted elements
        List<Integer> list = new ArrayList<>();

        // Min-Heap (PriorityQueue in Java is by default a min-heap)
        // It ensures we always extract the smallest element first
        PriorityQueue<Integer> heap = new PriorityQueue<>();

        // Step 1: Push all elements from all arrays into the heap
        for (int[] in : nums) {
            for (int i : in) {
                heap.add(i); // add each number into the heap
            }
        }

        // Step 2: Extract elements one by one (polling gives the smallest first)
        while (!heap.isEmpty()) {
            list.add(heap.poll());
        }

        // Step 3: Convert List<Integer> to int[] and return
        return list.stream().mapToInt(Integer::intValue).toArray();
    }
}