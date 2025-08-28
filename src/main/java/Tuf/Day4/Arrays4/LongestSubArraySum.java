package Tuf.Day4.Arrays4;

import java.util.HashMap;
import java.util.Map;

/**
 * ✅ Problem: Longest Subarray with Given Sum (for positive and mixed numbers)
 *
 * 🔸 Given an array of integers and a target value, return the length of the longest
 *     contiguous subarray whose sum is equal to the target.
 *
 * 🔸 Use a prefix sum approach with a HashMap to optimize for O(n) time complexity.
 *
 * 📌 Example:
 * Input: nums = [1, 2, 3, 1, 1, 1, 1], target = 5
 * Output: 4
 * Explanation: The longest subarray that sums to 5 is [2, 3], [3, 1, 1], or [1, 1, 1, 2]
 *              The longest one is of length 4 → [1, 1, 1, 2]
 */

public class LongestSubArraySum {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 1, 1, 1, 1};
        int target = 5;
        System.out.println("The Longest Sub Array Sum is " + subArraySum(nums, target));
    }

    public static int subArraySum(int[] nums, int target) {
        // HashMap to store the prefix sum and the earliest index at which it occurred
        Map<Integer, Integer> map = new HashMap<>();

        int maxLength = 0;  // To track the length of the longest subarray
        int sum = 0;        // Running sum of elements

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];  // Update the prefix sum

            // Case 1: If sum from index 0 to i equals the target
            if (sum == target) {
                maxLength = i + 1;
            }

            // Case 2: If (sum - target) is already in the map,
            // it means a subarray exists from map.get(sum - target) + 1 to i with sum = target
            if (map.containsKey(sum - target)) {
                maxLength = Math.max(maxLength, i - map.get(sum - target));
            }

            // Store the first occurrence of this sum
            // We only store it if it's not present to ensure longest subarray
            if (!map.containsKey(sum)) {
                map.put(sum, i);
            }
        }

        return maxLength;
    }
}
