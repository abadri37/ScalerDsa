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
         *
         * 💭 Why do we need a frequency map?
         *    → To know how many times each number appears.
         *
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
         * Step 2: Use a Min-Heap (PriorityQueue)
         *
         * 💭 Why min-heap and not max-heap?
         *    → We want to keep only the top K elements.
         *      The smallest frequency should be easy to remove.
         *
         * Comparator explanation:
         * (a, b) -> a.value - b.value
         * → sorts by frequency ascending (smallest freq at the top)
         */
        PriorityQueue<Pair> heapPair = new PriorityQueue<>(
                (a, b) -> a.value - b.value
        );

        /**
         * Step 3: Add elements into the heap
         *
         * 💭 What happens when heap size > k?
         *    → We remove the least frequent element (top of the min-heap).
         *
         * This ensures that after processing all entries,
         * the heap only contains the k most frequent elements.
         */
        for (Map.Entry<Integer, Integer> hashMap : map.entrySet()) {
            heapPair.add(new Pair(hashMap.getKey(), hashMap.getValue()));
            if (heapPair.size() > k) {
                heapPair.poll();  // removes the smallest frequency
            }
        }

        /**
         * Step 4: Extract elements from heap
         *
         * 💭 Why do we collect them in a list?
         *    → To easily convert to an array later.
         *
         * 💭 Will the result be in frequency order?
         *    → Not guaranteed; heap pops smallest remaining each time.
         *      You can reverse the list if you want descending order.
         */
        while (!heapPair.isEmpty()) {
            list.add(heapPair.poll().key);
        }

        /**
         * Step 5: Convert the List<Integer> to int[]
         * Final result: array of top-k frequent numbers
         *
         * 💭 Why use stream here?
         *    → Simple, clean conversion from List<Integer> → int[].
         */
        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    /**
     * Helper class to store key-value pairs.
     *
     * 💭 Why not use Map.Entry directly?
     *    → You could, but creating a small custom Pair class
     *      makes it reusable and clearer in the heap logic.
     */
    static class Pair {
        int key;
        int value;

        public Pair(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
}