package Tuf.Day11.BinarySearch;

import java.util.Arrays;

/**
 * Problem: Aggressive Cows
 * --------------------------------
 * We are given an array of stalls (nums) where each element represents
 * the position of a stall along a line. We have to place 'totalCows' cows
 * in these stalls such that the minimum distance between any two cows is maximized.
 * <p>
 * Example:
 * nums = [0, 3, 4, 7, 9, 10], totalCows = 3
 * Answer = 4 (Cows can be placed at positions 0, 4, 9)
 * <p>
 * Approach:
 * 1. Sort the stall positions.
 * 2. Use Binary Search on the "answer space" (minimum distance between cows).
 * - low = 1  (minimum possible distance)
 * - high = max(stall) - min(stall)  (maximum possible distance if only 2 cows are placed)
 * 3. Check for each mid distance if it is feasible to place cows with at least 'mid' distance apart.
 * - If feasible → try bigger distance (low = mid + 1)
 * - If not feasible → try smaller distance (high = mid - 1)
 * 4. Keep track of the largest feasible distance (result).
 * <p>
 * Time Complexity:
 * - Sorting stalls: O(N log N)
 * - Binary Search steps: O(log(max(stall) - min(stall)))
 * - Feasibility check in each step: O(N)
 * - Overall: O(N log N + N log(max-min))
 * <p>
 * Space Complexity:
 * - O(1) (ignoring input storage)
 */
public class AggressiveCows {
    public static void main(String[] args) {
        int[] nums = new int[]{0, 3, 4, 7, 9, 10};
        int totalCows = 3;

        Arrays.sort(nums); // Step 1: Sort stall positions

        System.out.println("The maximum minimum distance where cows can be placed is "
                + solve(nums, totalCows));
    }

    /**
     * Binary Search on the "minimum distance" answer space.
     */
    public static int solve(int[] nums, int totalCows) {
        int low = 1;  // minimum possible distance
        int high = nums[nums.length - 1] - nums[0];  // maximum possible distance
        int result = 0;  // to store the answer

        while (low <= high) {
            int mid = (low + high) / 2; // candidate distance

            if (isSafe(nums, mid, totalCows)) {
                // Feasible → store the answer and try for a bigger distance
                result = mid;
                low = mid + 1;
            } else {
                // Not feasible → reduce distance
                high = mid - 1;
            }
        }
        return result;
    }

    /**
     * Greedy feasibility check:
     * Try to place cows such that each cow is at least 'dist' away from the last placed cow.
     */
    public static boolean isSafe(int[] nums, int value, int totalCows) {
        int target = 1;            // place the first cow in the first stall
        int prev = nums[0];        // last placed cow position
        for (int i = 1; i < nums.length; i++) {
            if (prev + value <= nums[i]) {  // check if current stall is at least 'value' apart
                prev = nums[i];             // place cow here
                target++;                   // increase count of cows placed
            }
        }
        return target >= totalCows;         // true if we could place all cows
    }
}
