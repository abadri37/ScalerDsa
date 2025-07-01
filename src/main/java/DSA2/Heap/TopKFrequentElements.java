package DSA2.Heap;

import java.util.*;

public class TopKFrequentElements {

    public static void main(String[] args) {
        // Q: What is the input?
        // A: An integer array and an integer k
        int[] nums = {1, 1, 1, 2, 2, 3};
        int k = 2;

        // Q: What is the function call returning?
        // A: The top k frequent elements from the array
        int[] ret = topKFrequent(nums, k);

        // Q: How are we printing the result?
        // A: Iterating through the returned array and printing each element
        System.out.print("The result is ");
        for (int n : ret) {
            System.out.print(n + " ");
        }
        System.out.println();
    }

    /**
     * Finds the top K frequent elements in the input array.
     *
     * @param nums The input array of integers
     * @param k    Number of top frequent elements to return
     * @return     An array of the top k frequent elements
     */
    public static int[] topKFrequent(int[] nums, int k) {
        // Q: What does this map store?
        // A: The frequency count of each number
        Map<Integer, Integer> map = new HashMap<>();
        for (Integer in : nums) {
            if (map.get(in) != null) {
                map.put(in, map.get(in) + 1);
            } else {
                map.put(in, 1);
            }
        }

        // Q: What is this priority queue (min-heap) used for?
        // A: To keep track of top k frequent elements by comparing frequencies
        PriorityQueue<Integer> heap = new PriorityQueue<>((a, b) -> map.get(a) - map.get(b));

        // Q: How are we deciding which elements to keep in the heap?
        // A: If the heap size exceeds k, we remove the least frequent element
        for (int num : map.keySet()) {
            heap.add(num);
            if (heap.size() > k) {
                heap.poll(); // remove the element with the lowest frequency
            }
        }

        // Q: Why are we using a list here?
        // A: To collect all elements remaining in the heap
        List<Integer> list = new ArrayList<>();
        while (!heap.isEmpty()) {
            list.add(heap.poll());
        }

        // Q: How do we convert the list to an array?
        // A: Using Java 8 stream to convert List<Integer> to int[]
        return list.stream().mapToInt(i -> i).toArray();
    }
}
