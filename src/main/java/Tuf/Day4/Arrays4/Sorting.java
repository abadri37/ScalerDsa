package Tuf.Day4.Arrays4;

public class Sorting {
    public static void main(String[] args) {
        // Input array to be sorted
        int[] arr = {38, 27, 43, 3, 9, 82, 10};

        // Calling the merge sort function on the entire array
        mergeSort(arr, 0, arr.length - 1);

        // Printing the sorted array
        System.out.println("The Sorted Array is ");
        for (int n : arr) {
            System.out.print(n + " ");
        }
        System.out.println();
    }

    // Recursive function to divide the array into halves and sort them
    public static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            // Find the middle point to divide the array into two halves
            int mid = (right + left) / 2;

            // Recursively sort the first half
            mergeSort(arr, left, mid);

            // Recursively sort the second half
            mergeSort(arr, mid + 1, right);

            // Merge the two sorted halves
            merge(arr, left, mid, right);
        }
    }

    // Function to merge two sorted subarrays: arr[left..mid] and arr[mid+1..right]
    public static void merge(int[] arr, int left, int mid, int right) {
        // Sizes of the two subarrays
        int n1 = mid - left + 1;
        int n2 = right - mid;

        // Temporary arrays to hold the two halves
        int[] leftArr = new int[n1];
        int[] rightArr = new int[n2];

        // Copy data to temporary arrays
        for (int i = 0; i < n1; i++) {
            leftArr[i] = arr[left + i]; // elements from left to mid
        }
        for (int i = 0; i < n2; i++) {
            rightArr[i] = arr[mid + 1 + i]; // elements from mid+1 to right
        }

        // Merge the temporary arrays back into the original array

        int i = 0; // Initial index of leftArr
        int j = 0; // Initial index of rightArr
        int k = left; // Initial index of merged subarray in arr

        // Compare elements from leftArr and rightArr, and copy the smaller one
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

        // Copy remaining elements from leftArr if any
        while (i < n1) {
            arr[k] = leftArr[i];
            i++;
            k++;
        }

        // Copy remaining elements from rightArr if any
        while (j < n2) {
            arr[k] = rightArr[j];
            j++;
            k++;
        }
    }
}
