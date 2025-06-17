package DSA1.Array.Sorting;

import java.util.PriorityQueue;

public class TopKSort {
    public static void main(String[] args) {
        // Input array and value of k
        int[] arr = {12, 5, 787, 1, 23, 45, 99, 300, 123, 55, 7, 88, 42, 67, 1000};
        int k = 5;

        // Call function to get top K largest elements
        int[] ret = topKSort(arr, k);

        // Print the result
        System.out.print("Top K elements in the array are: ");
        for (int i = 0; i < ret.length; i++) {
            System.out.print(ret[i] + " ");
        }
        System.out.println();
    }

    // Function to return top K largest elements using a min-heap
    public static int[] topKSort(int[] arr, int k) {
        int[] ret = new int[k];

        // Min-heap to keep track of top K elements
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        // Traverse the array
        for (int i = 0; i < arr.length; i++) {
            // If heap size is less than k, add element directly
            if (minHeap.size() < k) {
                minHeap.add(arr[i]);
            } else {
                // If current element is greater than the smallest in heap
                if (arr[i] > minHeap.peek()) {
                    minHeap.poll();         // Remove the smallest
                    minHeap.add(arr[i]);    // Add the new larger element
                }
            }
        }

        // Extract top K elements from the min-heap
        int cnt = 0;
        while (!minHeap.isEmpty()) {
            ret[cnt] = minHeap.poll();  // Elements will not be in sorted order
            cnt++;
        }

        return ret;
    }
}
