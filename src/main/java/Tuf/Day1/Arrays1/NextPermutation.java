package Tuf.Day1.Arrays1;

import java.util.Arrays;

public class NextPermutation {
    // ✅ Leetcode 31 — Next Permutation
    // Time complexity: O(n)
    // Space complexity: O(1)
    // Goal: Rearrange numbers to form the next lexicographically greater permutation.
    // If not possible (array is descending), rearrange to smallest permutation (ascending).

    public static void main(String[] args) {
        int[] nums = {1, 5, 8, 4, 7, 6, 5, 3, 1};  // Example input

        System.out.println("Original: " + Arrays.toString(nums));
        NextPermutation sol = new NextPermutation();
        sol.nextPermutation(nums);  // Modify array to its next permutation
        System.out.println("Next permutation: " + Arrays.toString(nums));
    }

    public void nextPermutation(int[] nums) {
        int pivot = -1;

        // 🔹 Step 1: Find the "pivot" — the first element from right which breaks descending order
        // e.g. in [1,5,8,4,7,6,5,3,1], pivot = 4 (at index 3)
        int i = nums.length - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }
        pivot = i; // store pivot index (can be -1 if array is descending like [3,2,1])

        // 🔹 Step 2: If pivot exists, find the next larger element to the right
        if (i >= 0) {
            int j = nums.length - 1;
            // Find the smallest number greater than nums[pivot] from the end
            while (nums[j] <= nums[i]) {
                j--;
            }
            // Swap pivot and next greater element
            swap(nums, i, j);
        }

        // 🔹 Step 3: Reverse the sub-array after the pivot
        // This ensures the suffix is in the smallest lexicographic order
        reverse(nums, i + 1);
    }

    // 🔁 Swap two array elements
    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    // 🔄 Reverse the array from given index to end
    public void reverse(int[] nums, int i) {
        int left = i;
        int right = nums.length - 1;
        while (left < right) {
            swap(nums, left, right);
            left++;
            right--;
        }
    }
}