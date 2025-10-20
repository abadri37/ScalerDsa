package Tuf.Day16.Strings2;

public class MinimumInsertiontoMakePalindrome {

    public static void main(String[] args) {
        // Input string
        String st = "abca";

        // Reverse the string
        String rev = new StringBuilder(st).reverse().toString();

        // Concatenate original string + '$' + reversed string
        // The '$' acts as a separator to prevent overlapping while computing LPS
        String value = st + "$" + rev;

        // Compute the LPS array for the concatenated string
        int[] lps = lps(value.toCharArray());

        // Minimum characters to add = length of original string - length of longest palindromic prefix
        System.out.println("The total characters that needed to be added to make the string palindrome is "
                + (st.length() - lps[lps.length - 1]));
    }

    /**
     * Computes the LPS (Longest Prefix Suffix) array for the given character array.
     * LPS[i] = length of the longest prefix which is also a suffix for substring ch[0..i]
     *
     * @param ch Character array for which LPS is calculated
     * @return int array representing LPS values
     */
    public static int[] lps(char[] ch) {
        int[] lps = new int[ch.length]; // Initialize LPS array
        int len = 0;                    // Length of the previous longest prefix which is also a suffix
        int i = 1;

        while (i < ch.length) {
            if (ch[i] == ch[len]) {
                // Characters match → extend the current prefix-suffix length
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len != 0) {
                    // Mismatch after some matches → fall back to previous LPS value
                    len = lps[len - 1];
                } else {
                    // No prefix matched → set LPS to 0 for current position
                    lps[i] = 0;
                    i++;
                }
            }
        }
        return lps;
    }
}