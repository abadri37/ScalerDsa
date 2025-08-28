package Tuf.Day7.LinkedListArrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {
    public static void main(String[] args) {
        int[] nums = {-1, 0, 1, 2, -1, -4};

        // Call threeSum to find all unique triplets that sum to 0
        List<List<Integer>> result = threeSum(nums);

        System.out.println("The corresponding values are ");
        for (List<Integer> list : result) {
            System.out.println(list);
        }
    }

    // Returns all unique triplets [nums[i], nums[j], nums[k]] such that sum = 0
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

        // Step 1: Sort the array for two-pointer approach
        Arrays.sort(nums);

        // Step 2: Iterate through the array, fixing one element nums[i]
        for (int i = 0; i < nums.length - 2; i++) {
            // Skip duplicate values for nums[i]
            if (i > 0 && (nums[i] == nums[i - 1])) {
                continue;
            }

            // Two-pointer approach: j (left), k (right)
            int j = i + 1;
            int k = nums.length - 1;

            // Step 3: Find pairs nums[j], nums[k] such that nums[i] + nums[j] + nums[k] == 0
            while (j < k) {
                int ret = nums[i] + nums[j] + nums[k];

                if (ret == 0) {
                    // Found a valid triplet
                    result.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    j++;
                    k--;

                    // Skip duplicate values for nums[j] and nums[k]
                    while (j < k && nums[j] == nums[j - 1]) {
                        j++;
                    }
                    while (j < k && nums[k] == nums[k + 1]) {
                        k--;
                    }
                } else if (ret < 0) {
                    j++; // Increase sum by moving left pointer
                } else {
                    k--; // Decrease sum by moving right pointer
                }
            }
        }
        return result;
    }
}