package Tuf.Day2.Arrays2;

public class FindDuplicateNumber {
    // Leetcode 287: Find the Duplicate Number
    public static void main(String[] args) {
        /*
         * Problem setup:
         * Given an array nums of length n+1 where each element is an integer in the range [1, n],
         * there is only ONE repeated number, but it may appear multiple times.
         * Our goal is to find that duplicate without modifying the array and using constant extra space.
         *
         * Example:
         * nums = [4, 1, 7, 9, 2, 6, 10, 3, 5, 8, 7]
         * n = 10 (since array length = 11 = n + 1)
         */
        int[] nums = {4, 1, 7, 9, 2, 6, 10, 3, 5, 8, 7};

        // Initialize two pointers, slow and fast, both starting from the first element.
        // Concept: Treat array values as "pointers" (index → next index).
        // Since there's one duplicate, it forms a cycle (like a linked list with a loop).
        int slow = nums[0];
        int fast = nums[0];

        /*
         * Phase 1: Detect the cycle
         * --------------------------------
         * - Move slow pointer by one step: slow = nums[slow]
         * - Move fast pointer by two steps: fast = nums[nums[fast]]
         * - Continue until slow == fast (they meet inside the cycle).
         *
         * Why it works:
         * - Think of nums[] as a directed graph where each index points to nums[index].
         * - The duplicate value causes two indices to point to the same next node,
         *   forming a cycle (just like a loop in a linked list).
         */
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);

        /*
         * Phase 2: Find the entrance of the cycle
         * ---------------------------------------
         * - Once both pointers meet, we know we’re inside the cycle.
         * - To find the "entrance" of the cycle (the duplicate number):
         *   → Move one pointer back to the start of the array.
         *   → Keep moving both pointers by one step.
         *   → The point where they meet again is the duplicate value.
         *
         * Reason:
         * - This is equivalent to finding the start of the loop in a linked list.
         */
        slow = nums[0];
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }

        // When both meet, that index/value is the duplicate number.
        System.out.println("The repeating number is " + slow);
    }
}