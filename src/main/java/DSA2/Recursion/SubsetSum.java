package DSA2.Recursion;

import java.util.ArrayList;
import java.util.List;

public class SubsetSum {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4};  // Input array
        int target = 6;             // Target sum to find in subsets

        List<List<Integer>> result = new ArrayList<>();  // To store all generated subsets

        // Generate all subsets of the array
        generateSubSet(nums, 0, new ArrayList<>(), result);

        // Iterate through all subsets and print those with sum equal to target
        for (List<Integer> list : result) {
            int sum = 0;
            for (Integer in : list) {
                sum += in;
            }
            if (sum == target) {
                System.out.println(list); // Print subset if sum equals target
            }
        }
    }

    // Recursive function to generate all subsets
    public static void generateSubSet(int[] nums, int index, List<Integer> list, List<List<Integer>> results) {
        // Base case: if we've considered all elements
        if (index == nums.length) {
            results.add(new ArrayList<>(list)); // Add the current subset to result
            return;
        }

        // Include the current element in the subset
        list.add(nums[index]);
        generateSubSet(nums, index + 1, list, results); // Recurse with inclusion

        // Backtrack: remove the last added element to explore exclusion path
        list.remove(list.size() - 1);

        // Exclude the current element from the subset
        generateSubSet(nums, index + 1, list, results); // Recurse with exclusion
    }
}
