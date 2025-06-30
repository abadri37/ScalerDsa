package DSA2.Recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSumII {
    public static void main(String[] args) {
        List<List<Integer>> result = new ArrayList<>();

        // Input array (may contain duplicates)
        int[] nums = {10, 1, 2, 7, 6, 1, 5};
        int target = 8;

        // Sort the array to bring duplicates together and allow early pruning
        Arrays.sort(nums); // Sorted: [1, 1, 2, 5, 6, 7, 10]

        // Begin recursive combination search
        generate(nums, 0, target, new ArrayList<>(), result);

        // Print all unique combinations that sum to the target
        for (List<Integer> list : result) {
            System.out.println(list);
        }
    }

    /**
     * Recursive method to generate all unique combinations that sum up to the target.
     *
     * @param nums    Sorted array of candidate numbers (may contain duplicates)
     * @param index   Current index in the array to explore from
     * @param target  Remaining target to achieve
     * @param values  Current combination being formed
     * @param result  List to collect all valid unique combinations
     */
    public static void generate(int[] nums, int index, int target, List<Integer> values, List<List<Integer>> result) {
        // ✅ Base case: If the target becomes 0, we found a valid combination
        if (target == 0) {
            result.add(new ArrayList<>(values)); // Add a copy of the current path
            return;
        }

        // Loop through the numbers starting from the current index
        for (int i = index; i < nums.length; i++) {

            // ❌ Skip duplicates at the same recursion level
            if (i > index && nums[i] == nums[i - 1]) {
                continue;
            }

            // 🚫 If the current number exceeds the remaining target, break (no need to continue further)
            if (nums[i] > target) {
                break;
            }

            // ✅ Include the current number in the combination
            values.add(nums[i]);

            // 🔁 Move to the next index (i + 1) to ensure each number is used only once
            generate(nums, i + 1, target - nums[i], values, result);

            // ↩️ Backtrack: remove the last added number to explore other combinations
            values.remove(values.size() - 1);
        }
    }
}
