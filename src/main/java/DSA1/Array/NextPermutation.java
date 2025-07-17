package DSA1.Array;

import java.util.Arrays;

public class NextPermutation {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};  // Example input

        System.out.println("Original: " + Arrays.toString(nums));
        NextPermutation sol = new NextPermutation();
        sol.nextPermutation(nums);  // Modify array to its next permutation
        System.out.println("Next permutation: " + Arrays.toString(nums));
    }

    public void nextPermutation(int[] nums) {
        int pivot = -1;

        // Step 1: Move from right to left, find the first index 'i' where nums[i] < nums[i + 1]
        // (This is the pivot — the first dip where the ascending order is broken from the end)
        int i = nums.length - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }
        pivot = i;

        // Step 2: If pivot is valid (i >= 0), find the next larger element to the right and swap
        if (i >= 0) {
            int j = nums.length - 1;
            // Find the first number greater than nums[i] from the end
            while (nums[j] <= nums[i]) {
                j--;
            }
            // Swap the pivot and next greater element
            swap(nums, i, j);
        }

        // Step 3: Reverse the sub-array to the right of the pivot (or whole array if no pivot)
        reverse(nums, i + 1);
    }

    // Swap two elements in the array
    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    // Reverse the array from index 'i' to the end
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
