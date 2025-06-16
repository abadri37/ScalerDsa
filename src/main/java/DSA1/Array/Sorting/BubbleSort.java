package DSA1.Array.Sorting;

public class BubbleSort {

    public static void main(String[] args) {
        // Input array to be sorted
        int[] arr = {64, 34, 25, 12, 22, 11, 90};

        // Outer loop for each pass
        // After each pass, the largest unsorted element "bubbles up" to the end
        for (int i = 0; i < arr.length; i++) {
            // Inner loop for comparing adjacent elements
            // Each pass skips the last i elements since they are already sorted
            for (int j = 0; j < arr.length - i - 1; j++) {
                // Swap if the current element is greater than the next
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];           // Store current in temp
                    arr[j] = arr[j + 1];         // Move smaller element to left
                    arr[j + 1] = temp;           // Move larger element to right
                }
            }
        }

        // Output the sorted array
        System.out.print("The sorted array is: ");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println(); // Move to next line
    }
}
