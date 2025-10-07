package Tuf.Day26.DynamicProgramming2;

/**
 * Problem: Egg Dropping Problem (Leetcode 887)
 * ---------------------------------------------
 * You are given n eggs and k floors.
 * Find the minimum number of moves required to determine the highest floor
 * from which an egg can be dropped without breaking.
 *
 * Allowed operations:
 *  - You can drop an egg from any floor.
 *  - If it breaks → you cannot reuse that egg.
 *  - If it doesn’t break → you can reuse it again.
 *
 * ---------------------------------------------
 * Approach: Dynamic Programming (Optimized by moves)
 * ---------------------------------------------
 * Instead of focusing on the number of floors, we focus on the number of moves.
 * Let dp[e][m] = maximum number of floors that can be tested with e eggs and m moves.
 *
 * Recurrence:
 * dp[e][m] = 1 + dp[e-1][m-1] + dp[e][m-1]
 *
 * - If the egg breaks, we can only check floors below → dp[e-1][m-1]
 * - If it doesn’t break, we can check floors above → dp[e][m-1]
 * - Add 1 for the current floor.
 *
 * We increase 'moves' until dp[n][moves] >= k (we can test all floors).
 *
 * ---------------------------------------------
 * Time Complexity: O(n * moves)
 * Space Complexity: O(n * moves)
 *
 * ---------------------------------------------
 * Example:
 * Input:  n = 2, k = 10
 * Output: 4
 * Explanation: Minimum 4 moves required.
 */

public class EggDroppingProblem {

    public static void main(String[] args) {
        EggDroppingProblem obj = new EggDroppingProblem();

        System.out.println("Example 1:");
        System.out.println("Eggs = 1, Floors = 10 → Moves = " + obj.superEggDrop(1, 10)); // Expected 10

        System.out.println("\nExample 2:");
        System.out.println("Eggs = 2, Floors = 10 → Moves = " + obj.superEggDrop(2, 10)); // Expected 4

        System.out.println("\nExample 3:");
        System.out.println("Eggs = 3, Floors = 14 → Moves = " + obj.superEggDrop(3, 14)); // Expected 4

        System.out.println("\nExample 4:");
        System.out.println("Eggs = 3, Floors = 100 → Moves = " + obj.superEggDrop(3, 100)); // Expected 9
    }

    /**
     * @param n number of eggs
     * @param k number of floors
     * @return minimum number of moves required
     */
    public int superEggDrop(int n, int k) {
        // dp[e][m] = maximum number of floors we can test
        int[][] dp = new int[n + 1][k + 1];
        int moves = 0;

        // Keep increasing the number of moves until we can test all k floors
        while (dp[n][moves] < k) {
            moves++;
            for (int eggs = 1; eggs <= n; eggs++) {
                // dp[e][m] = 1 (current floor) + dp[e-1][m-1] (egg breaks)
                //                           + dp[e][m-1] (egg survives)
                dp[eggs][moves] = 1 + dp[eggs - 1][moves - 1] + dp[eggs][moves - 1];
            }
        }

        return moves;
    }
}