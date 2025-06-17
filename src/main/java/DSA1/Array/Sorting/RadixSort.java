package DSA1.Array.Sorting;

public class RadixSort {
    public static void main(String[] args) {
        // Input array to be sorted
        int[] arr = {170, 45, 75, 90, 802, 24, 2, 66};

        // Step 1: Find the maximum number to determine number of digits
        int max = getMax(arr); // O(n)

        // Step 2: Perform counting sort for every digit (unit, tens, hundreds, etc.)
        // Time Complexity for this loop: O(d * (n + k)) ≈ O(d * n), where
        // d = number of digits in the maximum number
        // n = number of elements in the array
        // k = base (radix), here it's 10 (digits 0–9)
        for (int exp = 1; max / exp > 0; exp *= 10) {
            radixSort(arr, exp); // Perform counting sort by digit
        }

        // Output the sorted array
        System.out.print("The sorted array is: ");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // Performs Counting Sort based on the digit at exp place
    // Time Complexity: O(n + k), Space Complexity: O(n + k)
    public static void radixSort(int[] arr, int exp) {
        int[] count = new int[10]; // Count array for digits 0 to 9 → O(k) space where k = 10
        int[] output = new int[arr.length]; // Output array to build sorted result → O(n) space

        // Step 1: Count occurrences of each digit in current place
        // Time: O(n)
        for (int i = 0; i < arr.length; i++) {
            int digit = (arr[i] / exp) % 10;
            count[digit]++;
        }

        // Step 2: Transform count to prefix sum array
        // Time: O(k), where k = 10
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        // Step 3: Build the output array using stable sort logic (iterate from right to left)
        // Time: O(n)
        for (int i = arr.length - 1; i >= 0; i--) {
            int digit = (arr[i] / exp) % 10;
            output[count[digit] - 1] = arr[i];
            count[digit]--;
        }

        // Step 4: Copy the sorted digits back to the original array
        // Time: O(n)
        for (int i = 0; i < arr.length; i++) {
            arr[i] = output[i];
        }
    }

    // Utility to get maximum value from the array
    // Time Complexity: O(n)
    public static int getMax(int[] arr) {
        int max = arr[0];
        for (int num : arr) {
            max = Math.max(max, num);
        }
        return max;
    }
}
