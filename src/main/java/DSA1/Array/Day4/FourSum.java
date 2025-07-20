package DSA1.Array.Day4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * ✅ Problem: Four Sum (LeetCode 18)
 * --------------------------------------
 * Given an array of integers `nums` and an integer `target`, return all unique quadruplets
 * [nums[a], nums[b], nums[c], nums[d]] such that:
 *      nums[a] + nums[b] + nums[c] + nums[d] == target
 *
 * Constraints:
 * - The solution set must not contain duplicate quadruplets.
 * - Elements in the output can be returned in any order.
 *
 * 🧠 Approach:
 * - Sort the array
 * - Fix the first two elements using two loops (i and j)
 * - Use two pointers (left and right) to find the remaining two elements
 * - Skip duplicates at all stages
 * - Avoid integer overflow using long for the sum calculation
 *
 * 📦 Time Complexity: O(n^3)
 *     - Outer loop (i): O(n)
 *     - Second loop (j): O(n)
 *     - Two-pointer scan: O(n)
 *     -> Total: O(n^3)
 *
 * 🧠 Space Complexity: O(1) (excluding output)
 */
public class FourSum {

    public static void main(String[] args) {
        // 🔢 Input array
        int[] nums = {-3, -1, 0, 2, 4, 5};
        int target = 2;

        // ✅ Output: [[-3, -1, 2, 4]]
        System.out.println("The Four Sum is " + fourSum(nums, target));
    }

    public static List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();

        // ✅ Step 1: Sort the array to handle duplicates and enable two-pointer technique
        Arrays.sort(nums);

        // ✅ Step 2: Fix the first element (i)
        for (int i = 0; i < nums.length - 3; i++) {

            // Skip duplicate values for i
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            // ✅ Step 3: Fix the second element (j)
            for (int j = i + 1; j < nums.length - 2; j++) {

                // Skip duplicate values for j
                if (j > i + 1 && nums[j] == nums[j - 1]) continue;

                // ✅ Step 4: Use two pointers from both ends to find remaining two numbers
                int left = j + 1;
                int right = nums.length - 1;

                while (left < right) {
                    // Use long to prevent integer overflow
                    long sum = (long) nums[i] + nums[j] + nums[left] + nums[right];

                    if (sum == target) {
                        // ✅ Found a valid quadruplet
                        result.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));

                        // Skip duplicates for left pointer
                        while (left < right && nums[left] == nums[left + 1]) left++;
                        // Skip duplicates for right pointer
                        while (left < right && nums[right] == nums[right - 1]) right--;

                        // Move both pointers after processing the valid combination
                        left++;
                        right--;

                    } else if (sum < target) {
                        // If sum is less than target, move left to increase sum
                        left++;
                    } else {
                        // If sum is greater than target, move right to decrease sum
                        right--;
                    }
                }
            }
        }

        return result;
    }
}
