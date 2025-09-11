package Tuf.Day16.Strings2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KMPAlgo {

    public static void main(String[] args) {
        // Example 1
        String text1 = "abedabcabcabdabcaabcabc";
        String pattern1 = "abcab";
        System.out.println("Pattern '" + pattern1 + "' found at indexes: " + Arrays.toString(search(text1, pattern1).toArray()));

        // Example 2
        String text2 = "aaaaa";
        String pattern2 = "aa";
        System.out.println("Pattern '" + pattern2 + "' found at indexes: " + Arrays.toString(search(text2, pattern2).toArray()));

        // Example 3
        String text3 = "abcdabcabcd";
        String pattern3 = "abcd";
        System.out.println("Pattern '" + pattern3 + "' found at indexes: " + Arrays.toString(search(text3, pattern3).toArray()));

        // Example 4
        String text4 = "xyz";
        String pattern4 = "abc";
        System.out.println("Pattern '" + pattern4 + "' found at indexes: " + Arrays.toString(search(text4, pattern4).toArray()));
    }

    /**
     * Searches for occurrences of 'pattern' in 'text' using KMP algorithm.
     *
     * @param text    The main text string
     * @param pattern The pattern to search
     * @return List of starting indexes where pattern is found in text
     */
    public static List<Integer> search(String text, String pattern) {
        List<Integer> list = new ArrayList<>();
        int[] lps = lpsArray(pattern); // Precompute longest prefix-suffix array
        int i = 0; // index for text
        int j = 0; // index for pattern
        int m = text.length();
        int n = pattern.length();

        while (i < m) {
            // Case 1: Characters match, move both pointers
            if (text.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
            }

            // Case 2: Found a complete match of pattern in text
            if (j == n) {
                list.add(i - j); // store the starting index of match
                j = lps[j - 1];  // reset j using LPS array for next possible match
            }
            // Case 3: Mismatch after some matches
            else if (i < m && text.charAt(i) != pattern.charAt(j)) {
                if (j != 0) {
                    // use LPS to avoid rechecking unnecessary characters
                    j = lps[j - 1];
                } else {
                    i++; // if no prefix matched, just move i
                }
            }
        }
        return list;
    }

    /**
     * Builds the LPS (Longest Prefix Suffix) array.
     * LPS[i] = length of the longest proper prefix of pattern[0..i]
     *          which is also a suffix of pattern[0..i].
     */
    public static int[] lpsArray(String pattern) {
        int[] lps = new int[pattern.length()];
        int len = 0; // length of the previous longest prefix suffix
        int i = 1;

        while (i < pattern.length()) {
            if (pattern.charAt(i) == pattern.charAt(len)) {
                // Characters match → extend the current LPS
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len != 0) {
                    // backtrack to previous LPS
                    len = lps[len - 1];
                } else {
                    // no prefix matches → set to 0
                    lps[i] = 0;
                    i++;
                }
            }
        }
        return lps;
    }
}