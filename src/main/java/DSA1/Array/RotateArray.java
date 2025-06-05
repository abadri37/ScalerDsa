package DSA1.Array;

public class RotateArray {

    /**
     * Rotates an array to the right (backward) by k positions.
     * For example: [1,2,3,4,5,6,7,8] rotated by 3 becomes [6,7,8,1,2,3,4,5]
     *
     * The idea is to:
     * 1. Reverse the entire array.
     * 2. Reverse the first k elements.
     * 3. Reverse the remaining elements from k to end.
     */
    public static void rotateArrayRight(int[] A, int k) {
        int n = A.length;

        // Handle cases where k is larger than array length
        k = k % n;

        // Step 1: Reverse the entire array
        reverse(A, 0, n - 1);

        // Step 2: Reverse the first k elements (which were at the end originally)
        reverse(A, 0, k - 1);

        // Step 3: Reverse the rest of the elements
        reverse(A, k, n - 1);

        // Print the rotated array
        System.out.println("Rotated Right (backward) by " + k + " steps:");
        printArray(A);
    }

    /**
     * Rotates an array to the left (forward) by k positions.
     * For example: [1,2,3,4,5,6,7,8] rotated left by 3 becomes [4,5,6,7,8,1,2,3]
     *
     * The idea is to:
     * 1. Reverse the first k elements.
     * 2. Reverse the remaining elements.
     * 3. Reverse the entire array.
     */
    public static void rotateArrayLeft(int[] A, int k) {
        int n = A.length;

        // Handle cases where k is larger than array length
        k = k % n;

        // Step 1: Reverse the first k elements
        reverse(A, 0, k - 1);

        // Step 2: Reverse the remaining elements
        reverse(A, k, n - 1);

        // Step 3: Reverse the whole array
        reverse(A, 0, n - 1);

        // Print the rotated array
        System.out.println("Rotated Left (forward) by " + k + " steps:");
        printArray(A);
    }

    /**
     * Helper method to reverse elements in array between index i and j
     */
    public static void reverse(int[] A, int i, int j) {
        while (i < j) {
            int temp = A[i];
            A[i] = A[j];
            A[j] = temp;
            i++;
            j--;
        }
    }

    /**
     * Utility method to print array
     */
    public static void printArray(int[] A) {
        for (int num : A) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 3, 4, 5, 6, 7, 8};
        int[] nums2 = nums1.clone(); // Make a copy for left rotation

        int k = 3;

        // Rotate to the right (backward)
        rotateArrayRight(nums1, k); // Output: 6 7 8 1 2 3 4 5

        // Rotate to the left (forward)
        rotateArrayLeft(nums2, k);  // Output: 4 5 6 7 8 1 2 3
    }
}
