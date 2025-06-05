package DSA1.Array;

public class RangeSumQuery {

    /**
     * Returns the sum of elements from index 'start' to 'end' (inclusive)
     * using a prefix sum array.
     *
     * @param A     The input array
     * @param start The starting index of the range (inclusive)
     * @param end   The ending index of the range (inclusive)
     * @return The sum of elements in the given range
     */
    public static int getRangeSum(int[] A, int start, int end) {
        // Step 1: Construct the prefix sum array
        int[] prefix = new int[A.length];

        // First element of prefix sum is the same as the first element of A
        prefix[0] = A[0];

        // Compute cumulative sums for the rest of the array
        for (int i = 1; i < A.length; i++) {
            prefix[i] = A[i] + prefix[i - 1];
            // prefix[i] stores the sum of A[0] + A[1] + ... + A[i]
        }

        // Step 2: Compute the sum from 'start' to 'end'
        // If start is 0, simply return prefix[end]
        // Else subtract prefix[start - 1] to exclude elements before 'start'
        return start == 0 ? prefix[end] : prefix[end] - prefix[start - 1];
    }

    public static void main(String[] args) {
        // Sample input array
        int[] A = {3, 6, 2, 8, -1, 7, 4};

        // Perform range sum queries
        System.out.println("Sum from index 0 to 3: " + getRangeSum(A, 0, 3)); // Output: 19
        System.out.println("Sum from index 2 to 5: " + getRangeSum(A, 2, 5)); // Output: 16
        System.out.println("Sum from index 1 to 6: " + getRangeSum(A, 1, 6)); // Output: 26
    }
}
