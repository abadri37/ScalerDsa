package Tuf.Day2.Arrays2;

public class MergeTwoSortedArray {
    public static void main(String[] args) {
        int m = 5; // Number of valid elements in nums1
        int n = 3; // Number of elements in nums2

        // nums1 has enough space (length = m + n) to hold the merged result
        int[] nums1 = {1, 3, 5, 7, 9, 0, 0, 0};
        int[] nums2 = {2, 4, 6};

        // Start merging from the end of both arrays
        int i = m - 1;          // Pointer to the last valid element in nums1
        int j = n - 1;          // Pointer to the last element in nums2
        int k = m + n - 1;      // Pointer to the last position in nums1

        // Merge nums1 and nums2 in reverse order to avoid overwriting
        while (i >= 0 && j >= 0) {
            if (nums1[i] > nums2[j]) {
                nums1[k] = nums1[i]; // Place the larger element at the end
                i--;
            } else {
                nums1[k] = nums2[j];
                j--;
            }
            k--;
        }

        // Copy remaining elements from nums2 (if any)
        // (No need to copy from nums1 as they are already in place)
        while (j >= 0) {
            nums1[k] = nums2[j];
            j--;
            k--;
        }

        // Print the merged sorted array
        System.out.print("The Sorted Array is: ");
        for (int num : nums1) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
