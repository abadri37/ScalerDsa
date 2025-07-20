package DSA1.Array.Day4;

import java.util.HashMap;
import java.util.Map;

public class TwoSum {
    public static void main(String[] args) {
        int[] nums = new int[]{2, 7, 11, 15};
        int target = 9;
        int[] result = twoSum(nums, target);
        System.out.println("The indices that add to the total target of " + target + " are: " + result[0] + " and " + result[1]);
    }

    /**
     * This method returns the indices of the two numbers in the array
     * that add up to the given target.
     */
    public static int[] twoSum(int[] nums, int target) {
        // Result array to store the two indices
        int[] result = new int[2];

        // HashMap to store number -> index mapping
        Map<Integer, Integer> map = new HashMap<>();

        // Traverse through the array
        for (int i = 0; i < nums.length; i++) {
            // Calculate the number needed to reach the target
            int complement = target - nums[i];

            // If the complement exists in the map, we've found our answer
            if (map.containsKey(complement)) {
                result[0] = map.get(complement); // index of complement
                result[1] = i;                   // current index
                return result;
            }

            // If not found, store the current number and its index
            map.put(nums[i], i);
        }

        // Return empty result if no such pair is found (although the problem usually guarantees a solution)
        return result;
    }
}
