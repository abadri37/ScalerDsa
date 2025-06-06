package DSA1.Array;

/**
 * 🔄 Sliding Window Technique
 *
 * 🔹 Use Case: Efficiently solve problems involving subarrays or substrings with a fixed or variable window size.
 *
 * ✅ Typical Problems Solved:
 *  - Maximum/Minimum sum or average of subarray of size k
 *  - Longest substring with at most K distinct characters
 *  - Smallest subarray with sum ≥ target
 *  - Maximum number of vowels in a substring of given length
 *  - First negative number in every window of size k
 */
public class SlidingWindow {

    public static void main(String[] args) {
        // Input array and window size k
        int[] A = new int[]{1, 12, -5, -6, 50, 3};
        int k = 4;

        // i is the start index of the sliding window
        int i = 0;

        // j is the end index (exclusive) of the sliding window
        int j = 0;

        // Sum of elements inside the current window
        int sum = 0;

        // Variable to track the maximum average found so far
        double maxAverage = Double.NEGATIVE_INFINITY;

        // Calculate the sum of the first 'k' elements to initialize the window
        for (j = 0; j < k; j++) {
            sum += A[j];
        }

        // Calculate the average of the first window and update maxAverage
        maxAverage = Math.max(maxAverage, (double) sum / k);

        // Slide the window across the array
        while (j < A.length) {
            // Remove the leftmost element of the previous window and add the next element
            sum = sum - A[i] + A[j];

            // Update maxAverage if current window's average is higher
            maxAverage = Math.max(maxAverage, (double) sum / k);

            // Move the window forward
            i++;
            j++;
        }

        // Print the maximum average found across all windows
        System.out.println("Maximum average of any subarray of size " + k + " is: " + maxAverage);
    }
}
