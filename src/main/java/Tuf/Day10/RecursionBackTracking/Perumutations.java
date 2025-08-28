package Tuf.Day10.RecursionBackTracking;

import java.util.ArrayList;
import java.util.List;

public class Perumutations {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3};

        // Q: Given an array of distinct integers, generate all possible permutations.
        // Example: nums = [1,2,3] →
        // Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]

        List<List<Integer>> results = new ArrayList<>();
        generate(nums, new ArrayList<>(), results);

        // print all generated permutations
        for (List<Integer> list : results) {
            System.out.println(list);
        }
    }

    // Recursive method to generate permutations
    public static void generate(int[] nums, List<Integer> list, List<List<Integer>> result) {
        // base case: if current permutation length == nums length → store it
        if (list.size() == nums.length) {
            result.add(new ArrayList<>(list));
            return;
        }

        // try placing each number at the current position
        for (int i = 0; i < nums.length; i++) {
            if (list.contains(nums[i])) {
                continue; // skip already used numbers
            }

            list.add(nums[i]); // choose
            generate(nums, list, result); // explore further
            list.remove(list.size() - 1); // backtrack
        }
    }
}