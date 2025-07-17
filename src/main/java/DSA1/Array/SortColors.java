package DSA1.Array;

public class SortColors {
    public static void main(String[] args) {
        int[] colors = {2, 0, 2, 1, 1, 0};  // Unsorted array of colors (0 = red, 1 = white, 2 = blue)

        // Step 1: Count number of 0s, 1s, and 2s
        int zeros = 0;
        int ones = 0;
        int twos = 0;

        for (int i = 0; i < colors.length; i++) {
            if (colors[i] == 0) {
                zeros++;      // Count number of 0s
            } else if (colors[i] == 1) {
                ones++;       // Count number of 1s
            } else {
                twos++;       // Count number of 2s
            }
        }

        // Step 2: Overwrite the original array with counted values in sorted order

        int i = 0;

        // Fill in 0s
        while (i <= colors.length - 1 && zeros > 0) {
            colors[i] = 0;
            i++;
            zeros--;
        }

        // Fill in 1s
        while (i <= colors.length - 1 && ones > 0) {
            colors[i] = 1;
            i++;
            ones--;
        }

        // Fill in 2s
        while (i <= colors.length - 1 && twos > 0) {
            colors[i] = 2;
            i++;
            twos--;
        }

        // Step 3: Print the sorted array
        for (int ii = 0; ii < colors.length; ii++) {
            System.out.print(colors[ii] + " ");
        }
        System.out.println(); // Move to next line after output
    }
}
