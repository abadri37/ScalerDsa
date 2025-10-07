package Tuf.Day26.DynamicProgramming2;

public class SubSetSum {

    /**
     * Function to check if the array can be partitioned into two subsets with equal sum
     * @param nums input array of positive integers
     * @return true if possible, false otherwise
     */
    public boolean canPartition(int[] nums) {
        // Step 1: Calculate total sum of all elements
        int sum = 0;
        for (int i : nums) {
            sum += i;
        }

        // Step 2: If total sum is odd, cannot partition into equal subsets
        if (sum % 2 == 1) {
            return false;
        }

        // Step 3: Create DP table for memoization
        // dp[index][sum] = whether a subset sum of 'sum' is possible starting from index 'index'
        Boolean[][] dp = new Boolean[nums.length][sum / 2 + 1];

        // Step 4: Try to find a subset with sum = total_sum / 2
        return solve(nums, 0, dp, sum / 2);
    }

    /**
     * Recursive helper function with memoization
     * @param nums input array
     * @param index current index
     * @param dp memoization table
     * @param sum remaining sum to find
     * @return true if subset with 'sum' exists starting from index
     */
    public boolean solve(int[] nums, int index, Boolean[][] dp, int sum) {
        // Base case: sum is 0 → subset found
        if (sum == 0) {
            return true;
        }

        // Base case: reached end of array → no subset found
        if (index >= nums.length) {
            return false;
        }

        // If already computed, return memoized value
        if (dp[index][sum] != null) {
            return dp[index][sum];
        }

        // Try including current element if it does not exceed sum
        if (nums[index] <= sum) {
            if (solve(nums, index + 1, dp, sum - nums[index])) {
                dp[index][sum] = true;
                return true;
            }
        }

        // Try excluding current element
        dp[index][sum] = solve(nums, index + 1, dp, sum);
        return dp[index][sum];
    }

    // Main method for testing
    public static void main(String[] args) {
        SubSetSum partition = new SubSetSum();

        // Test input 1
        int[] nums1 = {1, 5, 11, 5};
        System.out.println("Can partition {1,5,11,5}: " + partition.canPartition(nums1)); // true

        // Test input 2
        int[] nums2 = {1, 2, 3, 5};
        System.out.println("Can partition {1,2,3,5}: " + partition.canPartition(nums2)); // false
    }
}