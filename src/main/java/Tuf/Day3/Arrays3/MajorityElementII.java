package Tuf.Day3.Arrays3;

import java.util.ArrayList;
import java.util.List;

public class MajorityElementII {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 1, 1, 3, 3, 2, 2, 2};
        System.out.println("The n/3 Majority elements are " + majorityElement(nums));
    }

    // Question:
    // Find all elements in the array that appear more than ⌊n/3⌋ times.
    // Constraint: You must solve it in O(n) time and O(1) space (excluding output list).

    // Algorithm used: Extended Moore’s Voting Algorithm

    public static List<Integer> majorityElement(int[] nums) {
        List<Integer> results = new ArrayList<>();

        // Step 1: Identify potential candidates that could occur more than n/3 times.
        // At most, there can be only 2 such elements.
        int candidate1 = -1;
        int count1 = 0;

        int candidate2 = -1;
        int count2 = 0;

        // Traverse the array to find potential candidates.
        for (int i = 0; i < nums.length; i++) {
            if (candidate1 == nums[i]) {
                // If current number is same as candidate1, increase its count.
                count1 += 1;
            } else if (candidate2 == nums[i]) {
                // If current number is same as candidate2, increase its count.
                count2 += 1;
            } else if (count1 == 0) {
                // If count1 is zero, assign current number as candidate1.
                candidate1 = nums[i];
                count1 = 1;
            } else if (count2 == 0) {
                // If count2 is zero, assign current number as candidate2.
                candidate2 = nums[i];
                count2 = 1;
            } else {
                // If current number doesn't match any candidate and both counts are non-zero,
                // reduce both counts.
                count1--;
                count2--;
            }
        }

        // Step 2: Count the actual frequency of candidate1 and candidate2
        // to verify if they appear more than n/3 times.
        count1 = 0;
        count2 = 0;

        for (int n : nums) {
            if (n == candidate1) {
                count1++;
            } else if (n == candidate2) {
                count2++;
            }
        }

        // Step 3: Add the valid candidates to the result list.
        int threshold = nums.length / 3;

        if (count1 > threshold) {
            results.add(candidate1);
        }

        if (count2 > threshold) {
            results.add(candidate2);
        }

        return results;
    }
}
