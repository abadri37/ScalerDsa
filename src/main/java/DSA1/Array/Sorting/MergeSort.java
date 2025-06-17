package DSA1.Array.Sorting;

public class MergeSort {

    public static void main(String[] args) {
        // Input array to be sorted
        int[] arr = {38, 27, 43, 3, 9, 82, 10};

        // Start merge sort with the full range of the array
        mergeSort(arr, 0, arr.length - 1);

        // Output the sorted array
        System.out.print("The sorted array is: ");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println(); // Move to next line
    }

    /**
     * Recursively splits the array into halves, sorts and merges them.
     * Time Complexity: O(n log n) - Divide step is O(log n), merge step is O(n)
     * Space Complexity: O(n) - Temporary arrays are created during merge
     */
    public static void mergeSort(int[] arr, int left, int right) {
        // Base condition: when left index is less than right, there are at least 2 elements
        if (left < right) {
            // Find the middle index
            int mid = (left + right) / 2;

            // Recursively sort the left half
            mergeSort(arr, left, mid);

            // Recursively sort the right half
            mergeSort(arr, mid + 1, right);

            // Merge the two sorted halves
            merge(arr, left, mid, right);
        }
    }

    /**
     * Merges two sorted subarrays into one sorted subarray.
     * Subarrays:
     * - arr[left...mid]
     * - arr[mid+1...right]
     */
    public static void merge(int[] arr, int left, int mid, int right) {
        // Sizes of temporary subarrays
        int n1 = mid - left + 1;
        int n2 = right - mid;

        // Create temp arrays
        int[] leftArr = new int[n1];
        int[] rightArr = new int[n2];

        // Copy data to temp arrays
        for (int i = 0; i < n1; i++) {
            leftArr[i] = arr[left + i];
        }
        for (int i = 0; i < n2; i++) {
            rightArr[i] = arr[mid + 1 + i];
        }

        // Initial indexes of temp arrays and merged subarray
        int i = 0; // index for leftArr
        int j = 0; // index for rightArr
        int k = left; // index for merged array

        // Merge the temp arrays back into arr[left...right]
        while (i < n1 && j < n2) {
            if (leftArr[i] <= rightArr[j]) {
                arr[k] = leftArr[i];
                i++;
            } else {
                arr[k] = rightArr[j];
                j++;
            }
            k++;
        }

        // Copy remaining elements of leftArr[], if any
        while (i < n1) {
            arr[k] = leftArr[i];
            i++;
            k++;
        }

        // Copy remaining elements of rightArr[], if any
        while (j < n2) {
            arr[k] = rightArr[j];
            j++;
            k++;
        }
    }
}
