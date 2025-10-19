package Tuf.Day15.Strings;

public class LongestPalindromeInString {

    /**
     * Returns the longest palindromic substring from the given string.
     *
     * @param s Input string
     * @return Longest palindrome substring
     */
    public String longestPalindrome(String s) {
        // Handle null or empty string case
        if (s == null || s.isEmpty()) {
            return "";
        }

        // 'start' and 'end' will track the indices of the longest palindrome found
        int start = 0;
        int end = 0;

        // Loop through each character as potential center of a palindrome
        for (int i = 0; i < s.length(); i++) {

            // len1: palindrome length assuming odd length (centered at i)
            int len1 = expand(s, i, i);

            // len2: palindrome length assuming even length (centered between i and i+1)
            int len2 = expand(s, i, i + 1);

            // Choose the longer palindrome length
            int len = Math.max(len1, len2);

            // If this palindrome is longer than the previous longest, update start and end indices
            if (len > (end - start)) {
                if (len % 2 == 1) {
                    // Odd length palindrome: center at i
                    start = i - (len / 2);
                    end = i + (len / 2);
                } else {
                    // Even length palindrome: center between i and i+1
                    start = i - (len / 2) + 1;
                    end = i + (len / 2);
                }
            }
        }

        // Return the longest palindromic substring
        return s.substring(start, end + 1);
    }

    /**
     * Expands around the given center and returns the length of palindrome.
     *
     * @param s     Input string
     * @param left  Left index of the center
     * @param right Right index of the center
     * @return Length of the palindrome
     */
    public int expand(String s, int left, int right) {
        // Expand as long as characters match and boundaries are valid
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }

        // Length is right - left - 1 because we expand one extra step in the last iteration
        return right - left - 1;
    }

    // Main method for testing
    public static void main(String[] args) {
        LongestPalindromeInString lp = new LongestPalindromeInString();

        // Test Cases
        String[] testCases = {
                "babad",    // Expected: "bab" or "aba"
                "cbbd",     // Expected: "bb"
                "a",        // Expected: "a"
                "ac",       // Expected: "a" or "c"
                "",         // Expected: ""
                "racecar",  // Expected: "racecar"
                "abba"      // Expected: "abba"
        };

        for (String test : testCases) {
            System.out.println("Input: " + test + " → Longest Palindrome: " + lp.longestPalindrome(test));
        }
    }
}