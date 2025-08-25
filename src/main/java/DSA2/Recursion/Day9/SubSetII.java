package DSA2.Recursion.Day9;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubSetII {

    public static void main(String[] args) {
        List<List<Integer>> result = new ArrayList<>();

        // Input array (may contain duplicates)
        int[] nums = {1, 3, 2, 3, 2};

        // ❓ Why do we need to sort the array before generating subsets?
        Arrays.sort(nums); // nums becomes [1, 2, 2, 3, 3]

        // ❓ Why do we pass an empty ArrayList at the beginning?
        generateSubSet(nums, 0, new ArrayList<>(), result);

        // Print all unique subsets
        for (List<Integer> list : result) {
            System.out.println(list);
        }
    }

    public static void generateSubSet(int[] nums, int index, List<Integer> list, List<List<Integer>> results) {
        // ❓ Why do we add the current list to results at every recursive call?
        results.add(new ArrayList<>(list));

        // Explore all possibilities starting from current index
        for (int i = index; i < nums.length; i++) {

            // ❓ Why do we skip if nums[i] == nums[i - 1] when i > index?
            if (i > index && nums[i] == nums[i - 1]) {
                continue;
            }

            // Choose nums[i]
            list.add(nums[i]);

            // ❓ Why do we call generateSubSet with i + 1 instead of index + 1?
            generateSubSet(nums, i + 1, list, results);

            // Backtrack step: remove last added element
            // ❓ Why is this removal necessary?
            list.remove(list.size() - 1);
        }
    }
}