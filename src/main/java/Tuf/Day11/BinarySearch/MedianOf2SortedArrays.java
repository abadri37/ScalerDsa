package Tuf.Day11.BinarySearch;

public class MedianOf2SortedArrays {
    public static void main(String[] args) {
        int[] nums1 = new int[]{1, 3, 4};
        int[] nums2 = new int[]{2};
        System.out.println("The median is " + solve(nums1, nums2));
    }

    public static double solve(int[] nums1, int[] nums2) {
        // Ensure nums1 is the smaller array for binary search
        if (nums1.length > nums2.length) {
            return solve(nums2, nums1);
        }

        int m = nums1.length;
        int n = nums2.length;
        int total = m + n;
        int half = (total + 1) / 2; // left half count

        int low = 0, high = m;

        while (low <= high) {
            // Partition nums1 at index i
            int i = (low + high) / 2;
            // Partition nums2 at index j so that left half has "half" elements
            int j = half - i;

            // Edge cases: handle out-of-bound by using ±∞
            int Aleft  = (i == 0) ? Integer.MIN_VALUE : nums1[i - 1];
            int Aright = (i == m) ? Integer.MAX_VALUE : nums1[i];
            int Bleft  = (j == 0) ? Integer.MIN_VALUE : nums2[j - 1];
            int Bright = (j == n) ? Integer.MAX_VALUE : nums2[j];

            // ✅ Correct partition found
            if (Aleft <= Bright && Bleft <= Aright) {
                if (total % 2 == 0) {
                    // Even length → median = average of middle two
                    return (Math.max(Aleft, Bleft) + Math.min(Aright, Bright)) / 2.0;
                } else {
                    // Odd length → median = max of left half
                    return Math.max(Aleft, Bleft);
                }
            }
            // If Aleft is too big, shrink search space left
            else if (Aleft > Bright) {
                high = i - 1;
            }
            // Else move right
            else {
                low = i + 1;
            }
        }

        // Code should never reach here if input arrays are sorted
        throw new IllegalArgumentException("Input arrays are not sorted correctly.");
    }
}