package DSA1.Array.Day2;

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
 * The array may be unsorted.
 */

public class RepeatAndMissingNumber {
    public static void main(String[] args) {
        // Input: One number is repeated, and one is missing from [1, 20]
        int[] nums = {
                12, 7, 4, 14, 5, 18, 1, 16, 9, 2,
                6, 8, 13, 10, 17, 11, 3, 20, 5, 19
        };

        // 🔁 Using Floyd's Cycle Detection Algorithm (Tortoise and Hare) to find the repeating number
        int slow = nums[0];
        int fast = nums[0];

        // Phase 1: Find the intersection point in the cycle
        do {
            // Subtracting 1 because array is 0-based but values are 1-based (i.e., in range [1, n])
            slow = nums[slow - 1];
            fast = nums[nums[fast - 1] - 1];
        } while (slow != fast);

        // Phase 2: Find the entrance of the cycle (which is the repeating number)
        slow = nums[0];
        while (slow != fast) {
            slow = nums[slow - 1];
            fast = nums[fast - 1];
        }

        int repeatingNumber = slow;

        // 🧮 Finding the missing number using sum difference
        int totalSum = 0;
        for (int num : nums) {
            totalSum += num;
        }

        int n = nums.length;
        int expectedSum = (n * (n + 1)) / 2;

        // missing = expectedSum - (actualSum - duplicate)
        int missingNumber = expectedSum - (totalSum - repeatingNumber);

        // 🖨 Output the results
        System.out.println("The Repeating Number is " + repeatingNumber);
        System.out.println("The Missing Number is " + missingNumber);
    }
}
