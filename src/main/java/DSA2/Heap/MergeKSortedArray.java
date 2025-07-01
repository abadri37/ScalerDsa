package DSA2.Heap;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class MergeKSortedArray {

    public static void main(String[] args) {
        // Q: What is the input here?
        // A: A 2D array where each sub-array is individually sorted
        int[][] arrays = {
                {1, 4, 5},
                {1, 3, 4},
                {2, 6}
        };

        // Q: Why are we using a PriorityQueue (Min-Heap)?
        // A: To automatically keep elements in sorted order while merging
        PriorityQueue<Integer> heap = new PriorityQueue<>();

        // Q: How are we adding elements to the heap?
        // A: By iterating over each array and inserting every element into the heap
        for (int[] in : arrays) {
            for (int i : in) {
                heap.add(i); // O(log N) per insertion
            }
        }

        // Q: What will this list store?
        // A: The final merged sorted array
        List<Integer> list = new ArrayList<>();

        // Q: How are we getting sorted elements from the heap?
        // A: By polling (removing) the smallest element one-by-one until the heap is empty
        while (!heap.isEmpty()) {
            list.add(heap.poll()); // O(log N) per removal
        }

        // Q: What is the final output?
        // A: A single list that contains all elements from all arrays in sorted order
        System.out.println("The Merged Array is " + list);
    }
}
