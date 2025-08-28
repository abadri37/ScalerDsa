package Tuf.Day10.RecursionBackTracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class WordBreak {
    public static void main(String[] args) {
        String s = "catsanddog"; // Input string
        List<String> dict = Arrays.asList("cat", "cats", "and", "sand", "dog"); // Dictionary
        HashSet<String> set = new HashSet<>(dict); // Store dictionary in a HashSet for O(1) lookup
        List<String> result = new ArrayList<>();   // Final list of all possible sentences

        // Call recursive helper
        solveWordBreak(s, 0, result, set, new ArrayList<>());

        // Print all possible sentences
        for (String st : result) {
            System.out.println(st + " ");
        }
    }

    /**
     * Recursive function to find all possible word breaks.
     * @param s input string
     * @param startIndex current position in the string
     * @param result final list of sentences
     * @param dict dictionary set
     * @param list current path (words formed so far)
     */
    public static void solveWordBreak(String s, int startIndex, List<String> result, HashSet<String> dict, List<String> list) {
        // BASE CASE: if we've reached the end of the string
        if (startIndex == s.length()) {
            result.add(String.join(" ", list)); // Join words with spaces and add to result
            return;
        }

        // Try all possible substrings starting from startIndex
        for (int end = startIndex + 1; end <= s.length(); end++) {
            // Take substring from current startIndex to end
            String st = s.substring(startIndex, end);

            // If substring exists in dictionary
            if (dict.contains(st)) {
                // Choose: add word to the current path
                list.add(st);

                // Explore: recurse for the remaining string
                solveWordBreak(s, end, result, dict, list);

                // Backtrack: remove the last word added
                list.remove(list.size() - 1);
            }
        }
    }
}