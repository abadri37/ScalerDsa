package Tuf.Day26.DynamicProgramming2;

import java.util.*;

public class WordBreak {
    public static void main(String[] args) {
        // Test cases
        List<String> testStrings = Arrays.asList(
                "applepenapple",
                "leetcode",
                "catsanddog",
                "aaaaaaa",
                "pineapplepenapple",
                "catsandog"
        );

        List<List<String>> testDicts = Arrays.asList(
                Arrays.asList("apple", "pen"),                  // Example 1
                Arrays.asList("leet", "code"),                 // Example 2
                Arrays.asList("cats", "dog", "sand", "and", "cat"), // Example 3
                Arrays.asList("a", "aa", "aaa"),               // Example 4
                Arrays.asList("apple", "pen", "applepen", "pine"), // Example 5
                Arrays.asList("cats", "dog", "sand", "and", "cat")  // Example 6
        );

        // Run all test cases
        for (int i = 0; i < testStrings.size(); i++) {
            String s = testStrings.get(i);
            List<String> dict = testDicts.get(i);
            boolean result = wordBreak(s, dict);
            System.out.println("Input: \"" + s + "\", Dictionary: " + dict);
            System.out.println("Can be segmented? " + result);
            System.out.println("------------------------------------------------");
        }
    }

    /**
     * Word Break using Dynamic Programming
     * dp[i] = true if substring s[0..i-1] can be segmented using words from wordDict
     *
     * Intuition:
     * 1. Start from the beginning of the string.
     * 2. For each index i, try all possible splits j (0 <= j < i).
     * 3. If the left part dp[j] is true and the right part s[j..i-1] exists in the dictionary,
     *    then s[0..i-1] can also be segmented (dp[i] = true).
     * 4. Continue until dp[n] (whole string).
     */
    public static boolean wordBreak(String s, List<String> wordDict) {
        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true; // empty string can always be segmented

        // Use HashSet for fast O(1) lookup of words
        Set<String> set = new HashSet<>(wordDict);

        // Build dp[] array
        for (int i = 1; i <= n; i++) { // i = end index of substring
            for (int j = 0; j < i; j++) { // j = split point
                // If left part can be segmented and right part exists in dictionary
                if (dp[j] && set.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break; // No need to check further splits
                }
            }
        }

        return dp[n]; // dp[n] tells if whole string can be segmented
    }
}