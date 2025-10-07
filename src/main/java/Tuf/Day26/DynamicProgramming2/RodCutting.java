package Tuf.Day26.DynamicProgramming2;

import java.util.Arrays;

public class RodCutting {

    public static void main(String[] args) {
        int[] price = {1, 6, 8, 9, 10, 19, 7, 20};
        int N = 8;

        // Question 1: What is the maximum profit we can get from rod of length N?
        System.out.println("Maximum profit: " + maxProfit(price, N));

        // Question 2: How does the recursion tree look for N = 8?
        // Answer: We try every possible first cut (1..8) and recursively solve the remaining rod.
    }

    /**
     * Top-down DP (recursion + memoization) to solve Rod Cutting
     * @param price : price[i] is price of rod of length i+1
     * @param n     : current rod length
     * @return max profit for rod of length n
     */
    public static int maxProfit(int[] price, int n) {
        // dp[i] will store the maximum profit for rod of length i
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1); // -1 means not computed yet
        return solve(price, dp, n);
    }

    /**
     * Recursive function to find max profit
     * @param price : price array
     * @param dp    : memoization array
     * @param n     : current rod length
     * @return max profit
     */
    public static int solve(int[] price, int[] dp, int n) {
        if (n == 0) return 0; // Base case: rod of length 0 has profit 0

        if (dp[n] != -1) return dp[n]; // Return already computed value

        int maxVal = 0;

        // Try every possible first cut (length 1..n)
        for (int i = 1; i <= n; i++) {
            // Profit = price of first piece + max profit from remaining rod
            int currentVal = price[i - 1] + solve(price, dp, n - i);
            maxVal = Math.max(maxVal, currentVal);
        }

        dp[n] = maxVal; // Store result for memoization
        return maxVal;
    }
}