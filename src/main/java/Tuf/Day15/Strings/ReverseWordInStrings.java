package Tuf.Day15.Strings;

public class ReverseWordInStrings {

    public static void main(String[] args) {
        // Example 1: Simple case
        String s1 = "the sky is blue";
        System.out.println("Input: \"" + s1 + "\"");
        System.out.println("Output: \"" + reverse(s1) + "\"");
        // Expected: "blue is sky the"

        // Example 2: Extra spaces between words
        String s2 = "  hello   world  ";
        System.out.println("\nInput: \"" + s2 + "\"");
        System.out.println("Output: \"" + reverse(s2) + "\"");
        // Expected: "world hello"

        // Example 3: Single word
        String s3 = "Java";
        System.out.println("\nInput: \"" + s3 + "\"");
        System.out.println("Output: \"" + reverse(s3) + "\"");
        // Expected: "Java"

        // Example 4: Multiple spaces everywhere
        String s4 = "   a   good   example   ";
        System.out.println("\nInput: \"" + s4 + "\"");
        System.out.println("Output: \"" + reverse(s4) + "\"");
        // Expected: "example good a"
    }

    /**
     * Reverses the words in a string.
     * Steps:
     * 1. Trim leading and trailing spaces.
     * 2. Split the string by one or more spaces (regex "\\s+").
     * 3. Reverse the array of words using two-pointer approach.
     * 4. Join words back with a single space.
     *
     * Example:
     * Input: "  hello   world  "
     * Split: ["hello", "world"]
     * Reverse: ["world", "hello"]
     * Output: "world hello"
     */
    public static String reverse(String s) {
        s = s.trim();  // remove leading/trailing spaces
        String[] st = s.split("\\s+");  // split by multiple spaces
        int i = 0;
        int j = st.length - 1;

        // Reverse the array of words
        while (i < j) {
            String temp = st[i];
            st[i] = st[j];
            st[j] = temp;
            i++;
            j--;
        }

        return String.join(" ", st);  // join words with a single space
    }
}