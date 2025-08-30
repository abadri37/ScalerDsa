package Tuf.Day11.BinarySearch;

public class KthElementSortedArrays {
    public static void main(String[] args) {
        int[] A = new int[]{2, 3, 6, 7, 9};
        int[] B = new int[]{1, 4, 8, 10};
        int k = 7;
        System.out.println("The Solution exists in " + solve(A, B, k));
    }

    public static int solve(int[] A, int[] B, int k) {
        // Step 1: Always make sure A is the smaller array
        // Why? -> Binary search will be performed on A, so smaller size reduces search space.
        if (A.length > B.length) {
            return solve(B, A, k);
        }

        int m = A.length; // length of first array
        int n = B.length; // length of second array

        // Step 2: Define binary search range on A
        // low -> minimum elements we can take from A (can't take less than 0, but must take at least k-n if k>n)
        // high -> maximum elements we can take from A (can't take more than m, can't take more than k itself)
        int low = Math.max(0, k - n);
        int high = Math.min(k, A.length);

        // Step 3: Start binary search
        while (low <= high) {
            // Partition A into left (i elements) and right (remaining)
            int i = (low + high) / 2;

            // j = elements we need to take from B such that total = k
            int j = k - i;

            // Get boundaries of partitions
            // Aleft -> last element in left partition of A
            // Aright -> first element in right partition of A
            // Bleft -> last element in left partition of B
            // Bright -> first element in right partition of B
            int Aleft = (i == 0) ? Integer.MIN_VALUE : A[i - 1];
            int Aright = (i == m) ? Integer.MAX_VALUE : A[i];
            int Bleft = (j == 0) ? Integer.MIN_VALUE : B[j - 1];
            int Bright = (j == n) ? Integer.MAX_VALUE : B[j];

            // Step 4: Check if partition is valid
            // Valid partition means: left elements ≤ right elements
            if (Aleft <= Bright && Bleft <= Aright) {
                // Found correct partition
                // Kth element is the maximum element on the left side
                return Math.max(Aleft, Bleft);
            }
            // Step 5: If partition not valid, adjust binary search
            else if (Aleft > Bright) {
                // Too many elements taken from A, move left
                high = i - 1;
            } else {
                // Too few elements taken from A, move right
                low = i + 1;
            }
        }

        // Ideally never reached if inputs are valid
        return -1;
    }
}