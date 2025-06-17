package DSA1.Array.Sorting;

public class QuickSort {
    public static void main(String[] args) {
        // Input array to be sorted
        int[] arr = {8, 4, 7, 3, 10, 2};

        // Call Quick Sort on entire array
        quickSort(arr, 0, arr.length - 1);

        // Output the sorted array
        System.out.print("The sorted array is: ");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println(); // Move to next line
    }

    // Recursive Quick Sort function
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            // Partition the array and get the pivot index
            int pivot = quickSortPartition(arr, low, high);

            // Recursively sort elements before and after partition
            quickSort(arr, low, pivot - 1);   // Left side of pivot
            quickSort(arr, pivot + 1, high);  // Right side of pivot
        }
    }

    // Partition function that returns the correct position of pivot
    public static int quickSortPartition(int[] arr, int low, int high) {
        int pivot = arr[high]; // Choosing the last element as pivot
        int i = low - 1;       // Index of smaller element

        for (int j = low; j < high; j++) {
            // If current element is <= pivot, swap it with element at i+1
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }

        // Place pivot in the correct position
        swap(arr, i + 1, high);
        return i + 1; // Return the pivot index
    }

    // Swap helper function
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
