package DSA1.Array;

public class OddEvenIndexSumArray {

    // Method to count how many indices can be removed to equalize even and odd indexed sums
    public static int process(int[] A) {
        int count = 0;

        // Initialize prefix sum arrays:
        // prefixEven[i] stores the sum of all even indexed elements from 0 to i
        // prefixOdd[i] stores the sum of all odd indexed elements from 0 to i
        int[] prefixEven = new int[A.length];
        int[] prefixOdd = new int[A.length];

        // Base case: First element (index 0 is even)
        prefixEven[0] = A[0];
        prefixOdd[0] = 0;

        // Compute prefix sums for even and odd indices separately
        for (int i = 1; i < A.length; i++) {
            if (i % 2 == 0) {
                prefixEven[i] = prefixEven[i - 1] + A[i];
                prefixOdd[i] = prefixOdd[i - 1];
            } else {
                prefixOdd[i] = prefixOdd[i - 1] + A[i];
                prefixEven[i] = prefixEven[i - 1];
            }
        }

        // Now iterate over each index to simulate removal of that element
        for (int i = 0; i < A.length; i++) {
            int sumEven = 0;
            int sumOdd = 0;

            if (i == 0) {
                // Removing the first element, shift all indexes left
                sumEven = prefixOdd[A.length - 1];
                // New odd indexed sum = sum of old even indices except removed element
                sumOdd = prefixEven[A.length - 1];
            } else {
                // When removing index i:
                // - Elements before i retain their position
                // - Elements after i shift left (odd <-> even)

                // Sum of even indices after removal:
                //   = prefixEven[0 to i-1] + prefixOdd[i+1 to N-1]
                sumEven = prefixEven[i - 1] + (prefixOdd[A.length - 1] - prefixOdd[i]);

                // Sum of odd indices after removal:
                //   = prefixOdd[0 to i-1] + prefixEven[i+1 to N-1]
                sumOdd = prefixOdd[i - 1] + (prefixEven[A.length - 1] - prefixEven[i]);
            }

            // If both sums are equal, increment the count
            if (sumEven == sumOdd) {
                count++;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        // Input array
        int[] A = {4, 1, 7, 9, 3, 2, 5, 8, 6, 2, 1, 4};

        // Output the number of such indices
        System.out.println("Total indices which when removed make the even and odd index sums equal: " + process(A));
    }
}
