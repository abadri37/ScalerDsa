package Tuf.Day2.Arrays2;

/**
 * 🔹 Problem: Merge Two Sorted Arrays (Leetcode #88)
 * ---------------------------------------------------
 * You are given two sorted integer arrays:
 *   nums1[] → of size (m + n), where the first m elements are valid,
 *   nums2[] → of size n.
 *
 * Both arrays are sorted in non-decreasing order.
 *
 * Your task is to merge nums2 into nums1 as one sorted array.
 *
 * ⚙️ The merge should be done IN-PLACE (i.e., modify nums1 directly).
 *
 * ---------------------------------------------------
 * Example:
 * --------
 * Input:
 *   nums1 = [1,3,5,7,9,0,0,0], m = 5
 *   nums2 = [2,4,6], n = 3
 *
 * Output:
 *   nums1 = [1,2,3,4,5,6,7,9]
 *
 * ---------------------------------------------------
 * 🔹 Approach (Two-Pointer Technique from the End):
 * ---------------------------------------------------
 * ✅ Step 1: Start from the END of both arrays (since nums1 has trailing zeros).
 * ✅ Step 2: Compare the last elements of nums1 & nums2.
 * ✅ Step 3: Place the larger one at the END of nums1.
 * ✅ Step 4: Move pointers backward.
 * ✅ Step 5: If elements remain in nums2, copy them to nums1.
 *
 * 🔹 Time Complexity:  O(m + n)
 * 🔹 Space Complexity: O(1)  (In-place)
 */

public class MergeTwoSortedArray {
    public static void main(String[] args) {
        int m = 5; // Number of valid elements in nums1
        int n = 3; // Number of elements in nums2

        // nums1 has extra space (size = m + n) to store merged result
        int[] nums1 = {1, 3, 5, 7, 9, 0, 0, 0};
        int[] nums2 = {2, 4, 6};

        // 🔹 Step 1: Initialize pointers
        int i = m - 1;          // Points to the last valid element in nums1
        int j = n - 1;          // Points to the last element in nums2
        int k = m + n - 1;      // Points to the last index in nums1 (destination)

        /**
         * 🔹 Step 2: Merge both arrays in reverse order
         * Why from the end? → To avoid overwriting elements in nums1.
         * We always place the larger value at the end (nums1[k]).
         */
        while (i >= 0 && j >= 0) {
            // Compare current elements from both arrays
            if (nums1[i] > nums2[j]) {
                nums1[k] = nums1[i];  // Place the larger one at the end
                i--;                   // Move nums1 pointer left
            } else {
                nums1[k] = nums2[j];  // Place the larger one from nums2
                j--;                   // Move nums2 pointer left
            }
            k--; // Move the destination pointer left
        }

        /**
         * 🔹 Step 3: Copy remaining elements of nums2 (if any)
         * If nums2 still has elements, copy them to nums1.
         * (We don’t need to copy nums1’s leftovers—they’re already in place.)
         */
        while (j >= 0) {
            nums1[k] = nums2[j];
            j--;
            k--;
        }

        // 🔹 Step 4: Print the merged sorted array
        System.out.print("The Sorted Array is: ");
        for (int num : nums1) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}

/*
---------------------------------------------------------
🔹 DRY RUN EXAMPLE
---------------------------------------------------------
nums1 = [1,3,5,7,9,0,0,0]
nums2 = [2,4,6]
m = 5, n = 3

Initialize:
 i = 4 → nums1[i] = 9
 j = 2 → nums2[j] = 6
 k = 7 → last index of nums1

---------------------------------------------------------
Step 1: Compare nums1[i]=9, nums2[j]=6
 → 9 > 6 → nums1[k] = 9
 → nums1 = [1,3,5,7,9,0,0,9]
 i=3, j=2, k=6

Step 2: Compare nums1[i]=7, nums2[j]=6
 → 7 > 6 → nums1[k] = 7
 → nums1 = [1,3,5,7,9,0,7,9]
 i=2, j=2, k=5

Step 3: Compare nums1[i]=5, nums2[j]=6
 → 5 < 6 → nums1[k] = 6
 → nums1 = [1,3,5,7,9,6,7,9]
 i=2, j=1, k=4

Step 4: Compare nums1[i]=5, nums2[j]=4
 → 5 > 4 → nums1[k] = 5
 → nums1 = [1,3,5,7,5,6,7,9]
 i=1, j=1, k=3

Step 5: Compare nums1[i]=3, nums2[j]=4
 → 3 < 4 → nums1[k] = 4
 → nums1 = [1,3,5,4,5,6,7,9]
 i=1, j=0, k=2

Step 6: Compare nums1[i]=3, nums2[j]=2
 → 3 > 2 → nums1[k] = 3
 → nums1 = [1,3,3,4,5,6,7,9]
 i=0, j=0, k=1

Step 7: Compare nums1[i]=1, nums2[j]=2
 → 1 < 2 → nums1[k] = 2
 → nums1 = [1,2,3,4,5,6,7,9]
 i=0, j=-1, k=0

✅ nums2 is exhausted → done merging.

---------------------------------------------------------
✅ Final nums1 = [1, 2, 3, 4, 5, 6, 7, 9]
---------------------------------------------------------
🔹 Complexity:
   Time  → O(m + n)
   Space → O(1)
---------------------------------------------------------
*/
