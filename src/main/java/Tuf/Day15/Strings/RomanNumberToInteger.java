package Tuf.Day15.Strings;

import java.util.HashMap;
import java.util.Map;

public class RomanNumberToInteger {
    public static void main(String[] args) {
        // Example 1: Standard Roman numeral
        String s1 = "MCMXCIV"; // 1994
        System.out.println("Input: " + s1);
        System.out.println("Output: " + solve(s1));

        // Example 2: Simple numeral
        String s2 = "III"; // 3
        System.out.println("\nInput: " + s2);
        System.out.println("Output: " + solve(s2));

        // Example 3: Subtractive notation (IV = 4)
        String s3 = "IV"; // 4
        System.out.println("\nInput: " + s3);
        System.out.println("Output: " + solve(s3));

        // Example 4: Mixed case (LVIII = 58)
        String s4 = "LVIII"; // 50 + 5 + 3 = 58
        System.out.println("\nInput: " + s4);
        System.out.println("Output: " + solve(s4));

        // Example 5: Another subtractive case (XL = 40)
        String s5 = "XL"; // 40
        System.out.println("\nInput: " + s5);
        System.out.println("Output: " + solve(s5));
    }

    /**
     * Converts a Roman numeral string to its integer equivalent.
     *
     * Algorithm:
     * 1. Store mappings of Roman characters to integer values in a map.
     * 2. Traverse the string left to right.
     * 3. If the current value is smaller than the next value → subtract it.
     *    Example: IV → I (1) < V (5) → subtract 1.
     * 4. Otherwise → add it.
     *    Example: VI → V (5) + I (1).
     * 5. Return the total sum.
     */
    public static int solve(String s) {
        char[] ch = s.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        int ret = 0;

        for (int i = 0; i < ch.length; i++) {
            int val = map.get(ch[i]);

            // If current value is less than next → subtract
            if (i < ch.length - 1 && val < map.get(ch[i + 1])) {
                ret -= val;
            } else {
                // Otherwise → add
                ret += val;
            }
        }
        return ret;
    }
}