package DSA1.Array;

public class EquillibriumIndex {

    // Method to compute number of equilibrium indices in array A
    public static int process(int[] A) {
        // Step 1: Construct prefix sum array
        int[] prefix = new int[A.length];
        prefix[0] = A[0]; // First element of prefix is same as A[0]

        for (int i = 1; i < A.length; i++) {
            prefix[i] = A[i] + prefix[i - 1]; // Each element stores cumulative sum up to index i
        }

        int count = 0; // To store number of equilibrium indices found

        // Step 2: Loop through each index to check for equilibrium condition
        for (int i = 0; i < A.length; i++) {
            int left = 0;   // Sum of elements to the left of index i
            int right = 0;  // Sum of elements to the right of index i

            // If at index 0, there are no elements on the left
            if (i == 0) {
                left = 0;
            } else {
                left = prefix[i - 1]; // Sum from index 0 to i-1
            }

            // Right sum = total sum - prefix[i] (i.e., exclude current index and all to its left)
            right = prefix[A.length - 1] - prefix[i];

            // If left and right sums are equal, it's an equilibrium index
            if (left == right) {
                count++;
            }
        }

        return count; // Return total number of equilibrium indices
    }

    public static void main(String[] args) {
        // Sample input array with known equilibrium indices at index 3 and 6
        int[] A = {-7, 1, 5, 2, -4, 3, 0};

        // Output the result of process function
        System.out.println("Total Equillibrium index in the given array is " + process(A));
    }
}
