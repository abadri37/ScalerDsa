package Tuf.Day25.DynamicProgramming1;

public class MaximumSumIncreasingSubSequence {

    // Function to find the maximum sum of increasing subsequence
    public int MaximumSumIncreasingSubSequence(int[] arr, int n) {

        // dp[i] will store the maximum sum of increasing subsequence ending at index i
        int[] dp = new int[n];

        // Initialize dp[i] with arr[i] itself
        // Minimum sum for a subsequence ending at i is the element itself
        for (int i = 0; i < n; i++) {
            dp[i] = arr[i];
        }

        // Compute dp[i] for all indices i
        // Check all previous elements j < i
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                // If arr[i] can extend the increasing subsequence ending at arr[j]
                // and results in a larger sum, update dp[i]
                if (arr[i] > arr[j] && dp[i] < dp[j] + arr[i]) {
                    dp[i] = dp[j] + arr[i];
                }
            }
        }

        // Find the maximum sum among all subsequences
        int maxSum = 0;
        for (int sum : dp) {
            maxSum = Math.max(maxSum, sum);
        }

        // Return the maximum sum of increasing subsequence
        return maxSum;
    }

    public static void main(String[] args) {
        // Hardcoded input array for testing
        int[] arr = {1, 101, 2, 3, 100};
        int n = arr.length;

        MaximumSumIncreasingSubSequence msis = new MaximumSumIncreasingSubSequence();
        int maxSum = msis.MaximumSumIncreasingSubSequence(arr, n);

        System.out.println("Maximum Sum Increasing Subsequence: " + maxSum);
    }
}