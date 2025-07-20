package DSA1.Array.Day3;

public class PowerFunction {

    public static void main(String[] args) {
        double x = 2.00000; // The base
        int n = 10;         // The exponent (can be negative or positive)

        /*
         * Goal:
         * Implement a fast power function to compute x^n.
         * That is, return x raised to the power of n (x^n).
         *
         * For example:
         * Input: x = 2.00000, n = 10
         * Output: 1024.00000
         */

        double result = 1.0; // Initialize result as 1 (identity for multiplication)
        long k = n; // Use long to safely handle Integer.MIN_VALUE edge case

        // Step 1: If exponent is negative, handle it
        if (k < 0) {
            // x^(-n) = 1 / (x^n)
            x = 1 / x;
            k = -k; // Convert exponent to positive
        }

        /*
         * Step 2: Use Exponentiation by Squaring
         *
         * Binary Exponentiation Formula:
         *   If n is even: x^n = (x * x)^(n / 2)
         *   If n is odd:  x^n = x * (x * x)^(n / 2)
         *
         * Every number can be represented in binary (e.g., 10 = 1010).
         * We multiply the result by x only when the current bit is 1.
         * At each step, we square x and shift the exponent to the right.
         */
        while (k > 0) {
            // If the least significant bit is 1, include x in result
            if ((k & 1) == 1) {
                result *= x;
            }

            // Square the base for the next bit
            x *= x;

            // Move to the next bit by right-shifting the exponent
            k >>= 1; // Same as k = k / 2
        }

        // Step 3: Print the final computed result
        System.out.println(result);
    }
}