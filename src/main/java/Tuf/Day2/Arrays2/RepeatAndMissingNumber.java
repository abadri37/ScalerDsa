package Tuf.Day2.Arrays2;

/**
 * 🔷 Problem Statement:
 * Given an integer array `nums` of size `n` containing numbers from `1` to `n`,
 * where:
 * - One number `A` appears **twice**
 * - One number `B` is **missing**
 *
 * Return the values A and B as an array: [A, B], where:
 * - A is the number that repeats
 * - B is the number that is missing
 *
 * Example:
 * Input: [3, 1, 2, 5, 3]
 * Output: [3, 4] → 3 is repeated, 4 is missing
 */

public class RepeatAndMissingNumber {
    public static void main(String[] args) {

        // ✅ Input array of numbers from 1 to 20
        //    One number (5) is repeated, and one number is missing.
        int[] nums = {
                12, 7, 4, 14, 5, 18, 1, 16, 9, 2,
                6, 8, 13, 10, 17, 11, 3, 20, 5, 19
        };

        // ===========================================================
        // 🌀 Step 1: Detect the repeating number using
        //            Floyd’s Cycle Detection Algorithm
        // ===========================================================
        // Treat array values as "pointers" to the next index.
        // Example: nums[i] → next index = nums[i] - 1
        // This creates a cycle where the duplicate number causes a loop.

        int slow = nums[0]; // Tortoise pointer (moves 1 step)
        int fast = nums[0]; // Hare pointer (moves 2 steps)

        // -----------------------------------------------------------
        // Phase 1: Move slow and fast until they meet (cycle detected)
        // -----------------------------------------------------------
        do {
            // Move slow one step ahead
            slow = nums[slow - 1];

            // Move fast two steps ahead
            fast = nums[nums[fast - 1] - 1];
        } while (slow != fast); // Continue until both meet in the cycle

        // -----------------------------------------------------------
        // Phase 2: Find the start point of the cycle (duplicate number)
        // -----------------------------------------------------------
        slow = nums[0]; // Reset slow to start
        while (slow != fast) {
            slow = nums[slow - 1]; // Move both one step at a time
            fast = nums[fast - 1];
        }

        int repeatingNumber = slow; // Both meet at the repeating number

        // ===========================================================
        // 🧮 Step 2: Find the missing number using mathematical formula
        // ===========================================================
        // Formula:
        //    missing = expectedSum - (actualSum - duplicate)
        // Explanation:
        //    If one number repeats, total sum increases by (duplicate - missing).
        //    So we can rearrange to get the missing number.

        int totalSum = 0; // To store the actual sum of array elements
        for (int num : nums) {
            totalSum += num;
        }

        int n = nums.length; // n = size of array = 20 here

        // Expected sum of first n natural numbers: (n * (n + 1)) / 2
        int expectedSum = (n * (n + 1)) / 2;

        // Derive the missing number from the difference
        int missingNumber = expectedSum - (totalSum - repeatingNumber);

        // ===========================================================
        // 🖨 Step 3: Print the results
        // ===========================================================
        System.out.println("The Repeating Number is " + repeatingNumber);
        System.out.println("The Missing Number is " + missingNumber);
    }
}