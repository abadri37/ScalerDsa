package Tuf.Day15.Strings;

public class ImplementATOISTR {

    /**
     * LeetCode 8
     * Converts a string to a 32-bit signed integer (like C's atoi).
     *
     * @param s Input string
     * @return Converted integer, handling overflow/underflow
     */
    public int myAtoi(String s) {
        // Use long to handle potential overflow during computation
        long result = 0;

        // Step 1: Handle null or empty string
        if (s == null || s.isEmpty()) {
            return 0;
        }

        int i = 0;
        int n = s.length();

        // Step 2: Skip leading spaces
        while (i < n && s.charAt(i) == ' ') {
            i++;
        }

        // If the string had only spaces, return 0
        if (i == n) {
            return 0;
        }

        // Step 3: Handle optional '+' or '-' sign
        int sign = 1;
        if (s.charAt(i) == '+' || s.charAt(i) == '-') {
            if (s.charAt(i) == '-') {
                sign = -1;
            }
            // Move index past the sign
            i++;
        }

        // Step 4: Convert digits to integer
        while (i < n && Character.isDigit(s.charAt(i))) {
            int digit = s.charAt(i) - '0';
            result = result * 10 + digit;

            // Step 5: Check for overflow / underflow
            if (sign == 1 && result > Integer.MAX_VALUE) {
                return Integer.MAX_VALUE;
            }
            if (sign == -1 && -result < Integer.MIN_VALUE) { // Corrected check
                return Integer.MIN_VALUE;
            }

            i++;
        }

        // Step 6: Apply sign and cast to int
        return (int) (result * sign);
    }

    // Main method for testing
    public static void main(String[] args) {
        ImplementATOISTR atoi = new ImplementATOISTR();

        // Test Cases
        String[] testInputs = {
                "42",               // Positive number → 42
                "   -42",           // Leading spaces + negative → -42
                "4193 with words",  // Stops at first non-digit → 4193
                "words and 987",    // No digits at start → 0
                "-91283472332",     // Underflow → Integer.MIN_VALUE
                "2147483648",       // Overflow → Integer.MAX_VALUE
                "+1",               // Explicit plus sign → 1
                "",                 // Empty string → 0
                "    ",             // Only spaces → 0
                "+-12",             // Invalid format → 0 (stops at first non-digit after sign)
                "000123",           // Leading zeros → 123
        };

        for (String input : testInputs) {
            System.out.println("Input: \"" + input + "\" → Output: " + atoi.myAtoi(input));
        }
    }
}