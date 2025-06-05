package DSA1.Array;

public class RerverseArray {

    // Method to reverse the given array in-place
    public static void reverseArray(int[] A) {
        // Initialize two pointers: one at the start, one at the end
        int i = 0;
        int j = A.length - 1;

        // Continue swapping elements until the pointers meet in the middle
        while (i < j) {
            // Swap the elements at index i and j
            int temp = A[i];
            A[i] = A[j];
            A[j] = temp;

            // Move the pointers towards the center
            i++;
            j--;
        }

        // Print the reversed array
        System.out.println("The Reversed Array is:");
        for (int ii = 0; ii < A.length; ii++) {
            System.out.print(A[ii] + " ");
        }
        System.out.println(); // Print a newline after displaying the array
    }

    public static void main(String[] args) {
        // Input array to be reversed
        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8};

        // Call the method to reverse the array
        reverseArray(nums);
    }
}
