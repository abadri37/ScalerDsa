package Tuf.Day25.DynamicProgramming1;

import java.util.Arrays;

public class LongestIncreasingSubSequence {

    /**
     * Function to find the length of the Longest Increasing Subsequence
     * @param nums input array of integers
     * @return length of the longest strictly increasing subsequence
     */
    public int lengthOfLIS(int[] nums) {
        // dp[i] will store the length of the longest increasing subsequence ending at index i
        int[] dp = new int[nums.length];

        // Initially, each element is a subsequence of length 1
        Arrays.fill(dp, 1);

        // result will keep track of the maximum LIS length found so far
        int result = dp[0];

        // Iterate through the array to build dp[]
        for (int i = 1; i < nums.length; i++) {
            // Check all previous elements before index i
            for (int j = 0; j < i; j++) {
                // If nums[i] can extend the increasing subsequence ending at nums[j]
                if (nums[i] > nums[j]) {
                    // Update dp[i] if extending gives a longer subsequence
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            // Update the global maximum LIS length
            result = Math.max(dp[i], result);
        }

        return result;
    }

    // Optional main method for testing
    public static void main(String[] args) {
        LongestIncreasingSubSequence lis = new LongestIncreasingSubSequence();

        // Test input 1
        int[] nums1 = {10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println("Length of LIS: " + lis.lengthOfLIS(nums1)); // Output: 4 ([2,3,7,101])

        // Test input 2
        int[] nums2 = {0, 1, 0, 3, 2, 3};
        System.out.println("Length of LIS: " + lis.lengthOfLIS(nums2)); // Output: 4 ([0,1,2,3])
    }
}