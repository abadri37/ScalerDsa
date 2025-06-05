package DSA1.Array;

public class RerverseArray {

    // Method to reverse the given array in-place
    public static void reverseArray(int[] A) {
        // Loop till the middle of the array
        for (int i = 0; i < A.length / 2; i++) {
            // Store the current element at index i
            int temp = A[i];

            // Calculate the index from the end of the array
            int index = (A.length - 1) - i;

            // Swap the current element with the corresponding element from the end
            A[i] = A[index];
            A[index] = temp;
        }

        // Print the reversed array
        System.out.println("The Reversed Array is");
        for (int i = 0; i < A.length; i++) {
            System.out.print(A[i] + " ");
        }
        System.out.println(); // For newline after printing array
    }

    public static void main(String[] args) {
        // Input array to be reversed
        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8};

        // Call the reverse method
        reverseArray(nums);
    }
}
