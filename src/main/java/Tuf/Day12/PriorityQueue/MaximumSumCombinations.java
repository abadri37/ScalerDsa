package Tuf.Day12.PriorityQueue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class MaximumSumCombinations {

    public static void main(String[] args) {
        MaximumSumCombinations sol = new MaximumSumCombinations();

        // Example inputs
        int[] nums1 = {4, 2, 5, 1};
        int[] nums2 = {8, 0, 3, 5};
        int k = 3;

        int[] result = sol.maxSumCombinations(nums1, nums2, k);
        System.out.println("Top " + k + " maximum sum combinations: " + Arrays.toString(result));
        // Expected output: [13, 12, 10]
    }

    /**
     * Problem:
     * Given two arrays nums1 and nums2 of equal size n and an integer k,
     * find the top k maximum sums where each sum is of the form nums1[i] + nums2[j].
     *
     * Approach:
     * 1. Sort both arrays in descending order to prioritize larger numbers.
     * 2. Use a max-heap (PriorityQueue) to efficiently get the next largest sum.
     * 3. Keep track of visited index pairs (i,j) to avoid duplicates.
     * 4. Extract the largest sum from heap k times to get top k sums.
     */
    /**
     * Returns the top k maximum sum combinations from two arrays.
     * Each sum is nums1[i] + nums2[j].
     */
    public int[] maxSumCombinations(int[] nums1, int[] nums2, int k) {
        int[] result = new int[k];

        // Edge case checks
        if (nums1.length == 0 || nums2.length == 0 || k == 0 || nums1.length != nums2.length) {
            return result;
        }

        // Sort both arrays in ascending order and then reverse to descending order
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        reverse(nums1);
        reverse(nums2);

        // Max-heap to store sum combinations along with their indices (i,j)
        // Each element in the heap: [sum, i, j]
        PriorityQueue<int[]> heap = new PriorityQueue<>(
                (a, b) -> Integer.compare(b[0], a[0]) // max-heap based on sum
        );

        // Start with the largest possible sum (nums1[0] + nums2[0])
        int[] item = new int[3];
        item[0] = nums1[0] + nums2[0];
        item[1] = 0; // index in nums1
        item[2] = 0; // index in nums2
        heap.add(item);

        // To avoid adding the same pair twice
        Set<String> set = new HashSet<>();
        set.add("0,0");

        int count = 0;

        // Extract top k sums
        while (!heap.isEmpty() && k > 0) {
            int[] value = heap.poll();
            int sum = value[0];
            int i = value[1];
            int j = value[2];

            result[count] = sum;

            // Push next possible sum by moving index in nums1
            if (i + 1 < nums1.length) {
                String key = (i + 1) + "," + j;
                if (!set.contains(key)) {
                    heap.offer(new int[]{nums1[i + 1] + nums2[j], i + 1, j});
                    set.add(key);
                }
            }

            // Push next possible sum by moving index in nums2
            if (j + 1 < nums2.length) {
                String key = i + "," + (j + 1);
                if (!set.contains(key)) {
                    heap.offer(new int[]{nums1[i] + nums2[j + 1], i, j + 1});
                    set.add(key);
                }
            }

            k--;
            count++;
        }

        return result;
    }

    /**
     * Reverses the given array in-place.
     */
    public void reverse(int[] nums) {
        int i = 0;
        int j = nums.length - 1;
        while (i <= j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
            i++;
            j--;
        }
    }
}