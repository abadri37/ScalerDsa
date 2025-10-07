package Tuf.Day26.DynamicProgramming2;

import java.util.ArrayList;
import java.util.List;

public class PalindromePartitioning {

    public static List<List<String>> partition(String s) {
        int n = s.length();

        // Step 1: Precompute palindrome table
        // isPal[i][j] = true if substring s[i..j] is a palindrome
        boolean[][] isPal = new boolean[n][n];
        for (int i = n - 1; i >= 0; i--) {        // start index from end
            for (int j = i; j < n; j++) {         // end index from i to n-1
                // substring s[i..j] is palindrome if:
                // 1. s[i] == s[j] AND
                // 2. the substring inside (s[i+1..j-1]) is palindrome OR length <= 2
                if (s.charAt(i) == s.charAt(j) && (j - i <= 2 || isPal[i + 1][j - 1])) {
                    isPal[i][j] = true;
                }
            }
        }

        // Step 2: DP table where dp[i] stores all palindrome partitions of substring s[i..n-1]
        // dp[n] is the base case: empty list at the end
        List<List<String>>[] dp = new ArrayList[n + 1];
        dp[n] = new ArrayList<>();
        dp[n].add(new ArrayList<>()); // empty list for the end

        // Step 3: Fill dp table from end to start
        for (int i = n - 1; i >= 0; i--) {
            dp[i] = new ArrayList<>(); // initialize list for index i
            for (int j = i; j < n; j++) {
                // If substring s[i..j] is palindrome
                if (isPal[i][j]) {
                    String sub = s.substring(i, j + 1); // current palindrome substring

                    // Append this substring to all partitions starting at j+1
                    for (List<String> list : dp[j + 1]) {
                        List<String> temp = new ArrayList<>();
                        temp.add(sub);        // add current palindrome
                        temp.addAll(list);    // append partitions of remaining substring
                        dp[i].add(temp);      // store in dp[i]
                    }
                }
            }
        }

        // Step 4: Return all palindrome partitions starting at index 0
        return dp[0];
    }

    public static void main(String[] args) {
        String s = "aabaa";
        System.out.println("Palindrome partitions of \"" + s + "\":");
        System.out.println(partition(s));

        s = "baa";
        System.out.println("Palindrome partitions of \"" + s + "\":");
        System.out.println(partition(s));
    }
}
