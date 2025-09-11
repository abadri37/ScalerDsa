package Tuf.Day16.Strings2;

public class CountAndSay {

    public static void main(String[] args) {
        // Multiple test cases
        int[] testInputs = {1, 2, 3, 4, 5, 6};

        for (int n : testInputs) {
            System.out.println("n = " + n + " → Count and Say Sequence: " + countAndSay(n));
        }
    }

    /**
     * Generates the nth term of the "Count and Say" sequence.
     *
     * Example:
     * n = 1 → "1"
     * n = 2 → "11" (one 1)
     * n = 3 → "21" (two 1s)
     * n = 4 → "1211" (one 2, one 1)
     * n = 5 → "111221" (one 1, one 2, two 1s)
     *
     * @param n the position in the sequence
     * @return the nth term as a string
     */
    public static String countAndSay(int n) {
        // Base case
        if (n == 1) {
            return "1";
        }

        // Start sequence with "1"
        String result = "1";

        // Build sequence up to n
        for (int i = 2; i <= n; i++) {
            result = count(result);
        }
        return result;
    }

    /**
     * Reads the given string and produces the "Count and Say" description.
     *
     * For example:
     * input: "21" → output: "1211"
     * explanation: "21" is read as "one 2, one 1"
     *
     * @param st current sequence
     * @return next sequence string
     */
    public static String count(String st) {
        StringBuffer sb = new StringBuffer();
        int count = 1;

        // Traverse characters
        for (int i = 1; i < st.length(); i++) {
            if (st.charAt(i) == st.charAt(i - 1)) {
                // Same char → increase count
                count++;
            } else {
                // Different char → append count + previous char
                sb.append(count).append(st.charAt(i - 1));
                count = 1; // reset counter
            }
        }

        // Append last group
        sb.append(count).append(st.charAt(st.length() - 1));
        return sb.toString();
    }
}