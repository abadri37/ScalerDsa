package DSA2.Recursion.Day9;

import java.util.ArrayList;
import java.util.List;

public class SubsetSum {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4};  // Input array
        int target = 6;

        List<List<Integer>> results = new ArrayList<>();
        List<Integer> list = new ArrayList<>();

        // Q: Why do we start with index=0 and an empty list?
        subsetSum(nums, 0, list, results);

        // Iterate over all subsets and check if their sum matches target
        for (List<Integer> result : results) {
            int sum = 0;
            for (Integer in : result) {
                sum += in;
            }

            // Q: Why do we check the sum only here and not inside recursion?
            if (sum == target) {
                System.out.println("The total sum available in a list is " + result);
            }
        }
    }

    // Recursive function to generate all subsets
    public static void subsetSum(int[] nums, int index, List<Integer> list, List<List<Integer>> results) {
        // Base case: if index reaches end, store the subset
        if (index == nums.length) {
            results.add(new ArrayList<>(list));
            return;
        }

        // ✅ Choice 1: include current element
        list.add(nums[index]);
        subsetSum(nums, index + 1, list, results);

        // ✅ Backtrack (remove last element)
        list.remove(list.size() - 1);

        // ✅ Choice 2: exclude current element
        subsetSum(nums, index + 1, list, results);
    }
}