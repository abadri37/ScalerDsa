package Tuf.Day15.Strings;

public class LongestCommonPrefix {

    public String longestCommonPrefix(String[] strs) {
        // Handle empty array or null input
        if (strs == null || strs.length == 0) {
            return "";
        }

        // StringBuilder to accumulate the common prefix
        StringBuilder sb = new StringBuilder();

        // Loop through each character position of the first string
        for (int i = 0; i < strs[0].length(); i++) {
            int count = 0; // To count how many strings match at this position

            // Compare the character at position i in all other strings
            for (int j = 1; j < strs.length; j++) {
                // Check bounds and character match
                if (i < strs[j].length() && strs[0].charAt(i) == strs[j].charAt(i)) {
                    count++;
                }
            }

            // If all strings matched at this character, add it to the prefix
            if (count == strs.length - 1) {
                sb.append(strs[0].charAt(i));
            } else {
                // Stop at first mismatch
                return sb.toString();
            }
        }

        return sb.toString();
    }

    // Main method to test different cases
    public static void main(String[] args) {
        LongestCommonPrefix lcp = new LongestCommonPrefix();

        // Test cases
        String[][] testCases = {
                {"flower","flow","flight"},  // Expected: "fl"
                {"dog","racecar","car"},     // Expected: ""
                {"interspecies","interstellar","interstate"}, // Expected: "inters"
                {"throne","throne"},         // Expected: "throne"
                {""},                        // Expected: ""
                {"a"},                        // Expected: "a"
                {"prefix","preform","prevent"} // Expected: "pre"
        };

        for (String[] test : testCases) {
            System.out.print("Input: ");
            for (String s : test) System.out.print(s + " ");
            System.out.println("→ Longest Common Prefix: " + lcp.longestCommonPrefix(test));
        }
    }
}