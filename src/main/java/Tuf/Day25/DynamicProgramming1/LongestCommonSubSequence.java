package Tuf.Day25.DynamicProgramming1;

public class LongestCommonSubSequence {

    /**
     * Function to find the length of the Longest Common Subsequence (LCS) between two strings
     * @param text1 first string
     * @param text2 second string
     * @return length of the longest common subsequence
     */
    public int longestCommonSubsequence(String text1, String text2) {
        // dp[i][j] stores the length of LCS between text1[0..i-1] and text2[0..j-1]
        int[][] dp = new int[text1.length() + 1][text2.length() + 1];

        // Fill the DP table
        for (int i = 1; i <= text1.length(); i++) {
            for (int j = 1; j <= text2.length(); j++) {
                // If characters match, extend the LCS from previous indices
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    // If no match, take the maximum LCS by ignoring one character from either string
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        // Return the length of LCS for the full strings
        return dp[text1.length()][text2.length()];
    }

    // Main method for testing
    public static void main(String[] args) {
        LongestCommonSubSequence lcs = new LongestCommonSubSequence();

        // Test input 1
        String text1 = "abcde";
        String text2 = "ace";
        System.out.println("LCS length of \"" + text1 + "\" and \"" + text2 + "\": " +
                lcs.longestCommonSubsequence(text1, text2)); // Output: 3 ("ace")

        // Test input 2
        String text3 = "abc";
        String text4 = "abc";
        System.out.println("LCS length of \"" + text3 + "\" and \"" + text4 + "\": " +
                lcs.longestCommonSubsequence(text3, text4)); // Output: 3 ("abc")

        // Test input 3
        String text5 = "abc";
        String text6 = "def";
        System.out.println("LCS length of \"" + text5 + "\" and \"" + text6 + "\": " +
                lcs.longestCommonSubsequence(text5, text6)); // Output: 0 (no common subsequence)
    }
}