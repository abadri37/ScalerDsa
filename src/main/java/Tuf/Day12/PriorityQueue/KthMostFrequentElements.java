package Tuf.Day12.PriorityQueue;

import java.util.*;

public class KthMostFrequentElements {
    public static void main(String[] args) {
        int[] nums = new int[]{4, 4, 4, 5, 5, 6, 7, 7, 7, 7};
        int k = 2;
        System.out.println("The K most Frequent Elements in the array are "
                + Arrays.toString(solve(nums, k)));
    }

    public static int[] solve(int[] nums, int k) {
        List<Integer> list = new ArrayList<>();

        /**
         * Step 1: Count frequencies of each number
         * Example:
         * nums = [4,4,4,5,5,6,7,7,7,7]
         * Frequency map will look like:
         * {4=3, 5=2, 6=1, 7=4}
         */
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        /**
         * Step 2: Create a max-heap (priority queue)
         * Comparator: higher frequency elements should come first.
         * In Java, PriorityQueue is min-heap by default,
         * so we reverse the order: b.getValue() - a.getValue()
         */
        PriorityQueue<Map.Entry<Integer, Integer>> heap =
                new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());

        // Step 3: Push all map entries into the heap
        heap.addAll(map.entrySet());

        /**
         * Step 4: Extract top k elements from the heap
         * Each poll() removes the entry with the highest frequency.
         * We add its key (the number itself) to the result list.
         */
        while (!heap.isEmpty() && k > 0) {
            list.add(heap.poll().getKey());
            k--;
        }

        /**
         * Step 5: Convert the List<Integer> to int[]
         * Final result: array of top-k frequent numbers
         */
        return list.stream().mapToInt(Integer::intValue).toArray();
    }
}