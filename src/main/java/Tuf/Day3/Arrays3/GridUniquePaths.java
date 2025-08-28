package Tuf.Day3.Arrays3;

public class GridUniquePaths {

    public static void main(String[] args) {
        int m = 3;  // Number of rows in the grid
        int n = 7;  // Number of columns in the grid

        /*
         * Question:
         * Given a grid of size m x n, a robot starts at the top-left corner (0,0) and needs to reach the bottom-right corner (m-1,n-1).
         * The robot can only move either down or right at any point in time.
         * Return the total number of unique paths the robot can take.
         *
         * Example:
         * Input: m = 3, n = 7
         * Output: 28
         *
         * Follow-up Questions to Ask Yourself:
         * - Can the robot move left or up? (No)
         * - Can we apply recursion or memoization? (Yes)
         * - What subproblems do we solve here? (Number of ways to reach cell (i,j))
         */

        int totalPaths = uniquePaths(m, n);
        System.out.println("Total unique paths from top-left to bottom-right in a " + m + "x" + n + " grid: " + totalPaths);
    }

    public static int uniquePaths(int m, int n) {
        // dp[i][j] will store the number of unique paths to reach cell (i,j)
        int[][] dp = new int[m][n];

        // Step 1: Initialize first column (can only come from the top)
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;  // Only one way to go down vertically
        }

        // Step 2: Initialize first row (can only come from the left)
        for (int j = 0; j < n; j++) {
            dp[0][j] = 1;  // Only one way to go right horizontally
        }

        /*
         * Step 3: Fill the rest of the dp table using bottom-up approach
         * For each cell (i,j), the robot could have come from:
         * - Top cell (i-1, j)
         * - Left cell (i, j-1)
         *
         * So, dp[i][j] = dp[i-1][j] + dp[i][j-1]
         */
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }

        // The bottom-right cell contains the total number of unique paths
        return dp[m - 1][n - 1];
    }
}
