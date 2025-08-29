package Tuf.Day11.BinarySearch;

public class SearchInSortedArray {

    public static void main(String[] args) {
        // Example input
        int[] nums = new int[]{6, 7, 0, 1, 2, 4, 5};
        int target = 0;

        // Function call
        System.out.println("The Target element lies in index " + solveSorted(nums, target));
    }

    /**
     * Question:
     * Given a rotated sorted array and a target value,
     * return the index of the target if it exists, otherwise return -1.
     *
     * Example:
     * Input: nums = [6,7,0,1,2,4,5], target = 0
     * Output: 2
     */
    public static int solveSorted(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;

            // If mid element is target → return index
            if (nums[mid] == target) {
                return mid;
            }

            // Case 1: Left half is sorted
            if (nums[left] <= nums[mid]) {
                // Check if target lies in this sorted left half
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1; // Search in left half
                } else {
                    left = mid + 1;  // Otherwise, search right half
                }
            }
            // Case 2: Right half is sorted
            else {
                // Check if target lies in this sorted right half
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;  // Search in right half
                } else {
                    right = mid - 1; // Otherwise, search left half
                }
            }
        }
        // Target not found
        return -1;
    }
}