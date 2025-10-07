package Tuf.Day26.DynamicProgramming2;

public class MinimumFallingPathSum {

    /**
     * Problem:
     * ---------
     * Given an n x n integer matrix, find the minimum sum of a falling path from the top row
     * to the bottom row, where you can move:
     *  - directly below (down)
     *  - diagonally left (down-left)
     *  - diagonally right (down-right)
     *
     * Example:
     * matrix = [
     *   [1, 2, 10, 4],
     *   [100, 3, 2, 1],
     *   [1, 1, 20, 2],
     *   [1, 2, 2, 1]
     * ]
     * Output = 6
     *
     * Explanation:
     * Start at (0,0)=1 → down-right (1,1)=3 → down (2,1)=1 → down-left (3,0)=1 → total = 6
     */

    public int minFallingPathSum(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        // dp[i][j] = minimum falling path sum to reach cell (i, j)
        int[][] dp = new int[m][n];

        // Base case: the first row is same as matrix first row
        for (int j = 0; j < n; j++) {
            dp[0][j] = matrix[0][j];
        }

        // Build the dp table row by row
        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {

                // Option 1: coming straight from above
                int up = dp[i - 1][j];

                // Option 2: coming from top-left diagonal
                int leftDiagonal = (j > 0) ? dp[i - 1][j - 1] : Integer.MAX_VALUE;

                // Option 3: coming from top-right diagonal
                int rightDiagonal = (j < n - 1) ? dp[i - 1][j + 1] : Integer.MAX_VALUE;

                // Current cell value + best among the three options
                dp[i][j] = matrix[i][j] + Math.min(up, Math.min(leftDiagonal, rightDiagonal));
            }
        }

        // The answer is the minimum value in the last row
        int minSum = Integer.MAX_VALUE;
        for (int j = 0; j < n; j++) {
            minSum = Math.min(minSum, dp[m - 1][j]);
        }

        return minSum;
    }

    // --------------------------------------------------------------------------
    // 🧠 Intuition:
    // --------------------------------------------------------------------------
    // Each cell (i, j) depends only on the previous row.
    // So we build solutions bottom-up.
    //
    // At every step, we ask:
    //   "If I am standing at matrix[i][j], what was the minimum cost path
    //    to reach here from row 0?"
    //
    // Transition relation:
    //   dp[i][j] = matrix[i][j] + min(
    //       dp[i - 1][j],        // from above
    //       dp[i - 1][j - 1],    // from top-left
    //       dp[i - 1][j + 1]     // from top-right
    //   )
    //
    // Base case:
    //   dp[0][j] = matrix[0][j]
    //
    // Result:
    //   min(dp[m - 1][j]) for all j in [0, n-1]
    //
    // --------------------------------------------------------------------------
    // 🧮 Example walkthrough:
    // --------------------------------------------------------------------------
    // matrix = [
    //   [1, 2, 10, 4],
    //   [100, 3, 2, 1],
    //   [1, 1, 20, 2],
    //   [1, 2, 2, 1]
    // ]
    //
    // Step 1: Initialize dp[0] = [1, 2, 10, 4]
    //
    // Step 2: Compute dp[1]:
    //   dp[1][0] = 100 + min(1, ∞, 2)  = 101
    //   dp[1][1] = 3 + min(1, 2, 10)   = 4
    //   dp[1][2] = 2 + min(2, 10, 4)   = 4
    //   dp[1][3] = 1 + min(10, 4, ∞)   = 5
    //
    // Step 3: dp[2]:
    //   dp[2][0] = 1 + min(101, ∞, 4)  = 5
    //   dp[2][1] = 1 + min(101, 4, 4)  = 5
    //   dp[2][2] = 20 + min(4, 4, 5)   = 24
    //   dp[2][3] = 2 + min(4, 4, ∞)    = 6
    //
    // Step 4: dp[3]:
    //   dp[3][0] = 1 + min(5, ∞, 5)    = 6
    //   dp[3][1] = 2 + min(5, 5, 24)   = 7
    //   dp[3][2] = 2 + min(24, 5, 6)   = 7
    //   dp[3][3] = 1 + min(6, 24, ∞)   = 7
    //
    // Step 5: Answer = min(dp[3]) = 6 ✅
    //
    // --------------------------------------------------------------------------
    // 🕒 Time Complexity:  O(m * n)
    // 💾 Space Complexity: O(m * n)  (can be optimized to O(n))
    // --------------------------------------------------------------------------

    public static void main(String[] args) {
        MinimumFallingPathSum sol = new MinimumFallingPathSum();

        int[][] matrix1 = {
                {1, 2, 10, 4},
                {100, 3, 2, 1},
                {1, 1, 20, 2},
                {1, 2, 2, 1}
        };
        System.out.println(sol.minFallingPathSum(matrix1)); // ✅ Output: 6

        int[][] matrix2 = {
                {1, 4, 3, 1},
                {2, 3, -1, -1},
                {1, 1, -1, 8}
        };
        System.out.println(sol.minFallingPathSum(matrix2)); // ✅ Output: -1
    }
}