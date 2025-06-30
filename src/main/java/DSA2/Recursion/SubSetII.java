package DSA2.Recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubSetII {
    public static void main(String[] args) {
        List<List<Integer>> result = new ArrayList<>();

        // Input array (may contain duplicates)
        int[] nums = {1, 3, 2, 3, 2};

        // Sort to bring duplicates together (required for skipping)
        Arrays.sort(nums); // nums becomes [1, 2, 2, 3, 3]

        // Start generating subsets from index 0 with empty current list
        generateSubSet(nums, 0, new ArrayList<>(), result);

        // Print all unique subsets
        for (List<Integer> list : result) {
            System.out.println(list);
        }
    }

    /**
     * Recursively generates all unique subsets.
     *
     * @param nums     The sorted input array
     * @param index    The current index in the array to consider
     * @param list     The current subset being built
     * @param results  The final list of all unique subsets
     */
    public static void generateSubSet(int[] nums, int index, List<Integer> list, List<List<Integer>> results) {
        // Add a copy of the current subset to results
        results.add(new ArrayList<>(list));

        // Loop through each element from current index to end
        for (int i = index; i < nums.length; i++) {

            // Skip duplicate elements at the same recursive level
            if (i > index && nums[i] == nums[i - 1]) {
                continue; // Avoid generating duplicate subsets
            }

            // Include the current element
            list.add(nums[i]);

            // Recurse with the next index
            generateSubSet(nums, i + 1, list, results);

            // Backtrack: remove the last added element before exploring the next possibility
            list.remove(list.size() - 1);
        }
    }
}
