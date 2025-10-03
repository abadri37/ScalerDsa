package Tuf.Day25.DynamicProgramming1;

import java.util.Arrays;

public class Knapsack {

    public static void main(String[] args) {
        int[] weight = new int[]{2, 3, 1, 4};
        int[] profits = new int[]{4, 5, 3, 7};
        int capacity = 7;

        // Example 1
        // Items: { (2,4), (3,5), (1,3), (4,7) }
        // Capacity: 7
        // Best choice is items (2,4), (3,5), (1,3) = total profit = 12
        System.out.println("Max Profit = " + solve(weight, profits, capacity));

        // Example 2
        int[] weight2 = {1, 2, 3, 5};
        int[] profits2 = {1, 6, 10, 16};
        int capacity2 = 7;
        // Best choice: items (2,6) + (5,16) = 22
        System.out.println("Max Profit = " + solve(weight2, profits2, capacity2));

        // Example 3
        int[] weight3 = {3, 2, 4};
        int[] profits3 = {8, 5, 6};
        int capacity3 = 5;
        // Best choice: items (3,8) + (2,5) not possible since > capacity,
        // so best is (3,8) alone
        System.out.println("Max Profit = " + solve(weight3, profits3, capacity3));
    }

    // Wrapper method to initialize DP table and start recursion
    public static int solve(int[] weight, int[] profit, int capacity) {
        // dp[i][c] will store the maximum profit we can get by considering
        // items from index i to end, given remaining capacity c
        int[][] dp = new int[profit.length][capacity + 1];

        // Initialize DP with -1 (meaning uncalculated state)
        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], -1);
        }

        return solveKnapsack(weight, profit, capacity, 0, dp);
    }

    /**
     * Recursive function with memoization to solve 0/1 Knapsack.
     *
     * @param weight        array of weights
     * @param profit        array of profits
     * @param capacity      remaining capacity in knapsack
     * @param currentIndex  current item being considered
     * @param dp            memoization table
     * @return maximum profit achievable
     */
    public static int solveKnapsack(int[] weight, int[] profit, int capacity, int currentIndex, int[][] dp) {
        // Base Case 1: if capacity is 0 -> can't take any item
        if (capacity <= 0) {
            return 0;
        }
        // Base Case 2: if no items left -> profit is 0
        if (currentIndex >= profit.length) {
            return 0;
        }

        // If already solved, return from DP table
        if (dp[currentIndex][capacity] != -1) {
            return dp[currentIndex][capacity];
        }

        // Option 1: include the current item (only if it fits in remaining capacity)
        int profit1 = 0;
        if (weight[currentIndex] <= capacity) {
            profit1 = profit[currentIndex] +
                    solveKnapsack(weight, profit,
                            capacity - weight[currentIndex], // reduce capacity
                            currentIndex + 1,               // move to next item
                            dp);
        }

        // Option 2: exclude the current item
        int profit2 = solveKnapsack(weight, profit, capacity, currentIndex + 1, dp);

        // Take the better of the two choices
        dp[currentIndex][capacity] = Math.max(profit1, profit2);

        return dp[currentIndex][capacity];
    }
}