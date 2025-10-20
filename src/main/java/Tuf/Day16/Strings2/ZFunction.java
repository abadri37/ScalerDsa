package Tuf.Day16.Strings2;

public class ZFunction {
    public static void main(String[] args) {
        // Input string in which we want to search for a pattern
        String input = "xyzabxyzabxyz";

        // Pattern to be searched
        String pattern = "xyz";

        // 🧩 Step 1: Create a combined character array in the form "pattern + $ + text"
        // Why do we add '$'? → It's a unique delimiter that ensures pattern and text don't mix
        // Example final string: "xyz$xyzabxyzabxyz"
        char[] ch = new char[input.length() + 1 + pattern.length()];
        int ii = 0;

        // Copy pattern into the array
        for (int i = 0; i < pattern.length(); i++) {
            ch[ii++] = pattern.charAt(i);
        }

        // Add a delimiter to separate pattern and text
        ch[ii++] = '$';

        // Copy the text (main string) into the array
        for (int i = 0; i < input.length(); i++) {
            ch[ii++] = input.charAt(i);
        }

        // 🧩 Step 2: Compute Z-function for this combined string
        // Z-function returns an array where Z[i] = length of longest substring starting at i
        // that matches the prefix of the string
        int[] zfunction = zFunction(ch);

        // 🧩 Step 3: Identify where the pattern appears in the text
        // Whenever Z[i] == pattern.length(), it means a match was found!
        for (int i = 0; i < zfunction.length; i++) {
            if (zfunction[i] == pattern.length()) {
                // The pattern starts (i - pattern.length() - 1) positions after the '$'
                System.out.println("The Pattern starts at index " + (i - pattern.length() - 1));
            }
        }
    }

    // 🧠 Z-function implementation
    public static int[] zFunction(char[] input) {
        int[] z = new int[input.length];

        // left and right define the "Z-box" — the interval where we know matches exist
        int left = 0;
        int right = 0;

        // 🔁 Start from index 1, since Z[0] = 0 (whole prefix matches itself)
        for (int k = 1; k < z.length; k++) {

            // Case 1️⃣: If k is OUTSIDE the current [left, right] box
            if (k > right) {
                left = right = k;  // Start a new box

                // Expand manually — compare chars with prefix
                while (right < input.length && input[right] == input[right - left]) {
                    right++;
                }

                // The number of matching characters = box width
                z[k] = right - left;
                right--;  // Move back one step (since last increment failed)
            }

            // Case 2️⃣: If k is INSIDE the current [left, right] box
            else {
                // Mirror index with respect to left boundary
                int k1 = k - left;

                // ❌ NOTE: There’s a bug in your original code: this should be z[k1], not z[k]
                // because we are trying to reuse the previously computed Z[k1].
                if (z[k1] < right - k + 1) {
                    // ✅ Safe to reuse Z[k1] — it fits entirely inside the box
                    z[k] = z[k1];
                } else {
                    // ⚡ Need to expand further beyond current right boundary
                    left = k;
                    while (right < input.length && input[right] == input[right - left]) {
                        right++;
                    }
                    z[k] = right - left;
                    right--;
                }
            }
        }

        return z;
    }
}