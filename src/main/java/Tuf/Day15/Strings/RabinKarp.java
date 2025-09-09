package Tuf.Day15.Strings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RabinKarp {

    // Base prime number used for hashing (acts like "radix" in polynomial rolling hash)
    public static int PRIME = 101;

    public static void main(String[] args) {
        String text = "abedabcabcabdabcaabcabc";
        String pattern = "abcab";

        // Search for all occurrences of pattern in text
        List<Integer> result = search(pattern, text);

        // Print result indices
        System.out.println(Arrays.toString(result.toArray()));
    }

    /**
     * Rabin–Karp search algorithm.
     * @param pattern the substring we are searching for
     * @param text the larger text where we search
     * @return list of starting indices where pattern occurs in text
     */
    public static List<Integer> search(String pattern, String text) {
        List<Integer> list = new ArrayList<>();
        int n = pattern.length();
        int m = text.length();


        // Edge case: if text is shorter than pattern, no matches possible
        if (m < n) {
            return list;
        }

        // Initial hash of first window of text and the pattern
        long textHash = generateHash(text, n);
        long patternHash = generateHash(pattern, n);

        // Slide over text windows of size n
        for (int i = 0; i <= m - n; i++) {
            // If hashes match, verify by comparing actual substring (to avoid false positives)
            if (patternHash == textHash && text.substring(i, i + n).equals(pattern)) {
                list.add(i);
            }

            // Compute rolling hash for next window
            if (i < m - n) {
                textHash = rollingHash(text, i, i + n, textHash, n);
            }
        }
        return list;
    }

    /**
     * Generate hash for a string of length m using polynomial rolling hash.
     * hash(s) = s[0]*PRIME^(m-1) + s[1]*PRIME^(m-2) + ... + s[m-1]*PRIME^0
     */
    public static long generateHash(String text, int m) {
        long hashValue = 0;
        for (int i = 0; i < m; i++) {
            hashValue = hashValue * PRIME + text.charAt(i);
        }
        return hashValue;
    }

    /**
     * Update rolling hash when sliding window by 1 character.
     * oldIndex → index of character leaving the window
     * newIndex → index of character entering the window
     */
    public static long rollingHash(String text, int oldIndex, int newIndex, long oldHashValue, int m) {
        // Highest power of PRIME in current window (for leftmost char)
        long highestPow = (long) Math.pow(PRIME, m - 1);

        // Remove leftmost char's contribution
        oldHashValue -= text.charAt(oldIndex) * highestPow;

        // Shift the window by multiplying by PRIME and add new character
        oldHashValue = oldHashValue * PRIME + text.charAt(newIndex);

        return oldHashValue;
    }
}