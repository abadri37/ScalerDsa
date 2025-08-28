package Tuf.Day4.Arrays4;

import java.util.HashSet;
import java.util.Set;

/**
 * 🧩 Problem: Longest Consecutive Subsequence
 *
 * Given an unsorted array of integers, find the length of the longest sequence
 * of consecutive elements.
 *
 * A consecutive sequence consists of numbers that follow each other with a gap of 1.
 * The elements can appear in any order in the array.
 *
 * ✅ Constraints:
 * - You must solve the problem in O(n) time.
 *
 * 📌 Examples:
 * Input: [100, 4, 200, 1, 3, 2]
 * Output: 4
 * Explanation: The longest consecutive sequence is [1, 2, 3, 4]
 *
 * Input: [0, 3, 7, 2, 5, 8, 4, 6, 0, 1]
 * Output: 9
 * Explanation: The longest consecutive sequence is [0, 1, 2, 3, 4, 5, 6, 7, 8]
 */

public class LongestConsecutiveSubsequence {
    public static void main(String[] args) {
        int[] nums = new int[]{0, 3, 7, 2, 5, 8, 4, 6, 0, 1};
        System.out.println("The Longest Consecutive Subsequence is " + longestConsecutive(nums));
    }

    /**
     * This method returns the length of the longest consecutive elements sequence.
     * Time Complexity: O(n), using a HashSet for constant time lookups.
     */
    public static int longestConsecutive(int[] nums) {
        int result = 0;

        // Step 1: Add all numbers to a set to eliminate duplicates and enable O(1) access.
        Set<Integer> set = new HashSet<>();
        for (int n : nums) {
            set.add(n);
        }

        // Step 2: Traverse the set
        for (int num : set) {

            // Start counting only if the number is the beginning of a sequence
            if (!set.contains(num - 1)) {
                int currentNum = num;
                int count = 1;

                // Count all consecutive numbers from currentNum
                while (set.contains(currentNum + 1)) {
                    count++;
                    currentNum++;
                }

                // Track the maximum sequence length found so far
                result = Math.max(result, count);
            }
        }

        return result;
    }
}
