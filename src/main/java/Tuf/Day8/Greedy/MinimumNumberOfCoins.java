package Tuf.Day8.Greedy;

import java.util.Arrays;

public class MinimumNumberOfCoins {
    public static void main(String[] args) {
        int[][] coinsList = {
                {1, 2, 5},     // Test 1 → possible (expected 3: 5+5+1)
                {2, 5},        // Test 2 → not possible for 3
                {4, 3, 1},     // Test 3 → possible (expected 2: 3+3)
                {1, 7, 10},    // Test 4 → possible (expected 2: 7+7 or 10+4*1)
                {5},           // Test 5 → possible (expected 4: 5+5+5+5)
                {7}            // Test 6 → not possible for 10
        };

        int[] amounts = {11, 3, 6, 14, 20, 10};

        for (int t = 0; t < coinsList.length; t++) {
            int[] coins = coinsList[t];
            int amount = amounts[t];
            int result = coinChangeGreedy(coins, amount);

            System.out.print("Test " + (t + 1) + " → coins = ");
            printArray(coins);
            System.out.println(", amount = " + amount);
            System.out.println("Output (fewest coins): " + result);
            System.out.println("-------------------------------");
        }
    }

    /**
     * Finds the minimum number of coins required to make a given sum
     * using a DP (bottom-up) approach.
     *
     * dp[i] = minimum number of coins required to make amount "i"
     */
    public static int coinChangeGreedy(int[] coins, int sum) {
        // DP array of size (sum+1), since we need values from 0 → sum
        int[] dp = new int[sum + 1];

        // Fill with "infinity" (MAX_VALUE) because we want to minimize later
        Arrays.fill(dp, Integer.MAX_VALUE);

        // Base case: 0 coins are needed to make sum 0
        dp[0] = 0;

        // Process each coin
        for (int coin : coins) {
            // Start from 'coin' because smaller amounts cannot include this coin
            for (int i = coin; i <= sum; i++) {
                // Only update if dp[i - coin] is valid (not infinity)
                if (dp[i - coin] != Integer.MAX_VALUE) {
                    // Recurrence relation:
                    // Either keep existing dp[i], OR use this coin (+1 coin count)
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }

        // If dp[sum] is still MAX_VALUE → not possible to form sum
        return dp[sum] == Integer.MAX_VALUE ? -1 : dp[sum];
    }

    // Helper method to print array
    private static void printArray(int[] arr) {
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) System.out.print(", ");
        }
        System.out.print("]");
    }
}