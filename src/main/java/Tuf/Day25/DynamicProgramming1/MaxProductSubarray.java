package Tuf.Day25.DynamicProgramming1;

public class MaxProductSubarray {

    /**
     * Function to find the maximum product subarray
     * @param nums input array of integers (can be negative or positive)
     * @return maximum product of any contiguous subarray
     */
    public int maxProduct(int[] nums) {
        // Initialize current maximum, minimum, and result to first element
        // curMax = max product ending at current index
        // curMin = min product ending at current index (needed for negative numbers)
        int curMax = nums[0];
        int curMin = nums[0];
        int result = nums[0];

        // Iterate through the array starting from index 1
        for (int i = 1; i < nums.length; i++) {
            int tempMax = curMax;
            int tempMin = curMin;

            // Update curMax and curMin
            // curMax can be either:
            // 1. Current element itself
            // 2. Current element * previous curMax
            // 3. Current element * previous curMin (in case of negative number)
            curMax = Math.max(nums[i], Math.max(nums[i] * tempMax, nums[i] * tempMin));

            // curMin is updated similarly to track the minimum product
            curMin = Math.min(nums[i], Math.min(nums[i] * tempMax, nums[i] * tempMin));

            // Update result with the maximum product so far
            result = Math.max(result, curMax);
        }

        return result;
    }

    // Main method for testing
    public static void main(String[] args) {
        MaxProductSubarray mps = new MaxProductSubarray();

        // Test input 1
        int[] nums1 = {2, 3, -2, 4};
        System.out.println("Max product of {2,3,-2,4}: " + mps.maxProduct(nums1)); // Output: 6

        // Test input 2
        int[] nums2 = {-2, 0, -1};
        System.out.println("Max product of {-2,0,-1}: " + mps.maxProduct(nums2)); // Output: 0

        // Test input 3
        int[] nums3 = {-2, 3, -4};
        System.out.println("Max product of {-2,3,-4}: " + mps.maxProduct(nums3)); // Output: 24
    }
}