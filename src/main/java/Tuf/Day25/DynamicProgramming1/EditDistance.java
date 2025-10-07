package Tuf.Day25.DynamicProgramming1;

import java.util.Arrays;

/**
 * Problem: Edit Distance (Leetcode 72)
 * -------------------------------------
 * Given two strings word1 and word2, find the minimum number of operations
 * required to convert word1 to word2.
 *
 * Allowed operations:
 * 1. Insert a character
 * 2. Delete a character
 * 3. Replace a character
 *
 * Example:
 * ----------
 * Input:  word1 = "horse", word2 = "ros"
 * Output: 3
 *
 * Explanation:
 * horse → rorse (replace 'h' with 'r')
 * rorse → rose  (remove 'r')
 * rose  → ros   (remove 'e')
 * Total operations = 3
 *
 * -------------------------------------
 * Approach:
 * ----------
 * This is a classic Dynamic Programming problem.
 *
 * Idea:
 *  - We use recursion with memoization (Top-Down DP).
 *  - Define a recursive function solve(i, j):
 *        → Minimum edit distance to convert word1[0..i] to word2[0..j]
 *
 *  - We have 3 possible operations if the characters don't match:
 *        1️⃣ Replace → move both i-1, j-1
 *        2️⃣ Insert  → move j-1 (since we match inserted char)
 *        3️⃣ Delete  → move i-1 (since we remove a char from word1)
 *
 *  - If characters match, no operation is needed → move i-1, j-1.
 *
 * Base Cases:
 *  - If i < 0 and j < 0 → both empty strings → 0 operations.
 *  - If i < 0 → word1 is empty → need to insert (j+1) characters.
 *  - If j < 0 → word2 is empty → need to delete (i+1) characters.
 *
 * Memoization:
 *  - We use dp[i][j] to store already computed results to avoid recomputation.
 *
 * Time Complexity: O(m * n)
 * Space Complexity: O(m * n)
 * -------------------------------------
 */

public class EditDistance {

    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();

        // dp[i][j] = minimum operations to convert word1[0..i] → word2[0..j]
        int[][] dp = new int[m][n];
        for (int[] row : dp) Arrays.fill(row, -1);

        // Start from last indices of both strings
        return solve(word1.toCharArray(), word2.toCharArray(), dp, m - 1, n - 1);
    }

    /**
     * Recursive function to calculate edit distance
     */
    private int solve(char[] ch1, char[] ch2, int[][] dp, int i, int j) {
        // Base Case 1: Both strings are empty
        if (i < 0 && j < 0) {
            return 0;
        }

        // Base Case 2: word1 is empty → insert all remaining chars of word2
        if (i < 0) {
            return j + 1;
        }

        // Base Case 3: word2 is empty → delete all remaining chars of word1
        if (j < 0) {
            return i + 1;
        }

        // If result already computed, return it (memoization)
        if (dp[i][j] != -1) {
            return dp[i][j];
        }

        // If characters match, no operation needed → move both pointers
        if (ch1[i] == ch2[j]) {
            return dp[i][j] = solve(ch1, ch2, dp, i - 1, j - 1);
        }

        // Otherwise, try all 3 operations and take the minimum
        int replace = solve(ch1, ch2, dp, i - 1, j - 1); // replace current char
        int insert  = solve(ch1, ch2, dp, i, j - 1);     // insert new char
        int delete  = solve(ch1, ch2, dp, i - 1, j);     // delete existing char

        // Take minimum of three operations and add cost (1 operation)
        return dp[i][j] = 1 + Math.min(replace, Math.min(delete, insert));
    }

    // 🔹 Run example test cases
    public static void main(String[] args) {
        EditDistance obj = new EditDistance();

        System.out.println("Example 1:");
        System.out.println("word1 = horse, word2 = ros");
        System.out.println("Output = " + obj.minDistance("horse", "ros"));
        // Expected Output: 3

        System.out.println("\nExample 2:");
        System.out.println("word1 = intention, word2 = execution");
        System.out.println("Output = " + obj.minDistance("intention", "execution"));
        // Expected Output: 5

        System.out.println("\nExample 3:");
        System.out.println("word1 = cat, word2 = cut");
        System.out.println("Output = " + obj.minDistance("cat", "cut"));
        // Expected Output: 1
    }
}