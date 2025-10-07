package Tuf.Day26.DynamicProgramming2;

import java.util.Arrays;

/**
 * 🧩 Problem: Coin Change (Minimum Coins)
 * --------------------------------------
 * You are given an array `coins` representing denominations of available coins,
 * and an integer `amount` representing the total value you need to make.
 *
 * Return the *minimum number of coins* needed to make up that amount.
 * If that amount cannot be made up by any combination of the coins, return -1.
 *
 * ⚙️ Example:
 * coins = [1, 2, 5], amount = 11
 * Output: 3  (Explanation: 5 + 5 + 1)
 *
 * coins = [2], amount = 3
 * Output: -1 (Explanation: Cannot make 3 using only coin of 2)
 *
 * --------------------------------------
 * 🧠 INTUITION:
 * --------------------------------------
 * We want to find the *fewest number of coins* to reach every amount up to `amount`.
 *
 * Define:
 *   dp[i] = minimum number of coins needed to make amount i.
 *
 * Base Case:
 *   dp[0] = 0  → 0 coins are needed to make amount 0.
 *
 * Transition (Recurrence):
 *   For each amount i:
 *       For each coin:
 *           If we can use this coin (i - coin >= 0):
 *               dp[i] = min(dp[i], 1 + dp[i - coin])
 *
 *   Meaning:
 *   - We check if using this coin gives us a smaller number of total coins.
 *   - 1 + dp[i - coin] → 1 coin (the one we use now) + best result for remaining amount.
 *
 * At the end:
 *   dp[amount] gives the minimum coins needed.
 *   If dp[amount] remains INF (impossible case), return -1.
 *
 * --------------------------------------
 * 🧮 COMPLEXITY:
 * --------------------------------------
 * Time:  O(amount * coins.length)
 * Space: O(amount)
 */

public class CoinChange {
    public static void main(String[] args) {
        // 🔹 Test Case 1
        int[] coins1 = {1, 2, 5};
        int amount1 = 11;
        System.out.println("Minimum coins required: " + coinChange(coins1, amount1)); // Expected: 3

        // 🔹 Test Case 2
        int[] coins2 = {2};
        int amount2 = 3;
        System.out.println("Minimum coins required: " + coinChange(coins2, amount2)); // Expected: -1

        // 🔹 Test Case 3
        int[] coins3 = {1};
        int amount3 = 0;
        System.out.println("Minimum coins required: " + coinChange(coins3, amount3)); // Expected: 0

        // 🔹 Test Case 4
        int[] coins4 = {1, 3, 4};
        int amount4 = 6;
        System.out.println("Minimum coins required: " + coinChange(coins4, amount4)); // Expected: 2 (3+3 or 2+4)
    }

    public static int coinChange(int[] coins, int amount) {
        // dp[i] → minimum coins required to make amount 'i'
        int[] dp = new int[amount + 1];

        // Initialize with a large value (infinity)
        int INF = amount + 1;
        Arrays.fill(dp, INF);

        // Base case
        dp[0] = 0; // 0 coins to make amount 0

        // Build the dp table
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (i - coin >= 0) {
                    // Choose the minimum between current value and using this coin
                    dp[i] = Math.min(dp[i], 1 + dp[i - coin]);
                }
            }
        }

        // If dp[amount] is still INF, amount cannot be formed
        return dp[amount] == INF ? -1 : dp[amount];
    }
}