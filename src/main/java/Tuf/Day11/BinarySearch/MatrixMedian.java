package Tuf.Day11.BinarySearch;

/**
 * Problem: Find the median in a row-wise sorted matrix.
 *
 * Time Complexity:
 *   - O(log(max - min) * R * logC)
 *     where R = number of rows, C = number of columns
 *     - log(max - min): Binary search on value range
 *     - R * logC: For each mid, count elements ≤ mid using binary search in each row
 *
 * Space Complexity:
 *   - O(1) (only variables, no extra DS used)
 */
public class MatrixMedian {
    public static void main(String[] args) {

        // Expected Median = 27
        int[][] matrix = {
                {10, 20, 30},
                {15, 25, 35},
                {27, 29, 37}
        };
        System.out.println(solveMedian(matrix));

        int[][] matrix2 = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };
        // Expected Median = 8 (or average of 8 & 9 if even-sized median is handled)
        System.out.println(solveMedian(matrix2));
    }

    /**
     * Finds the median in a row-wise sorted matrix
     */
    public static int solveMedian(int[][] nums) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        // Find the global min and max from the matrix
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums[0].length; j++) {
                min = Math.min(min, nums[i][j]);
                max = Math.max(max, nums[i][j]);
            }
        }

        int n = nums.length;  // number of rows
        int m = nums[0].length; // number of columns
        int countTarget;

        // Median position (depends on total elements count)
        if ((n * m) % 2 == 0) {
            countTarget = (n * m) / 2;
        } else {
            countTarget = ((n * m) / 2) + 1;
        }

        // Binary search on value range [min, max]
        while (min <= max) {
            int mid = (min + max) / 2;
            int countLessThanMid = 0;

            // Count elements <= mid across all rows
            for (int i = 0; i < nums.length; i++) {
                countLessThanMid += countSmallerElements(nums[i], mid);
            }

            // Adjust search range
            if (countLessThanMid >= countTarget) {
                max = mid - 1; // search left half
            } else {
                min = mid + 1; // search right half
            }
        }
        return min; // 'min' will hold the median
    }

    /**
     * Counts elements <= target in a sorted row using binary search
     */
    public static int countSmallerElements(int[] row, int target) {
        int low = 0;
        int high = row.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (row[mid] <= target) {
                low = mid + 1; // move right
            } else {
                high = mid - 1; // move left
            }
        }
        return low; // number of elements <= target
    }
}