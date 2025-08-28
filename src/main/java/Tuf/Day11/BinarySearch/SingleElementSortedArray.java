package Tuf.Day11.BinarySearch;

public class SingleElementSortedArray {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 1, 2, 3, 3, 4, 4, 8, 8};
        System.out.println("The single element in the given sorted array is " + solveSingleElement(nums));
    }

    /**
     * Intuition:
     * In a sorted array where every element appears twice except one:
     *  - Normally, pairs appear at (even, odd) indices: nums[0]==nums[1], nums[2]==nums[3], etc.
     *  - The moment a single element is introduced, this "pairing alignment" breaks.
     *      Example: [1,1,2,3,3,4,4,8,8]
     *               index 0-1 → 1 matches
     *               index 2   → single (2) → from here on, pairs shift.
     *
     * We use binary search:
     *  - If mid (adjusted to even) is properly paired with mid+1 → single element is to the RIGHT.
     *  - Otherwise → single element is to the LEFT (or at mid).
     *
     * Complexity:
     *  - Time: O(log n) → halving the search space
     *  - Space: O(1)   → constant extra memory
     */
    public static int solveSingleElement(int[] nums) {
        int low = 0;
        int high = nums.length - 1;

        // Binary search until low meets high
        while (low < high) {
            int mid = (low + high) / 2;

            // Ensure mid is even (because pairs start at even indices in a valid array)
            if (mid % 2 == 1) {
                mid--;
            }

            // Case 1: Pair is correct → move right
            // Example: nums[mid]==nums[mid+1] → single element must lie after mid+1
            if (nums[mid] == nums[mid + 1]) {
                low = mid + 2;
            }
            // Case 2: Pair is broken → single element is at mid or before
            else {
                high = mid;
            }
        }

        // When low == high, we've found the single element
        return nums[low];
    }
}