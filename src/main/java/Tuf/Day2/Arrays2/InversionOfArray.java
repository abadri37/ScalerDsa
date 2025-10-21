package Tuf.Day2.Arrays2;

import java.util.ArrayList;
import java.util.List;

/**
 * 🔹 Problem Statement:
 * ---------------------
 * Given an array of integers, count the total number of *inversions* present in the array.
 *
 * An inversion is a pair of indices (i, j) such that:
 *      i < j  and  nums[i] > nums[j]
 *
 * Example:
 * --------
 * Input:  nums = [2, 3, 7, 1, 3, 5]
 * Output: 5
 *
 * Explanation:
 * The 5 inversions are:
 *      (2,1), (3,1), (7,1), (7,3), (7,5)
 *
 * 🔹 Intuition:
 * -------------
 * - If the array were sorted, there would be *no* inversions.
 * - So, while sorting the array (using Merge Sort), we can count how many pairs
 *   are "out of order" (nums[i] > nums[j]) as we merge two sorted halves.
 *
 * 🔹 Why Merge Sort?
 * ------------------
 * - A brute force approach (checking every pair) takes O(n²).
 * - Merge Sort allows us to count inversions efficiently in O(n log n),
 *   because both halves are sorted during merging.
 */

public class InversionOfArray {
    public static void main(String[] args) {
        int[] nums = new int[]{2, 3, 7, 1, 3, 5};

        // The mergeSort method returns the total number of inversions in the array
        System.out.println("The total Inversions that exist is " + mergeSort(nums, 0, nums.length - 1));
    }

    /**
     * 🔹 mergeSort():
     * ---------------
     * Works like standard Merge Sort, but with an additional step:
     * it counts the number of inversions during the merging process.
     *
     * @param nums The input array
     * @param low  Starting index of subarray
     * @param high Ending index of subarray
     * @return Total inversions in nums[low..high]
     */
    public static int mergeSort(int[] nums, int low, int high) {
        // Base case: If there's only one element, it's already sorted — no inversions.
        if (low >= high) {
            return 0;
        }

        int count = 0;
        int mid = (low + high) / 2;

        // Step 1: Count inversions in the left half
        count += mergeSort(nums, low, mid);

        // Step 2: Count inversions in the right half
        count += mergeSort(nums, mid + 1, high);

        // Step 3: Count cross inversions — pairs where left element > right element
        count += countPairs(nums, low, mid, high);

        // Step 4: Merge the two sorted halves into one sorted segment
        merge(nums, low, mid, high);

        return count;
    }

    /**
     * 🔹 countPairs():
     * ----------------
     * Counts the number of *cross inversions* (i.e., where one element lies in the left half
     * and another in the right half, and the left element is greater).
     *
     * Both halves are already sorted, so we can count inversions efficiently.
     *
     * @param nums Array containing the subarrays [low..mid] and [mid+1..high]
     * @return Number of cross inversions between left and right halves
     */
    public static int countPairs(int[] nums, int low, int mid, int high) {
        int j = mid + 1; // Pointer for right half
        int count = 0;

        // For each element in the left half, find how many elements in the right half are smaller
        for (int i = low; i <= mid; i++) {

            // As long as nums[i] > nums[j], we found inversions.
            // Because both halves are sorted, if nums[i] > nums[j],
            // then nums[i] > all elements up to j (since right half is sorted).
            while (j <= high && nums[i] > nums[j]) {
                j++;
            }

            // The number of such smaller elements = (j - (mid + 1))
            count += (j - (mid + 1));
        }

        return count;
    }

    /**
     * 🔹 merge():
     * ------------
     * Standard merge step of Merge Sort.
     * It merges two sorted halves into one sorted list.
     * This ensures that each recursive call works with sorted subarrays.
     */
    public static void merge(int[] nums, int low, int mid, int high) {
        List<Integer> list = new ArrayList<>();
        int left = low;
        int right = mid + 1;

        // Step 1: Merge elements from both halves in sorted order
        while (left <= mid && right <= high) {
            if (nums[left] <= nums[right]) {
                list.add(nums[left]);
                left++;
            } else {
                list.add(nums[right]);
                right++;
            }
        }

        // Step 2: Append remaining elements of left half, if any
        while (left <= mid) {
            list.add(nums[left]);
            left++;
        }

        // Step 3: Append remaining elements of right half, if any
        while (right <= high) {
            list.add(nums[right]);
            right++;
        }

        // Step 4: Copy merged sorted elements back into original array
        // NOTE: 'list' starts at index 0, but we are copying into nums[low..high],
        // so we use (i - low) as the index offset.
        for (int i = low; i <= high; i++) {
            nums[i] = list.get(i - low);
        }
    }
}

/*
---------------------------------------------------
🔹 Example Dry Run: nums = [2, 3, 7, 1, 3, 5]
---------------------------------------------------

Divide step:
------------
Left  = [2, 3, 7]
Right = [1, 3, 5]

Count inversions inside left  → 0
Count inversions inside right → 0
Now count cross inversions between them.

Cross comparison:
-----------------
Left[i] = 2 → only (2,1) → +1
Left[i] = 3 → only (3,1) → +1
Left[i] = 7 → (7,1), (7,3), (7,5) → +3

✅ Total inversions = 5

Merged sorted array = [1, 2, 3, 3, 5, 7]
---------------------------------------------------
🔹 Final Output:
The total Inversions that exist is 5
---------------------------------------------------
*/