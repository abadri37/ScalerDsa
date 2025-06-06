package DSA1.Array;

/**
 * Kadane’s Algorithm - Use Cases:
 *
 * ✅ Finding the Maximum Subarray Sum in O(N) time.
 * ✅ Analyzing financial data (max profit/loss period).
 * ✅ Real-time signal processing to find the best-performing window.
 * ✅ Applied in 2D matrix max submatrix sum problems (extended form).
 * ✅ Problems like:
 *    - Maximum Sum Circular Subarray
 *    - Maximum Sum after K concatenations
 *    - Longest subarray with positive product (variant)
 * ✅ Common in Competitive Programming and Technical Interviews.
 */

public class KadanesAlgorithm {
    public static void main(String[] args) {
        int[] A = new int[]{3, -2, 5, -1, 2, -6, 4, -3, 2, 1};

        // Initialize maxSum and currentSum to the first element of the array
        int maxSum = A[0];
        int currentSum = A[0];

        // Traverse the array starting from the second element
        for (int i = 1; i < A.length; i++) {
            // At each step, decide whether to start a new subarray at A[i]
            // or to extend the previous subarray by adding A[i]
            currentSum = Math.max(A[i], currentSum + A[i]);

            // Update maxSum if the current subarray sum is greater than the max found so far
            maxSum = Math.max(maxSum, currentSum);
        }

        // Print the result
        System.out.println("The maximum subarray sum in the given input is " + maxSum);
    }
}
