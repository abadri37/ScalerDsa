package Tuf.Day13.StacksQueues1;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * LeetCode 496: Next Greater Element I
 *
 * Given two arrays:
 *   nums1 = subset of nums2
 * For each element in nums1, find the next greater element in nums2.
 * If it doesn't exist, return -1 for that number.
 *
 * Example:
 *   nums1 = [4,1,2]
 *   nums2 = [1,3,4,2]
 *   Output: [-1, 3, -1]
 *   Explanation:
 *     - For 4 → next greater element in nums2 = none → -1
 *     - For 1 → next greater = 3
 *     - For 2 → next greater = none → -1
 */
public class NextGreaterElement {

    public int[] nextGreaterElement(int[] nums1, int[] nums2) {

        // Result array to store the next greater elements for nums1
        int[] ret = new int[nums1.length];

        // Map to store next greater element for each number in nums2
        // Key: number, Value: its next greater number
        Map<Integer, Integer> map = new HashMap<>();

        // Stack to help find the next greater elements in nums2
        // We will maintain a decreasing stack
        Stack<Integer> stack = new Stack<>();

        /**
         * Step 1: Traverse nums2 and fill the map.
         *
         * Q: Why nums2 and not nums1?
         * A: Because nums1 is a subset of nums2 — we can find relationships
         *    (next greater elements) only by scanning the larger array (nums2).
         *
         * We use a monotonic decreasing stack:
         *  - While the stack is not empty and current element > stack top,
         *    current element is the next greater element of the stack top.
         */
        for (int i = 0; i < nums2.length; i++) {
            // Keep popping until current element is smaller than stack top
            while (!stack.isEmpty() && stack.peek() < nums2[i]) {
                // Map the popped number to its next greater number
                map.put(stack.pop(), nums2[i]);
            }

            // Push current element for future comparison
            stack.push(nums2[i]);
        }

        /**
         * Step 2: Build the result array for nums1.
         *
         * For each number in nums1:
         *  - If present in map → use mapped next greater number
         *  - Else → assign -1
         *
         * Q: Why check map for each nums1 element?
         * A: Because map contains next greater elements for all numbers in nums2,
         *    and nums1 is guaranteed to be a subset of nums2.
         */
        for (int i = 0; i < nums1.length; i++) {
            if (map.containsKey(nums1[i])) {
                ret[i] = map.get(nums1[i]);
            } else {
                ret[i] = -1;
            }
        }

        return ret;
    }

    /**
     * Main method for quick testing.
     */
    public static void main(String[] args) {
        NextGreaterElement nge = new NextGreaterElement();

        // 🧪 Example 1:
        int[] nums1 = {4, 1, 2};
        int[] nums2 = {1, 3, 4, 2};
        int[] result = nge.nextGreaterElement(nums1, nums2);

        // Expected Output: [-1, 3, -1]
        System.out.print("Next Greater Elements: [");
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i]);
            if (i < result.length - 1) System.out.print(", ");
        }
        System.out.println("]");

        // 🧪 Example 2:
        int[] nums3 = {2, 4};
        int[] nums4 = {1, 2, 3, 4};
        // Expected Output: [3, -1]
        int[] result2 = nge.nextGreaterElement(nums3, nums4);
        System.out.print("Next Greater Elements: [");
        for (int i = 0; i < result2.length; i++) {
            System.out.print(result2[i]);
            if (i < result2.length - 1) System.out.print(", ");
        }
        System.out.println("]");
    }
}