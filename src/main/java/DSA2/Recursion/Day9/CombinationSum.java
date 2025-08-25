package DSA2.Recursion.Day9;

import java.util.ArrayList;
import java.util.List;

public class CombinationSum {

    public static void main(String[] args) {
        List<List<Integer>> result = new ArrayList<>();

        // ❓ What does this input mean?
        // A set of distinct integers where you can reuse elements
        int[] nums = {2, 3, 6, 7};
        int target = 7;

        // ❓ Why start with index 0 and an empty path?
        // We explore from index 0 and build combinations from scratch
        generate(nums, 0, target, new ArrayList<>(), result);

        // ❓ What does this loop do?
        // Prints each valid combination that sums to the target
        for (List<Integer> list : result) {
            System.out.println(list);
        }
    }

    /**
     * Recursively generates all combinations that sum to the given target.
     *
     * @param nums   The list of candidate numbers
     * @param index  The current index in the array (used to prevent backward traversal)
     * @param target The remaining target to be achieved
     * @param values The current combination being built
     * @param result The master list to store all valid combinations
     */
    public static void generate(int[] nums, int index, int target, List<Integer> values, List<List<Integer>> result) {

        // ❓ When do we add a combination to the result?
        // When the remaining target becomes exactly 0
        if (target == 0) {
            result.add(new ArrayList<>(values)); // Deep copy because `values` is reused
            return;
        }

        // ❓ Why do we loop from `index` to end?
        // To avoid using earlier elements again (and allow reuse of the same element via recursion)
        for (int i = index; i < nums.length; i++) {

            // ❓ Why only proceed if nums[i] <= target?
            // If nums[i] > target, including it would overshoot the target
            if (nums[i] <= target) {
                values.add(nums[i]); // Include the current element

                // ❓ Why do we pass `i` again (not i + 1)?
                // Because the same number can be chosen multiple times
                generate(nums, i, target - nums[i], values, result);

                // ❓ What is backtracking?
                // We remove the last added element and explore the next option
                values.remove(values.size() - 1);
            }
        }
    }
}